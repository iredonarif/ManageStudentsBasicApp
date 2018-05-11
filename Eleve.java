import java.util.*;
import java.io.*;

public class Eleve extends Personne{
	int num;
	String sexe;

	public Eleve(int numEleve, String nom, String prenom, String sexeEleve){
		super(nom, prenom);
		this.num = numEleve;
		this.sexe = sexeEleve;
	}
	public void setNumero(int numEleve){
		this.num = numEleve;
	}
	public int getNumero(){
		return this.num;
	}
	public void setSexe(String sexeEleve){
		this.sexe = sexeEleve;
	}
	public String getSexe(){
		return this.sexe;
	}
	public void enregisterEleve(Eleve objet){
		PrintWriter outputStream = null;
		try{
			outputStream = new PrintWriter(new FileOutputStream("eleve.txt", true));
		}
		catch(FileNotFoundException e){
			System.out.println("Problème d'ouvertue du fichier");
		}
		outputStream.println(objet.getNumero()+" "+objet.getNom()+" "+objet.getPrenom()+" "+objet.getSexe());
		outputStream.close();
	}
}