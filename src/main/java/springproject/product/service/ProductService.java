package springproject.product.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import springproject.product.dao.IProductDao;
import springproject.product.model.Product;

@Transactional
@Service
public class ProductService implements IProductService {

	@Autowired
	private IProductDao productDao;

	public ProductService() {
	}

	public List<Product> selectAll() {
		System.out.println("ProductService : selectAll");
		return productDao.selectAll();
	}

	public Product selectByID(int id) {
		System.out.println("ProductService : selectByID");
		return productDao.selectByID(id);
	}

	public Product insert(Product product) {
		return productDao.insert(product);
	}

	public Product update(Product product) {
		return productDao.update(product);
	}
//	public Product update(int id, String name, String type, int stock, double cost, double price, String image) {
//		System.out.println("ProductService: " + "update: ");
//		return productDao.update(id, name, type, stock, cost, price, image);
//	}

	public boolean delete(int id) {
		return productDao.delete(id);
	}

	public void insertImage(HttpServletRequest request, Product product, MultipartFile imageFile)
			throws IllegalStateException, IOException {
		productDao.insertImage(request, product, imageFile);

	}
}
