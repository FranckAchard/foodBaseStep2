package co.simplon.pf1.fooddb;

import java.util.*;

public class Food {
	private String name;
	private String category;
	private double energetic;
	private double protein;
	private double carbohydrate;
	private double lipid;
	public static final int NB_FOOD_FEATURE=6;
	
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


	public double getEnergetic() {
		return energetic;
	}


	public void setEnergetic(double energetic) {
		this.energetic = energetic;
	}


	public double getProtein() {
		return protein;
	}


	public void setProtein(double protein) {
		this.protein = protein;
	}


	public double getCarbohydrate() {
		return carbohydrate;
	}


	public void setCarbohydrate(double carbohydrate) {
		this.carbohydrate = carbohydrate;
	}


	public double getLipid() {
		return lipid;
	}


	public void setLipid(double lipid) {
		this.lipid = lipid;
	}

	@Override
	public String toString() {
		return "Food [name=" + name + ", category=" + category + ", energetic=" + energetic + ", protein=" + protein
				+ ", carbohydrate=" + carbohydrate + ", lipid=" + lipid + "]";
	}

	// constructors
	// argument-less constructor 
	public Food() {
		this.name = "<unkown>";
		this.category = "<unkown>";
		this.energetic = 0.0;
		this.protein = 0.0;
		this.carbohydrate = 0.0;
		this.lipid = 0.0;
	}

	// constructor from list of attributes
	public Food(String name, String category, double energetic, double protein, double carbohydrate, double lipid) {
		this.name = name;
		this.category = category;
		this.energetic = energetic;
		this.protein = protein;
		this.carbohydrate = carbohydrate;
		this.lipid = lipid;
	}
	
	// constructor from database record
	public Food(String databaserecord) {
		// format = name;category;energetic;protein;carbohydrate;lipid
		String[] splitFood= databaserecord.split(";", NB_FOOD_FEATURE);
		this.name = splitFood[0];
		this.category = splitFood[1];
		this.energetic = Double.parseDouble(splitFood[2].replace(',', '.'));
		this.protein = Double.parseDouble(splitFood[3].replace(',', '.'));
		this.carbohydrate = Double.parseDouble(splitFood[4].replace(',', '.'));
		this.lipid = Double.parseDouble(splitFood[5].replace(',', '.'));
	}
	
	public static List<String> parseCsvLine(String csvLine) {
		
		List<String> result = new ArrayList<>();

		StringBuffer curVal = new StringBuffer();
		boolean inQuotes = false;
		boolean startCollectChar = false;
		boolean doubleQuotesInColumn = false;

		char[] chars = csvLine.toCharArray();

		for (char ch : chars) {

			if (inQuotes) {
				startCollectChar = true;
				//Fixed : allow "" in custom quote enclosed
				if (ch == '\"') {
					if (!doubleQuotesInColumn) {
						curVal.append(ch);
						doubleQuotesInColumn = true;
					}
				} else {
					curVal.append(ch);
				}

			} else {
				if (ch == customQuote) {

					inQuotes = true;

					//Fixed : allow "" in empty quote enclosed
					if (chars[0] != '"' && customQuote == '\"') {
						curVal.append('"');
					}

					//double quotes in column will hit this!
					if (startCollectChar) {
						curVal.append('"');
					}

				} else if (ch == separators) {

					result.add(curVal.toString());

					curVal = new StringBuffer();
					startCollectChar = false;

				} else if (ch == '\r') {
					//ignore LF characters
					continue;
				} else if (ch == '\n') {
					//the end, break!
					break;
				} else {
					curVal.append(ch);
				}
			}

		}

		result.add(curVal.toString());

		return result;
	}

}
