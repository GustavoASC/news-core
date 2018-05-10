/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Classe para carregar as configurações do projeto de Notícias
 * 
 * @author Gustavo
 */
public class NewsConfigs {
    
    /* Nome do arquivo de propriedades */
    private static final String PROPERTIES_NAME = "news-configs.properties";
    /* Porta default do usuário */
    private static final int DEFAULT_USER_PORT = 10997;
    /* Porta default do servidor de backup */
    private static final int DEFAULT_BACKUP_PORT = 10998;
    /* Porta default do servidor de notícias */
    private static final int DEFAULT_NEWS_PORT = 10999;
    /* Properties com as configurações do projeto */
    private static Properties props;
    
    
    /**
     * Retorna o IP do servidor de backup
     *
     * @return IP do servidor de backup
     * @throws IOException se não conseguir buscar o IP do servidor de backup
     */
    public String getBackupServerIp() throws IOException {
        return retrieveProperties().getProperty("backup.server.ip", "127.0.0.1");
    }
    
    /**
     * Retorna a porta do servidor de backup
     *
     * @return IP do servidor de backup
     * @throws IOException se não conseguir buscar o IP do servidor de backup
     */
    public int getBackupServerPort() throws IOException {
        String port = retrieveProperties().getProperty("backup.server.port", Integer.toString(DEFAULT_BACKUP_PORT));
        try {
            return Integer.parseInt(port);
        } catch (NumberFormatException ex) {
            return DEFAULT_BACKUP_PORT;
        }
    }
    
    /**
     * Retorna o nome do serviço do servidor de backup
     *
     * @return IP do servidor de backup
     * @throws IOException se não conseguir buscar o IP do servidor de backup
     */
    public String getBackupServerService() throws IOException {
        return retrieveProperties().getProperty("backup.server.service", "backup");
    }
    
    /**
     * Retorna o IP do servidor de notícias
     *
     * @return IP do servidor de backup
     * @throws IOException se não conseguir buscar o IP do servidor de backup
     */
    public String getNewsServerIp() throws IOException {
        return retrieveProperties().getProperty("news.server.ip", "127.0.0.1");
    }
    
    /**
     * Retorna a porta do servidor de notícias
     *
     * @return IP do servidor de backup
     * @throws IOException se não conseguir buscar o IP do servidor de backup
     */
    public int getNewsServerPort() throws IOException {
        String port = retrieveProperties().getProperty("news.server.port", Integer.toString(DEFAULT_NEWS_PORT));
        try {
            return Integer.parseInt(port);
        } catch (NumberFormatException ex) {
            return DEFAULT_NEWS_PORT;
        }
    }
    
    /**
     * Retorna o nome do serviço do servidor de notícias
     *
     * @return IP do servidor de backup
     * @throws IOException se não conseguir buscar o IP do servidor de backup
     */
    public String getNewsServerService() throws IOException {
        return retrieveProperties().getProperty("news.server.service", "news");
    }
    
    /**
     * Retorna a porta do usuário
     *
     * @return IP do servidor de backup
     * @throws IOException se não conseguir buscar o IP do servidor de backup
     */
    public int getUserServerPort() throws IOException {
        String port = retrieveProperties().getProperty("user.server.port", Integer.toString(DEFAULT_USER_PORT));
        try {
            return Integer.parseInt(port);
        } catch (NumberFormatException ex) {
            return DEFAULT_USER_PORT;
        }
    }
    
    /**
     * Retorna o nome do serviço do usuário
     *
     * @return IP do servidor de backup
     * @throws IOException se não conseguir buscar o IP do servidor de backup
     */
    public String getUserServerService() throws IOException {
        return retrieveProperties().getProperty("user.server.service", "user");
    }
    
    /**
     * Retorna objeto de propriedades carregando-o caso necessário
     * 
     * @return Properties
     * @throws IOException se não conseguir buscar o IP do servidor de backup
     */
    private Properties retrieveProperties() throws IOException {
        if (props == null ) {
            props = new Properties();
            if (new File(PROPERTIES_NAME).exists()) {
                props.load(new FileInputStream(PROPERTIES_NAME));
            }
        }
        return props;
    }
    
    
}
