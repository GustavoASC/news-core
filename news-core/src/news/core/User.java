/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.core;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import javax.swing.JOptionPane;

/**
 * Usuário do sistema de notícias
 */
public class User implements Serializable {

    /* Nome do usuário */
    private String username;
    /* Senha */
    private char[] password;
    /* Tem permissão para escrever notícias */
    private boolean publisher;
    /* Tópicos em que este usuário está inscrito */
    private final List<Topic> subscriptions;

    /**
     * Cria usuário no sistema de notícias
     * 
     * @param username nome do usuário
     * @param password senha
     * @param publisher tem permissão para escrever notícias
     */
    public User(String username, char[] password, boolean publisher) {
        this.username = username;
        this.password = password;
        this.publisher = publisher;
        this.subscriptions = new LinkedList<>();
    }

    /**
     * Cria usuário com o nome especificado
     *
     * @param username nome do usuário
     */
    public User(String username) {
        this.username = username;
        this.subscriptions = new LinkedList<>();
    }

    /**
     * Retorna o nome do usuário
     *
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retorna a senha do usuário
     *
     * @return char[]
     */
    public char[] getPassword() {
        return password;
    }

    /**
     * Define a senha do usuário
     *
     * @param password
     */
    public void setPassword(char[] password) {
        this.password = password;
    }

    /**
     * Retorna {@code true} se tem permissão para escrever notícias
     *
     * @return boolean
     */
    public boolean isPublisher() {
        return publisher;
    }

    /**
     * Define se tem permissão para escrever notícias
     *
     * @param publisher
     */
    public void setPublisher(boolean publisher) {
        this.publisher = publisher;
    }

    /**
     * Retorna {@code true} se o usuário está inscrito no tópico especificado
     *
     * @param topic tópico
     * @return boolean
     */
    public boolean isSubscribed(Topic topic) {
        // Se o usuario não tem inscrições
        if (subscriptions == null)
            return false;
        // Busca o nome do tópico para inscrição
        for(Topic t: subscriptions){
            if (t.getName().equals(topic.getName()))
                return true;
        }
        // Retorna que não encontrou a inscrição
        return false;
    }

    /**
     * Inscreve o usuário no tópico especificado
     *
     * @param topic
     */
    public void subscribe(Topic topic) {
        subscriptions.add(topic);
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + Arrays.toString(password) + ", publisher=" + publisher + ", subscriptions=" + subscriptions + '}';
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.username);
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
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

}
