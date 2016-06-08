package ejer2;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Window;
/*2. Realizar un formulario que simule el teclado de un teléfono móvil clásico.
 Debe tener al menos 12 botones con los números del 1 al 9 en las tres primeras filas y en la última
la almohadilla (#), el 0 y el asterisco (*). Se inicializarán los 12 mediante un bucle (no habrá 12
variables JButton).
 Cuando el ratón pase por encima de los botones cambiará de color y si se pulsan quedarán
permanentemente con un tercer color.
 Además se dispondrá de un textfield donde se mostrarán los números pulsados. Será no editable.
 Si se pulsa una tecla que corresponda a un número también funcionará.
 Finalmente se creará otro botón más de reset (este no es necesario que se haga en el bucle)
mediante el cual se borra el textfield y se restaura el color de todos los botones.
 Crea una barra de menú con tres entradas. El primera denominada Archivo, habrá las siguientes
opciones:
- Grabar número: se permitirá añadir el número actual a un archivo.
- Leer números: Muestra en un JOptionPane los números guardados en el archivo.
En la segunda denominada Móvil tendrá como ítems Reset, un separador y Salir. La tercera será 
Otros con un único ítem Acerca de... que sacará un JOptionPane con información sobre la aplicación
y el autor. Debe responder a teclas ALT.
*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.*;

public class Bol8_Ejer2 extends JFrame implements ActionListener {
	JButton[] btnBotones;
	String[] strBotones;
	JPanel pnlFila1;
	JPanel pnlFila2;
	JPanel pnlFila3;
	JPanel pnlFila4;
	JTextField txfPantalla;
	JButton btnReset;
	JMenuBar mnuPrincipal;
	JMenu mnuArchivo, mnuMovil, mnuOtros;
	JMenuItem mnuGrabar, mnuLeer, mnuReset, mnuSalir, mnuAcerca;
	PrintWriter fleAgenda;

	public Bol8_Ejer2() {
		super("Teléfono");
		this.setLayout(new FlowLayout());
		setFocusable(true);

		mnuGrabar = new JMenuItem("Guardar Número");
		mnuGrabar.setMnemonic('S');
		mnuGrabar.addActionListener(this);

		mnuLeer = new JMenuItem("Leer Número");
		mnuLeer.setMnemonic('O');
		mnuLeer.addActionListener(this);

		mnuReset = new JMenuItem("Reiniciar");
		mnuReset.setMnemonic('R');
		mnuReset.addActionListener(this);

		mnuArchivo = new JMenu("Archivo");
		mnuArchivo.setMnemonic('A');

		mnuArchivo.add(mnuGrabar);
		mnuArchivo.add(mnuLeer);

		mnuReset = new JMenuItem("Reiniciar");
		mnuReset.setMnemonic('R');
		mnuReset.addActionListener(this);

		mnuSalir = new JMenuItem("Salir");
		mnuSalir.setMnemonic('C');
		mnuSalir.addActionListener(this);

		mnuMovil = new JMenu("Móvil");
		mnuMovil.setMnemonic('M');

		mnuMovil.add(mnuReset);
		mnuMovil.addSeparator();
		mnuMovil.add(mnuSalir);

		mnuAcerca = new JMenuItem("Acerca de...");
		mnuAcerca.setMnemonic('B');
		mnuAcerca.addActionListener(this);

		mnuOtros = new JMenu("Otros");
		mnuOtros.setMnemonic('O');

		mnuOtros.add(mnuAcerca);

		mnuPrincipal = new JMenuBar();
		mnuPrincipal.add(mnuArchivo);
		mnuPrincipal.add(mnuMovil);
		mnuPrincipal.add(mnuOtros);
		this.setJMenuBar(mnuPrincipal);

		txfPantalla = new JTextField(20);
		txfPantalla.setBackground(Color.white);
		txfPantalla.setForeground(new Color(150, 87, 255));
		txfPantalla.setEditable(false);
		this.add(txfPantalla);

		pnlFila1 = new JPanel();
		
		
		
		pnlFila2 = new JPanel();
		pnlFila3 = new JPanel();
		pnlFila4 = new JPanel();

		this.add(pnlFila1);
		this.add(pnlFila2);
		this.add(pnlFila3);
		this.add(pnlFila4);

		strBotones = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "*", "0", "#" };

		btnBotones = new JButton[12];
		for (int i = 0; i < btnBotones.length; i++) {

			btnBotones[i] = new JButton(strBotones[i]);
			btnBotones[i].setBackground(Color.white);
			btnBotones[i].setName("" + i);
			btnBotones[i].setFocusable(false);
			if (i < 3) {
				pnlFila1.add(btnBotones[i]);
			} else if (i < 6) {
				pnlFila2.add(btnBotones[i]);
			} else if (i < 9) {
				pnlFila3.add(btnBotones[i]);
			} else {
				pnlFila4.add(btnBotones[i]);
			}
			btnBotones[i].addMouseListener(new MouseHandler());

		}
		btnReset = new JButton("      Borrar Todo      ");
		btnReset.setFocusable(false);
		btnReset.addActionListener(this);
		this.add(btnReset);

		this.addKeyListener(new KeyHandler());
	}

	private class KeyHandler extends KeyAdapter {
		@Override
		public void keyTyped(KeyEvent e) {
			if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyChar() == '#' || e.getKeyChar() == '*') {
				txfPantalla.setText(txfPantalla.getText() + e.getKeyChar());
				for (int i = 0; i < btnBotones.length; i++) {
					if (strBotones[i].equals(e.getKeyChar() + ""))
						btnBotones[i].setBackground(new Color(251, 255, 177));
				}
			}
		}
	}

	private class MouseHandler extends MouseAdapter {
		@Override
		public void mouseEntered(java.awt.event.MouseEvent e) {
			JButton nuevo = (JButton) e.getSource();
			if (nuevo.getBackground() == Color.white)
				nuevo.setBackground(new Color(234, 177, 255));

		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent e) {
			JButton nuevo = (JButton) e.getSource();
			Color temp = new Color(234, 177, 255);
			if (nuevo.getBackground().equals(temp))
				nuevo.setBackground(Color.white);

		}

		@Override
		public void mouseReleased(java.awt.event.MouseEvent e) {
			JButton nuevo = (JButton) e.getSource();
			nuevo.setBackground(new Color(251, 255, 177));
			txfPantalla.setText(txfPantalla.getText() + nuevo.getText());

		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnReset || arg0.getSource() == mnuReset) {
			txfPantalla.setText("");
			for (int i = 0; i < btnBotones.length; i++) {
				btnBotones[i].setBackground(Color.white);
			}
		}
		if (arg0.getSource() == mnuGrabar) {
			if (txfPantalla.getText().trim().equals("")) {
				
			} else {
				try {
					File crear = new File(System.getProperty("user.home") + "/Agenda.txt");
					crear.createNewFile();
					fleAgenda = new PrintWriter(new FileWriter(System.getProperty("user.home") + "/Agenda.txt", true));
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(this, "No se ha podido encontrar la agenda");
				} catch (IOException a) {
					JOptionPane.showMessageDialog(this, "No se ha podido crear la agenda");
				}
				fleAgenda.println(txfPantalla.getText());
				fleAgenda.close();
			}
		} else if (arg0.getSource() == mnuLeer) {
			try {
				Scanner file = new Scanner(new File(System.getProperty("user.home") + "/Agenda.txt"));
				String temp = "<html><table>";
				while (file.hasNext()) {
					temp += "<tr>" + file.nextLine() + "</tr>";
				}
				JOptionPane.showMessageDialog(this, temp, "Tu Agenda", JOptionPane.PLAIN_MESSAGE);
				temp += "</table></html>";
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Todavía no has guardado ningún número en la agenda", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		} else if (arg0.getSource() == mnuSalir) {
			exit(this);
		} else if (arg0.getSource() == mnuAcerca) {
			JOptionPane.showMessageDialog(this,
					"<html><table><tr>Esta aplicación te permite guardar tus números de teléfono</tr> "
							+ "<tr>en un archivo de texto, leerlos, y resetear tu agenda.</tr>"
							+ "<tr>Esta aplicación ha sido creada por Sheyla Venade Carrascal.</tr>",
					"Acerca de...", JOptionPane.PLAIN_MESSAGE);
		}
	}

	static void exit(Window ventana) {
		int opc = JOptionPane.showConfirmDialog(ventana, "¿Deseas cerrar el programa?", "Móvil",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (opc == JOptionPane.OK_OPTION)
			ventana.dispose();
	}
}