package springproject.product.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import springproject.product.service.IProductService;
import springproject.product.service.ProductService;



@WebServlet("/admin/product/delete")
public class AdminProductDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WebApplicationContext context;
	
	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("Product Delete Servlet");
		
		IProductService productService = context.getBean("productService", ProductService.class);
	
		int id = Integer.parseInt(request.getParameter("Product_ID"));
		productService.delete(id);	
		
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
