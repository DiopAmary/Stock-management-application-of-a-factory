package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CategoriesProduitsWindow {
	Stage window = new Stage();
	VBox root = new VBox();
	Scene scene = new Scene(root);
	Label titleLabel = new Label("Listes des catégories");
	Button telephoneButton = new Button("Téléphone");
	Button ordinateurButton = new Button("Ordinateur");
	Button televiseurButton = new Button("Téléviseur");
	Button tabletteButton = new Button("Tablette");
	Button casqueButton = new Button("Casque");
	Button quitterButton = new Button("Quitter");
	
	private void addNodeToPane() {
		root.getChildren().addAll(titleLabel, telephoneButton, ordinateurButton, televiseurButton, tabletteButton, casqueButton, quitterButton);
	}
	
	private void addStyleToNode() {
		scene.getStylesheets().add("css/styles.css");
		titleLabel.getStyleClass().add("titleLabelStyle");
		titleLabel.setMinWidth(window.getWidth());
		root.setSpacing(15);
		quitterButton.getStyleClass().add("btn-danger");
		telephoneButton.getStyleClass().add("buttons");
		telephoneButton.setMinWidth(window.getWidth());
		telephoneButton.setMinHeight(50);
		
		ordinateurButton.getStyleClass().add("buttons");
		ordinateurButton.setMinWidth(window.getWidth());
		ordinateurButton.setMinHeight(50);
		
		televiseurButton.getStyleClass().add("buttons");
		televiseurButton.setMinWidth(window.getWidth());
		televiseurButton.setMinHeight(50);
		
		tabletteButton.getStyleClass().add("buttons");
		tabletteButton.setMinWidth(window.getWidth());
		tabletteButton.setMinHeight(50);
		
		casqueButton.getStyleClass().add("buttons");
		casqueButton.setMinWidth(window.getWidth());
		casqueButton.setMinHeight(50);
		
	}
	
	private void addEvent() {
		quitterButton.setOnAction(event->{window.close();});
		telephoneButton.setOnAction(event->{new ProduitsByCategorieWindow("Téléphone");});
		ordinateurButton.setOnAction(event->{new ProduitsByCategorieWindow("Ordinateur");});
		televiseurButton.setOnAction(event->{new ProduitsByCategorieWindow("Téléviseur");});
		tabletteButton.setOnAction(event->{new ProduitsByCategorieWindow("Tablette");});
		casqueButton.setOnAction(event->{new ProduitsByCategorieWindow("Casque");});
	}
	
	private void initWindow() {
		window.setTitle("Liste des catégories de Produits");
		window.setWidth(400);
		window.setHeight(450);
		window.setScene(scene);
	}
	public CategoriesProduitsWindow() {
		addNodeToPane();
		initWindow();
		addStyleToNode();
		addEvent();
		window.show();
	}
}
