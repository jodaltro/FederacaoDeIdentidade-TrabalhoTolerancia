package trabalhofinal;

public class ServerRegister {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		jgroups serverResquest = new jgroups();
		serverResquest.user_name = "register";
//		String user="jojo";
//		String password="jo";
//		System.out.println(Nuvem.validateUser(user, password));
		serverResquest.start("register");
	}

}
