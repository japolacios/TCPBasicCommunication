package co.edu.icesi.per.servidor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ComServer extends Thread {

	private ServerSocket ss;
	private Socket s;
	private final int PORT = 6000;
	private boolean online;

	public ComServer() {
		try {
			ss = new ServerSocket(PORT);
			s = null;
			online = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (online) {
			try {
				if (s == null) {
					s = ss.accept();
					enviarInt();
				} else{
					recibirMensaje();
				}
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (SocketException e) {
				s = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void enviarInt() throws IOException {

		DataOutputStream salida = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
		int randomY = 50 + (int) (Math.random() * 850);
		salida.writeInt(randomY);

		salida.flush();

	}
	
	private void recibirMensaje() throws IOException {
		DataInputStream entrada = new DataInputStream(new BufferedInputStream(s.getInputStream()));
		int msg = entrada.readInt();
		if (msg == 1){
			enviarInt();
		}
		
	}

}
