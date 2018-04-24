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
        News news = new News();
        news.setTitle("Título de teste");
        news.setPublisher(new User("Gustavo Cassel"));
        news.setPublicationDate(new Date(2018 + 1900, 0, 13));
        news.setContent("Conteúdo da notícia");
        //
        Topic topic = new Topic();
        topic.addNews(news);
        //
        NewsServer server = new NewsServer();
        server.addTopic(topic);
        //
        News lastNews = server.retrieveLastNews(topic);
        assertEquals(news, lastNews);
    }

    @Test
    public void testAddNews() {
        System.out.println("addNews");
        News news = null;
        Topic topic = null;
        NewsServer instance = new NewsServer();
        instance.addNews(news, topic);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testRetrieveAvailableTopics() {
        System.out.println("retrieveAvailableTopics");
        NewsServer instance = new NewsServer();
        List<Topic> expResult = null;
        List<Topic> result = instance.retrieveAvailableTopics();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testRetrievePublishedNews() {
        System.out.println("retrievePublishedNews");
        User user = null;
        NewsServer instance = new NewsServer();
        List<News> expResult = null;
        List<News> result = instance.retrievePublishedNews(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testRetrieveNews() {
        System.out.println("retrieveNews");
        Topic topic = null;
        Date initialDate = null;
        Date finalDate = null;
        NewsServer instance = new NewsServer();
        List<News> expResult = null;
        List<News> result = instance.retrieveNews(topic, initialDate, finalDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testRetrieveLastNews() {
        System.out.println("retrieveLastNews");
        Topic topic = null;
        NewsServer instance = new NewsServer();
        News expResult = null;
        News result = instance.retrieveLastNews(topic);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
