package application;

import produitsDataAccess.ProduitsDAOImplementation;
import ventes.ventesInterface.VentesWindow;

import java.util.List;
import produitsDataAccess.Produits;

public class ListeProduitsHandler {
	VentesWindow ventesWindow = null;
	ListeProduitsWindow listeProduitsWindow = null;
	public  ListeProduitsHandler(ListeProduitsWindow listeProduitsWindow){
		this.listeProduitsWindow = listeProduitsWindow;
	}
	public  ListeProduitsHandler(VentesWindow ventesWindow){
		this.ventesWindow = ventesWindow;
	}
	
	public void venteWindowUpdateListeProduits() {
		ProduitsDAOImplementation idao = new ProduitsDAOImplementation();
		List<Produits> list = idao.findAll();
		ventesWindow.observableList.addAll(list);
	}
	
	public void updateListeProduitsWindow() {
		ProduitsDAOImplementation idao = new ProduitsDAOImplementation();
		List<Produits> list = idao.findAll();
		listeProduitsWindow.observableList.addAll(list);
	}
	
}