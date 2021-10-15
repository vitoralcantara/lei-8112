package br.edu.ifrn.diatinf.tads.psd.rmi.helloworld.impl;

import java.rmi.RemoteException;

import br.edu.ifrn.diatinf.tads.psd.rmi.helloworld.HelloWorld;

public class HelloWorldImpl implements HelloWorld {

	@Override
	public String sayHello() throws RemoteException {
		return "Hello World!";
	}
}
