package springproject.product.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springproject.product.dao.IProductTypeDao;
import springproject.product.model.ProductType;

@Service("productTypeService")
public class ProductTypeService implements IProductTypeService {

	@Autowired
	private IProductTypeDao productTypeDao;

	@Override
	public ProductType selectByID(int id) {
		System.out.println("ProductTypeService : selectByID");
		return productTypeDao.selectByID(id);
	}

	@Override
	public List<ProductType> selectAll() {
		System.out.println("ProductTypeService : selectAll");
		return productTypeDao.selectAll();
	}

	@Override
	public ProductType insert(ProductType productType) {
		return productTypeDao.insert(productType);
	}

}
