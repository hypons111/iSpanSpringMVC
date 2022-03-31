package springproject.product.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springproject.product.model.ProductType;

@Repository("productTypeDao")
public class ProductTypeDao implements IProductTypeDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
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

	@Override
	public ProductType selectByID(int id) {
		System.out.println("ProductTypeDao: selectByID: " + id);
		Session session = sessionFactory.getCurrentSession();
		return session.get(ProductType.class, id);
	}

	@Override
	public List<ProductType> selectAll() {
		System.out.println("ProductTypeDao: selectAll");
		Session session = sessionFactory.getCurrentSession();
		Query<ProductType> result = session.createQuery("from ProductType", ProductType.class);
		return result.list();
	}


}
