/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.core;

import java.rmi.Remote;

/**
 * Operações ofeceridas pelo cliente e que serão chamadas pelo servidor de
 * notícias
 *
 * @author 0145022
 */
public interface Client extends Remote {

    /**
     * Recebe a lista de notícias especificada
     *
     * @param news
     */
    public void retrieveNews(News news);

}
