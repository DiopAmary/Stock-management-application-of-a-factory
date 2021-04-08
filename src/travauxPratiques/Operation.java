package travauxPratiques;

import java.sql.Date;


public class Operation {
	private long id;
	private Date date;
	private double montant;
	private String type;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Operation(long id, String type, double montant, Date date) {
		this.id = id;
		this.date = date;
		this.montant = montant;
		this.type = type;
	}
	
}
