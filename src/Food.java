import java.io.*;
import java.util.*;

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
	private static String foodFileName= foodFileDir + "foodBase.csv";

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
					System.out.println("bye bye!");
					break;
				
				default:
					System.out.println("Enlève tes moufles gros patapouf!");
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
		
		/* on crée des variables de type File car on va manipuler 2 fichiers = le fichier de données 
		   + 1 fichier temporaire  :
		   1. lire ligne fichier données
		   2. si ligne ne commence pas par l'aliment à supprimer, on écrit ligne dans fichier temp
		   3. une fois le fichier données lu entièrement, le supprimer et renommer fichier temp en fichier données
		 */
		
		// food file
		File inputFile = new File(foodFileName);
		
		// temp file
		File tempFile = new File(foodFileDir + "myTempFile.txt");
		
		// objects for reading food file
		FileReader fReader= new FileReader(inputFile);
		BufferedReader bReader = new BufferedReader(fReader);
		String currentLine="";

		//  objects for writing in temp file
		FileWriter fWriter= new FileWriter(tempFile);
		BufferedWriter bWriter = new BufferedWriter(fWriter);
		
		// input food to delete
		System.out.println("food name?");
		delFood= input.nextLine();

		// making of temp file
		do {
			currentLine= bReader.readLine();
			
			// if current line is not null and doesn't start with food to delete, write it in temp file
			if ( currentLine != null && !(currentLine.startsWith(delFood)) ) {
				// write line in temp file
				bWriter.write(currentLine);
				bWriter.newLine();
			}

		} while(currentLine != null);
		
		// close
		bReader.close();
		bWriter.close();
		fReader.close();
		fWriter.close();

		// remove old foodFile
		
		/*
		if (inputFile.delete()) {
			System.out.println(foodFileName + " is deleted");
		}
		else {
			System.out.println(foodFileName + " is not deleted!!!");
		}
		*/
			
		// rename temp file to foodFileName
		if (tempFile.renameTo(inputFile)) {
			System.out.println("rename OK");
		} else {
			System.out.println("rename KO!!!");
		}
			
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
		//File foodFile = new File(foodFileName);
		FileReader fReader= new FileReader(foodFileName);
		BufferedReader bReader= new BufferedReader(fReader);
		// current read line
		String currentLine="";

		do {
			currentLine= bReader.readLine();
			if (currentLine != null) {
				System.out.println(currentLine);
			}
		} while (currentLine != null);
		
	}
}
