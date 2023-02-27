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
public class ClientImp extends UnicastRemoteObject implements IClient {
     JTextArea area;
     
     
    public ClientImp(JTextArea area) throws RemoteException
    {
        this.area=area;
    }

    @Override
    public void notifier(String msg) throws RemoteException {
        area.append("\n"+msg);
    }

   
}
    
