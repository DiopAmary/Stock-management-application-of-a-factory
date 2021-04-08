package UserInterface;

import UserDataAccess.Clients;
import UserDataAccess.ClientsDAOIplementation;

public class SupprimerClientsHandler {
	public void updateListeClientsByDelete(int id) {
		ClientsDAOIplementation idao = new ClientsDAOIplementation();
		Clients clients = idao.find(id);
		idao.delete(clients);
	}
}
