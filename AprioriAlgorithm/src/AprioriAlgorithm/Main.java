package AprioriAlgorithm;

import java.awt.EventQueue;

import AprioriAlgorithm.GUI;

public class Main {

	public static void main(String[] args) {					
		//Se inicia un nuevo evento en la cola
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					//Cre un nuevo objeto de tio GUI (La interfaz Grafica)
					GUI frame = new GUI();
					//Establece el frame en visible
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		/*//Ejemplo vasado en la tabla de juego de tenis y atributo estado
		//cuenta = a la ocurrencias por clase en la tabla
		int cuenta[]=new int[]{4,10};
		//atrib= a la concurrencia de clases por valor del atributo
		int atrib[][]=new int[][]{{2,3},{4,0},{4,1}};
		
		System.out.println("Entropia de tabla: "+entropy(cuenta));
		System.out.println("Ganancia de atriuto 'Estado' :"+ganancia(atrib));*/

	}
}
