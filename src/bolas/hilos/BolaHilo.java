package bolas.hilos;

import java.awt.Rectangle;

import bolas.bolas.Bola;
import bolas.ventanas.VentanaBolas;

public class BolaHilo extends Thread {

	private Bola bola;
	private VentanaBolas ventanaBolas;

	public BolaHilo(Bola bola, VentanaBolas ventanaBolas) {
		this.bola = bola;
		this.ventanaBolas = ventanaBolas;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (getBola().getPosicionX() < 0
					|| getBola().getPosicionX() > getVentanaBolas().getWidth() - getBola().getDimension())
				getBola().setSentidoX(getBola().getSentidoX() * -1);

			if (getBola().getPosicionY() < 0
					|| getBola().getPosicionY() > getVentanaBolas().getHeight() - getBola().getDimension())
				getBola().setSentidoY(getBola().getSentidoY() * -1);
			getBola().calcularPosicion();
			
			for (Bola otra : getVentanaBolas().getBolas()) {
				if(!getBola().equals(otra)) {
					Rectangle  yo = new Rectangle(getBola().getPosicionX(),getBola().getPosicionY(),getBola().getDimension(),getBola().getDimension());
					Rectangle otro = new Rectangle(otra.getPosicionX(),otra.getPosicionY(),otra.getDimension(),otra.getDimension());
					if(yo.intersects(otro)) {
						getBola().setSentidoX(getBola().getSentidoX()*-1);
						getBola().setSentidoY(getBola().getSentidoY()*-1);
					}
				}
				
			}
		}
	}

	public Bola getBola() {
		return bola;
	}

	public void setBola(Bola bola) {
		this.bola = bola;
	}

	public VentanaBolas getVentanaBolas() {
		return ventanaBolas;
	}

	public void setVentanaBolas(VentanaBolas ventanaBolas) {
		this.ventanaBolas = ventanaBolas;
	}

}
