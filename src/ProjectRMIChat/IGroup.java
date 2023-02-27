/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectRMIChat;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author user
 */
public interface IGroup extends Remote {

  //  public void sendToAll(String msg,String ClientID) throws RemoteException;

//    public void addClient(String id) throws RemoteException;

//    public void removeClient(String id) throws RemoteException;

//    public String gettAllClients() throws RemoteException;
   
     public void notifier(String msg,String ClientID) throws RemoteException ;

}
