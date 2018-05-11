/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;

/**
 * Implementação do servidor de backup
 *
 * @author 0145022
 */
public class BackupServerImpl implements BackupServer {

    /* Nome do arquivo de backup */
    private static final String BACKUP_FILE_NAME = "NewsServerBackup.ser";

    @Override
    public void createBackup(BackupData data) throws RemoteException {
        try (FileOutputStream fileOut = new FileOutputStream(BACKUP_FILE_NAME); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(data);
        } catch (IOException ex) {
            throw new RemoteException("", ex);
        }
    }

    @Override
    public BackupData restoreBackup() throws RemoteException {
        try (FileInputStream fis = new FileInputStream(BACKUP_FILE_NAME)) {
            try (ObjectInputStream in = new ObjectInputStream(fis)) {
                return (BackupData) in.readObject();
            }
        } catch (IOException | ClassNotFoundException ex) {
            return new BackupData();
        }
    }

}
