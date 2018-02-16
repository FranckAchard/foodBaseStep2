package co.simplon.pf1.fooddb;
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
	private static String foodFileDir= "C:\\Users\\Utilisateur.UTILISA-8GFQHGM\\Documents\\Workspaces\\BD alimentaire step2\\";
	private static String foodFileName= foodFileDir + "aliments.csv";
	//private static String foodFileName= foodFileDir + "toto.csv";
	
	// main function
	public static void main(String[] args) throws IOException {
		String menuChoice= "";
		
		do {
			// display menu
			System.out.println("\n");
			System.out.println("Hello dear friend, what do you want to do?");
			System.out.println("1. print food");
			System.out.println("2. add food");
			System.out.println("3. find food");
			System.out.println("4. delete food");
			System.out.println("0. quit");
			
			// get input then call assiocated subfunction
			menuChoice = input.nextLine();
			
			switch(menuChoice) {
				case "1":
					printAllFood();
					break;
					
				case "2":
					addFood();
					break;
					
				case "3":
					findFood();
					break;
					
				case "4":
					deleteFood();
					break;
					
				case "0":
					System.out.println("bye bye!");
					break;
				
				default:
					System.out.println("Enl�ve tes moufles gros patapouf!");
					break;
			}

		} while (!menuChoice.equals("0"));
		
		input.close();
	}

	private static void printAllFood() throws IOException {
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
		    //PrintStream out = new PrintStream(System.out, true, "UTF-8");

			do {
				currentLine= bReader.readLine();
				if (currentLine != null) {				
				    //out.println(currentLine);
					System.out.println(currentLine);
				}
			} while (currentLine != null);

			bReader.close();
			fReader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier " + foodFileName + " n'existe pas, �a va planter!!");
		}

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
		
		System.out.println("category of food?");
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
		printAllFood();

	}
	
	private static void findFood() {
		/* 
		 * Faire saisir categorieSaisie
		 * compteur = 0
		 * listeAliments = ""
		 * Tant que fichier a des lignes
		 * 		Lire ligne
		 * 		Splitter ligne
		 * 		aliment = 1er element		
		 * 		categorieLue = 2e element
		 * 		Si categorieLue ~ categorieSaisie
		 * 			compteur += 1
		 * 			listeAliments += aliment + saut ligne
		 * 	Afficher compteur
		 * 	Demander si affichage liste, si oui afficher
		 */
		
		// food counter
		int countFood=0;
		
		// input kind of food
		String inputCategory;
		System.out.println("food category?");
		inputCategory= input.nextLine();
		
		
		
		
	}

	private static void deleteFood() throws IOException {
		// string for food to delete
		String delFood= "";
		
		/* on cr�e des variables de type File car on va manipuler 2 fichiers = le fichier de donn�es 
		   + 1 fichier temporaire  :
		   1. lire ligne fichier donn�es
		   2. si ligne ne commence pas par l'aliment � supprimer, on �crit ligne dans fichier temp
		   3. une fois le fichier donn�es lu enti�rement, le supprimer et renommer fichier temp en fichier donn�es
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
				currentLineSplit= currentLine.split(";", 6);
				// if current line doesn't start with food to delete, write it in temp file
				if ( currentLineSplit[0] != null && !(currentLineSplit[0].equals(delFood)) ) {
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
			
		Files.move(tempFilePath, foodFilePath, StandardCopyOption.ATOMIC_MOVE);
		// print food list
		printAllFood();
	}
	
	
}
