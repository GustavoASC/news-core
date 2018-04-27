/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.core;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        user1.setIp("192.168.1.1");
        user1.setPort(50);
        User user2 = new User("Fernanda");
        user2.setIp("192.168.1.2");
        user2.setPort(27);
        User user3 = new User("Chen");
        user3.setIp("192.168.1.3");
        user3.setPort(40);
        user3.setPublisher(true);
        user3.setPassword("12345".toCharArray());
        //
        List<User> users = new LinkedList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        //
        Topic topic = new Topic();
        topic.addNews(new News("Minha noticia", new Date(2018 + 1900, 0, 13), "Gustavo"));
        topic.addNews(new News("Outra noticia", new Date(2017 + 1900, 0, 13), "Fernanda"));
        topic.addNews(new News("Terceira noticia", new Date(2016 + 1900, 0, 13), "Chen"));
        //
        Topic secondTopic = new Topic();
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
