package travauxPratiques;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import UserDataAccess.Clients;
import produitsDataAccess.AbstractDao;
import produitsDataAccess.IDAO;

public class DetailSDAOIMPL extends AbstractDao implements IDAO<Operation> {

	@Override
	public Operation find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Operation obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Operation obj) {
		// TODO Auto-generated method stub
		
	}
	
	public void add(Compte obj) {
		PreparedStatement pst = null;
		String sql = "insert into compte(numero, nom, prenom) values (?,?,?)";
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, obj.getNumero());
			pst.setString(2, obj.getNom());
			pst.setString(3, obj.getPrenom());
			pst.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	@Override
	public List<Operation> findAll() {
		PreparedStatement pst = null;
		ResultSet resultats = null;
		String sql = "select * from operation";
		List<Operation> liste= new ArrayList<Operation>();
		try {
			pst = connection.prepareStatement(sql);
			resultats = pst.executeQuery();
			while(resultats.next()) {
				liste.add(new Operation(resultats.getLong(1), resultats.getString(3), resultats.getDouble(4),resultats.getDate(5)));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return liste;
	}

	@Override
	public List<Operation> findAll(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
