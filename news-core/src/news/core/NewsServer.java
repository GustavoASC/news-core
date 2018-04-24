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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Servidor de notícias
 */
public class NewsServer implements Server {

    /* Notícias publicadas pelos escritores */
    private final List<News> publishedNews;
    /* Tópicos existentes nas notícias */
    private final List<Topic> topics;
    /* Lista de usuários registrados no sistema */
    private final List<User> registeredUsers;

    /**
     * Cria o servidor de notícias
     */
    public NewsServer() {
        publishedNews = new LinkedList<>();
        topics = new LinkedList<>();
        registeredUsers = new LinkedList<>();
    }

    @Override
    public void addTopic(Topic topic) {
        topics.add(topic);
    }

    @Override
    public void addNews(News news, Topic topic) {
        if (topics.contains(topic)) {
            publishedNews.add(news);
            topic.addNews(news);
            registeredUsers.parallelStream()
                    .filter(user -> user.isSubscribed(topic))
                    .forEach(user -> sendNewsToUser(news, user));
        }
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
            Logger.getLogger(Starter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Topic> retrieveAvailableTopics() {
        return topics;
    }

    @Override
    public List<News> retrievePublishedNews(User user) {
        return publishedNews.stream()
                .filter(news -> news.getPublisher().equals(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<News> retrieveNews(Topic topic, Date initialDate, Date finalDate) {
        List<News> associated = topic.getAssociatedNews();
        return associated.stream()
                .filter(news -> !news.getPublicationDate().before(initialDate))
                .filter(news -> !news.getPublicationDate().after(finalDate))
                .collect(Collectors.toList());
    }

    @Override
    public News retrieveLastNews(Topic topic) {
        List<News> associated = topic.getAssociatedNews();
        return associated.stream()
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
     * Classe para ordenar as notícias por ordem decrescente de data
     */
    private class NewsDateSorter implements Comparator<News> {

        @Override
        public int compare(News first, News second) {
            Date firstDate = first.getPublicationDate();
            Date secondDate = second.getPublicationDate();
            if (firstDate.after(secondDate)) {
                return 1;
            }
            if (firstDate.before(secondDate)) {
                return -1;
            }
            return 0;
        }

    }

}
