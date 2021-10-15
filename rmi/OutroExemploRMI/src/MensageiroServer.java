import java.rmi.Naming;

public class MensageiroServer {

	public MensageiroServer() {
		try {
			Mensageiro m = new MensageiroImpl();
			// Naming.rebind("rmi://localhost:1099/MensageiroService", m);
			Naming.rebind("Mensageiro", m);
		} catch (Exception e) {
			System.out.println("Trouble: " + e);
		}
	}

	public static void main(String[] args) {
		new MensageiroServer();
	}
}
