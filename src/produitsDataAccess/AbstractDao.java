package produitsDataAccess;

import java.sql.Connection;

public abstract class AbstractDao {
	protected Connection connection = Connexion.getConnexion(); 
}
