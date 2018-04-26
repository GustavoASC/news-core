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
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servidor de notícias
 */
public class NewsServer implements Server {

    /* Tópicos existentes nas notícias */
    private final List<Topic> topics;
    /* Lista de usuários registrados no sistema */
    private final List<User> registeredUsers;

    /**
     * Cria o servidor de notícias
     */
    public NewsServer() {
        topics = new LinkedList<>();
        registeredUsers = new LinkedList<>();
    }

    @Override
    public void addTopic(Topic topic) {
        topics.add(topic);
    }

    @Override
    public void addNews(News news, Topic topic) {
        addNews(news, topic, this::sendNewsToUser);
    }

    /**
     * Envia via RMI a notícia para o usuário especificado
     *
     * @param news notícia que será enviada
     * @param user usuário que receberá a notícia
     */
    private void sendNewsToUser(News news, User user) {
        try {
            Registry registry = LocateRegistry.getRegistry(user.getIp(), user.getPort());
            Client service = (Client) registry.lookup("news");
            service.retrieveNews(news);
        } catch (RemoteException | NotBoundException ex) {
            ex.printStackTrace();
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
            registeredUsers.parallelStream()
                    .filter(user -> user.isSubscribed(topic))
                    .forEach(user -> dispatcher.sendNewsToUser(news, user));
        }
    }

    @Override
    public List<Topic> retrieveAvailableTopics() {
        return topics;
    }

    /**
     * Retorna a lista de notícias publicada pelo nome de usuário especificado
     *
     * @param username usuário para filtro
     * @return lista de notícias
     */
    public List<News> retrievePublishedNews(String username) {
        return retrievePublishedNews(new User(username));
    }

    @Override
    public List<News> retrievePublishedNews(User user) {
        List<News> publishedNews = new LinkedList<>();
        topics.stream().forEach(
                t -> publishedNews.addAll(t.getNews().stream()
                        .filter(news -> news.getPublisher().equals(user))
                        .collect(Collectors.toList())
                ));
        return publishedNews;
    }

    @Override
    public List<News> retrieveNews(Topic topic, Date initialDate, Date finalDate) {
        List<News> topicNews = topic.getNews();
        return topicNews.stream()
                .filter(news -> !news.getPublicationDate().before(initialDate))
                .filter(news -> !news.getPublicationDate().after(finalDate))
                .collect(Collectors.toList());
    }

    @Override
    public News retrieveLastNews(Topic topic) {
        List<News> topicNews = topic.getNews();
        return topicNews.stream()
                .sorted(new NewsDateSorter())
                .findFirst()
                .get();
    }

    @Override
    public void subscribe(User user, Topic topic) {
        if (registeredUsers.contains(user) && topics.contains(topic)) {
            user.subscribe(topic);
        }
    }

    @Override
    public void addUser(User user) {
        registeredUsers.add(user);
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

}
