package application;

import produitsDataAccess.Produits;
import produitsDataAccess.ProduitsDAOImplementation;

public class addProduitsHandler {
	ProduitsDAOImplementation idao = new ProduitsDAOImplementation();
	FormProduitsWindow formProduitsWindow = null;
	public addProduitsHandler(FormProduitsWindow formProduitsWindow) {
		this.formProduitsWindow = formProduitsWindow;
	}
	
	public void addClick() {
		String designation = formProduitsWindow.desigantionField.getText();
		double prixAchat = Double.valueOf(formProduitsWindow.prixAchatField.getText());
		double prixVente = Double.valueOf(formProduitsWindow.prixVenTeField.getText());
		String categorie = formProduitsWindow.categorieField.getText();
		Produits produits = new Produits(0, designation, prixAchat, prixVente, categorie);
		idao.create(produits);
	}
	public void editClick(Produits produits) {
		String designation = formProduitsWindow.desigantionField.getText();
		double prixAchat = Double.valueOf(formProduitsWindow.prixAchatField.getText());
		double prixVente = Double.valueOf(formProduitsWindow.prixVenTeField.getText());
		String categorie = formProduitsWindow.categorieField.getText();
		idao.update(produits, designation, prixAchat, prixVente, categorie);
	}
}
