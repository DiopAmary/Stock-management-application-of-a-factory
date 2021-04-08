package UserInterface;

import java.util.List;

import UserDataAccess.Clients;
import UserDataAccess.ClientsDAOIplementation;

public class ListeClientsHandler {
	ListeClientsWindow listeClientsWindow = null;
	public ListeClientsHandler(ListeClientsWindow listeClientsWindow) {
		this.listeClientsWindow = listeClientsWindow;
	}
	
	public void updateListeClientsWindow() {
		ClientsDAOIplementation idao = new ClientsDAOIplementation();
		List<Clients> list = idao.findAll();
		listeClientsWindow.observableList.addAll(list);
	}
}
