/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.core;

import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testes do servidor de notícias
 */
public class NewsServerTest {

    @Test
    public void testAddTopic() {
        //
        News news = new News("Título de teste");
        news.setPublisher("Gustavo Cassel");
        news.setPublicationDate(new Date(2018 + 1900, 0, 13));
        news.setContent("Conteúdo da notícia");
        //
        Topic topic = new Topic();
        topic.addNews(news);
        //
        NewsServer server = new NewsServer();
        server.addTopic(topic);
        //
        News lastNews;
        lastNews = server.retrieveLastNews(topic);
        assertEquals(news, lastNews);
        //
        News second = new News("Título da segunda notícia");
        second.setPublisher("Gãs");
        second.setPublicationDate(new Date(2016 + 1900, 0, 13));
        second.setContent("Texto da segunda notícia");
        //
        topic.addNews(second);
        //
        lastNews = server.retrieveLastNews(topic);
        assertEquals(news, lastNews);
        //
        News third = new News("Título da terceira notícia");
        third.setPublisher("Gãs");
        third.setPublicationDate(new Date(2018 + 1900, 0, 20));
        third.setContent("Texto da terceira notícia");
        //
        topic.addNews(third);
        lastNews = server.retrieveLastNews(topic);
        assertEquals(third, lastNews);
    }

    @Test
    public void testRetrieveAvailableTopics() {
        //
        Topic topic = new Topic();
        topic.addNews(new News("Título de teste", new Date(2018 + 1900, 0, 13), "Gustavo Cassel"));
        //
        NewsServer server = new NewsServer();
        server.addTopic(topic);
        //
        List<Topic> availableTopics;
        //
        availableTopics = server.retrieveAvailableTopics();
        assertEquals(1, availableTopics.size());
        assertEquals(topic, availableTopics.get(0));
        //
        Topic secondTopic = new Topic();
        secondTopic.addNews(new News("Título da segunda notícia", new Date(2016 + 1900, 0, 13), "Gãs"));
        server.addTopic(secondTopic);
        //
        availableTopics = server.retrieveAvailableTopics();
        assertEquals(2, availableTopics.size());
        assertEquals(topic, availableTopics.get(0));
        assertEquals(secondTopic, availableTopics.get(1));
    }

    @Test
    public void testRetrievePublishedNews() {
        //
        Topic topic = new Topic();
        News first = new News("Título de teste", new Date(2018 + 1900, 0, 13), "Gustavo Cassel");
        News second = new News("Outra notícia a", new Date(2018 + 1900, 0, 13), "Gustavo Cassel");
        News fernanda = new News("Título de teste", new Date(2018 + 1900, 0, 13), "Fernanda");
        topic.addNews(first);
        topic.addNews(fernanda);
        topic.addNews(new News("Título de teste", new Date(2018 + 1900, 0, 13), "Jairo"));
        topic.addNews(second);
        topic.addNews(new News("Título de teste", new Date(2018 + 1900, 0, 13), "Edilse"));
        //
        NewsServer server = new NewsServer();
        server.addTopic(topic);
        //
        List<News> publishedNews;
        //
        publishedNews = server.retrievePublishedNews("Gustavo Cassel");
        assertEquals(2, publishedNews.size());
        assertEquals(first, publishedNews.get(0));
        assertEquals(second, publishedNews.get(1));
        //
        publishedNews = server.retrievePublishedNews("Fernanda");
        assertEquals(1, publishedNews.size());
        assertEquals(fernanda, publishedNews.get(0));
        //
        publishedNews = server.retrievePublishedNews("Inexistente");
        assertEquals(0, publishedNews.size());
    }

    @Test
    public void testRetrieveNews() {
        Topic topic = new Topic();
        //
        News first = new News("Título de teste", new Date(2018 + 1900, 0, 13), "Gustavo Cassel");
        News second = new News("Outra notícia a", new Date(2018 + 1900, 0, 15), "Gustavo Cassel");
        News third = new News("Terceira noticia", new Date(2018 + 1900, 0, 17), "Gustavo Cassel");
        //
        topic.addNews(first);
        topic.addNews(second);
        topic.addNews(third);
        //
        NewsServer server = new NewsServer();
        server.addTopic(topic);
        //
        List<News> filteredNews;
        filteredNews = server.retrieveNews(topic, new Date(2018 + 1900, 0, 12), new Date(2018 + 1900, 0, 16));
        assertEquals(2, filteredNews.size());
        assertEquals(first, filteredNews.get(0));
        assertEquals(second, filteredNews.get(1));
        //
        filteredNews = server.retrieveNews(topic, new Date(2018 + 1900, 0, 12), new Date(2018 + 1900, 0, 18));
        assertEquals(3, filteredNews.size());
        assertEquals(first, filteredNews.get(0));
        assertEquals(second, filteredNews.get(1));
        assertEquals(third, filteredNews.get(2));
        //
        filteredNews = server.retrieveNews(topic, new Date(2018 + 1900, 0, 13), new Date(2018 + 1900, 0, 13));
        assertEquals(1, filteredNews.size());
        assertEquals(first, filteredNews.get(0));
        //
        filteredNews = server.retrieveNews(topic, new Date(2018 + 1900, 0, 15), new Date(2018 + 1900, 0, 15));
        assertEquals(1, filteredNews.size());
        assertEquals(second, filteredNews.get(0));
        //
        filteredNews = server.retrieveNews(topic, new Date(2018 + 1900, 0, 16), new Date(2018 + 1900, 0, 16));
        assertEquals(0, filteredNews.size());
        //
        filteredNews = server.retrieveNews(topic, new Date(2018 + 1900, 0, 17), new Date(2018 + 1900, 0, 17));
        assertEquals(1, filteredNews.size());
        assertEquals(third, filteredNews.get(0));
    }

    @Test
    public void testSubscribe() {
        System.out.println("subscribe");
        User user = null;
        Topic topic = null;
        NewsServer instance = new NewsServer();
        instance.subscribe(user, topic);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testAddUser() {
        System.out.println("addUser");
        User user = null;
        NewsServer instance = new NewsServer();
        instance.addUser(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
