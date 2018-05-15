/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.server;

import news.backup.BackupServer;
import news.core.BackupData;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import news.core.Setup;

/**
 * Classe responsável por iniciar o servidor de notícias e o servidor de backup
 *
 * @author 0145022
 */
public class NewsServerStarter {

    /* Instância do servidor de notícias */
    private NewsServerImpl newsServer;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            new NewsServerStarter().startNewsServer();
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException | NotBoundException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Inicia o servidor de notícias
     *
     * @param backup instância do servidor de backup
     * @throws IOException se não conseguir levantar o servidor
     * @throws NotBoundException se não conseguir encontrar o servidor de backup
     */
    private void startNewsServer() throws IOException, NotBoundException {
        //
        Setup configs = new Setup();
        //
        BackupServer backupServer = findBackupServer();
        if (backupServer != null) {
            BackupData backup = backupServer.restoreBackup();
            newsServer = new NewsServerImpl(backup.getTopics(), backup.getUsers());
            newsServer.startBackupManagement(backupServer);
        } else {
            newsServer = new NewsServerImpl();
        }
        //
        Registry registry = LocateRegistry.createRegistry(configs.getNewsServerPort());
        NewsServer server = (NewsServer) UnicastRemoteObject.exportObject(newsServer, 0);
        registry.rebind(configs.getNewsServerService(), server);
        System.out.println("Servidor no ar!");
    }

    /**
     * Busca a instância do servidor de backup via RMI
     *
     * @return instância encontrada
     */
    private BackupServer findBackupServer() {
        try {
            Setup configs = new Setup();
            Registry registry = LocateRegistry.getRegistry(configs.getBackupServerIp(), configs.getBackupServerPort());
            BackupServer backup = (BackupServer) registry.lookup(configs.getBackupServerService());
            return backup;
        } catch (IOException | NotBoundException ex) {
            return null;
        }
    }

}
