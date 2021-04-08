package UserInterface;

import UserDataAccess.Clients;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RechercherClientsResultats {
	ObservableList<Clients> observableList = null;
	public RechercherClientsResultats(ObservableList<Clients> observableList) {
		this.observableList = observableList;
		
		updateColumn();
		addNodeToPane();
		initWindow();
		addStyleToNodes();
		addEvent();
		window.show();
	}
	
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
	Button quitterButton = new Button("Quitter");
	
	private void initWindow() {
		window.setScene(scene);
		window.setWidth(1050);
		window.setHeight(615);
		window.setTitle("Liste des Clients");
	}
	
	private void addEvent() {
		quitterButton.setOnAction(event->{window.close();});
	}
	
	private void addStyleToNodes() {
		scene.getStylesheets().add("css/styles.css");
		titleLabel.getStyleClass().add("titleLabelStyle");
		titleLabel.setMinWidth(window.getWidth());
		clientsTableView.getStyleClass().add("cell");
		clientsTableView.setMinHeight(500);
		quitterButton.getStyleClass().add("btn-danger");
		root.setSpacing(5);
	}
	
	private void addNodeToPane() {
		clientsTableView.getColumns().addAll(idColumn, nomColumn, prenomColumn, telephoneColumn, emailColumn, adressColumn);
		root.getChildren().add(titleLabel);
		root.getChildren().add(clientsTableView);
		root.getChildren().add(quitterButton);
		clientsTableView.setItems(observableList);
	}
	
	private void updateColumn() {
		idColumn.setCellValueFactory(new PropertyValueFactory<Clients, Integer>("id"));
		idColumn.setPrefWidth(50);
		nomColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("nom"));
		nomColumn.setPrefWidth(150);
		prenomColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("prenom"));
		prenomColumn.setPrefWidth(150);
		telephoneColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("telephone"));
		telephoneColumn.setPrefWidth(200);
		emailColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("email"));
		emailColumn.setPrefWidth(200);
		adressColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("adresse"));
		adressColumn.setPrefWidth(300);
	}
}
