package pl.smolo.icse.model;

public class SamochodRow {

	private String imageHref;

	private String carName;

	private String carHref;

	private String shortDesc;

	private String year;

	private String mileage;

	private String price;
	
	private String source;

	public SamochodRow()
	{

	}

	public SamochodRow(String imageHref, String carName, String carHref, String shortDesc,
	        String year, String mileage, String price, String source)
	{
		this.imageHref = imageHref;
		this.carName = carName;
		this.carHref = carHref;
		this.shortDesc = shortDesc;
		this.year = year;
		this.mileage = mileage;
		this.price = price;
		this.source =  source;
	}

	public String getImageHref() {
		return imageHref;
	}

	public void setImageHref(String imageHref) {
		this.imageHref = imageHref;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getCarHref() {
		return carHref;
	}

	public void setCarHref(String carHref) {
		this.carHref = carHref;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String toString()
	{
		return "Name " + carName + "\n" +
		        "ImgSrc " + imageHref + "\n" +
		        "Car href " + carHref + "\n" +
		        "Description: " + shortDesc + "\n" +
		        "Year " + year + "\n" +
		        "Mileage " + mileage + "\n" +
		        "Price " + price;
	}

	public String getSource() {
	    return source;
    }

	public void setSource(String source) {
	    this.source = source;
    }

}
