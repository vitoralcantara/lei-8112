package rmiknolexample;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

/**
 * 
 * @author Andre
 */
public class ForumImpl extends UnicastRemoteObject implements IForum {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Vector<String> posts;

	// O construtor padrão deve gera a exeção RemoteException
	public ForumImpl() throws RemoteException {
		posts = new Vector<String>();
	}

	public void postMessage(String author, String aMessage)
			throws RemoteException {
		posts.add(author + " disse uma vez: " + aMessage);
	}

	public String[] readPosts() throws RemoteException {
		String[] result = new String[posts.size()];
		posts.toArray(result);
		return result;
	}

	public String[] readPosts(int beginAt) throws RemoteException {
		String[] results = readPosts();
		String[] copy = new String[results.length - beginAt];
		System.arraycopy(results, beginAt, copy, 0, copy.length);
		return copy;
	}
}