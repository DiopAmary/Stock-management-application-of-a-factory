package produitsDataAccess;

import java.util.List;

public interface IDAO<T> {
	public T find(int id);
	public void create(T obj);
	public void delete(T obj);
	public List<T> findAll();
	public List<T> findAll(String key);
}
