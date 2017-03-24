package co.edu.icesi.per.cliente;

import processing.core.PApplet;

public class Cliente extends PApplet {

	static ComClient com;
	public int posX = 50;
	public int posY;

	public static void main(String[] args) {
		PApplet.main("co.edu.icesi.per.cliente.Cliente");

	}

	// Metodos

	public void settings() {
		System.out.println("Set Canvas Size");
		size(1440, 900);
	}

	public void setup() {
		System.out.println("Starting COM");
		com = new ComClient();
		com.start();
		System.out.println("Starting Client");
	}

	public void draw() {
		background(255, 255, 255);
		if (com.getData() != false) {
			posY = com.getInt();

			if (posX <= 1390) {
				fill(0);
				ellipse(posX, posY, 50, 50);
				posX = posX + 5;
			} else {
				System.out.println("Comunicacndose con el servidor");
				com.setData(false);
				com.infromarServidor();
				posX = 50;
			}
		}
	}

}
