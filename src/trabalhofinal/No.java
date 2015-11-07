/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal;

import de.uniba.wiai.lspi.chord.data.URL;
import de.uniba.wiai.lspi.chord.service.impl.ChordImpl;
import java.net.MalformedURLException;

/**
 *
 * @author kinst
 */
public class No {
    private URL localURL;
    private ChordImpl no;
    private int porta;
    private String protocolo;
    private int idAux;
    
    public No(int porta, int nNos){
        this.porta = porta;
        this.idAux = nNos;
        protocolo = URL.KNOWN_PROTOCOLS.get(URL.SOCKET_PROTOCOL);
        this.iniciaAdress(protocolo, "localhost", porta);
        this.no = new de.uniba.wiai.lspi.chord.service.impl.ChordImpl();
    }
    
    public void iniciaAdress(String protocolo, String host, int port) {
        try {
            this.localURL = new URL(protocolo + "://" + host + ":" + port + "/");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public URL getAdress(){
        return this.localURL;
    }
    
    public ChordImpl getNo(){
        return this.no;
    }
    
    public int getPorta(){
        return this.porta;
    }
    
    public String getID(){
        return this.no.getID()+"";
    }
    
    public int getIdAux(){
        return this.idAux;
    }
}
