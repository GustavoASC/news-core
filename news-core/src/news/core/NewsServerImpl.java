/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.core;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

/**
 * Servidor de notícias
 */
public class NewsServerImpl implements NewsServer {

    /* Número máximo de tentativas para enviar a notícia ao usuário */
    private static final int MAX_SENDING_ATTEMPTS = 5;
    /* Tópicos existentes nas notícias */
    private final List<Topic> topics;
    /* Lista de usuários registrados no sistema */
    private final List<User> registeredUsers;
    /* Map associando os usuários logados e seus respectivos IPs */
    private final Map<User, String> loggedUsers;

    /**
     * Cria o servidor de notícias
     */
    public NewsServerImpl() {
        this(new LinkedList<>(), new LinkedList<>());
    }

    /**
     * Cria o servidor de notícias
     *
     * @param topics tópicos existentes nas notícias
     * @param registeredUsers Lista de usuários registrados no sistema
     */
    public NewsServerImpl(List<Topic> topics, List<User> registeredUsers) {
        this.topics = topics;
        this.registeredUsers = registeredUsers;
        this.loggedUsers = new HashMap<>();
    }

    /**
     * Inicia o gerenciamento de backup
     *
     * @param backup instância do servidor de backup
     */
    public void startBackupManagement(BackupServer backup) {
        Thread thread = new Thread(new BackupManager(backup));
        thread.start();
    }

    @Override
    public void addTopic(Topic topic) throws RemoteException {
        topics.add(topic);
    }

    @Override
    public void addNews(News news, Topic topic) throws RemoteException {
        addNews(news, topic, this::sendNewsToUser);
    }

    /**
     * Envia via RMI a notícia para o usuário especificado
     *
     * @param news notícia que será enviada
     * @param user usuário que receberá a notícia
     */
    private void sendNewsToUser(News news, User user) {
        NewsConfigs configs = new NewsConfigs();
        String ip = loggedUsers.get(user);
        for (int i = 0; i < MAX_SENDING_ATTEMPTS; i++) {
            try {
                Registry registry = LocateRegistry.getRegistry(ip, configs.getUserServerPort());
                UserServer service = (UserServer) registry.lookup(configs.getUserServerService());
                service.retrieveNews(news);
                Thread.sleep(5000);
                // CASSEL: se atingiu 5 tentativas deve deslogar o usuário
                break;
            } catch (RemoteException | NotBoundException | InterruptedException ex) {
                // CASSEL: deve exibir a stack mesmo se não atingiu as N retentativas?
                ex.printStackTrace();
            }
        }
    }

    /**
     * Envia a notícia ao usuário utilizando o dispatcher especificado
     *
     * @param news notícia que será enviada
     * @param topic
     * @param dispatcher
     */
    protected void addNews(News news, Topic topic, NewsDispatcher dispatcher) {
        if (topics.contains(topic)) {
            topic.addNews(news);
            loggedUsers.keySet().parallelStream()
                    .filter(user -> user.isSubscribed(topic))
                    .forEach(user -> dispatcher.sendNewsToUser(news, user));
        }
    }

    @Override
    public List<Topic> retrieveAvailableTopics() throws RemoteException {
        return topics;
    }

    /**
     * Retorna a lista de notícias publicada pelo nome de usuário especificado
     *
     * @param username usuário para filtro
     * @return lista de notícias
     * @throws RemoteException se ocorrer algum erro durante a comunicação RMI
     */
    public List<News> retrievePublishedNews(String username) throws RemoteException {
        return retrievePublishedNews(new User(username));
    }

    @Override
    public List<News> retrievePublishedNews(User user) throws RemoteException {
        List<News> publishedNews = new LinkedList<>();
        topics.stream().forEach(
                t -> publishedNews.addAll(t.getNews().stream()
                        .filter(news -> news.getPublisher().equals(user))
                        .collect(Collectors.toList())
                ));
        return publishedNews;
    }

    @Override
    public List<News> retrieveNews(Topic topic, Date initialDate, Date finalDate) throws RemoteException {
        List<News> topicNews = topic.getNews();
        return topicNews.stream()
                .filter(news -> !news.getPublicationDate().before(initialDate))
                .filter(news -> !news.getPublicationDate().after(finalDate))
                .collect(Collectors.toList());
    }

    @Override
    public News retrieveLastNews(Topic topic) throws RemoteException {
        List<News> topicNews = topic.getNews();
        try{
            return topicNews.stream()
                    .sorted(new NewsDateSorter())
                    .findFirst()
                    .get();
        } catch(Exception e) {
            return null;
        }
    }

    @Override
    public void subscribe(User user, Topic topic) throws RemoteException {
        if (registeredUsers.contains(user) && topics.contains(topic)) {
            user.subscribe(topic);
        }
    }

    @Override
    public void addUser(User user) throws RemoteException {
        registeredUsers.add(user);
    }

    @Override
    public void addLoggedUser(User user, String ip) throws RemoteException {
        if (!registeredUsers.contains(user)) {
            return;
        }
        loggedUsers.put(user, ip);
    }

    @Override
    public User retUser(String userName, char[] userPassword) throws RemoteException {
        for (int i = 0; i < registeredUsers.size(); i++) {
            if (registeredUsers.get(i).getUsername().equals(userName)) {
                if(Arrays.equals(userPassword, registeredUsers.get(i).getPassword())){
                    return registeredUsers.get(i);
                }else{
                    JOptionPane.showMessageDialog(null,"Senha inválido!");
                    return null;
                }
            }
        }
        JOptionPane.showMessageDialog(null,"Usuario invalido!");
        return null;
    }

    /**
     * Interface para efetuar o despacho da notícia para o destino
     */
    protected interface NewsDispatcher {

        /**
         * Envia via RMI a notícia para o usuário especificado
         *
         * @param news notícia que será enviada
         * @param user usuário que receberá a notícia
         */
        public void sendNewsToUser(News news, User user);

    }

    /**
     * Classe para ordenar as notícias por ordem crescente de data
     */
    private class NewsDateSorter implements Comparator<News> {

        @Override
        public int compare(News first, News second) {
            Date firstDate = first.getPublicationDate();
            Date secondDate = second.getPublicationDate();
            if (firstDate.after(secondDate)) {
                return -1;
            }
            if (firstDate.before(secondDate)) {
                return 1;
            }
            return 0;
        }

    }

    /**
     * Runnable para gerenciar o backup automático do servidor
     */
    private class BackupManager implements Runnable {

        /* Servidor de bakcup */
        private final BackupServer backupServer;

        /**
         * Cria runnable para gerenciar o backup
         *
         * @param backupServer servidor de bakcup
         */
        public BackupManager(BackupServer backupServer) {
            this.backupServer = backupServer;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(10000);
                    BackupData data = new BackupData(registeredUsers, topics);
                    backupServer.createBackup(data);
                }
            } catch (InterruptedException | RemoteException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public List<Topic> getTopics() {
        return topics;
    }

}
