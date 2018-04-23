/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.core;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Usuário do sistema de notícias
 */
public class User {

    /* Nome do usuário */
    private String username;
    /* Senha */
    private char[] password;
    /* Permite escrever notícias */
    private boolean publisher;
    /* Tópicos em que este usuário está inscrito */
    private final List<Topic> subscriptions;
    /* IP de origem do usuário para conexão RMI */
    private String ip;
    /* Porta para conexão RMI com o usuário */
    private int port;

    /**
     * Cria usuário do sistema de notícias
     */
    public User() {
        this("");
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public boolean isPublisher() {
        return publisher;
    }

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
        return subscriptions.contains(topic);
    }

    /**
     * Inscreve o usuário no tópico especificado
     *
     * @param topic
     */
    public void subscribe(Topic topic) {
        subscriptions.add(topic);
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.username);
        hash = 61 * hash + Arrays.hashCode(this.password);
        hash = 61 * hash + (this.publisher ? 1 : 0);
        hash = 61 * hash + Objects.hashCode(this.subscriptions);
        hash = 61 * hash + Objects.hashCode(this.ip);
        hash = 61 * hash + this.port;
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
        if (this.publisher != other.publisher) {
            return false;
        }
        if (this.port != other.port) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.ip, other.ip)) {
            return false;
        }
        if (!Arrays.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.subscriptions, other.subscriptions)) {
            return false;
        }
        return true;
    }

}
