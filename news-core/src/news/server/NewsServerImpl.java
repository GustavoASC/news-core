/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.server;

import news.client.UserServer;
import news.backup.BackupServer;
import news.core.BackupData;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import news.core.News;
import news.core.Setup;
import news.core.Topic;
import news.core.User;
import news.core.UserAddress;

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
    private final Map<User, UserAddress> loggedUsers;

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
    public void addTopic(String username, Topic topic) throws RemoteException, Exception {
        userIsLogged(username);
        topics.add(topic);
    }

    @Override
    public void addNews(String username, News news, Topic topic) throws RemoteException, Exception {
        userIsLogged(username);
        addNews(news, topic, this::sendNewsToUser);
    }

    /**
     * Envia via RMI a notícia para o usuário especificado
     *
     * @param news notícia que será enviada
     * @param user usuário que receberá a notícia
     */
    private void sendNewsToUser(String topic, News news, User user) {
        Setup configs = new Setup();
        UserAddress address = loggedUsers.get(user);
        for (int i = 0; i < MAX_SENDING_ATTEMPTS; i++) {
            try {
                Registry registry = LocateRegistry.getRegistry(address.getIp(), address.getPort());
                UserServer service = (UserServer) registry.lookup(configs.getUserServerService());
                service.retrieveNews(topic, news);
                break;
            } catch (RemoteException | NotBoundException ex) {
                // Se atingiu o limite de tentativas
                if (i == MAX_SENDING_ATTEMPTS - 1) {
                    loggedUsers.remove(user);
                }
                ex.printStackTrace();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
        news.setPublicationDate(Calendar.getInstance().getTime());
        for (Topic t:topics){
            if (t.getName().equals(topic.getName()))
                t.addNews(news);
        }
        loggedUsers.keySet().parallelStream()
                   .filter(user -> user.isSubscribed(topic))
                   .forEach(user -> dispatcher.sendNewsToUser(topic.getName(), news, user));
    }

    /**
     * Retorna a lista de notícias publicada pelo nome de usuário especificado
     *
     * @param username usuário para filtro
     * @return lista de notícias
     * @throws RemoteException se ocorrer algum erro durante a comunicação RMI
     */
    public List<News> retrievePublishedNews(String username) throws RemoteException {
        return retrievePublishedNews(this.getUserByName(username));
    }

    @Override
    public List<News> retrievePublishedNews(User user) throws RemoteException {
        List<News> publishedNews = new LinkedList<>();
        topics.stream().forEach(
                t -> publishedNews.addAll(t.getNews().stream()
                        .filter(news -> news.getPublisher().equals(user.getUsername()))
                        .collect(Collectors.toList())
                ));
        return publishedNews;
    }

    @Override
    public List<News> retrieveNews(String topicName, Date initialDate, Date finalDate) throws RemoteException {
        Topic topic = null;
        for (Topic t:topics){
            if (t.getName().equals(topicName))
                topic = t;
        }
        // Se não encontrou o tópico
        if (topic == null)
            return null;
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
    public void subscribe(String username, Topic topic) throws RemoteException, Exception{
        userIsLogged(username);
        // Cria objeto para o novo usuário
        User newUser = this.getUserByName(username);
        if (newUser != null) {
            newUser.subscribe(topic);
            // Atualiza o registro
            registeredUsers.set(registeredUsers.indexOf(this.getUserByName(username)), newUser);
        }
    }

    @Override
    public void addUser(String name, char[] senha, boolean finalidade) throws RemoteException {
        // Cria o objeto de usuário
        User newUser = new User(name, senha, finalidade);
        // Adiciona o usuário como registrado
        registeredUsers.add(newUser);
    }

    @Override
    public void addLoggedUser(String username, String ip, int port) throws RemoteException {
        User loggedUser = getUserByName(username);
        if (!registeredUsers.contains(loggedUser)) {
            return;
        }
        loggedUsers.put(loggedUser, new UserAddress(ip, port));
    }

    @Override
    public User validateLoginUser(String username, char[] userPassword) throws RemoteException {
        User user = getUserByName(username);
        // Se não encontrou o usuário
        if (user == null){
            JOptionPane.showMessageDialog(null,"Usuário inválido!");
            return null;
        }
        // Se a senha não confere
        if (!Arrays.equals(user.getPassword(), userPassword)){
            JOptionPane.showMessageDialog(null,"Senha inválida!");
            return null;
        }
        // Retorna o usuário váldo
        return user;
    }

    @Override
    public User getUserByName(String username) throws RemoteException {
        for (User u:registeredUsers){
            if (u.getUsername().equalsIgnoreCase(username))
                return u;
        }
        return null;
    }

    /**
     * Interface para efetuar o despacho da notícia para o destino
     */
    protected interface NewsDispatcher {

        /**
         * Envia via RMI a notícia para o usuário especificado
         *
         * @param topic nome do tópico
         * @param news notícia que será enviada
         * @param user usuário que receberá a notícia
         */
        public void sendNewsToUser(String topic, News news, User user);

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

     /**
     * Classe para retornar os tópicos existentes
     */
    @Override
    public List<Topic> getTopics() {
        return topics;
    }

     /**
     * Classe para buscar o tópico pelo nome
     */
    @Override
    public Topic getTopicByName(String name) throws RemoteException{
        for (Topic topic: topics){
            if (topic.getName().equalsIgnoreCase(name))
                return topic;
        }
        return null;
    }

     /**
     * Classe para verificar se o usuário está logado
     */
    @Override
    public void userIsLogged(String username) throws RemoteException, Exception {
        if (!loggedUsers.containsKey(this.getUserByName(username)))
           throw new Exception ("Falha de sistema! Usuário não está logado.");
    }

     /**
     * Classe para verificar se o usuário está logado
     */
    @Override
    public void removeLoggedUser(String username) throws RemoteException{
        loggedUsers.remove(this.getUserByName(username));
    }


}
