package ventes.ventesDataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import produitsDataAccess.AbstractDao;
import produitsDataAccess.IDAO;
import produitsDataAccess.Produits;

public class BonLivraisonDAOImplementation extends AbstractDao {
	
	public BonLivraison find(String numeroBL) {
		BonLivraison bonLivraison;
		PreparedStatement pst = null;
		ResultSet resultats = null;
		String sql = "select * from bon_livraison where numeroBL = ?";
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, numeroBL);
			resultats = pst.executeQuery();
			if(resultats.next()){
				bonLivraison= new BonLivraison(resultats.getString(1),resultats.getDate(2), resultats.getDouble(3), resultats.getString(4));
				return bonLivraison;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());;
		}
		return null;
	}
	
	public void create(BonLivraison obj) {
		PreparedStatement pst = null;
		String sql = "insert into bon_livraison(nom_client, date, total, numeroBL ) values (?,?,?,?)";
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, obj.getNomClients());
			pst.setDate(2, obj.getDate());
			pst.setDouble(3, obj.getTotal());
			pst.setString(4, obj.getNumeroBL());
			pst.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	
	public void delete(BonLivraison obj) {
		PreparedStatement pst = null;
		String numeroBL  = obj.getNumeroBL();
		String sql = "delete from bon_livraison where numeroBL = ?";
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, numeroBL);
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	
	public List<BonLivraison> findAll() {
		PreparedStatement pst = null;
		ResultSet resultats = null;
		String sql = "select * from bon_livraison";
		List<BonLivraison> liste= new ArrayList<BonLivraison>();
		try {
			pst = connection.prepareStatement(sql);
			resultats = pst.executeQuery();
			while(resultats.next()) {
				liste.add(new BonLivraison(resultats.getString(1),resultats.getDate(2), resultats.getDouble(3), resultats.getString(4)));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return liste;
	}
	
	public List<BonLivraison> findAll(String key) {
		PreparedStatement pst = null;
		ResultSet resultats = null;
		String sql = "select * from bon_livraison where nom_client = ?";
		List<BonLivraison> liste= new ArrayList<BonLivraison>();
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, key);
			resultats = pst.executeQuery();
			while(resultats.next()) {
				liste.add(new BonLivraison(resultats.getString(1),resultats.getDate(2), resultats.getDouble(3), resultats.getString(4)));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return liste;
	}
}
