/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.core;

import java.util.Objects;

/**
 * Classe para armazenar o endereçamento do usuário
 *
 * @author Gustavo
 */
public class UserAddress {

    /* IP do usuário */
    private final String ip;
    /* Porta do usuário */
    private final int port;

    /**
     * Cria instância para armazenar o endereçamento do usuário
     *
     * @param ip
     * @param port
     */
    public UserAddress(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    /**
     * Retorna o IP do usuário
     * 
     * @return String
     */
    public String getIp() {
        return ip;
    }

    /**
     * Retorna a porta do usuário
     * 
     * @return int
     */
    public int getPort() {
        return port;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.ip);
        hash = 53 * hash + this.port;
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
        final UserAddress other = (UserAddress) obj;
        if (this.port != other.port) {
            return false;
        }
        if (!Objects.equals(this.ip, other.ip)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserAddress{" + "ip=" + ip + ", port=" + port + '}';
    }

}
