package ventes.ventesInterface;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ventes.ventesDataAccess.BonLivraison;
import ventes.ventesDataAccess.LigneCommande;
import ventes.ventesDataAccess.LigneCommandeDAOImplementation;;

public class ListeBonLivraisonWindow {
	ListeBLHandler handler = new ListeBLHandler(this);
	Stage window = new Stage();
	VBox root = new VBox();
	Scene scene = new Scene(root);
	
	Label titleLabel = new Label("Liste des bons de livraison");
	TableView<BonLivraison> bonLivraisonTableView= new TableView<BonLivraison>();
	TableColumn<BonLivraison, String> clientColumn = new TableColumn<BonLivraison, String>("Client");
	TableColumn<BonLivraison, String> numeroColumn = new TableColumn<BonLivraison, String>("Numéro");
	TableColumn<BonLivraison, Double> totalColumn = new TableColumn<BonLivraison, Double>("Total");
	TableColumn<BonLivraison, Date> dateColumn = new TableColumn<BonLivraison, Date>("Date");
	Button quitterButton = new Button("Quitter");

	ObservableList<BonLivraison> observableList = FXCollections.observableArrayList();
	
	private void initWindow() {
		window.setScene(scene);
		window.setWidth(500);
		window.setHeight(515);
		window.setTitle("Bon de Livraison");
	}
	
	private void addStyleToNodes() {
		scene.getStylesheets().add("css/styles.css");
		titleLabel.getStyleClass().add("titleLabelStyle");
		titleLabel.setMinWidth(window.getWidth());
		bonLivraisonTableView.getStyleClass().add("cell");
		bonLivraisonTableView.setMinHeight(400);
		quitterButton.getStyleClass().add("btn-danger");
		root.setSpacing(5);
	}
	
	private void addNodeToPane() {
		bonLivraisonTableView.getColumns().addAll(clientColumn, numeroColumn, totalColumn, dateColumn);
		root.getChildren().add(titleLabel);
		root.getChildren().add(bonLivraisonTableView);
		root.getChildren().add(quitterButton);
		bonLivraisonTableView.setItems(observableList);
	}
	
	private void updateColumn() {
		clientColumn.setCellValueFactory(new PropertyValueFactory<BonLivraison, String>("nomClients"));
		clientColumn.setPrefWidth(200);
		numeroColumn.setCellValueFactory(new PropertyValueFactory<BonLivraison, String>("numeroBL"));
		numeroColumn.setPrefWidth(100);
		totalColumn.setCellValueFactory(new PropertyValueFactory<BonLivraison, Double>("total"));
		totalColumn.setPrefWidth(100);
		dateColumn.setCellValueFactory(new PropertyValueFactory<BonLivraison, Date>("date"));
		dateColumn.setPrefWidth(100);
	}
	
	private void addEvent() {
		quitterButton.setOnAction(event->{window.close();});
		bonLivraisonTableView.setOnMouseClicked(event->{
				BonLivraison bonLivraison=bonLivraisonTableView.getItems().get(bonLivraisonTableView.getSelectionModel().getSelectedIndex());
				List<LigneCommande> list = new ArrayList<LigneCommande>();
				LigneCommandeDAOImplementation idao = new LigneCommandeDAOImplementation();
				list = idao.findAll(bonLivraison.getNumeroBL());
				new DetailsBonLivraison(bonLivraison, list);
				window.close();
		});
		
	}
	
	public ListeBonLivraisonWindow(){
		updateColumn();
		addNodeToPane();
		initWindow();
		addStyleToNodes();
		handler.updateListeBonLivraison();
		addEvent();
		window.show();
	}

	public ListeBonLivraisonWindow(String client){
		updateColumn();
		addNodeToPane();
		initWindow();
		addStyleToNodes();
		handler.updateListeBonLivraison(client);
		addEvent();
		window.show();
	}
}
