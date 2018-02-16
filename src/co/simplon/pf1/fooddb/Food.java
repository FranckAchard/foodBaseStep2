package co.simplon.pf1.fooddb;

public class Food {
	private String name;
	private String category;
	private int energetic;
	private int protein;
	private int carbohydrate;
	private int lipid;
	
	// get & set methods
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public int getEnergetic() {
		return energetic;
	}


	public void setEnergetic(int energetic) {
		this.energetic = energetic;
	}


	public int getProtein() {
		return protein;
	}


	public void setProtein(int protein) {
		this.protein = protein;
	}


	public int getCarbohydrate() {
		return carbohydrate;
	}


	public void setCarbohydrate(int carbohydrate) {
		this.carbohydrate = carbohydrate;
	}


	public int getLipid() {
		return lipid;
	}


	public void setLipid(int lipid) {
		this.lipid = lipid;
	}


	
	@Override
	public String toString() {
		return "Food [name=" + name + ", category=" + category + ", energetic=" + energetic + ", protein=" + protein
				+ ", carbohydrate=" + carbohydrate + ", lipid=" + lipid + "]";
	}

	// constructors
	
	public Food() {
		this.name = "<unkown>";
		this.category = "<unkown>";
		this.energetic = 0;
		this.protein = 0;
		this.carbohydrate = 0;
		this.lipid = 0;
	}


	public Food(String name, String category, int energetic, int protein, int carbohydrate, int lipid) {
		this.name = name;
		this.category = category;
		this.energetic = energetic;
		this.protein = protein;
		this.carbohydrate = carbohydrate;
		this.lipid = lipid;
	}
	
	

}
