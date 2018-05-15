/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.core;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import news.core.Topic;
import news.core.User;

/**
 * Dados que são manipulados pelo servidor de backup
 *
 * @author 0145022
 */
public class BackupData implements Serializable {

    /* Usuários manipulados pelo servidor de backup */
    private final List<User> users;
    /* Tópicos manipulados pelo servidor de backup */
    private final List<Topic> topics;

    /**
     * Cria objeto vazio com dados que são manipulados pelo servidor de backup
     */
    public BackupData() {
        this(new LinkedList<>(), new LinkedList<>());
    }

    /**
     * Cria objeto com dados que são manipulados pelo servidor de backup
     *
     * @param users usuários
     * @param topics tópicos
     */
    public BackupData(List<User> users, List<Topic> topics) {
        this.users = users;
        this.topics = topics;
    }

    /**
     * Retorna usuários manipulados pelo servidor de backup
     *
     * @return usuários
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Retorna tópicos manipulados pelo servidor de backup
     *
     * @return tópicos
     */
    public List<Topic> getTopics() {
        return topics;
    }

    @Override
    public String toString() {
        return "BackupData{" + "users=" + users + ", topics=" + topics + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.users);
        hash = 67 * hash + Objects.hashCode(this.topics);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BackupData other = (BackupData) obj;
        if (!users.equals(users)) {
            return false;
        }
        if (!topics.equals(topics)) {
            return false;
        }
        return true;
    }

}
