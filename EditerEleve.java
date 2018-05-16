import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

//Fenêtre permettant de modifier ou de supprimer un élève
public class EditerEleve extends JFrame implements ActionListener{
	private	JTextField num = new JTextField(15), nom = new JTextField(15), prenom = new JTextField(15);
	//private JComboBox sexe = new JComboBox();
	private JTextField sexe = new JTextField(15); 
	private JOptionPane inf = new JOptionPane();

	public EditerEleve(){
		setTitle("Editer Eleve");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		JPanel panelInfos = new JPanel();
		JPanel panelNum = new JPanel();
		panelNum.setLayout(new GridLayout(1,2));
		num.setEditable(false);
		num.setPreferredSize(new Dimension(40, 30));
		JPanel panelNom = new JPanel();
		panelNom.setLayout(new GridLayout(1,2));
		nom.setPreferredSize(new Dimension(40, 30));
		JPanel panelPrenom = new JPanel();
		panelPrenom.setLayout(new GridLayout(1,2));
		prenom.setPreferredSize(new Dimension(40, 30));
		JPanel panelSexe = new JPanel();
		panelSexe.setLayout(new GridLayout(1,2));
		sexe.setPreferredSize(new Dimension(40, 30));

		panelNum.add(new JLabel("Numero Eleve "));
		panelNum.add(num);
		panelNom.add(new JLabel("Nom Eleve "));
		panelNom.add(nom);
		panelPrenom.add(new JLabel("Prenom Eleve "));
		panelPrenom.add(prenom);
		panelSexe.add(new JLabel("Sexe Eleve "));
		panelSexe.add(sexe);

		JPanel panelBouton = new JPanel();
		JButton modifier = new JButton("Modifier"), supprimer = new JButton("Supprimer"), quitter = new JButton("Quitter");
		supprimer.setBackground(Color.red);
		supprimer.addActionListener(this);
		modifier.addActionListener(this);
		quitter.addActionListener(this);

		panelBouton.add(modifier);
		panelBouton.add(supprimer);
		panelBouton.add(quitter);
		panelInfos.add(panelNum);
		panelInfos.add(panelNom);
		panelInfos.add(panelPrenom);
		panelInfos.add(panelSexe);
		container.add(panelInfos, BorderLayout.CENTER);
		container.add(panelBouton, BorderLayout.SOUTH);
		add(container);
	}
//permet d'afficher les informations d'un élève dans les champs correspondant
	public void afficheInfosEleve(String numeleve){
		Scanner inputStream = null;
		try{
			inputStream = new Scanner(new FileInputStream("eleve.txt"));}
		catch (FileNotFoundException e){
			System.out.println("Le fichier eleve.txt n'existe pas");
		}
		while (inputStream.hasNextLine()) {
			String[] infos = inputStream.nextLine().split(" ");
			if (infos[0].equals(numeleve)) {
				num.setText(infos[0]);
				nom.setText(infos[1]);
				prenom.setText(infos[2]);
				sexe.setText(infos[3]);
			}
		}
	}
	public void actionPerformed(ActionEvent ev){
		//code permettant de supprimer un élève du fichier eleve.txt
		if (ev.getActionCommand().equals("Supprimer")){
			Scanner inputStream = null;
			PrintWriter outputStream = null;
			ArrayList<String> eleves = new ArrayList<String>();
			try{
				inputStream = new Scanner(new FileInputStream("eleve.txt"));
			}
			catch (FileNotFoundException e){
				System.out.println("Probleme d'ouverture du fichier");
			}
			while (inputStream.hasNextLine()) {
				String infos = inputStream.nextLine();
				if (infos.contains(num.getText())==false){
					eleves.add(infos);
				}
			}
			inputStream.close();
			try {
				outputStream = new PrintWriter(new FileOutputStream("eleve.txt"));}
			catch (FileNotFoundException e){
				System.out.println("Probleme d'ouverture du fichier");
			}
			for (String eleve : eleves ) {
				outputStream.println(eleve);
			}
			outputStream.close();
			inf.showMessageDialog(null, "Élève supprimé avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
			ChercherEleve chercheEleve = new ChercherEleve();
			chercheEleve.setVisible(true);
			dispose();
		}
//Code permettant de modifier un élève dans le fichier élève.txt
		else if(ev.getActionCommand().equals("Modifier")){
			Scanner inputStream = null;
			PrintWriter outputStream = null;
			ArrayList<String> eleves = new ArrayList<String>();
			try{
				inputStream = new Scanner(new FileInputStream("eleve.txt"));}
			catch (FileNotFoundException e){
				System.out.println("Le fichier eleve.txt n'existe pas");
			}
			while (inputStream.hasNextLine()) {
				String eleve = inputStream.nextLine();
				if (eleve.contains(num.getText())==true) {
					String[] infos = eleve.split(" ");
					infos[1] = nom.getText();
					infos[2] = prenom.getText();
					eleve = num.getText()+" "+nom.getText()+" "+prenom.getText()+" "+sexe.getText();
				}
				eleves.add(eleve);
			}
			inputStream.close();
			try {
				outputStream = new PrintWriter(new FileOutputStream("eleve.txt"));}
			catch (FileNotFoundException e){
				System.out.println("Probleme d'ouverture du fichier");
			}
			for (String eleve : eleves ) {
				outputStream.println(eleve);
			}
			outputStream.close();
			inf.showMessageDialog(null, "Élève modifié avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
		}

		else if(ev.getActionCommand().equals("Quitter")){
			System.exit(0);
		}
	}
}