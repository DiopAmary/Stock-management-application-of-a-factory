package paiementsInterface;

import java.util.List;

import paimentsDataAccess.Paiements;
import paimentsDataAccess.PaiementsDAOImplementation;

public class PaiementsHandler {
	private PaiementsWindow paiementsWindow = null;
	private DetailsPaiements detailsPaiements = null;

	public PaiementsHandler(DetailsPaiements detailsPaiements) {
		this.detailsPaiements = detailsPaiements;
	}

	public PaiementsHandler(PaiementsWindow paiementsWindow) {
		this.paiementsWindow = paiementsWindow;
	}
	
	public void addClick(){
		String numeroBL = paiementsWindow.numeroBlValueLabel.getText();
		PaiementsDAOImplementation idao = new PaiementsDAOImplementation();
		idao.delete(numeroBL);
		for(Paiements paiements:paiementsWindow.observableList) {
			idao.create(paiements, numeroBL);
		}
	}
	public void updatePaiementsListe() {
		String numeroBL = paiementsWindow.numeroBlValueLabel.getText();
		PaiementsDAOImplementation idao = new PaiementsDAOImplementation();
		List<Paiements> list = idao.findAll(numeroBL);
		paiementsWindow.observableList.addAll(list); 
	}
	
	public void updatePaiementsListeDetails() {
		String numeroBL = detailsPaiements.numeroBLValueLabel.getText();
		PaiementsDAOImplementation idao = new PaiementsDAOImplementation();
		List<Paiements> list = idao.findAll(numeroBL);
		detailsPaiements.observableList.addAll(list); 
	}
}
