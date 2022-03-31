package springproject.product.service;

import java.util.List;

import springproject.product.model.ProductType;

public interface IProductTypeService {

	ProductType selectByID(int id);

	List<ProductType> selectAll();

	ProductType insert(ProductType productType);

}