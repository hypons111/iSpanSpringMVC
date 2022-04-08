package springproject.product.dao;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import springproject.product.model.Product;

@Transactional
@Repository
public class ProductDao implements IProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	public ProductDao() {
	}

	public List<Product> selectAll() {
		System.out.println("ProductDao : selectAll");
		Session session = sessionFactory.getCurrentSession();
		Query<Product> result = session.createQuery("from Product", Product.class);
		List<Product> list = result.list();
		return list;
	}

	public Product selectByID(int id) {
		System.out.println("ProductDao: selectByID: " + id);
		Session session = sessionFactory.getCurrentSession();
		return session.get(Product.class, id);
	}

	public Product insert(Product product) {
		System.out.println("ProductDao: insert");
		Session session = sessionFactory.getCurrentSession();
		// 先用假資料建立 product, 有 id 後才能把 id 用作 image 名稱, 因為 id 是 sql 建立資料時自動產生
		session.save(product);
		// result 是連接到資料庫的某一筆資料
		Product result = session.get(Product.class, product.getProduct_ID());
		// 更改imageName
		result.setProduct_Image(product.getProduct_ID() + ".jpg");
		return result;
	}

	public Product update(Product product) {
		System.out.println("ProductDao: update : ");
		Session session = sessionFactory.getCurrentSession();
		Product result = session.get(Product.class, product.getProduct_ID());
		if (result != null) {
			result.setProduct_Name(product.getProduct_Name());
			result.setProduct_Type(product.getProduct_Type());
			result.setProduct_Stock(product.getProduct_Stock());
			result.setProduct_Cost(product.getProduct_Cost());
			result.setProduct_Price(product.getProduct_Price());
			result.setProduct_Image(product.getProduct_Image());
		}
		return result;
	}

	public boolean delete(int id) {
		System.out.println("ProductDao: delete: " + id);
		Session session = sessionFactory.getCurrentSession();
		Product result = session.get(Product.class, id);
		if (result != null) {
			session.delete(result);
			File image = new File("C:/DataSource/workspace/iSpanSpring/src/main/webapp/admin/product/images/"
					+ result.getProduct_Image());
			image.delete();
			return true;
		}
		return false;
	}

	public void insertImage(HttpServletRequest request, Product product, MultipartFile imageFile) throws IllegalStateException, IOException {
		// 把圖片傳到資料夾
		String saveTempFileDir = request.getSession().getServletContext().getRealPath("/") + "WEB-INF/resources/product/image/";
		File saveTempDirFile = new File(saveTempFileDir);
		saveTempDirFile.mkdirs();
		String saveFilePath = saveTempFileDir + product.getProduct_ID() + ".jpg";
		File saveFile = new File(saveFilePath);
		imageFile.transferTo(saveFile);
	}
}