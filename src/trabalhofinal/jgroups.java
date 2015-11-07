package trabalhofinal;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import org.jgroups.util.Util;

import java.util.List;
import java.util.Scanner;
import java.util.LinkedList;

public class jgroups extends ReceiverAdapter {
    JChannel channel;
    String user_name=System.getProperty("user.name", "n/a");
    final List<String> state=new LinkedList<String>();
    public void viewAccepted(View new_view) {
        System.out.println("** view: " + new_view);
    }

    public void receive(Message msg) {
        String line=msg.getSrc() + ": " + msg.getObject();
       // System.out.println(line);
       
        synchronized(state) {
            state.add(line);
        }
    }

    public byte[] getState() {
        synchronized(state) {
            try {
                return Util.objectToByteBuffer(state);
            }
            catch(Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    @SuppressWarnings("unchecked")
	public void setState(byte[] new_state) {
        try {
            List<String> list=(List<String>)Util.objectFromByteBuffer(new_state);
            synchronized(state) {
                state.clear();
                state.addAll(list);
            }
            System.out.println("received state (" + list.size() + " messages in chat history):");
            for(String str: list) {
                System.out.println(str);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void start() throws Exception {
        channel=new JChannel();
        channel.setReceiver(this);
        channel.connect("nuvem");
        channel.getState(null, 10000);
        eventLoop();
        channel.close();
    }

    @SuppressWarnings("resource")
	private void eventLoop() {
       // BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
    	int cont = 0;
        while(true) {
            try {
            	System.out.println("Cadastrar novo Usuario!");
                System.out.println("Login:");
                String usuario = new Scanner(System.in).nextLine();
                System.out.println("Senha:");
                String senha = new Scanner(System.in).nextLine();
                Nuvem.novoUsuario(usuario,senha);
                for (int x = 0; x < Nuvem.nos.size(); x++) {
                            System.out.println("***No; " + Nuvem.nos.get(x).getIdAux());
                            System.out.println("-- " + Nuvem.nos.get(x).getID() + " " + Nuvem.nos.get(x).getAdress());
                            System.out.println("-->" + Nuvem.nos.get(x).getNo().printFingerTable());
                            System.out.println("->" + Nuvem.nos.get(x).getNo().printEntries());
                            System.out.println("\n");
                }
                //System.out.println(mainDHT.validaAqui(usuario, senha));
       
               // System.out.print("> "); 
                //System.out.flush();
                //String line=in.readLine().toLowerCase();
                /*if(line.startsWith("quit") || line.startsWith("exit")) {
                    break;
                }*/
                usuario=usuario +":"+ senha +":"+cont;
                cont++;
                Message msg=new Message(null, null, usuario);
             
               // String duvido = 
               // System.out.println();
                channel.send(msg);
               // System.out.println("aqui foi"+msg.toString());
                
               
                
            }
            catch(Exception e) {
            }
        }
    }
}

