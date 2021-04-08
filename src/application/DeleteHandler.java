package application;

import produitsDataAccess.Produits;
import produitsDataAccess.ProduitsDAOImplementation;

public class DeleteHandler {
	SupprimerProduit supprimerProduit = null;
	public  DeleteHandler(SupprimerProduit supprimerProduit){
		this.supprimerProduit = supprimerProduit;
	}
	public  DeleteHandler(){
	}
	public void updateListeProduitByDelete() {
		ProduitsDAOImplementation idao = new ProduitsDAOImplementation();
		Produits produits = idao.find(Integer.valueOf(supprimerProduit.idField.getText()));
		idao.delete(produits);
	}
	public void updateListeProduitByDelete(int id) {
		ProduitsDAOImplementation idao = new ProduitsDAOImplementation();
		Produits produits = idao.find(id);
		idao.delete(produits);
	}
}
