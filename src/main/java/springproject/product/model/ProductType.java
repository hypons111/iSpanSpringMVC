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
@Table(name = "ProductType")
public class ProductType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "productType_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productType_ID;

	@Column(name = "productType_Name")
	private String productType_Name;

	public ProductType() {
	}

	public ProductType(String name) {
		this.productType_Name = name;
	}

	public int getProductType_ID() {
		return productType_ID;
	}

	public void setProductType_ID(int productType_ID) {
		this.productType_ID = productType_ID;
	}

	public String getProductType_Name() {
		return productType_Name;
	}

	public void setPT_Name(String productType_Name) {
		this.productType_Name = productType_Name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
