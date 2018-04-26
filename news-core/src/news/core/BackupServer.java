/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.core;

import java.rmi.Remote;

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
     */
    public void createBackup(BackupData data);

    /**
     * Restaura o backup de todos os usuários, tópicos e informações a eles
     * relacionadas BackupData
     *
     * @return
     */
    public BackupData restoreBackup();

}
