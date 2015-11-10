package trabalhofinal;

public class ServerValidate {
	public static String user="jojo";
	public static String password="jo";

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		jgroups serverResquest = new jgroups();
		serverResquest.user_name = "validate";
//		System.out.println(Nuvem.validateUser(user, password));
		serverResquest.start("validate");
	}

}
