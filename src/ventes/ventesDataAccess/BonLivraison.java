package ventes.ventesDataAccess;

import java.sql.Date;


public class BonLivraison {
	private String numeroBL;
	private double total;
	private Date date;
	private String nomClients;
	
	public BonLivraison(String nomClients, Date date, double total, String numeroBL) {
		this.numeroBL = numeroBL;
		this.total = total;
		this.date = date;
		this.nomClients = nomClients;
	}
	public String getNumeroBL() {
		return numeroBL;
	}
	public void setNumeroBL(String numeroBL) {
		this.numeroBL = numeroBL;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getNomClients() {
		return nomClients;
	}
	public void setNomClients(String nomClients) {
		this.nomClients = nomClients;
	}
}
