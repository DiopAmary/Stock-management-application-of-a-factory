package application;

import java.util.List;

import produitsDataAccess.Produits;
import produitsDataAccess.ProduitsDAOImplementation;

public class CategorieHandler {
	ProduitsByCategorieWindow produitsByCategorieWindow = null;
	String categorie =null;
	public  CategorieHandler(ProduitsByCategorieWindow produitsByCategorieWindow, String cat){
		this.produitsByCategorieWindow = produitsByCategorieWindow;
		this.categorie = cat;
	}
	
	public void updateListeProduitByCategorie() {
		ProduitsDAOImplementation idao = new ProduitsDAOImplementation();
		List<Produits> list = idao.getByCategorie(categorie);
		produitsByCategorieWindow.observableList.addAll(list);
	}
}