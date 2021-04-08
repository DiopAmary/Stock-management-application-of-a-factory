package UserInterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import produitsDataAccess.Produits;
import UserDataAccess.Clients;
import application.DisplayProduits;


public class ListeClientsWindow {
	ListeClientsHandler handler = new ListeClientsHandler(this);
	Stage window = new Stage();
	VBox root = new VBox();
	Scene scene = new Scene(root);
	Label titleLabel = new Label("Liste des Clients");
	TableView<Clients> clientsTableView= new TableView<Clients>();
	TableColumn<Clients, Integer> idColumn = new TableColumn<Clients, Integer>("Id");
	TableColumn<Clients, String> nomColumn = new TableColumn<Clients, String>("Nom");
	TableColumn<Clients, String> prenomColumn= new TableColumn<Clients, String>("prenom");
	TableColumn<Clients, String> telephoneColumn = new TableColumn<Clients, String>("Téléphone");
	TableColumn<Clients, String> emailColumn = new TableColumn<Clients, String>("Email");
	TableColumn<Clients, String> adressColumn = new TableColumn<Clients, String>("Adresse");
	HBox buttonBox = new HBox();
	Button quitterButton = new Button("Quitter");
	Button NouveauButton = new Button("Nouveau client");
	
	ObservableList<Clients> observableList = FXCollections.observableArrayList();
	
	private void initWindow() {
		window.setScene(scene);
		window.setWidth(650);
		window.setHeight(615);
		window.setTitle("Liste des Clients");
	}
	
	private void addEvent() {
		quitterButton.setOnAction(event->{window.close();});
		clientsTableView.setOnMouseClicked(event->{
			Clients clients=clientsTableView.getItems().get(clientsTableView.getSelectionModel().getSelectedIndex());
			new DisplayClients(clients);
			window.close();
		});
		NouveauButton.setOnAction(event->{
			new FormClientsWindow();
			window.close();
		});
	}
	
	private void addStyleToNodes() {
		scene.getStylesheets().add("css/styles.css");
		titleLabel.getStyleClass().add("titleLabelStyle");
		titleLabel.setMinWidth(window.getWidth());
		clientsTableView.getStyleClass().add("cell");
		clientsTableView.setMinHeight(500);
		quitterButton.getStyleClass().add("btn-danger");
		NouveauButton.getStyleClass().add("btn-primary");
		buttonBox.setSpacing(20);
		root.setSpacing(5);
	}
	
	private void addNodeToPane() {
		clientsTableView.getColumns().addAll(idColumn, nomColumn, prenomColumn, telephoneColumn, emailColumn, adressColumn);
		root.getChildren().add(titleLabel);
		root.getChildren().add(clientsTableView);
		buttonBox.getChildren().addAll(NouveauButton, quitterButton);
		root.getChildren().add(buttonBox);
		clientsTableView.setItems(observableList);
	}
	
	private void updateColumn() {
		idColumn.setCellValueFactory(new PropertyValueFactory<Clients, Integer>("id"));
		idColumn.setPrefWidth(50);
		nomColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("nom"));
		nomColumn.setPrefWidth(100);
		prenomColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("prenom"));
		prenomColumn.setPrefWidth(100);
		telephoneColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("telephone"));
		telephoneColumn.setPrefWidth(120);
		emailColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("email"));
		emailColumn.setPrefWidth(120);
		adressColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("adresse"));
		adressColumn.setPrefWidth(160);
	}
	
	
	public ListeClientsWindow(){
		updateColumn();
		addNodeToPane();
		initWindow();
		addStyleToNodes();
		handler.updateListeClientsWindow();
		addEvent();
		window.show();
	}
}
