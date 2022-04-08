package springproject.product.dao;

import java.util.List;

import springproject.product.model.ProductType;

public interface IProductTypeDao {

	ProductType selectByID(int id);

	List<ProductType> selectAll();

	ProductType insert(ProductType productType);

}