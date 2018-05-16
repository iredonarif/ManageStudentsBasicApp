import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Inscription extends JFrame implements ActionListener{
	 	private JTextField nom = new JTextField(15), prenom = new JTextField(15);
		private JPasswordField passwd = new JPasswordField(15);
		//JButton inscrire = new JButton("Inscription");
	
	//Cette fenêtre permet de s'inscrire en créant un compte	
	public  Inscription(){
		setTitle("Inscription");
		setSize(380, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		JPanel container = new JPanel();
		JPanel panelPrenom = new JPanel(), panelNom = new JPanel(), panelPasswd = new JPanel(), panelBouton = new JPanel();
		panelNom.setLayout(new GridLayout(1,2));
		panelPrenom.setLayout(new GridLayout(1,2));
		panelPasswd.setLayout(new GridLayout(1,2));
		nom.setPreferredSize(new Dimension(40,30));

		JLabel labelNom = new JLabel("Nom "), labelPrenom = new JLabel("Prenom "), labelPasswd = new JLabel("Mot de passe ");
		panelNom.add(labelNom);
		panelNom.add(nom);
		prenom.setPreferredSize(new Dimension(40, 30));
		panelPrenom.add(labelPrenom);
		panelPrenom.add(prenom); 
		passwd.setPreferredSize(new Dimension(40,30));
		panelPasswd.add(labelPasswd);
		panelPasswd.add(passwd);
		JButton inscrire = new JButton("Inscription");
		//inscrire.setEnabled(false);
		inscrire.addActionListener(this);
		inscrire.setPreferredSize(new Dimension(170, 30));
		panelBouton.add(inscrire);//, BorderLayout.EAST);

		container.add(panelNom);
		container.add(panelPrenom);
		container.add(panelPasswd);
		container.add(panelBouton);
		add(container);
		if(!(nom.getText().equals(""))&&!(prenom.getText().equals(""))&&!(passwd.getText().equals(""))){
			System.out.println("dans if");
			inscrire.setEnabled(true);
		}
	}
	// fonction permettant de créer un Utilisateur
	public void creerUtilisateur(){
		Utilisateur user = new Utilisateur(nom.getText(), prenom.getText(), String.valueOf(passwd.getPassword()));
		user.enregisterUtilisateur(user);
	}
	public void actionPerformed(ActionEvent e){
		
		if(e.getActionCommand().equals("Inscription")){
			creerUtilisateur();
			dispose();
			JOptionPane inf = new JOptionPane();
			inf.showMessageDialog(null, "Utilisateur créé avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
			FenetreUtilisateur fe = new FenetreUtilisateur();
			fe.setVisible(true);
		}
	}
	public static void main(String[] args) {
		Inscription ins = new Inscription();
		ins.setVisible(true);

	}
	
}