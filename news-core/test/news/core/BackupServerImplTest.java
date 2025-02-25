/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.core;

import news.backup.BackupServerImpl;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 0145022
 */
public class BackupServerImplTest {

    /**
     * Test of createBackup method, of class BackupServerImpl.
     */
    @Test
    public void testCreateBackup() {
        User user1 = new User("Gustavo");
        User user2 = new User("Fernanda");
        User user3 = new User("Chen");
        user3.setPublisher(true);
        user3.setPassword("12345".toCharArray());
        //
        List<User> users = new LinkedList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        //
        Topic topic = new Topic("Tópico");
        topic.addNews(new News("Minha noticia", new Date(2018 + 1900, 0, 13), "Gustavo"));
        topic.addNews(new News("Outra noticia", new Date(2017 + 1900, 0, 13), "Fernanda"));
        topic.addNews(new News("Terceira noticia", new Date(2016 + 1900, 0, 13), "Chen"));
        //
        Topic secondTopic = new Topic("Tópico 2");
        secondTopic.addNews(new News("Minha noticia dois", new Date(2019 + 1900, 2, 5), "Gustavo Cassel"));
        secondTopic.addNews(new News("Outra noticia dois", new Date(2020 + 1900, 3, 6), "Fernanda X"));
        secondTopic.addNews(new News("Terceira noticia dois", new Date(2021 + 1900, 4, 7), "Chen Y"));
        //
        List<Topic> topics = new LinkedList<>();
        topics.add(topic);
        topics.add(secondTopic);
        //
        BackupServerImpl server = new BackupServerImpl();
        try {
            BackupData data = new BackupData(users, topics);
            server.createBackup(data);
            //
            BackupData restored = server.restoreBackup();
            assertEquals(data, restored);
        } catch (RemoteException ex) {
            fail();
        }
    }

}
