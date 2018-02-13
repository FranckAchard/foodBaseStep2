import java.io.*;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author Utilisateur
 *
 */
public class Food {

	private static Scanner input = new Scanner(System.in);
	private static String foodFileDir= "C:\\Users\\Utilisateur.UTILISA-8GFQHGM\\Documents\\Workspaces\\BD alimentaire\\";
	private static String foodFileName= foodFileDir + "food_database.csv";

	/**
	 * 
	 */
	public Food() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String menuChoice= "";
		
		do {
			// display menu
			System.out.println("\n");
			System.out.println("Hello dear friend, what do you want to do?");
			System.out.println("1. add food");
			System.out.println("2. delete food");
			System.out.println("3. print all food");
			System.out.println("0. quit");
			
			// get input then call assiocated subfunction
			menuChoice = input.nextLine();
			
			switch(menuChoice) {
				case "1":
					addFood();
					break;
					
				case "2":
					deleteFood();
					break;
					
				case "3":
					printFood();
					break;
					
				case "0":
					System.out.println("by by!");
					break;
				
				default:
					break;
			}

		} while (!menuChoice.equals("0"));
		
		input.close();
	}

	private static void addFood() throws IOException {
		// string for new food input
		String newFood= "";
		// filewriter for writing in food file in append mode
		FileWriter foodFile= new FileWriter(foodFileName, true);
		// object bufferwriter for writing in file
		BufferedWriter writer = new BufferedWriter(foodFile);
		
		System.out.println("food name?");
		newFood= input.nextLine();
		newFood+= ";";
		
		System.out.println("kind of food?");
		newFood+= input.nextLine();
		newFood+= ";";

		System.out.println("energetic value? (kcal)");
		newFood+= input.nextLine();
		newFood+= ";";

		System.out.println("protein rate? (g/100g)");
		newFood+= input.nextLine();
		newFood+= ";";
		
		System.out.println("carbohydrate rate? (g/100g)");
		newFood+= input.nextLine();
		newFood+= ";";

		System.out.println("lipid rate? (g/100g)");
		newFood+= input.nextLine();
		//newFood+= "\n";

		// write input in file
		writer.write(newFood);
		writer.newLine();
		
		// close bufferwriter
		writer.close();
		
		// print food list
		printFood();

	}
	
	private static void deleteFood() throws IOException {
		// string for food to delete
		String delFood= "";
		
		// food file
		File inputFile = new File(foodFileName);
		// temp file
		File tempFile = new File(foodFileDir + "myTempFile.txt");
		
		//  bufferedreader for reading food file
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		// current read line
		String currentLine="";

		//  bufferwriter for writing in temp file
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
		
		// input food to delete
		System.out.println("food name?");
		delFood= input.nextLine();

		// making of temp file
		while((currentLine= reader.readLine()) != null) {
			// if current line doesn't start wih food to delete, write it in temp file
			if (!(currentLine.startsWith(delFood))) {
				// write line in temp file
				writer.write(currentLine);
				writer.newLine();
			}
		}
		
		// close bufferwriter
		reader.close();
		writer.close();

		// remove old foodFile
		inputFile.delete();
		
		// rename temp file to foodFileName
		tempFile.renameTo(inputFile);
		
		// print food list
		printFood();
	}
	
	private static void printFood() throws IOException {
		/*
		String fileContent= "";
		Scanner scanner= new Scanner(new File(foodFileName));
		while (scanner.hasNextLine()) {
			fileContent += scanner.nextLine() + "\n";
		}
		scanner.close();
		System.out.println(fileContent);
		*/
		
		// essai modif / git
		File foodFile = new File(foodFileName);
		BufferedReader reader = new BufferedReader(new FileReader(foodFile));
		// current read line
		String currentLine="";

		while ((currentLine= reader.readLine()) != null) {
			System.out.println(currentLine);
		}
		
	}
}
