package springproject.product.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "product")
@Component //("product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "product_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int product_ID;

	@Column(name = "product_Type")
	private String product_Type;

	@Column(name = "product_Name")
	private String product_Name;

	@Column(name = "product_Stock")
	private int product_Stock;

	@Column(name = "product_Cost")
	private double product_Cost;

	@Column(name = "product_Price")
	private double product_Price;

	@Column(name = "product_Image")
	private String product_Image;

	@Column(name = "product_Description")
	private String product_Description;


	public Product() {
	}

	public Product(int id, String name, String type, int stock, double cost, double price, String image,
			String description) {
		this.product_ID = id;
		this.product_Type = type;
		this.product_Name = name;
		this.product_Stock = stock;
		this.product_Cost = cost;
		this.product_Price = price;
		this.product_Image = image;
		this.product_Description = description;
	}

	public int getProduct_ID() {
		return product_ID;
	}

	public void setProduct_ID(int product_ID) {
		this.product_ID = product_ID;
	}

	public String getProduct_Type() {
		return product_Type;
	}

	public void setProduct_Type(String product_Type) {
		this.product_Type = product_Type;
	}

	public String getProduct_Name() {
		return product_Name;
	}

	public void setProduct_Name(String product_Name) {
		this.product_Name = product_Name;
	}

	public int getProduct_Stock() {
		return product_Stock;
	}

	public void setProduct_Stock(int product_Stock) {
		this.product_Stock = product_Stock;
	}

	public double getProduct_Cost() {
		return product_Cost;
	}

	public void setProduct_Cost(double product_Cost) {
		this.product_Cost = product_Cost;
	}

	public double getProduct_Price() {
		return product_Price;
	}

	public void setProduct_Price(double product_Price) {
		this.product_Price = product_Price;
	}

	public String getProduct_Image() {
		return product_Image;
	}

	public void setProduct_Image(String product_Image) {
		this.product_Image = product_Image;
	}

	public String getProduct_Description() {
		return product_Description;
	}

	public void setProduct_Description(String product_Description) {
		this.product_Description = product_Description;
	}

}
