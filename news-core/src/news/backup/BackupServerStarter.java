/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.backup;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import news.core.Setup;

/**
 * Classe para iniciar e levantar o servidor de backup
 *
 * @author Gustavo
 */
public class BackupServerStarter {

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
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Inicia o servidor de backup
     *
     * @throws RemoteException se não conseguir levantar o servidor
     */
    private void startBackupServer() throws IOException {
        Setup configs = new Setup();
        //
        this.serverImpl = new BackupServerImpl();
        Registry registry = LocateRegistry.createRegistry(configs.getBackupServerPort());
        BackupServer server = (BackupServer) UnicastRemoteObject.exportObject(serverImpl, 0);
        registry.rebind(configs.getBackupServerService(), server);
        System.out.println("Servidor de backup no ar!");
    }

}
