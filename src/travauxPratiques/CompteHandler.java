package travauxPratiques;

import java.util.List;

public class CompteHandler {
	CreerCompte creerCompte = null;
	public CompteHandler(CreerCompte creerCompte) {
		this.creerCompte = creerCompte;
	}
	
	public void add() {
		DetailSDAOIMPL idao = new DetailSDAOIMPL();
		int id = 0;
		String nom = creerCompte.nomField.getText();
		String prenom = creerCompte.prenomField.getText();
		String numero = creerCompte.numeroField.getText();
		Compte compte = new Compte(id, numero, nom, prenom);
		idao.add(compte);
	}

}
