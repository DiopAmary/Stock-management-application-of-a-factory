package application;
	
import UserInterface.FormClientsWindow;
import UserInterface.ListeClientsWindow;
import UserInterface.RechercherClientsWindow;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import ventes.ventesInterface.ListeBonLivraisonWindow;


public class Main extends Application {
	private BorderPane root = new BorderPane();
	private Scene scene = new Scene(root,600,500);
	
	MenuItem nouveauItemProduits = new MenuItem("Nouveau");
	MenuItem listeItemProduits = new MenuItem("Liste");
	MenuItem categoriesItemProduits = new MenuItem("Catégories");
	MenuItem rechercheItemProduits = new MenuItem("Recherche");
	
	MenuItem nouveauClientsItem = new MenuItem("Nouveau");
	MenuItem ListeClientsItem = new MenuItem("Liste");
	MenuItem rechercherClientsItem = new MenuItem("Rechercher");
	
	MenuItem nouveauBonLivraison = new MenuItem("Nouveau");
	MenuItem ListeBonLivraison = new MenuItem("Liste");
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage window) throws Exception {
			createMenu();
			addEvent();
			addStyleToNode();
			window.setScene(scene);
			window.setTitle("dioppp___ store management");
			window.getIcons().add(new Image("file:css/logo.png"));// pas de resultat actu
			window.show();
	}
	
	private void createMenu() {
		MenuBar menuBar = new MenuBar();
		Menu produitsMenu = new Menu("Produits");
		Menu clientsMenu = new Menu("Clients");
		Menu ventesMenu = new Menu("Ventes");
		clientsMenu.getItems().addAll(nouveauClientsItem, ListeClientsItem, rechercherClientsItem);
		ventesMenu.getItems().addAll(nouveauBonLivraison, ListeBonLivraison);
		produitsMenu.getItems().addAll(nouveauItemProduits, listeItemProduits, categoriesItemProduits, rechercheItemProduits);
		menuBar.getMenus().addAll(produitsMenu, clientsMenu, ventesMenu);
		root.setTop(menuBar);
	}
	
	private void addEvent(){
		nouveauItemProduits.setOnAction(event->{new FormProduitsWindow();});
		listeItemProduits.setOnAction(event->{new ListeProduitsWindow();});
		categoriesItemProduits.setOnAction(event->{new CategoriesProduitsWindow();});
		rechercheItemProduits.setOnAction(event->{new RechercheProduitsWindow();});
		
		nouveauClientsItem.setOnAction(event->{new FormClientsWindow();});
		ListeClientsItem.setOnAction(event->{new ListeClientsWindow();});
		rechercherClientsItem.setOnAction(event->{new RechercherClientsWindow();});
		
		ListeBonLivraison.setOnAction(event->{new ListeBonLivraisonWindow();});
		nouveauBonLivraison.setOnAction(event->{new ListeClientsWindow();});
	}
	
	private void addStyleToNode() {
		scene.getStylesheets().add("css/styles.css");
		root.getStyleClass().add("mainWindow");
	}
}
