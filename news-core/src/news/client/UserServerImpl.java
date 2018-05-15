/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.client;

import java.rmi.RemoteException;
import javax.swing.SwingUtilities;
import news.core.News;

/**
 * Client que receberá notícias do servidor
 */
public class UserServerImpl implements UserServer {

    /* Tela principal com as notícias exibidas ao usuário */
    private final TelaPrincipal telaPrincipal;

    /**
     * Cria client que receberá notícias do servidor
     *
     * @param telaPrincipal
     */
    public UserServerImpl(TelaPrincipal telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
    }

    @Override
    public void retrieveNews(String topic, News news) throws RemoteException {
        SwingUtilities.invokeLater(() -> {
            System.out.println("Atualizando tabela de notícias...");
            telaPrincipal.updateNewsTable(topic, news);
        });
    }

}
