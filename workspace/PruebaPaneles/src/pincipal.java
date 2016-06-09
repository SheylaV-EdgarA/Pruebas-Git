
import javax.swing.JFrame;

public class pincipal {

	public static void main(String[] args) {
		System.out.println("Hola");
		paneles aplicacion=new paneles();
		aplicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aplicacion.setSize(600,400);
		aplicacion.setVisible(true);

	}

}
