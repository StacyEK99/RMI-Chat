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
public interface IServer extends Remote {

    public void subscribe(String id) throws RemoteException;

    public void createGroup(String idGroup, String idAdmin, IGroup grp) throws RemoteException;

    public void connect(String id, IClient client) throws RemoteException;

    public void disconnect(String id) throws RemoteException;

    public void sendToClient(String msg, String id) throws RemoteException;

    public void sendToGroup(String msg, String idGroup, String senderID) throws RemoteException;

    public String getAllClients() throws RemoteException;

    public void broadcast(String msg,String senderID) throws RemoteException;

    public String getAdmin(String idGroup) throws RemoteException;

    public String getGroups() throws RemoteException;

   public void addClient(String id,String idGroup) throws RemoteException;

    public void removeClient(String id,String idGroup) throws RemoteException;

    public String getGrpMembers(String idGroup) throws RemoteException;
    
    public void deleteGroup(String idGroup) throws RemoteException;

}
