import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

//fenêtre permettant d'ajouter un élève dans le fichier eleve.txt
public class AjouterEleve extends JFrame implements ActionListener{
	private	JTextField num = new JTextField(15),nom = new JTextField(15), prenom = new JTextField(15);
	//par défaut le sexe est féminin
	private String sexe = "féminin";
	private JOptionPane inf = new JOptionPane();

	public AjouterEleve(){
		setTitle("Ajouter Eleve");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		JPanel container = new JPanel();
		JPanel panelNum = new JPanel(), panelNom = new JPanel(), panelPrenom = new JPanel(), panelSexe = new JPanel();
		panelNum.setLayout(new GridLayout(1,2));
		panelNom.setLayout(new GridLayout(1,2));
		panelPrenom.setLayout(new GridLayout(1,2));
		panelSexe.setLayout(new GridLayout(1,2));
		JLabel labelNum = new JLabel("numero "), labelNom = new JLabel("Nom "), labelPrenom = new JLabel("Prenom "), labelSexe = new JLabel("Sexe ");
		num.setPreferredSize(new Dimension(40, 30));
		panelNum.add(labelNum);
		panelNum.add(num);
		nom.setPreferredSize(new Dimension(40, 30));
		panelNom.add(labelNom);
		panelNom.add(nom);
		prenom.setPreferredSize(new Dimension(40, 30));
		panelPrenom.add(labelPrenom);
		panelPrenom.add(prenom);

		JPanel panelRadio = new JPanel(new GridLayout(0,1));
		ButtonGroup group = new ButtonGroup();
		//création des boutons radios
		JRadioButton sexe1 = new JRadioButton("féminin", true), sexe2 = new JRadioButton("masculin");
		sexe1.addActionListener(this);
		sexe2.addActionListener(this);
		group.add(sexe1);
		panelRadio.add(sexe1);
		group.add(sexe2);
		panelRadio.add(sexe2);
		panelSexe.add(labelSexe);
		panelSexe.add(panelRadio);
		JPanel panelButton = new JPanel(new FlowLayout());
		//création des boutons Ajouter, Effacer et Retour
		JButton ajouter = new JButton("Ajouter"), effacer = new JButton("Effacer"),retour = new JButton("Retour"), quitter = new JButton("Quitter");
		ajouter.addActionListener(this);
		effacer.addActionListener(this);
		quitter.addActionListener(this);
		retour.addActionListener(this);
		panelButton.add(ajouter);
		panelButton.add(effacer);
		panelButton.add(retour);
		panelButton.add(quitter);
		container.add(panelNum);
		container.add(panelNom);
		container.add(panelPrenom);
		container.add(panelSexe);
		container.add(panelButton);
		add(container);
	}
//Permet de vérifier dans le fichier si le numéro saisit n'existe pas déjà dans le fichier eleve.txt
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
//permet de creer un élève
	public void creerEleve(){
				Eleve eleve = new Eleve(Integer.valueOf(num.getText()), nom.getText(), prenom.getText(), sexe);
				eleve.enregisterEleve(eleve);
	}
	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand().equals("masculin")) {
			sexe = "masculin";
		}
		if(e.getActionCommand().equals("Ajouter")){
			if (num.getText().equals("")||nom.getText().equals("")||prenom.getText().equals("")) {
				inf.showMessageDialog(null, "Vous devez renseigner tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			if(!(verifierNum())){
				creerEleve();
				dispose();
				inf.showMessageDialog(null, "Élève enregitré avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
				AjouterEleve ae = new AjouterEleve();
				ae.setVisible(true);
			}
			else {
				inf.showMessageDialog(null, "Un élève de même numéro existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (e.getActionCommand().equals("Effacer")){
			num.setText("");
			nom.setText("");
			prenom.setText("");
		}
		else if(e.getActionCommand().equals("Retour")){
			GestionEleve gestion = new GestionEleve();
			gestion.setVisible(true);
			dispose();
		}
		else if(e.getActionCommand().equals("Quitter")){
			System.exit(0);
		}
	}
}