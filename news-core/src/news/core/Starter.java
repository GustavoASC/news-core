/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.core;

import java.io.IOException;
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
        NewsConfigs configs = new NewsConfigs();
        //
        BackupServer backupServer = findBackupServer();
        BackupData backup = backupServer.restoreBackup();
        //
        newsServer = new NewsServerImpl(backup.getTopics(), backup.getUsers());
        newsServer.startBackupManagement(backupServer);
        //
        Registry registry = LocateRegistry.createRegistry(configs.getNewsServerPort());
        NewsServer server = (NewsServer) UnicastRemoteObject.exportObject(newsServer, 0);
        registry.rebind(configs.getNewsServerIp() + "/" + configs.getNewsServerService(), server);
        System.out.println("Servidor no ar!");
    }

    /**
     * Busca a instância do servidor de backup via RMI
     *
     * @return instância encontrada
     * @throws IOException se não conseguir levantar o servidor
     * @throws NotBoundException se não conseguir levantar o servidor
     */
    private BackupServer findBackupServer() throws IOException, NotBoundException {
        NewsConfigs configs = new NewsConfigs();
        Registry registry = LocateRegistry.getRegistry(configs.getBackupServerIp(), configs.getBackupServerPort());
        BackupServer backup = (BackupServer) registry.lookup(configs.getBackupServerIp() + "/" + configs.getBackupServerService());
        return backup;
    }

}
