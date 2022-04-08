package springproject.product.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springproject.product.model.Product;
import springproject.product.model.ProductType;

@Transactional
@Repository
public class ProductTypeDao implements IProductTypeDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public ProductTypeDao() {}
	
	public List<ProductType> selectAll() {
		System.out.println("ProductTypeDao: selectAll");
		Session session = sessionFactory.openSession();
		Query<ProductType> result = session.createQuery("from ProductType", ProductType.class);
		List<ProductType> list = result.list();
		session.close();
		return list;
	}
	
	public ProductType selectByID(int id) {
		System.out.println("ProductTypeDao: selectByID: " + id);
		Session session = sessionFactory.getCurrentSession();
		return session.get(ProductType.class, id);
	}


	
	public ProductType insert(ProductType productType) {
		System.out.println("ProductTypeDao: insert: " + productType);
		Session session = sessionFactory.getCurrentSession();
		ProductType productTypeResult = session.get(ProductType.class, productType.getProductType_ID());
		if (productTypeResult == null) {
			session.save(productType);
			return productType;
		}
		return null;
	}




}
