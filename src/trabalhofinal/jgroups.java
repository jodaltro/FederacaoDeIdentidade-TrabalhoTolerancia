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
	String user_name = System.getProperty("user.name", "n/a");
	final List<String> state = new LinkedList<String>();

	public void viewAccepted(View new_view) {
		System.out.println("** view: " + new_view);
	}

	public void receive(Message msg) {
		String line = msg.getSrc() + ": " + msg.getObject();
		// System.out.println(line);

		synchronized (state) {
			state.add(line);
		}
	}

	public byte[] getState() {
		synchronized (state) {
			try {
				return Util.objectToByteBuffer(state);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void setState(byte[] new_state) {
		try {
			List<String> list = (List<String>) Util.objectFromByteBuffer(new_state);
			synchronized (state) {
				state.clear();
				state.addAll(list);
			}
			System.out.println("received state (" + list.size() + " messages in chat history):");
			for (String str : list) {
				System.out.println(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void start() throws Exception {
		channel = new JChannel();
		channel.setReceiver(this);
		channel.connect("nuvem");
		channel.getState(null, 10000);
		eventLoop();
		channel.close();
	}

	@SuppressWarnings("resource")
	private void eventLoop() throws Exception {
		// BufferedReader in=new BufferedReader(new
		// InputStreamReader(System.in));
		int cont = 0;
		boolean flag = true; // is false, access the reserve host
		String user_Pass = null;
		while (true) {
			try {
				System.out.println("Sign up new user!");
				System.out.println("Login:");
				String user = new Scanner(System.in).nextLine();
				System.out.println("Password:");
				String password = new Scanner(System.in).nextLine();
				user_Pass = user + ":" + password + ":" + cont;
				cont++;
				Nuvem.newUser(user, password);
				for (int x = 0; x < Nuvem.nos.size(); x++) {
					System.out.println("***No; " + Nuvem.nos.get(x).getIdAux());
					System.out.println("-- " + Nuvem.nos.get(x).getID() + " " + Nuvem.nos.get(x).getAdress());
					System.out.println("-->" + Nuvem.nos.get(x).getNo().printFingerTable());
					System.out.println("->" + Nuvem.nos.get(x).getNo().printEntries());
					System.out.println("\n");
				}
				Message msg = new Message(null, null, user_Pass);
				channel.send(msg);

			} catch (Exception e) {
				System.out.println("Problem to conect to network\n");
				flag = false;
			}
			if (!flag) {
				flag = true;
				System.out.println("Trying to connect the auxiliary network...");
				Message msg = new Message(null, null, user_Pass);
				channel.send(msg);
				System.out.println("User added only in auxiliary network!");
			}
		}
	}
}
