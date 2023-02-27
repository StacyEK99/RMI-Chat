/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectRMIChat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import javax.swing.JTextArea;

/**
 *
 * @author user
 */
public final class GroupImp extends UnicastRemoteObject implements IGroup {

    JTextArea area;
    HashMap<String, String> members = new HashMap();
    IServer server;

    
     public GroupImp(JTextArea area) throws RemoteException {
        this.area = area;
    }

    public void notifier(String msg,String ClientID) throws RemoteException {
        area.append("\n"+ClientID+ ">>" + msg );
    }


//    @Override
//    public void sendToAll(String msg,String ClientID) throws RemoteException {
//        
//        area.append("\n"+ClientID+ ">>" + msg );
//    }
//
//    /**
//     *
//     * @param id
//     * @throws RemoteException
//     */
//    @Override
//    public void addClient(String id) throws RemoteException {
//        members.put(id, idGroup);
//         System.out.println(members);
//    }
//
//    @Override
//    public void removeClient(String id) throws RemoteException {
//        members.remove(id);
//        System.out.println(members);
//    }
//
//    @Override
//    public String gettAllClients() throws RemoteException {
//        return members.keySet().toString();
//    }
}
