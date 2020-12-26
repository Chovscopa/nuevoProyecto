package sa;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
	private static ProductDAO instance;
	private static List<Product> data = new ArrayList<>();
	
	static {
		data.add(new Product(1, "iPhone X", 999.99f));
		data.add(new Product(2, "XBOX 360", 329.50f));
	}
	
	private ProductDAO() {
		
	}
	
	public static ProductDAO getInstance() {
		if (instance == null) {
			instance = new ProductDAO();
		}
		
		return instance;				
	}
	
	public List<Product> listAll() {
		return new ArrayList<Product>(data);
	}
	
	public Product get(int id) {
		Product productToFind = new Product(id);
		int index = data.indexOf(productToFind);
		if (index >= 0) {
			return data.get(index);
		}
		return null;
	}
	
	public int add(Product product) {
		//int newId = data.size() + 1;
		int newId = (data.get(data.size()-1).getId())+1;
		product.setId(newId);
		data.add(product);
		
		return newId;
	}
	
	public boolean update(Product product) {
		int index = data.indexOf(product);
		if (index >= 0) {
			data.set(index, product);
			return true;
		}
		return false;
	}
	
	public boolean delete(int id) {
		Product productToFind = new Product(id);
		int index = data.indexOf(productToFind);
		if (index >= 0) {
			data.remove(index);
			return true;
		}
		
		return false;
	}
}
//AÑADIR curl -v -X POST -H "Content-Type: application/json" -d "{\"name\":\"MacPro\",\"price\":3000}" http://localhost:8080/REST_apiii/products
//LISTAR curl http://localhost:8080/REST_apiii/products/
//BUSCAR POR ID curl http://localhost:8080/REST_apiii/products/2
//MODIFICAR POR ID curl -v -X PUT -H "Content-Type: application/json" -d "{\"name\":\"iPad\",\"price\":888}" http://localhost:8080/REST_apiii/products/1
//ELIMINAR POR ID curl -v -X DELETE http://localhost:8080/REST_apiii/products/2