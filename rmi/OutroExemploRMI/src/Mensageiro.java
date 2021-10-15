import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Mensageiro extends Remote {

	public void enviarMensagem(String msg) throws RemoteException;

	public String lerMensagem() throws RemoteException;
}