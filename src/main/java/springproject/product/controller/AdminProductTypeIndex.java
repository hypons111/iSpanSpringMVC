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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;

import springproject.product.model.ProductType;
import springproject.product.service.IProductTypeService;
import springproject.product.service.ProductTypeService;




@WebServlet("/admin/product/type/index")
public class AdminProductTypeIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WebApplicationContext context;
	
	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("Product Type Index Servlet");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		IProductTypeService productTypeService = context.getBean("productTypeService", ProductTypeService.class);

		List<ProductType> productTypeResultList = productTypeService.selectAll();
		PrintWriter out = response.getWriter();

		Gson gson = new Gson();
		String jsonString = gson.toJson(productTypeResultList);
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