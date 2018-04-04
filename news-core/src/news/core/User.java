/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.core;

import java.util.LinkedList;
import java.util.List;

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

}
