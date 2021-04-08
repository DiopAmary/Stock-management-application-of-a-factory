package application;

import java.util.List;

import produitsDataAccess.Produits;
import produitsDataAccess.ProduitsDAOImplementation;

public class RechercheHandler {
	RechercheProduitsWindow rechercheProduitsWindow = null;
	public  RechercheHandler(RechercheProduitsWindow rechercheProduitsWindow){
		this.rechercheProduitsWindow = rechercheProduitsWindow;
	}
	public void updateListeRechercheProduitsWindow() {
		ProduitsDAOImplementation idao = new ProduitsDAOImplementation();
		List<Produits> list = idao.findAll(rechercheProduitsWindow.desigantionField.getText());
		rechercheProduitsWindow.observableList.addAll(list);
	}
}
