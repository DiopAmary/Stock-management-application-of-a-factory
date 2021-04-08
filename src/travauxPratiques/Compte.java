package travauxPratiques;

import java.util.ArrayList;
import java.util.List;

public class Compte {
	private long id;
	private String numero;
	private String nom;
	List<Operation> operations = new ArrayList<Operation>();
	public Compte(long id, String numero, String nom, String prenom) {
		this.id = id;
		this.numero = numero;
		this.nom = nom;
		this.prenom = prenom;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	private String prenom;
}
