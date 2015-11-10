/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal;

import de.uniba.wiai.lspi.chord.data.URL;
import de.uniba.wiai.lspi.chord.service.ServiceException;
import de.uniba.wiai.lspi.chord.service.impl.ChordImpl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;


/**
 *
 * @author kinst
 */
public class Nuvem {

    public static ArrayList<No> nos;
    private static int port = 8080;
    private static URL bootstrapURL = null;
    public static No nodoaux = null;
    public static ChordImpl chord = null; 
    private static int nNos = 0;

   
    
    public static void newUser(String _user, String _password)
    {
       /* File arquivo = null;
        String dir = null;
        StringKey myKEY = null;*/
        User user = null;
        user = new User();
            user.setName(_user);
            user.setPassword(_password);
            Nuvem.addPassword(user, nodoaux);
    }
    
    public static boolean createNetwork()
    {
        de.uniba.wiai.lspi.chord.service.PropertiesLoader.loadPropertyFile();
        Nuvem.chord = new de.uniba.wiai.lspi.chord.service.impl.ChordImpl();
       nos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            nos.add(new No(port, nNos));
            if (port == 8080) {
                bootstrapURL = nos.get(0).getAdress();
                setNetwork(nos.get(0).getNo(), nos.get(0).getAdress());
            } else {
                setNodo(nos.get(i).getNo(), nos.get(i).getAdress(), bootstrapURL);
            }
            System.out.println("Criado No:" + (i) + " Porta:" + nos.get(i).getPorta() + " Id: " + nos.get(i).getID());
            Nuvem.port++;
            Nuvem.nNos++;
        }
        return true;
    }

    public static void setNetwork(ChordImpl begin, URL url) {
        try {
            begin.create(url);
        } catch (ServiceException e) {
            throw new RuntimeException("Could not create DHT !", e);
        }
    }

    public static void setNodo(ChordImpl begin, URL url, URL boot) {
        try {
            begin.join(url, boot);
        } catch (ServiceException e) {
            throw new RuntimeException(" Could not join DHT !", e);
        }
    }
    
    public static void addPassword(User user, No no)
    {
        user.breakPassword(user.encryptPassword(user.getPassword()));
        no.getNo().insert(new StringKey(user.getName()), user.getPassPt1());
        no.getNo().insert(new StringKey(user.getName()), user.getPassPt2());
        no.getNo().insert(new StringKey(user.getName()), user.getPassPt3());
        no.getNo().insert(new StringKey(user.getName()), user.getPassPt4());
        no.getNo().insert(new StringKey(user.getName()), user.getPassPt5());
        no.getNo().insert(new StringKey(user.getName()), user.getPassPt6());
        no.getNo().insert(new StringKey(user.getName()), user.getPassPt7());
        no.getNo().insert(new StringKey(user.getName()), user.getPassPt8());
        no.getNo().insert(new StringKey(user.getPassword()), user.getPassPt8());
    }
    
    @SuppressWarnings("resource")
	public static void createArchive(File source, File destination) throws IOException {
        if (destination.exists()) {
            destination.delete();
        }

        FileChannel sourceChannel = null;
        FileChannel destinationChannel = null;

        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destinationChannel = new FileOutputStream(destination).getChannel();
            sourceChannel.transferTo(0, sourceChannel.size(),
                    destinationChannel);
        } finally {
            if (sourceChannel != null && sourceChannel.isOpen()) {
                sourceChannel.close();
            }
            if (destinationChannel != null && destinationChannel.isOpen()) {
                destinationChannel.close();
            }
        }
    }

    //
    /**
     * @param _name
     * @return
     * Create a hash key from a name of user
     */
    public StringKey getKey(String _name) {
        return new StringKey(_name);
    }
    
    
    /**
     * @param _user
     * @param _password
     * @param no
     * @return
     * Method called by company that need validate a user in your system
     */
    public static String validateUser(String _user, String _password ,Nodo no)
    {
        String codePass = null;
        String aux = null;
        String aux2 = null;
        User user = new User();
        StringKey myKey = new StringKey(_user);
        Set<Serializable> vals = null;
        vals = no.getChord().retrieve(myKey);
        Iterator<Serializable> it = vals.iterator();
        while (it.hasNext()) {
            aux = (String) it.next();
            aux2 = aux.substring(aux.length() - 1, aux.length());
            switch (aux2) {
                case "1":
                    user.setPassPt1(aux.substring(0, aux.length() - 1));
                    continue;
                case "2":
                    user.setPassPt2(aux.substring(0, aux.length() - 1));
                    continue;
                case "3":
                    user.setPassPt3(aux.substring(0, aux.length() - 1));
                    continue;
                case "4":
                    user.setPassPt4(aux.substring(0, aux.length() - 1));
                    continue;
                case "5":
                    user.setPassPt5(aux.substring(0, aux.length() - 1));
                    continue;
                case "6":
                    user.setPassPt6(aux.substring(0, aux.length() - 1));
                    continue;
                case "7":
                    user.setPassPt7(aux.substring(0, aux.length() - 1));
                    continue;
                case "8":
                    user.setPassPt8(aux.substring(0, aux.length() - 1));
                    continue;
            }
        }
        if (user.unitePassword().equals(user.encryptPassword(_password))) {
            myKey = new StringKey(_password);
            vals = no.getChord().retrieve(myKey);
            it = vals.iterator();
            while (it.hasNext()) {
                codePass = (String) it.next();
                break;
            }
        }
        return codePass;
    }
}
