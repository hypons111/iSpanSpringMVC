package springproject.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;

import springproject.product.model.Product;
import springproject.product.service.IProductService;
import springproject.product.service.ProductService;




@WebServlet("/admin/product/index")
public class AdminProductIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WebApplicationContext context;

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("Product Index Servlet");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		IProductService productService = context.getBean("productService", ProductService.class);
	
		PrintWriter out = response.getWriter();
		List<Product> productResultList = productService.selectAll();

		Gson gson = new Gson();
		String jsonString = gson.toJson(productResultList);
		out.print(jsonString);
		out.close();
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