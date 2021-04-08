package ventes.ventesDataAccess;

import produitsDataAccess.Produits;
import produitsDataAccess.ProduitsDAOImplementation;

public class LigneCommande {
	private int id;
	private String numeroBL;
	private int quantite;
	private double sousTotal;
	private Produits produits;
	private String produitsCategorie;
	private String produitsDesignation;
	private double produitsPrixAchat;
	
	
	public String getnumeroBL() {
		return numeroBL;
	}
	public void setnumeroBL(String numeroBL) {
		this.numeroBL = numeroBL;
	}
	public String getProduitsCategorie() {
		return produitsCategorie;
	}
	public void setProduitsCategorie(String produitsCategorie) {
		this.produitsCategorie = produitsCategorie;
	}
	public String getProduitsDesignation() {
		return produitsDesignation;
	}
	public void setProduitsDesignation(String produitsDesignation) {
		this.produitsDesignation = produitsDesignation;
	}
	public double getProduitsPrixAchat() {
		return produitsPrixAchat;
	}
	public void setProduitsPrixAchat(double produitsPrixAchat) {
		this.produitsPrixAchat = produitsPrixAchat;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public double getSousTotal() {
		return sousTotal;
	}
	public void setSousTotal(double sousTotal) {
		this.sousTotal = sousTotal;
	}
	public Produits getProduits() {
		return produits;
	}
	public void setProduits(Produits produits) {
		this.produits = produits;
	}
	public LigneCommande(int id, int quantite,String designation) {
		Produits produits = null;
		ProduitsDAOImplementation idao = new ProduitsDAOImplementation();
		produits = idao.getProduits(designation);
		this.id = id;
		this.quantite = quantite;
		this.sousTotal = produits.getPrixVente() * quantite;
		this.produits = produits;
		this.produitsCategorie = produits.getcategorie();
		this.produitsDesignation = produits.getDesignation();
		this.produitsPrixAchat = produits.getPrixVente();
	}
	public LigneCommande(int id, String categorie, String designation, double prix, int qte, double sousTotal,  String numeroBL) {
		this.numeroBL = numeroBL;
		this.produitsCategorie = categorie;
		this.produitsDesignation = designation;
		this.produitsPrixAchat = prix;
		this.quantite = qte;
		this.sousTotal = sousTotal;
	}
	
}

