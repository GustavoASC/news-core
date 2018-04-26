/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.core;

import java.io.Serializable;
import java.util.List;

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

}
