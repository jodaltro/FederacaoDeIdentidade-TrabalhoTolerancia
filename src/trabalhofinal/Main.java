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
		de.uniba.wiai.lspi.chord.service.PropertiesLoader.loadPropertyFile();
		Nuvem.chord = new de.uniba.wiai.lspi.chord.service.impl.ChordImpl();

		while (!Nuvem.createNetwork()) {
			Nuvem.port++;
			System.out.println("Nao criou na porta " + Nuvem.port);
		}
		System.out.println("Criou na porta " + Nuvem.port);
		Nuvem.nodoaux = Nuvem.nos.get(0);
		new jgroups().start("host");

	}
}
