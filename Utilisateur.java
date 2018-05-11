import java.util.*;
import java.io.*;

public class Utilisateur extends Personne{

	String motDePasse;

	public Utilisateur(String nomUtilisateur, String prenom, String motDePasse){
		super(nomUtilisateur, prenom);
		this.motDePasse = motDePasse;
	}
	public Utilisateur(){

	}
	/*public Utilisateur(String nomUtilisateur){
		super(nomUtilisateur);
	}*/
	public void setMotDePasse(String motDePasse){
		this.motDePasse = motDePasse;
	}
	public String getMotDePasse(){
		return this.motDePasse;
	}
	public void enregisterUtilisateur(Utilisateur objet){
		PrintWriter outputStream = null;
		try{
			outputStream = new PrintWriter(new FileOutputStream("utilisateur.txt", true));
		}
		catch(FileNotFoundException e){
			System.out.println("Problème d'ouvertue du fichier");
		}
		outputStream.println(objet.getNom()+" "+objet.getPrenom()+" "+objet.getMotDePasse());
		outputStream.close();
	}
}