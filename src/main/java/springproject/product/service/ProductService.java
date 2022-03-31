package springproject.product.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springproject.product.dao.IProductDao;
import springproject.product.model.Product;

@Transactional
@Service //("productService")
public class ProductService implements IProductService{

	@Autowired
	private IProductDao productDao;
	
	

	public ProductService() {
	}

	
	public Product selectByID(int id) {
		System.out.println("ProductService : selectByID");
		return productDao.selectByID(id);
	}
	
	
	public List<Product> selectAll() {
		System.out.println("ProductService : selectAll");
		return productDao.selectAll();
	}

	
	public Product insert(Product product) {
		return productDao.insert(product);
	}

	
	public Product update(int id, String name, String type, int stock, double cost, double price, String Description) {
		System.out.println("ProductService: " + "update: ");
		return productDao.update(id, name, type, stock, cost, price, Description);
	}

	
	public boolean delete(int id) {
		return productDao.delete(id);
	}
}
