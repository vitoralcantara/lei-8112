package servidor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import br.edu.ifrn.diatinf.tads.psd.rmi.helloworld.HelloWorld;
import br.edu.ifrn.diatinf.tads.psd.rmi.helloworld.impl.HelloWorldImpl;

public class Servidor {

	public static void main(String[] args) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		HelloWorld hello = new HelloWorldImpl();
		HelloWorld stub = null;

		try {
			String name = "HelloWorld";
			stub = (HelloWorld) UnicastRemoteObject.exportObject(hello, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind(name, stub);
			System.out.println("HelloWorld bound");
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}
}
