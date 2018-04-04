/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.core;

import java.util.List;

/**
 * Client que receberá notícias do servidor
 */
public class NewsClient implements Client {

    /* Usuário logado no sistema */
    private final User currentUser;

    /**
     * Cria client que receberá notícias do servidor
     *
     * @param currentUser
     */
    public NewsClient(User currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public void retrieveNews(News news) {
//        currentUser

    }

}
