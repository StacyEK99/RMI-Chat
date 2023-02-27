/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectRMIChat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author user
 */
public class ServerImp extends UnicastRemoteObject implements IServer {

    HashMap<String, IClient> listConnected = new HashMap();
    HashMap<String, String> offLineMsg = new HashMap();
    HashMap<String, String> groups = new HashMap(); // grp id + admin id
    HashMap<String, IGroup> allGroups = new HashMap(); //grp id + Group 
    HashMap<String, String> members = new HashMap(); //grp id + list of members id

    public ServerImp() throws RemoteException {

    }

    @Override
    public void subscribe(String id) throws RemoteException {
        if (listConnected.keySet().contains(id)) {
            throw new RemoteException("This id is already subscribed. Please enter a new id");
        }
        listConnected.put(id, null);
        System.out.println(listConnected);
    }

    @Override
    public void createGroup(String idGroup, String idAdmin, IGroup grp) throws RemoteException {
        String grps = getGroups();
        if (grps.contains(idGroup)) {
            throw new RemoteException("A group already exists with the same id. Please enter a new id");
        }
        groups.put(idGroup, idAdmin);
        allGroups.put(idGroup, grp);
        members.put(idGroup, idAdmin);
        System.out.println(groups);
        System.out.println(allGroups);
    }

    @Override
    public void connect(String id, IClient client) throws RemoteException {
        listConnected.put(id, client);
        System.out.println(listConnected);
        System.out.println("connect " + id);
        String msg = offLineMsg.get(id);
        if (msg != null) {
            client.notifier(msg);
            offLineMsg.put(id, null);
        }
    }

    @Override
    public void disconnect(String id) throws RemoteException {
        listConnected.remove(id);
        System.out.println(listConnected);
    }

    @Override
    public void sendToClient(String msg, String id) throws RemoteException {
        msg = ">>" + msg;
        IClient cl = listConnected.get(id);
        if (cl != null) {
            // client online
            cl.notifier(msg);
            System.out.println("send " + id);
        } else {
            //client offline
            String oldmsg = offLineMsg.get(id);
            if (oldmsg == null) {
                oldmsg = "";
            }
            oldmsg += "'" + msg;
            offLineMsg.put(id, oldmsg);
            System.out.println("put " + id);
        }
    }

    @Override
    public void sendToGroup(String msg, String idGroup, String senderID) throws RemoteException {

            msg = " to " + idGroup + " " + msg;
            String membs = members.get(idGroup);
            String[] cli = membs.split(",");
            for (int i = 0; i < cli.length; i++) {
                if (!cli[i].equals(senderID)) {
                    sendToClient(msg, cli[i].trim());
                }
            }
        }

    @Override
    public String getAllClients() throws RemoteException {
        return listConnected.keySet().toString();
    }

    @Override
    public void broadcast(String msg, String senderID) throws RemoteException {
        msg = "Broadcast from " + senderID + " " + msg;
        Iterator iterator = listConnected.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry me2 = (Map.Entry) iterator.next();
            String id = (String) me2.getKey();
            if (!id.equals(senderID)) {
                sendToClient(msg, id);
            }
        }
        Iterator iterator2 = offLineMsg.entrySet().iterator();
        while (iterator2.hasNext()) {
            Map.Entry me3 = (Map.Entry) iterator2.next();
            String id = (String) me3.getKey();

            String oldmsg = offLineMsg.get(id);
            if (!id.equals(senderID)) {
                if (oldmsg == null) {
                    oldmsg = "";
                }
                oldmsg += "'" + msg;
                offLineMsg.put(id, oldmsg);
            }
        }
    }

    @Override
    public String getAdmin(String idGroup) throws RemoteException {
        if (!groups.keySet().contains(idGroup)) {
            throw new RemoteException("This group does not exist");
        }
        return groups.get(idGroup).trim();
    }

    @Override
    public String getGroups() throws RemoteException {
        return allGroups.keySet().toString();
    }

    /**
     *
     * @param id
     * @throws RemoteException
     */
    @Override
    public void addClient(String id, String idGroup) throws RemoteException {

        if (!listConnected.containsKey(id)) {
            throw new RemoteException("Client does not exist");
        } else {

            String cli = members.get(idGroup);
            if (!cli.contains(id)) {
                cli += "," + id;
                members.put(idGroup, cli);
                System.out.println(members);
            } else {
                throw new RemoteException("Client already in group");
            }
        }
    }

    @Override
    public void removeClient(String id, String idGroup) throws RemoteException {
        String cli = members.get(idGroup);
        String c = "";
        int count = 0;
        String[] membs = cli.split(",");
        for (int i = 0; i < membs.length; i++) {
            if (!membs[i].equals(id)) {
                if (i == membs.length - 1) {
                    count++;
                    c += membs[i];
                } else {
                    count++;
                    c += membs[i] + ",";
                }
            }
        }
        if (count == membs.length) {
            throw new RemoteException("Client not in group");
        }
        members.put(idGroup, c);
        System.out.println(members);
    }

    @Override
    public String getGrpMembers(String idGroup) throws RemoteException {
        return members.get(idGroup).trim();
    }

    @Override
    public void deleteGroup(String idGroup) throws RemoteException {
        members.remove(idGroup, members.get(idGroup));
        groups.remove(idGroup, groups.get(idGroup));
        allGroups.remove(idGroup, allGroups.get(idGroup));
    }
}
