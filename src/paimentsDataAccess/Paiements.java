package paimentsDataAccess;

import java.sql.Date;

public class Paiements {
	private int numero;
	private double montant;
	private Date date;
	private String type;
	private String numeroCheque;
	private String proprietaire;
	private String banque;
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNumeroCheque() {
		return numeroCheque;
	}
	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}
	public String getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}
	public String getBanque() {
		return banque;
	}
	public void setBanque(String banque) {
		this.banque = banque;
	}
	public Paiements(int numero, double montant, Date date, String type, String numeroCheque, String banque, String proprietaire) {
		this.numero = numero;
		this.montant = montant;
		this.date = date;
		this.type = type;
		this.numeroCheque = numeroCheque;
		this.proprietaire = proprietaire;
		this.banque = banque;
	}
	@Override
	public String toString() {
		return "Paiements [numero=" + numero + ", montant=" + montant + ", date=" + date + ", type=" + type
				+ ", numeroCheque=" + numeroCheque + ", proprietaire=" + proprietaire + ", banque=" + banque + "]";
	}
	
	
}
