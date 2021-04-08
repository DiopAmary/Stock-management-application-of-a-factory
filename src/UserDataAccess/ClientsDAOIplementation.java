package UserDataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import produitsDataAccess.AbstractDao;
import produitsDataAccess.IDAO;
import produitsDataAccess.Produits;

public class ClientsDAOIplementation extends AbstractDao implements IDAO<Clients>{

	public void update(Clients obj, String nom, String prenom, String telephone, String email, String adresse) {
		PreparedStatement pst = null;
		int id = obj.getId();
		String sql = "update clients set nom = ?, prenom = ?, téléphone = ?, email = ?, adresse = ? where id = ?";
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, nom);
			pst.setString(2, prenom);
			pst.setString(3, telephone);
			pst.setString(4, email);
			pst.setString(5, adresse);
			pst.setInt(6, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public Clients find(int id) {
		Clients clients;
		PreparedStatement pst = null;
		ResultSet resultats = null;
		String sql = "select * from clients where id = ?";
		try {
			pst = connection.prepareStatement(sql);
			pst.setInt(1, id);
			resultats = pst.executeQuery();
			if(resultats.next()){
				clients = new Clients(resultats.getInt(1), resultats.getString(2), resultats.getString(3), resultats.getString(4), resultats.getString(5), resultats.getString(6));
				return clients;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());;
		}
		return null;
	}

	@Override
	public void create(Clients obj) {
		PreparedStatement pst = null;
		String sql = "insert into clients(nom, prenom, téléphone, email, adresse) values (?,?,?,?,?)";
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, obj.getNom());
			pst.setString(2, obj.getPrenom());
			pst.setString(3, obj.getTelephone());
			pst.setString(4, obj.getEmail());
			pst.setString(5, obj.getAdresse());
			pst.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void delete(Clients obj) {
		PreparedStatement pst = null;
		int id  = obj.getId();
		String sql = "delete from clients where id = ?";
		try {
			pst = connection.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public List<Clients> findAll() {
		PreparedStatement pst = null;
		ResultSet resultats = null;
		String sql = "select * from clients";
		List<Clients> liste= new ArrayList<Clients>();
		try {
			pst = connection.prepareStatement(sql);
			resultats = pst.executeQuery();
			while(resultats.next()) {
				liste.add(new Clients(resultats.getInt(1), resultats.getString(2), resultats.getString(3), resultats.getString(4), resultats.getString(5), resultats.getString(6)));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return liste;
	}

	@Override
	public List<Clients> findAll(String key) {
		List<Clients> liste = new ArrayList<Clients>();
		PreparedStatement pst = null;
		ResultSet resultats = null;
		String sql = "select * from clients where nom like ?";
		try {
			pst = connection.prepareStatement(sql);			
			pst.setString(1, key+"%");
			resultats = pst.executeQuery();
			while(resultats.next()) {
				liste.add(new Clients(resultats.getInt(1), resultats.getString(2), resultats.getString(3), resultats.getString(4), resultats.getString(5), resultats.getString(6)));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return liste;
	}

}
