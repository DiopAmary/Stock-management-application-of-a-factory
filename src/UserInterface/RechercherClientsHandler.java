package UserInterface;

import java.util.List;

import UserDataAccess.Clients;
import UserDataAccess.ClientsDAOIplementation;

public class RechercherClientsHandler {
	RechercherClientsWindow rechercherClientsWindow = null;
	public  RechercherClientsHandler(RechercherClientsWindow rechercherClientsWindow){
		this.rechercherClientsWindow = rechercherClientsWindow;
	}
	public void updateListeRechercheClientsWindow() {
		ClientsDAOIplementation idao = new ClientsDAOIplementation();
		List<Clients> list = idao.findAll(rechercherClientsWindow.nomField.getText());
		rechercherClientsWindow.observableList.addAll(list);
	}
}
