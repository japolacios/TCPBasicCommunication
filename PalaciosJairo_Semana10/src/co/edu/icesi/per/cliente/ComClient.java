package co.edu.icesi.per.cliente;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ComClient extends Thread {

	private Socket s;
	private final int PORT = 6000;
	private boolean online;
	private boolean data = false;
	private int posY;
	
	
	public ComClient() {
		online = true;
	}

	private void recibirInt() throws IOException {
		DataInputStream entrada = new DataInputStream(new BufferedInputStream(
				s.getInputStream()));
		int _posY = entrada.readInt();
		posY = _posY;
		data = true;
		
	}

	public void infromarServidor()  {
		try {
			int llego = 1;
			DataOutputStream salida = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
			salida.writeInt(llego);
			System.out.println(llego);
			salida.flush();
			//salida.close();
			System.out.println("Notificacion Enviada al Servidor");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean getData(){
		return data;
	}
	
	public void setData(boolean _b){
		data = _b;
	}
	
	public int getInt(){
		return posY;
	}

	@Override
	public void run() {
		while (online) {
			if (s!=null){
				try{
					if(data == false){
						recibirInt();
						}
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (s == null) {
				try {
					s = new Socket(InetAddress.getByName("127.0.0.1"), PORT);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
