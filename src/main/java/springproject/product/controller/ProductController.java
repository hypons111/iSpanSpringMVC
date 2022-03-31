package springproject.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import springproject.product.model.Product;
import springproject.product.service.IProductService;

@Controller
@RequestMapping(path = "/admin/product")
public class ProductController {

	@Autowired
	private IProductService productService;

	@RequestMapping(path = "/productmain.controller", method = RequestMethod.GET)
	public String processMainAction() {
		return "product/index";
	}

	@RequestMapping(path = "/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Product> processJsonAction() {
		List<Product> productResultList = productService.selectAll();
		return productResultList;
	}



}
