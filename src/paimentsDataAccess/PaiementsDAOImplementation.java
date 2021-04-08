package paimentsDataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import produitsDataAccess.AbstractDao;
import ventes.ventesDataAccess.LigneCommande;

public class PaiementsDAOImplementation extends AbstractDao{
	public void create(Paiements obj, String numeroBl) {
		PreparedStatement pst = null;
		String sql = "insert into paiements(numero_BL, numero, montant, date, type, numero_cheque, banque, propriétaire) values (?,?,?,?,?,?,?,?)";
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, numeroBl);
			pst.setInt(2, obj.getNumero());
			pst.setDouble(3, obj.getMontant());
			pst.setDate(4, obj.getDate());
			pst.setString(5, obj.getType());
			pst.setString(6, obj.getNumeroCheque());
			pst.setString(7, obj.getBanque());
			pst.setString(8, obj.getProprietaire());
			pst.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public List<Paiements> findAll(String key) {
		PreparedStatement pst = null;
		ResultSet resultats = null;
		String sql = "select * from paiements where numero_BL = ?";
		List<Paiements> liste= new ArrayList<Paiements>();
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, key);
			resultats = pst.executeQuery();
			while(resultats.next()) {
				liste.add(new Paiements(resultats.getInt(3), resultats.getDouble(4), resultats.getDate(5), resultats.getString(6),resultats.getString(7), resultats.getString(8), resultats.getString(9)));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return liste;
	}
	
	public void delete(String numeroBL) {
		PreparedStatement pst = null;
		String sql = "delete from paiements where numero_BL = ?";
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, numeroBL);
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
