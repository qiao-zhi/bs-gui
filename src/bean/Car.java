package bean;

public class Car {

	private Integer id;

	private String type;
	private String make;
	private String model;
	private int seatingcapacity;
	private int luggagecapacity;
	private float mileage;
	private String status;
	private Double price;

	public static String[] intKeys = new String[] { "seatingcapacity", "luggagecapacity" };
	public static String[] floatKeys = new String[] { "mileage" };
	public static String[] doubleKeys = new String[] { "price" };

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getSeatingcapacity() {
		return seatingcapacity;
	}

	public void setSeatingcapacity(int seatingcapacity) {
		this.seatingcapacity = seatingcapacity;
	}

	public int getLuggagecapacity() {
		return luggagecapacity;
	}

	public void setLuggagecapacity(int luggagecapacity) {
		this.luggagecapacity = luggagecapacity;
	}

	public float getMileage() {
		return mileage;
	}

	public void setMileage(float mileage) {
		this.mileage = mileage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
