package banco.dao;

import java.util.List;

public interface Dao<T> {
	T getByKey(int id);
	List<T> getAll();
	void insert(T t);
	void delete(int id);
	void update(T t);
}
