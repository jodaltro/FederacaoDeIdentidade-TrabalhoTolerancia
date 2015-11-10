package trabalhofinal;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import org.jgroups.util.Util;

import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class jgroups extends ReceiverAdapter {
	JChannel channel;
	String client;
	String user_name = System.getProperty("user.name", "n/a");
	final List<String> state = new LinkedList<String>();

	public void viewAccepted(View new_view) {
		System.out.println("** view: " + new_view);
	}

	public void receive(Message msg) {
		String line = "" + msg.getObject();
		System.out.println(line);

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

	public void start(String identify) throws Exception {
		channel = new JChannel();
		channel.setReceiver(this);
		channel.connect("nuvem");
		channel.getState(null, 10000);
		eventLoop(identify);
		channel.close();
	}

	@SuppressWarnings("resource")
	private void eventLoop(String identify) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String user_Pass = null;
		int contReg = 0;
		if (identify.equals("host")) {
			int cont = 0;
			int last = 0;
			int cont_s;
			String msg_recebida[], user, password;
			boolean flag = true;
			while (true) {
				try {
					last = state.size();
					cont_s = 0;
					System.out.flush();
					if (last != 0) {
						msg_recebida = state.get(last - 1).split(":");
						// System.out.println("entro "+msg_recebida[0]);
						if (msg_recebida[0].equals("register")) {
							cont_s = Integer.parseInt(msg_recebida[3]);
							if (cont_s == cont) {
								user = msg_recebida[1];
								password = msg_recebida[2];
								user_Pass = identify + ":" + user + ":" + password + ":" + contReg;
								System.out.println("usuario  " + user);
								System.out.println("senha  " + password);
								Nuvem.newUser(user, password);
								Message msg = new Message(null, null, user_Pass);
								channel.send(msg);

								for (int x = 0; x < Nuvem.nos.size(); x++) {
									System.out.println("***No; " + Nuvem.nos.get(x).getIdAux());
									System.out.println(
											"-- " + Nuvem.nos.get(x).getID() + " " + Nuvem.nos.get(x).getAdress());
									System.out.println("-->" + Nuvem.nos.get(x).getNo().printFingerTable());
									System.out.println("->" + Nuvem.nos.get(x).getNo().printEntries());
									System.out.println("\n");
								}
								cont++;
								System.out.println("User added in host!");
							}
						} else if (msg_recebida[0].equals("validate")) {
							System.out.println("entor no validate");
							user = msg_recebida[1];
							password = msg_recebida[2];
							user_Pass = identify + "codigo" + ":" + Nuvem.validateUser(user, password);
							System.out.println("vai manda!!!");
							Message msg = new Message(null, null, user_Pass);
							channel.send(msg);
						}
					}

				} catch (Exception e) {
					System.out.println("Problem to conect to network\n");
					flag = false;
				}
				if (!flag) {
					flag = true;
					cont++;
					System.out.println("Trying to connect the auxiliary network...");
					Message msg = new Message(null, null, user_Pass);
					channel.send(msg);
					System.out.println("User added only in auxiliary network!");
				}
			}

		}

		else if (identify.equals("register")) {
			while (true) {
				System.out.println("Sign up new user!");
				System.out.println("Login:");
				String user = new Scanner(System.in).nextLine();
				System.out.println("Password:");
				String password = new Scanner(System.in).nextLine();
				user_Pass = identify + ":" + user + ":" + password + ":" + contReg;
				contReg++;
				Message msg = new Message(null, null, user_Pass);
				channel.send(msg);
				// Nuvem.newUser(user, password);
				// for (int x = 0; x < Nuvem.nos.size(); x++) {
				// System.out.println("***No; " +
				// Nuvem.nos.get(x).getIdAux());
				// System.out.println("-- " + Nuvem.nos.get(x).getID() + " "
				// + Nuvem.nos.get(x).getAdress());
				// System.out.println("-->" +
				// Nuvem.nos.get(x).getNo().printFingerTable());
				// System.out.println("->" +
				// Nuvem.nos.get(x).getNo().printEntries());
				// System.out.println("\n");
				// }

			}
		} else if (identify.equals("validate")) {
			int last = 0;
			String msg_r[];
			user_Pass = identify + ":" + ServerValidate.user + ":" + ServerValidate.password;
			Message msg = new Message(null, null, user_Pass);
			channel.send(msg);
			do 
			{
				last = state.size();
				System.out.println(last);
				msg_r = state.get(last - 1).split(":");
				System.out.println("entro " + msg_r[0]);
				if (msg_r[0].equals("hostcodigo")) {
					System.out.println(msg_r[1]);
				}
			}
			while(last == 0);

		}
		// int cont = 0;
		// boolean flag = true; // is false, access the reserve host
		// if (!client.contains("request")) {
		// // String user_Pass = null;
		// while (true) {
		// }

	}
}
