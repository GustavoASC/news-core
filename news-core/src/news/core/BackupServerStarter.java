/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.core;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Classe para iniciar e levantar o servidor de backup
 *
 * @author Gustavo
 */
public class BackupServerStarter {

    /* Porta usada pelo servidor de backup */
    public static final int BACKUP_SERVER_PORT = 10998;
    /* Nome usado pelo servidor de backup */
    public static final String BACKUP_SERVER_NAME = "backup";
    /* Instância do servidor de backup */
    private BackupServerImpl serverImpl;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            new BackupServerStarter().startBackupServer();
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Inicia o servidor de backup
     *
     * @throws RemoteException se não conseguir levantar o servidor
     */
    private void startBackupServer() throws RemoteException {
        this.serverImpl = new BackupServerImpl();
        Registry registry = LocateRegistry.createRegistry(BACKUP_SERVER_PORT);
        BackupServer server = (BackupServer) UnicastRemoteObject.exportObject(serverImpl, 0);
        registry.rebind("127.0.0.1/" + BACKUP_SERVER_NAME, server);
        System.out.println("Servidor de backup no ar!");
    }

}
