import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GestionEleve extends JFrame implements ActionListener{

//Cette fenêtre permet à l'utilisateur de chercher un élève à partir de son numéro ou d'ajouter un élève
	public GestionEleve(){
		this.setTitle("Gestion Eleve");
		this.setSize(380, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		setLayout(new BorderLayout());
		setLayout(new FlowLayout());
		JLabel indication1 = new JLabel("Cliquez sur Ajouter pour ajouter un Eleve");
		JLabel indication2 = new JLabel("Sur Chercher pour chercher un Eleve");
		add(indication1);
		add(indication2);
		JPanel panelGestion = new JPanel();
		panelGestion.setLayout(new FlowLayout());
		JButton ajouter = new JButton("Ajouter"), chercher = new JButton("Chercher");
		ajouter.addActionListener(this);
		panelGestion.add(ajouter);
		chercher.addActionListener(this);	
		panelGestion.add(chercher);
		add(panelGestion, BorderLayout.CENTER);
	}
	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand().equals("Ajouter")) {
			AjouterEleve ajoutEleve = new AjouterEleve();
			ajoutEleve.setVisible(true);
			dispose();
		}
		else{
			ChercherEleve chercheEleve = new ChercherEleve();
			chercheEleve.setVisible(true);
			dispose();
		}
	}
}