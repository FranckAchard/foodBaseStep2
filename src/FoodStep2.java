import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * 
 */

/**
 * @author Utilisateur
 *
 */
public class FoodStep2 {
	// class attributes
	private static Scanner input = new Scanner(System.in);
	private static String foodFileDir= "C:\\Users\\Utilisateur.UTILISA-8GFQHGM\\Documents\\Workspaces\\BD alimentaire\\";
	private static String foodFileName= foodFileDir + "foodBase.csv";
	//private static String foodFileName= foodFileDir + "toto.csv";
	
	// main function
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
				
		// objects for reading food file
		Path foodFilePath= Paths.get(foodFileName);
		BufferedReader bReader= Files.newBufferedReader(foodFilePath);
		String currentLine="";
		String[] currentLineSplit= new String[6];

		//  objects for writing in temp file
		Path tempFilePath = Files.createTempFile(Paths.get(foodFileDir), "myTempFile", ".txt");	
		BufferedWriter bWriter= Files.newBufferedWriter(tempFilePath, StandardOpenOption.WRITE);
		tempFilePath.toFile().deleteOnExit();
		// input food to delete
		System.out.println("food name?");
		delFood= input.nextLine();

		// making of temp file
		do {
			currentLine= bReader.readLine();
			if (currentLine != null) {
				currentLineSplit= currentLine.split(";");
				// if current line doesn't start with food to delete, write it in temp file
				if ( !(currentLineSplit[0].equals(delFood)) ) {
					// write line in temp file
					bWriter.write(currentLine);
					bWriter.newLine();
				}
			}
			
		} while(currentLine != null);
		
		// close
		bReader.close();
		bWriter.close();
		//fReader.close();
		//fWriter.close();

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
		/*
		if (tempFile.renameTo(inputFile)) {
			System.out.println("rename OK");
		} else {
			System.out.println("rename KO!!!");
		}
		*/
			
		Files.move(tempFilePath, foodFilePath, StandardCopyOption.REPLACE_EXISTING);
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
		
		try {
			
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

			bReader.close();
			fReader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier " + foodFileName + " n'existe pas, ça va planter!!");
		}

	}
}
