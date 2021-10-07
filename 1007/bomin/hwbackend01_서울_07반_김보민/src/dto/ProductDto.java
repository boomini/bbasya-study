package dto;

public class ProductDto {
	String product;
	int price;
	String desc;
	
	public ProductDto() {
		super();
	}

	public ProductDto(String product, int price, String desc) {
		super();
		this.product = product;
		this.price = price;
		this.desc = desc;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
