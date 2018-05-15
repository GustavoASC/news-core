/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import news.core.News;

/**
 * Operações ofeceridas pelo cliente e que serão chamadas pelo servidor de
 * notícias
 *
 * @author 0145022
 */
public interface UserServer extends Remote {

    /**
     * Recebe a lista de notícias especificada
     *
     * @param topic
     * @param news
     * @throws RemoteException se ocorrer algum erro durante a comunicação RMI
     */
    public void retrieveNews(String topic, News news) throws RemoteException;

}
