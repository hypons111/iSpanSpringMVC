package springproject.product.dao;

import java.io.File;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springproject.product.model.Product;

@Transactional
@Repository //("productDao")
public class ProductDao implements IProductDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	

	public ProductDao() {
		
	}

	
	public Product selectByID(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Product.class, id);
	}

	
	public List<Product> selectAll() {
		System.out.println("ProductDao : selectAll");
		Session session = sessionFactory.openSession();
		Query<Product> query = session.createQuery("from Product", Product.class);
		List<Product> list = query.list();
		session.close();
		return list;
	}

	
	public Product insert(Product product) {
		System.out.println("ProductDao: insert: " + product);
		Session session = sessionFactory.getCurrentSession();
		Product result = session.get(Product.class, product.getProduct_ID());
		if (result == null) {
			session.save(product);
			session.close();
			return product;
		}
		session.close();
		return null;
	}

	
	public Product update(int id, String name, String type, int stock, double cost, double price, String Description) {
		System.out.println("ProductDao : update : " + id + ", " + name + ", " + type + ", " + stock + ", " + cost + ", "
				+ price + ", " + Description);
		Session session = sessionFactory.getCurrentSession();
		Product result = session.get(Product.class, id);
		if (result != null) {
			result.setProduct_Name(name);
			result.setProduct_Type(type);
			result.setProduct_Stock(stock);
			result.setProduct_Cost(cost);
			result.setProduct_Price(price);
			result.setProduct_Description(Description);
		}
		return result;
	}

	
	public boolean delete(int id) {
		System.out.println("ProductDao: delete: " + id);
		Session session = sessionFactory.getCurrentSession();
		Product result = session.get(Product.class, id);
		if (result != null) {
			session.delete(result);
			File image = new File("C:/DataSource/workspace/iSpanSpring/src/main/webapp/admin/product/images/"
					+ result.getProduct_Image());
			image.delete();
			return true;
		}
		return false;
	}
}