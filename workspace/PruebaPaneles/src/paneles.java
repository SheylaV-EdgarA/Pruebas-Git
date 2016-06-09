import java.awt.FlowLayout;

import javax.swing.*;

public class paneles extends JFrame{
	JButton[] btnBotones;
	JPanel panel;
	public paneles(){
		super("Paneles");
		//this.setLayout(new FlowLayout());
		System.out.println("Mi ma que locura");
		
		panel=new JPanel();
		panel.setLayout(null);
		
		btnBotones=new  JButton[12];
		int h=20;
		int v=30;
		for(int i=0;i<btnBotones.length;i++){
			btnBotones[i]=new JButton(""+i);
			btnBotones[i].setSize(btnBotones[i].getPreferredSize());
			btnBotones[i].setLocation(h,v);
			h+=50;
			v+=50;
			panel.add(btnBotones[i]);
		}
		panel.setSize(panel.getPreferredSize());
		this.add(panel);
	}
}
