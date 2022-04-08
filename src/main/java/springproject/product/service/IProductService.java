package springproject.product.service;

import java.util.List;

import springproject.product.model.Product;

public interface IProductService {

	Product selectByID(int id);

	List<Product> selectAll();

	Product insert(Product product);

	Product update(Product product);
//	Product update(int id, String name, String type, int stock, double cost, double price, String image);

	boolean delete(int id);

}