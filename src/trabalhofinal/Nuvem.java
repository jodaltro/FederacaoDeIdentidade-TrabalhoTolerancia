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
    private static int porta = 8080;
    private static URL bootstrapURL = null;
    public static No nodoaux = null;
    public static ChordImpl chord = null; 
    private static int nNos = 0;

   
    
    public static void novoUsuario(String usuario, String senha)
    {
       /* File arquivo = null;
        String dir = null;
        StringKey myKEY = null;*/
        Usuario user = null;
        user = new Usuario();
            user.setNome(usuario);
            user.setSenha(senha);
            Nuvem.adicionaSenha(user, nodoaux);
    }
    
    public static boolean criaRede()
    {
        de.uniba.wiai.lspi.chord.service.PropertiesLoader.loadPropertyFile();
        Nuvem.chord = new de.uniba.wiai.lspi.chord.service.impl.ChordImpl();
       nos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            nos.add(new No(porta, nNos));
            if (porta == 8080) {
                bootstrapURL = nos.get(0).getAdress();
                setRede(nos.get(0).getNo(), nos.get(0).getAdress());
            } else {
                setNodo(nos.get(i).getNo(), nos.get(i).getAdress(), bootstrapURL);
            }
            System.out.println("Criado No:" + (i) + " Porta:" + nos.get(i).getPorta() + " Id: " + nos.get(i).getID());
            Nuvem.porta++;
            Nuvem.nNos++;
        }
        return true;
    }

    public static void setRede(ChordImpl begin, URL url) {
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
    
    public static void adicionaSenha(Usuario user, No no)
    {
        user.quebraSenha(user.criptografaSenha(user.getSenha()));
        no.getNo().insert(new StringKey(user.getNome()), user.getSenhaPt1());
        no.getNo().insert(new StringKey(user.getNome()), user.getSenhaPt2());
        no.getNo().insert(new StringKey(user.getNome()), user.getSenhaPt3());
        no.getNo().insert(new StringKey(user.getNome()), user.getSenhaPt4());
        no.getNo().insert(new StringKey(user.getNome()), user.getSenhaPt5());
        no.getNo().insert(new StringKey(user.getNome()), user.getSenhaPt6());
        no.getNo().insert(new StringKey(user.getNome()), user.getSenhaPt7());
        no.getNo().insert(new StringKey(user.getNome()), user.getSenhaPt8());
        no.getNo().insert(new StringKey(user.getSenha()), user.getSenhaPt8());
    }
    
    @SuppressWarnings("resource")
	public static void criaArquivo(File source, File destination) throws IOException {
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

    //cria uma hash key a partir do nome
    public StringKey getKey(String nome) {
        return new StringKey(nome);
    }
    
    public static String validaUsuario(String usuario, String senha ,Nodo no)
    {
        String codigo = null;
        String aux = null;
        String aux2 = null;
        Usuario user = new Usuario();
        StringKey myKey = new StringKey(usuario);
        Set<Serializable> vals = null;
        vals = no.getChord().retrieve(myKey);
        Iterator<Serializable> it = vals.iterator();
        while (it.hasNext()) {
            aux = (String) it.next();
            aux2 = aux.substring(aux.length() - 1, aux.length());
            switch (aux2) {
                case "1":
                    user.setSenhaPt1(aux.substring(0, aux.length() - 1));
                    continue;
                case "2":
                    user.setSenhaPt2(aux.substring(0, aux.length() - 1));
                    continue;
                case "3":
                    user.setSenhaPt3(aux.substring(0, aux.length() - 1));
                    continue;
                case "4":
                    user.setSenhaPt4(aux.substring(0, aux.length() - 1));
                    continue;
                case "5":
                    user.setSenhaPt5(aux.substring(0, aux.length() - 1));
                    continue;
                case "6":
                    user.setSenhaPt6(aux.substring(0, aux.length() - 1));
                    continue;
                case "7":
                    user.setSenhaPt7(aux.substring(0, aux.length() - 1));
                    continue;
                case "8":
                    user.setSenhaPt8(aux.substring(0, aux.length() - 1));
                    continue;
            }
        }
        if (user.juntaSenha().equals(user.criptografaSenha(senha))) {
            myKey = new StringKey(senha);
            vals = no.getChord().retrieve(myKey);
            it = vals.iterator();
            while (it.hasNext()) {
                codigo = (String) it.next();
                break;
            }
        }
        return codigo;
    }
}
