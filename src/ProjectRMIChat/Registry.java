/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectRMIChat;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Registry {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException {
         try {
            // TODO code application logic here
            int port = 3000;

            LocateRegistry.createRegistry(port);

            Scanner key = new Scanner(System.in);
            key.nextLine();
        } catch (RemoteException ex) {
            Logger.getLogger(Registry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
