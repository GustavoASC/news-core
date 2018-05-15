/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.backup;

import news.core.BackupData;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface com as operações oferecidas por um servidor de backup
 *
 * @author 0145022
 */
public interface BackupServer extends Remote {

    /**
     * Cria backup de todos os usuários, tópicos e informações a eles
     * relacionadas
     *
     * @param data dados a serem gravados
     * @throws RemoteException
     */
    public void createBackup(BackupData data) throws RemoteException;

    /**
     * Restaura o backup de todos os usuários, tópicos e informações a eles
     * relacionadas BackupData
     *
     * @return
     * @throws RemoteException
     */
    public BackupData restoreBackup() throws RemoteException;

}
