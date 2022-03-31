package springproject.product.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import springproject.product.service.IProductService;
import springproject.product.service.IProductTypeService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
@WebServlet("/admin/product/insert")
public class AdminProductInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WebApplicationContext context;

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("Product Insert Servlet");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		IProductService productService = context.getBean("productService", ProductService.class);
		IProductTypeService productTypeService = context.getBean("productTypeService", ProductTypeService.class);

		String name = request.getParameter("name").trim();
		String type = request.getParameter("type").trim().substring(0, 1).toUpperCase()
				+ request.getParameter("type").trim().toLowerCase().substring(1);
		int stock = Integer.parseInt(request.getParameter("stock"));
		double cost = Math.ceil(Double.parseDouble(request.getParameter("cost")) * 10.0) / 10.0;
		double price = Math.ceil(Double.parseDouble(request.getParameter("price")) * 10.0) / 10.0;
		String description = "";

		// 判斷是否要新增產品種類
		Set<String> productTypeNameResultSet = new HashSet<>();
		for (ProductType productType : productTypeService.selectAll()) {
			productTypeNameResultSet.add(productType.getProductType_Name());
		}
		if (productTypeNameResultSet.add(type)) {
			productTypeService.insert(new ProductType(type));
		}

		// 先用假資料建立 product, 有 id 後才能把 id 用作 image 名稱, 因為 id 是 sql 建立資料時自動產生
		// productResult 是連接到資料庫的某一筆資料
		Product productResult = productService
				.insert(new Product(0, name, type, stock, cost, price, "temp", description));
		// productResult.productResult.getP_ID() 指定修改該筆資料的 image
		String image = productResult.getProduct_ID() + ".jpg";
		productResult.setProduct_Image(image);

		// 儲存圖片
		try {
			for (Part part : request.getParts()) {
				part.write(
						"C:/DataSource/workspace/HibernateProjectTest/src/main/webapp/admin/images/product/" + image);
			}
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}

		response.sendRedirect("index.jsp");
	}

	public void init() throws ServletException {
		ServletContext application = getServletContext();
		context = WebApplicationContextUtils.getWebApplicationContext(application);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}
}
