package ventes.ventesDataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import produitsDataAccess.AbstractDao;
import produitsDataAccess.IDAO;

public class LigneCommandeDAOImplementation extends AbstractDao{

	public void create(LigneCommande obj) {
		PreparedStatement pst = null;
		String sql = "insert into ligne_commande(catégorie, désignation, prix, qte, sous_total, numeroBL ) values (?,?,?,?,?,?)";
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, obj.getProduitsCategorie());
			pst.setString(2, obj.getProduitsDesignation());
			pst.setDouble(3, obj.getProduitsPrixAchat());
			pst.setInt(4, obj.getQuantite());
			pst.setDouble(5, obj.getSousTotal());
			pst.setString(6, obj.getnumeroBL());
			pst.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void delete(LigneCommande obj) {
		PreparedStatement pst = null;
		String numerBL  = obj.getnumeroBL();
		String sql = "delete from ligne_commande where numeroBL = ?";
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, numerBL);
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<LigneCommande> findAll() {
		PreparedStatement pst = null;
		ResultSet resultats = null;
		String sql = "select * from ligne_commande";
		List<LigneCommande> liste= new ArrayList<LigneCommande>();
		try {
			pst = connection.prepareStatement(sql);
			resultats = pst.executeQuery();
			while(resultats.next()) {
				liste.add(new LigneCommande(resultats.getInt(1),resultats.getString(2),resultats.getString(3), resultats.getDouble(4), resultats.getInt(5), resultats.getDouble(6), resultats.getString(7)));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return liste;
	}

	public List<LigneCommande> findAll(String key) {
		PreparedStatement pst = null;
		ResultSet resultats = null;
		String sql = "select * from ligne_commande where numeroBL = ?";
		List<LigneCommande> liste= new ArrayList<LigneCommande>();
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, key);
			resultats = pst.executeQuery();
			while(resultats.next()) {
				liste.add(new LigneCommande(resultats.getInt(1),resultats.getString(2),resultats.getString(3), resultats.getDouble(4), resultats.getInt(5), resultats.getDouble(6), resultats.getString(7)));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return liste;
	}

}
