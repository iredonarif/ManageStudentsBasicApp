import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class ChercherEleve extends JFrame implements ActionListener{
	private JTextField num = new JTextField(15);
	
	public ChercherEleve(){
		setTitle("Chercher Eleve");
		setSize(400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		JPanel container = new JPanel(new BorderLayout());
		JLabel indication = new JLabel("Cherchez un Éleve à partir de son numéro");
		container.add(indication, BorderLayout.NORTH);
		JPanel panelNum = new JPanel();
		JLabel labelNum = new JLabel("Numéro Éleve : ");
		panelNum.add(labelNum);
		num.setFont(new Font("Calibri", Font.BOLD, 12));
		num.setBorder(BorderFactory.createLineBorder(Color.black));
		panelNum.add(num);
		container.add(panelNum, BorderLayout.CENTER);
		
		JPanel panelBouton = new JPanel();
		JButton retour = new JButton("Retour");
		retour.addActionListener(this);
		panelBouton.add(retour);
		JButton ok = new JButton("OK");
		ok.addActionListener(this);
		panelBouton.add(ok);
		container.add(panelBouton, BorderLayout.SOUTH);
		add(container);
		
	}
//Récupération du numéro saisit
	public String obtenirNum(){
		return num.getText();
	}
//Vérification de l'existance du numéro dans le fichier eleve.txt
	public boolean verifierNum(){
		Scanner inputStream = null;
		boolean valRetour = false;
		try{
			inputStream = new Scanner(new FileInputStream("eleve.txt"));
		}
		catch (FileNotFoundException e){
			System.out.println("Le fichier eleve.txt n'existe pas");
		}
		while (inputStream.hasNextLine()) {
			String[] valeurs = inputStream.nextLine().split(" ");
			if (valeurs[0].equals(num.getText())) {
				valRetour = true;
			}
		}
		return valRetour;
	}
	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand().equals("OK")) {
			if(!(verifierNum())){
				JOptionPane inf = new JOptionPane();
				inf.showMessageDialog(null, "Ce numéro n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			else{
				EditerEleve edition = new EditerEleve();
				edition.setVisible(true);
				edition.afficheInfosEleve(obtenirNum());
				dispose();
			}
		}
		else {
			GestionEleve gestion = new GestionEleve();
			gestion.setVisible(true);
			dispose();
		}
	}
	public static void main(String[] args) {
		ChercherEleve ce = new ChercherEleve();
		ce.setVisible(true);
	}
	
}