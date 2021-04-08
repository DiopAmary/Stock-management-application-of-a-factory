package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import produitsDataAccess.Produits;

public class ListeProduitsWindow {
	ListeProduitsHandler handler = new ListeProduitsHandler(this);
	Stage window = new Stage();
	VBox root = new VBox();
	Scene scene = new Scene(root);
	Label titleLabel = new Label("Liste des produits");
	TableView<Produits> prodduitsTableView= new TableView<Produits>();
	TableColumn<Produits, Integer> idColumn = new TableColumn<Produits, Integer>("Id");
	TableColumn<Produits, String> designationColumn = new TableColumn<Produits, String>("Désignation");
	TableColumn<Produits, Double> prixAchaTColumn = new TableColumn<Produits, Double>("Prix d'achat");
	TableColumn<Produits, Double> prixVenteColumn = new TableColumn<Produits, Double>("Prix de vente");
	Button quitterButton = new Button("Quitter");

	ObservableList<Produits> observableList = FXCollections.observableArrayList();
	
	private void initWindow() {
		window.setScene(scene);
		window.setWidth(700);
		window.setHeight(615);
		window.setTitle("Liste des produits");
	}
	
	private void addStyleToNodes() {
		scene.getStylesheets().add("css/styles.css");
		titleLabel.getStyleClass().add("titleLabelStyle");
		titleLabel.setMinWidth(window.getWidth());
		prodduitsTableView.getStyleClass().add("cell");
		prodduitsTableView.setMinHeight(500);
		quitterButton.getStyleClass().add("btn-danger");
		root.setSpacing(5);
	}
	
	private void addEvent() {
		quitterButton.setOnAction(event->{window.close();});
		prodduitsTableView.setOnMouseClicked(event->{
				Produits produit=prodduitsTableView.getItems().get(prodduitsTableView.getSelectionModel().getSelectedIndex());
				new DisplayProduits(produit);
				window.close();
		});
		
	}
	
	private void addNodeToPane() {
		prodduitsTableView.getColumns().addAll(idColumn, designationColumn, prixAchaTColumn, prixVenteColumn);
		root.getChildren().add(titleLabel);
		root.getChildren().add(prodduitsTableView);
		root.getChildren().add(quitterButton);
		prodduitsTableView.setItems(observableList);
	}
	
	private void updateColumn() {
		idColumn.setCellValueFactory(new PropertyValueFactory<Produits, Integer>("id"));
		idColumn.setPrefWidth(100);
		designationColumn.setCellValueFactory(new PropertyValueFactory<Produits, String>("designation"));
		designationColumn.setPrefWidth(300);
		prixAchaTColumn.setCellValueFactory(new PropertyValueFactory<Produits, Double>("prixAchat"));
		prixAchaTColumn.setPrefWidth(150);
		prixVenteColumn.setCellValueFactory(new PropertyValueFactory<Produits, Double>("prixVente"));
		prixVenteColumn.setPrefWidth(150);
	}
	
	
	public ListeProduitsWindow(){
		updateColumn();
		addNodeToPane();
		initWindow();
		addStyleToNodes();
		handler.updateListeProduitsWindow();
		addEvent();
		window.show();
	}
}
