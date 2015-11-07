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
import java.net.MalformedURLException;
import java.nio.channels.FileChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author kinst
 */
public class Nodo {

    private ChordImpl chord;

    public Nodo() {
        this.chord = null;
    }
//    public static void main(String[] args) {
//        de.uniba.wiai.lspi.chord.service.PropertiesLoader.
//                loadPropertyFile();
//        String protocol = URL.KNOWN_PROTOCOLS.get(URL.SOCKET_PROTOCOL);
//        URL localURL = null;
//        try {
//            localURL = new URL(protocol + "://localhost:8181/");
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//        URL bootstrapURL = null;
//        try {
//            bootstrapURL = new URL(protocol + "://localhost:8080/");
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//        Chord chord = new de.uniba.wiai.lspi.chord.service.impl.ChordImpl();
//        try {
//            chord.join(localURL, bootstrapURL);
//        } catch (ServiceException e) {
//            throw new RuntimeException(" Could not join DHT !", e);
//        }
//        String nome,endereco;
//        nome = JOptionPane.showInputDialog(null, "Entre com o nome do arquivo");
//        endereco = JOptionPane.showInputDialog(null, "Entre com o local do arquivo");
//        File origen = new File(endereco, nome);
//        StringKey myKey = new StringKey(nome);
//        try {
//            chord.insert(myKey, origen);
//        } catch (ServiceException ex) {
//            Logger.getLogger(Nodo.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            endereco = JOptionPane.showInputDialog(null, "Entre com o novo local do arquivo");
//            File destination = new File(endereco + origen.getName());
//            destination.createNewFile();
//            Set<Serializable> vals = chord.retrieve(myKey);
//            Iterator<Serializable> it = vals.iterator();
//            File t = null;
//            while (it.hasNext()) {
//                t = (File) it.next();
//            }
//            copyFile(t, destination);
//           // chord.leave();
//        } catch (ServiceException ex) {
//            Logger.getLogger(Nodo.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(Nodo.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public void conectaNodo(int port) {
        String protocol = URL.KNOWN_PROTOCOLS.get(URL.SOCKET_PROTOCOL);
        URL localURL = null;
        try {
            localURL = new URL(protocol + "://localhost:808" + port + "/");
            System.out.println(localURL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        this.chord = new de.uniba.wiai.lspi.chord.service.impl.ChordImpl();
        URL bootstrapURL = null;
        try {
            bootstrapURL = new URL(protocol + "://localhost:8080/");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
       // Chord chord = new de.uniba.wiai.lspi.chord.service.impl.ChordImpl();
        try {
            chord.join(localURL, bootstrapURL);
        } catch (ServiceException e) {
            throw new RuntimeException(" Could not join DHT !", e);
        }
    }

    public void insereArquivo() {
        String nome, endereco;
        nome = JOptionPane.showInputDialog(null, "Entre com o nome do arquivo");
        endereco = JOptionPane.showInputDialog(null, "Entre com o local do arquivo");
        System.out.println(endereco + nome);
        File origen = new File(endereco, nome);
        System.out.println(origen);
        StringKey myKey = new StringKey( nome);
        System.out.println("la"+myKey);
        this.chord.insert(myKey, origen);
        System.out.println("enrs");
        JOptionPane.showMessageDialog(null, "ARQUIVO ADICIONADO");
    }

//    public static String validaAqui(String usuario, String senha )
//    {
//        String protocol = URL.KNOWN_PROTOCOLS.get(URL.SOCKET_PROTOCOL);
//        URL localURL = null;
//        try {
//            localURL = new URL(protocol + "://localhost:8184/");
//            System.out.println(localURL);
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//        URL bootstrapURL = null;
//        try {
//            bootstrapURL = new URL(protocol + "://localhost:8080/");
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("entro");
//        ChordImpl no=new ChordImpl();
//        System.out.println("entro");
//        try {
//            no.join(localURL, bootstrapURL);
//        } catch (ServiceException ex) {
//            Logger.getLogger(IdentidadeServerImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        String codigo = null;
//        String aux = null;
//        String aux2 = null;
//        Usuario user = new Usuario();
//        StringKey myKey = new StringKey(usuario);
//        Set<Serializable> vals = null;
//        vals = no.getNo().retrieve(myKey);
//        Iterator<Serializable> it = vals.iterator();
//        while (it.hasNext()) {
//            aux = (String) it.next();
//            aux2 = aux.substring(aux.length() - 1, aux.length());
//            switch (aux2) {
//                case "1":
//                    user.setSenhaPt1(aux.substring(0, aux.length() - 1));
//                    continue;
//                case "2":
//                    user.setSenhaPt2(aux.substring(0, aux.length() - 1));
//                    continue;
//                case "3":
//                    user.setSenhaPt3(aux.substring(0, aux.length() - 1));
//                    continue;
//                case "4":
//                    user.setSenhaPt4(aux.substring(0, aux.length() - 1));
//                    continue;
//                case "5":
//                    user.setSenhaPt5(aux.substring(0, aux.length() - 1));
//                    continue;
//                case "6":
//                    user.setSenhaPt6(aux.substring(0, aux.length() - 1));
//                    continue;
//                case "7":
//                    user.setSenhaPt7(aux.substring(0, aux.length() - 1));
//                    continue;
//                case "8":
//                    user.setSenhaPt8(aux.substring(0, aux.length() - 1));
//                    continue;
//            }
//        }
//        if (user.juntaSenha().equals(user.criptografaSenha(senha))) {
//            myKey = new StringKey(senha);
//            vals = no.getNo().retrieve(myKey);
//            it = vals.iterator();
//            while (it.hasNext()) {
//                codigo = (String) it.next();
//                break;
//            }
//        }
//        return codigo;
//    }
//    
    public void recuperaArquivo() {
        String nome, endereco;
        try {
            nome = JOptionPane.showInputDialog(null, "Entre com o nome do arquivo que deseja recuperar:");
            endereco = JOptionPane.showInputDialog(null, "Entre com o novo local do arquivo:");
            File destination = new File(endereco + nome);
            StringKey myKey = new StringKey(nome);
            destination.createNewFile();
            Set<Serializable> vals = chord.retrieve(myKey);
            Iterator<Serializable> it = vals.iterator();
            File t = null;
            while (it.hasNext()) {
                t = (File) it.next();
            }
            copyFile(t, destination);
        JOptionPane.showMessageDialog(null, "ARQUIVO RECUPERADO");
            // chord.leave();
        } catch (IOException ex) {
            Logger.getLogger(Nodo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("resource")
	public static void copyFile(File source, File destination) throws IOException {
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

    public ChordImpl getChord() {
        return chord;
    }

}
