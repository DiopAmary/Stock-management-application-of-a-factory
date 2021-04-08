package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import produitsDataAccess.Produits;

public class ProduitsByCategorieWindow {
	String categorie = null;
	Stage window = new Stage();
	VBox root = new VBox();
	Scene scene = new Scene(root);
	TableView<Produits> prodduitsTableView= new TableView<Produits>();
	TableColumn<Produits, Integer> idColumn = new TableColumn<Produits, Integer>("Id");
	TableColumn<Produits, String> designationColumn = new TableColumn<Produits, String>("Désignation");
	TableColumn<Produits, Double> prixAchaTColumn = new TableColumn<Produits, Double>("Prix d'achat");
	TableColumn<Produits, Double> prixVenteColumn = new TableColumn<Produits, Double>("Prix de vente");
	ObservableList<Produits> observableList = FXCollections.observableArrayList();
	private void addStyleToNodes() {
		scene.getStylesheets().add("css/styles.css");
		prodduitsTableView.getStyleClass().add("cell");
		prodduitsTableView.setMinHeight(500);
	}
	
	private void addNodeToPane() {
		prodduitsTableView.getColumns().addAll(idColumn, designationColumn, prixAchaTColumn, prixVenteColumn);
		root.getChildren().add(prodduitsTableView);
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
	
	
	private void initWindow() {
		window.setScene(scene);
		window.setWidth(700);
		window.setHeight(600);
		window.setTitle("La catégorie "+categorie);
	}
	
	ProduitsByCategorieWindow(String categorie){
		this.categorie = categorie;
		CategorieHandler categorieHandler = new CategorieHandler(this, categorie);
		updateColumn();
		addNodeToPane();
		initWindow();
		addStyleToNodes();
		categorieHandler.updateListeProduitByCategorie();
		window.show();
	}
}
