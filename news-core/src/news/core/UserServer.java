/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.core;

import java.rmi.Remote;
import java.rmi.RemoteException;

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
     * @param news
     * @throws RemoteException se ocorrer algum erro durante a comunicação RMI
     */
    public void retrieveNews(News news) throws RemoteException;

}
