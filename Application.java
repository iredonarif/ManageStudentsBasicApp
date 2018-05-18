import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Application extends JFrame implements ActionListener{

	public Application(){
		setTitle("Application Gestion Eleve");
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setBackground(Color.blue);

		JMenu labelMenu = new JMenu("Application");
		JMenuItem lancer = new JMenuItem("Lancer");
		lancer.addActionListener(this);
		labelMenu.add(lancer);
		JMenuItem aide = new JMenuItem("Aide");
		aide.addActionListener(this);
		labelMenu.add(aide);
		JMenuItem arreter = new JMenuItem("Arrêter");
		arreter.addActionListener(this);
		labelMenu.add(arreter);
		JMenuBar barMenu = new JMenuBar();
		barMenu.add(labelMenu);
		setJMenuBar(barMenu);
		add(panel);
	}
	public void paint(Graphics g) {
		super.paint(g);
		g.setFont(new Font("Calibri", Font.BOLD, 36));
		g.setColor(Color.BLACK);
		g.drawString("Application Gestion Élève", 160, 300);
	}

	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("Lancer")){
			FenetreUtilisateur fut = new FenetreUtilisateur();
			fut.setVisible(true);
		}
		else if(e.getActionCommand().equals("Arrêter")){
			System.exit(0);
		}
	}
	public static void main(String[] args) {
		Application app = new Application();
		app.setVisible(true);
		
	}
}