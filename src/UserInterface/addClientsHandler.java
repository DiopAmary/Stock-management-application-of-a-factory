package UserInterface;

import UserDataAccess.Clients;
import UserDataAccess.ClientsDAOIplementation;
import produitsDataAccess.Produits;

public class addClientsHandler {
	ClientsDAOIplementation idao = new ClientsDAOIplementation();
	FormClientsWindow formClientsWindow = null;
	public addClientsHandler(FormClientsWindow formClientsWindow) {
		this.formClientsWindow = formClientsWindow;
	}
	
	public void addClick() {
		Clients clients = getClients();
		idao.create(clients);
	}
	public void editClick(Clients clients) {
		String nom = formClientsWindow.nomField.getText();
		String prenom = formClientsWindow.prenomField.getText();
		String telephone = formClientsWindow.telephoneField.getText();
		String email = formClientsWindow.emailField.getText();
		String adresse = formClientsWindow.adressField.getText();
		idao.update(clients,nom, prenom, telephone, email, adresse);
	}
	public Clients getClients() {
		String nom = formClientsWindow.nomField.getText();
		String prenom = formClientsWindow.prenomField.getText();
		String telephone = formClientsWindow.telephoneField.getText();
		String email = formClientsWindow.emailField.getText();
		String adresse = formClientsWindow.adressField.getText();
		Clients clients = new Clients(0, nom, prenom, telephone, email, adresse);
		return clients;
	}
}
