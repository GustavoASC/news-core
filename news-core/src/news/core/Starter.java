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
 *
 * @author 0145022
 */
public class Starter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException {
        // TODO code application logic here
        
        Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        NewsServer _srvImp = new NewsServer();
        Server _serv = (Server) UnicastRemoteObject.exportObject(_srvImp, 0);
        registry.rebind("127.0.0.1/service", _serv);
        System.out.println("Servidor no ar!");
        
        
    }

}
