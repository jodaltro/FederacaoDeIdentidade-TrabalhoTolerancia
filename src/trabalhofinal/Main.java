/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal;

//import de.uniba.wiai.lspi.chord.data.URL;

/**
 *
 * @author kinst
 */
public class Main {
     public static void main(String[] args) throws Exception {
        //cria o no inicial + 4 nos
        Nuvem.createNetwork();
        Nuvem.nodoaux = Nuvem.nos.get(0);
        new jgroups().start();
       
    }
}
