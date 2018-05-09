/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.core;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Classe responsável por iniciar o servidor de notícias e o servidor de backup
 *
 * @author 0145022
 */
public class Starter {
    
    /** Porta usada pelo servidor de notícias */
    public static final int NEWS_SERVER_PORT = 10999;
    /** Nome usado pelo servidor de notícias */
    public static final String NEWS_SERVER_NAME = "service";
    /* Instância do servidor de notícias */
    private NewsServerImpl newsServer;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            new Starter().startNewsServer();
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (RemoteException | NotBoundException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Inicia o servidor de notícias
     *
     * @param backup instância do servidor de backup
     * @throws RemoteException se não conseguir levantar o servidor
     * @throws NotBoundException se não conseguir encontrar o servidor de backup
     */
    private void startNewsServer() throws RemoteException, NotBoundException {
        //
        BackupServer backupServer = findBackupServer();
        BackupData backup = backupServer.restoreBackup();
        //
        newsServer = new NewsServerImpl(backup.getTopics(), backup.getUsers());
        newsServer.startBackupManagement(backupServer);
        //
        Registry registry = LocateRegistry.createRegistry(NEWS_SERVER_PORT);
        NewsServer server = (NewsServer) UnicastRemoteObject.exportObject(newsServer, 0);
        registry.rebind("127.0.0.1/" + NEWS_SERVER_NAME, server);
        System.out.println("Servidor no ar!");
    }

    /**
     * Busca a instância do servidor de backup via RMI
     * 
     * @return instância encontrada
     * @throws RemoteException se não conseguir levantar o servidor
     */
    private BackupServer findBackupServer() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("127.0.0.1", BackupServerStarter.BACKUP_SERVER_PORT);
        BackupServer backup = (BackupServer) registry.lookup("127.0.0.1/" + BackupServerStarter.BACKUP_SERVER_NAME);
        return backup;
    }

}
