package ventes.ventesInterface;

import java.util.ArrayList;
import java.util.List;

import ventes.ventesDataAccess.BonLivraison;
import ventes.ventesDataAccess.BonLivraisonDAOImplementation;
import ventes.ventesDataAccess.LigneCommande;
import ventes.ventesDataAccess.LigneCommandeDAOImplementation;

public class VentesHandler {
	private VentesWindow ventesWindow = null;
	private DetailsBonLivraison detailsBonLivraison = null;
	

	public VentesHandler(DetailsBonLivraison detailsBonLivraison) {
		this.detailsBonLivraison = detailsBonLivraison;
	}
	
	public VentesHandler(VentesWindow ventesWindow) {
		this.ventesWindow = ventesWindow;
	}
	
	
	public void addClick(BonLivraison bonLivraison, List<LigneCommande> list) {
		BonLivraisonDAOImplementation bonLivraisonDAO = new BonLivraisonDAOImplementation();
		LigneCommandeDAOImplementation ligneCommandeDAO = new LigneCommandeDAOImplementation();
		bonLivraisonDAO.create(bonLivraison);
		for(LigneCommande ligneCommande : list) {
			System.out.println(ligneCommande);
			ligneCommandeDAO.create(ligneCommande);
		}
	}
	
	public void supprimerBL(String numeroBL) {
		BonLivraisonDAOImplementation bonLivraisonDAOImplementation = new BonLivraisonDAOImplementation();
		LigneCommandeDAOImplementation ligneCommandeDAOImplementation = new LigneCommandeDAOImplementation();
		BonLivraison bl = bonLivraisonDAOImplementation.find(numeroBL);
		bonLivraisonDAOImplementation.delete(bl);
		List<LigneCommande> list = new ArrayList<LigneCommande>();
		list = ligneCommandeDAOImplementation.findAll(numeroBL);
		for(LigneCommande ligneCommande : list) {
			ligneCommandeDAOImplementation.delete(ligneCommande);
		}
	}
	
	public void update(BonLivraison bl, List<LigneCommande> list, String numeroBL) {
		supprimerBL(numeroBL);
		addClick(bl, list);
	}
	
}
