package ventes.ventesInterface;

import java.util.ArrayList;
import java.util.List;

import ventes.ventesDataAccess.BonLivraison;
import ventes.ventesDataAccess.BonLivraisonDAOImplementation;

public class ListeBLHandler {
	private ListeBonLivraisonWindow listeBonLivraisonWindow= null;

	public ListeBLHandler(ListeBonLivraisonWindow listeBonLivraisonWindow) {
		this.listeBonLivraisonWindow = listeBonLivraisonWindow;
	}
	
	public void updateListeBonLivraison() {
		List<BonLivraison> list = new ArrayList<BonLivraison>(); 
		BonLivraisonDAOImplementation bonLivraisonDAOImplementation = new BonLivraisonDAOImplementation();
		list = bonLivraisonDAOImplementation.findAll();
		listeBonLivraisonWindow.observableList.addAll(list);
	}
	public void updateListeBonLivraison(String client) {
		List<BonLivraison> list = new ArrayList<BonLivraison>(); 
		BonLivraisonDAOImplementation bonLivraisonDAOImplementation = new BonLivraisonDAOImplementation();
		list = bonLivraisonDAOImplementation.findAll(client);
		listeBonLivraisonWindow.observableList.addAll(list);
	}
}
