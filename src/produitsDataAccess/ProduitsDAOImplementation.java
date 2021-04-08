package produitsDataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitsDAOImplementation extends AbstractDao implements IDAO<Produits>{

	@Override
	public Produits find(int id) {
		Produits produits;
		PreparedStatement pst = null;
		ResultSet resultats = null;
		String sql = "select * from produits where id = ?";
		try {
			pst = connection.prepareStatement(sql);
			pst.setInt(1, id);
			resultats = pst.executeQuery();
			if(resultats.next()){
				produits = new Produits(resultats.getInt(1), resultats.getString(2), resultats.getDouble(3), resultats.getDouble(4), resultats.getString(5));
				return produits;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());;
		}
		return null;
	}

	@Override
	public void create(Produits obj) {
		PreparedStatement pst = null;
		String sql = "insert into produits(désignation, prixAchat, prixVente, catégorie) values (?,?,?,?)";
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, obj.getDesignation());
			pst.setDouble(2, obj.getPrixAchat());
			pst.setDouble(3, obj.getPrixVente());
			pst.setString(4, obj.getcategorie());
			pst.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void delete(Produits obj) {
		PreparedStatement pst = null;
		int id  = obj.getId();
		String sql = "delete from produits where id = ?";
		try {
			pst = connection.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public List<Produits> findAll() {
		PreparedStatement pst = null;
		ResultSet resultats = null;
		String sql = "select * from produits";
		List<Produits> liste= new ArrayList<Produits>();
		try {
			pst = connection.prepareStatement(sql);
			resultats = pst.executeQuery();
			while(resultats.next()) {
				liste.add(new Produits(resultats.getInt(1),resultats.getString(2), resultats.getDouble(3),resultats.getDouble(4), resultats.getString(5)));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return liste;
	}

	@Override
	public List<Produits> findAll(String key) {
		List<Produits> liste = new ArrayList<Produits>();
		PreparedStatement pst = null;
		ResultSet resultats = null;
		String sql = "select * from produits where désignation like ?";
		try {
			pst = connection.prepareStatement(sql);			
			pst.setString(1, key+"%");
			resultats = pst.executeQuery();
			while(resultats.next()) {
				liste.add(new Produits(resultats.getInt(1),resultats.getString(2), resultats.getDouble(3),resultats.getDouble(4), resultats.getString(5)));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return liste;
	}

	public void update(Produits obj, String designation, double prixAcaht, double prixVente, String categorie) {
		PreparedStatement pst = null;
		int id = obj.getId();
		String sql = "update produits set prixVente = ?, prixAchat = ?, désignation = ?, catégorie = ? where id = ?";
		try {
			pst = connection.prepareStatement(sql);
			pst.setDouble(1, prixVente);
			pst.setDouble(2, prixAcaht);
			pst.setString(3, designation);
			pst.setString(4, categorie);
			pst.setInt(5, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<Produits> getByCategorie(String categorie) {
		List<Produits> liste = new ArrayList<Produits>();
		PreparedStatement pst = null;
		ResultSet resultats = null;
		String sql = "select * from produits where catégorie like ?";
		try {
			pst = connection.prepareStatement(sql);			
			pst.setString(1, categorie);
			resultats = pst.executeQuery();
			while(resultats.next()) {
				liste.add(new Produits(resultats.getInt(1),resultats.getString(2), resultats.getDouble(3),resultats.getDouble(4), resultats.getString(5)));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return liste;
	}
	public Produits getProduits(String key) {
		Produits produits = null;
		PreparedStatement pst = null;
		ResultSet resultats = null;
		String sql = "select * from produits where désignation = ?";
		try {
			pst = connection.prepareStatement(sql);			
			pst.setString(1, key);
			resultats = pst.executeQuery();
			if(resultats.next()) {
				produits = new Produits(resultats.getInt(1),resultats.getString(2), resultats.getDouble(3),resultats.getDouble(4), resultats.getString(5));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return produits;
	}
}
