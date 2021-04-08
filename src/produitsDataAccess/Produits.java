package produitsDataAccess;

public class Produits {
	private int id;
	private String designation;
	private double prixAchat;
	private double prixVente;
	private String categorie;
	
	public Produits(int id, String designation, double prixAchat, double prixVente, String categorie) {
		this.id = id;
		this.designation = designation;
		this.prixAchat = prixAchat;
		this.prixVente = prixVente;
		this.categorie = categorie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(double prixAchat) {
		this.prixAchat = prixAchat;
	}

	public double getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(double prixVente) {
		this.prixVente = prixVente;
	}

	public String getcategorie() {
		return categorie;
	}

	public void setcategorie(String categorie) {
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		return "Produits [id=" + id + ", designation=" + designation + ", prixAchat=" + prixAchat + ", prixVente="
				+ prixVente + ", categorie=" + categorie + "]";
	}
	
}
