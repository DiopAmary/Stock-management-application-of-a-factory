package UserInterface;

import java.util.ArrayList;
import java.util.List;

import UserDataAccess.Clients;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ventes.ventesInterface.ListeBLHandler;
import ventes.ventesInterface.ListeBonLivraisonWindow;
import ventes.ventesInterface.VentesWindow;

public class DisplayClients {
	SupprimerClientsHandler handler = new SupprimerClientsHandler();
	VBox root = new VBox();
	Stage window = new Stage();
	Scene scene = new Scene(root);
	Label titleLabel = new Label("Details du Client");
	Button editerButton = new Button("Editer");
	Button deleteButton = new Button("Supprimer");
	Button quitterButton = new Button("Quitter");
	Button nouveauBlButton = new Button("Nouveau BL");
	Button listeBLButton = new Button("Liste BL");
	
	HBox idBox = new HBox();
	HBox nomBox = new HBox();
	HBox prenom = new HBox();
	HBox telephoneBox = new HBox();
	HBox emailBox = new HBox();
	HBox adresseBox = new HBox();
	HBox buttonBox = new HBox();
	Label idLabel = new Label("Id\t\t\t:\t");
	Label nomLabel = new Label("Nom\t\t\t:\t");
	Label prenomLabel = new Label("Prenom\t\t:\t");
	Label telephoneLabel = new Label("Téléphone\t:\t");
	Label emailLabel = new Label("Email\t\t:\t");
	Label adresseLabel = new Label("Adresse\t\t:\t");
	
	private List<Label> getClientsLabels(Clients clents){
		List<Label> list = new ArrayList<Label>();
		Label idValueLabel = new Label(clents.getId()+"");
		Label nomValueLabel = new Label(clents.getNom());
		Label prenomValueLabel = new Label(clents.getPrenom());
		Label telephoneValueLabel = new Label(clents.getTelephone());
		Label emailValueLabel = new Label(clents.getEmail());
		Label adresseValueLabel = new Label(clents.getAdresse());
		list.add(idValueLabel);
		list.add(nomValueLabel);
		list.add(prenomValueLabel);
		list.add(telephoneValueLabel);
		list.add(emailValueLabel);
		list.add(adresseValueLabel);
		return list;
	}
	private void addToNode(Clients clents) {
		List<Label> list= getClientsLabels(clents);
		idBox.getChildren().addAll(idLabel, list.get(0));
		nomBox.getChildren().addAll(nomLabel, list.get(1));
		prenom.getChildren().addAll(prenomLabel, list.get(2));
		telephoneBox.getChildren().addAll(telephoneLabel, list.get(3));
		emailBox.getChildren().addAll(emailLabel, list.get(4));
		adresseBox.getChildren().addAll(adresseLabel, list.get(5));
		buttonBox.getChildren().addAll(editerButton, deleteButton, quitterButton, nouveauBlButton, listeBLButton);
		root.getChildren().addAll(titleLabel, idBox, nomBox, prenom, telephoneBox, emailBox, adresseBox, buttonBox);
		addStyleToNode(list);
	}
	private void addStyleToNode(List<Label> list) {
		scene.getStylesheets().add("css/styles.css");
		titleLabel.getStyleClass().add("titleLabelStyle");
		titleLabel.setMinWidth(467);
		titleLabel.setMinHeight(50);
		idLabel.getStyleClass().add("labelForm");
		idLabel.setMinHeight(30);
		nomLabel.getStyleClass().add("labelForm");
		nomLabel.setMinHeight(30);
		prenomLabel.getStyleClass().add("labelForm");
		prenomLabel.setMinHeight(30);
		telephoneLabel.getStyleClass().add("labelForm");
		telephoneLabel.setMinHeight(30);
		emailLabel.getStyleClass().add("labelForm");
		emailLabel.setMinHeight(30);
		adresseLabel.getStyleClass().add("labelForm");
		adresseLabel.setMinHeight(30);
		for(Label label :  list) {
			label.getStyleClass().add("labelForm");
			label.setMinHeight(30);
		}
		idBox.setMinHeight(40);
		nomBox.setMinHeight(40);
		prenom.setMinHeight(40);
		telephoneBox.setMinHeight(40);
		emailBox.setMinHeight(40);
		adresseBox.setMinHeight(40);
		adresseBox.setMinHeight(40);
		nouveauBlButton.getStyleClass().add("buttons");
		quitterButton.getStyleClass().add("btn-primary");
		deleteButton.getStyleClass().add("btn-danger");
		editerButton.getStyleClass().add("buttons");
		listeBLButton.getStyleClass().add("btn-primary");
		root.setSpacing(5);
		buttonBox.setSpacing(10);
	}
	private void addEvent(Clients clients) {
		quitterButton.setOnAction(event->{
			window.close();
			new ListeClientsWindow();
		});
		editerButton.setOnAction(event->{
			new FormClientsWindow(clients);
			window.close();
		});
		deleteButton.setOnAction(event->{
			handler.updateListeClientsByDelete(clients.getId());
			new ListeClientsWindow();
			window.close();
		});
		nouveauBlButton.setOnAction(event->{
			new VentesWindow(clients);
			window.close();
		});
		listeBLButton.setOnAction(event->{
			String nomString = clients.getNom()+" "+clients.getPrenom();
			System.out.println(nomString);
			new ListeBonLivraisonWindow(nomString);
		});
	}
	private void initWindow() {
		window.setScene(scene);
		window.setWidth(467);
		window.setHeight(400);
		window.setTitle("Details du Client");
		window.show();
	}
	public DisplayClients(Clients clients) {
		addToNode(clients);
		addEvent(clients);
		initWindow();
	}
}
