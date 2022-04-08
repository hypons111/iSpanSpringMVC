package springproject.product.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import springproject.product.model.Product;
import springproject.product.model.ProductType;
import springproject.product.service.IProductService;
import springproject.product.service.IProductTypeService;

@Controller
// 定義根目錄為 "/admin/product"
// @RequestMapping(path = "/productindex", method = RequestMethod.GET) 的絶對路徑會變成 "/admin/product"
@RequestMapping(path = "/admin/product")
public class ProductController {

	@Autowired
	private IProductService productService;

	@Autowired
	private IProductTypeService productTypeService;

	@RequestMapping(path = "/productindex", method = RequestMethod.GET)
	public String processMainAction() {
		return "product/index";
	}

	@RequestMapping(path = "/productjson", method = RequestMethod.GET)
	@ResponseBody
	public List<Product> processJsonAction() {
		List<Product> productResultList = productService.selectAll();
		return productResultList;
	}

	@RequestMapping(path = "/producttypejson", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductType> processTypeJsonAction() {
		List<ProductType> productTypeResultList = productTypeService.selectAll();
		return productTypeResultList;
	}

	@RequestMapping(path = "insert", method = RequestMethod.GET)
	public String getInsert() {
		return "product/insertform";
	}

	@RequestMapping(path = "insert", method = RequestMethod.POST)
	public ModelAndView postInsert(@RequestParam("name") String name, @RequestParam("type") String type,
			@RequestParam("stock") int stock, @RequestParam("cost") double cost, @RequestParam("price") double price,
			@RequestParam("file") MultipartFile imageFile, HttpServletRequest request)
			throws IllegalStateException, IOException {

		// 判斷是否要新增產品種類
		Set<String> productTypeNameResultSet = new HashSet<>();
		for (ProductType productType : productTypeService.selectAll()) {
			productTypeNameResultSet.add(productType.getProductType_Name());
		}
		if (productTypeNameResultSet.add(type)) {
			productTypeService.insert(new ProductType(type));
		}

		// 新增產品
		Product product = productService.insert(new Product(0, name, type, stock, cost, price, "temp", "temp"));

		// 把圖片儲存到資料夾
		productService.insertImage(request, product, imageFile);

		return new ModelAndView("redirect:/admin/product/productindex");
	}

	@RequestMapping(path = "update", method = RequestMethod.GET)
	public String getUpdate() {
		return "product/updateform";
	}

	@RequestMapping(path = "update", method = RequestMethod.POST)
	public ModelAndView postUpdate(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("type") String type, @RequestParam("stock") int stock, @RequestParam("cost") double cost,
			@RequestParam("price") double price, @RequestParam("image") MultipartFile imageFile,
			HttpServletRequest request) throws ServletException, IllegalStateException, IOException {
		String imageName = id + ".jpg";

		// 更新產品
		Product product = productService.update(new Product(id, name, type, stock, cost, price, imageName, "temp"));

		// 判斷是否要更新圖片
		if (imageFile.getOriginalFilename() != "") {
			productService.insertImage(request, product, imageFile);
		}

		return new ModelAndView("redirect:/admin/product/productindex");
	}

	@RequestMapping(path = "delete", method = RequestMethod.GET)
	public ModelAndView getDelete(@RequestParam("Product_ID") int id) {
		productService.delete(id);
		return new ModelAndView("redirect:/admin/product/productindex");
	}
}
