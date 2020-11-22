package com.yyyura.exercise;

import java.util.Scanner;

import com.yyyura.exercise.service.DbOperations;
import com.yyyura.exercise.utility.HibernateUtility;

/**
 * @author Jurijs Cicelimovs
 */


/*
 * 1. Add Person 
 * 2. Edit Person 
 * 3. Delete Person 
 * 4. Add Address to Person  
 * 5. Edit Address 
 * 6. Delete Address 
 * 7. Count Number of Persons 
 * 8. List Persons
 * 0. Exit
 */
public class App {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		boolean exit = false;
		int choice;

		// User input
		while (!exit) {
			System.out.print("Enter choise 1 - 8, enter 0 to exit: ");
			while (!scanner.hasNextInt()) {
				System.out.println("Input is not a number.");
				System.out.print("Enter choise 1 - 8, enter 0 to exit: ");
				scanner.nextLine();
			}
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1: // Add Person
				try {
					System.out.print("Enter person name, surename separate by space:");
					String str = scanner.nextLine();
					String[] arr = str.split(" ");
					DbOperations.addPerson(arr[0], arr[1]);
				} catch (Exception e) {
					System.out.println("try again");
				}
				break;
			case 2: // Edit Person
				try {
					System.out.print("Enter person id, name, surename separate by space:");
					String str = scanner.nextLine();
					String[] arr = str.split(" ");
					DbOperations.editPerson(Integer.parseInt(arr[0]), arr[1], arr[2]);
				} catch (Exception e) {
					System.out.println("try again");
				}
				break;
			case 3: // Delete Person
				try {
					System.out.print("Enter person id to delete:");
					String str = scanner.nextLine();
					String[] arr = str.split(" ");
					DbOperations.deletePerson(Integer.parseInt(arr[0]));
				} catch (Exception e) {
					System.out.println("try again");
				}
				break;
			case 4: // Add address to person with ID
				try {
					System.out.print("Enter existing user ID and new address separate by spaces:");
					String str = scanner.nextLine();
					String[] arr = str.split(" ");
					DbOperations.addAddress(Integer.parseInt(arr[0]), arr[1], arr[2], arr[3], arr[4]);
				} catch (Exception e) {
					System.out.println("try again");
				}
				break;
			case 5: // Edit Address
				try {
					System.out.print("Enter Address id, street, city, state, postalCode - separate by space:");
					String str = scanner.nextLine();
					String[] arr = str.split(" ");
					DbOperations.editAddress(Integer.parseInt(arr[0]), arr[1], arr[2], arr[3], arr[4]);
				} catch (Exception e) {
					System.out.println("try again");
				}
				break;
			case 6: // Delete Address
				try {
					System.out.print("Enter person ID and address ID to delete - separate by spaces: ");
					String str = scanner.nextLine();
					String[] arr = str.split(" ");
					DbOperations.deleteAddress(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
				} catch (Exception e) {
					System.out.println("try again");
				}
				break;
			case 7: // Count Number of Persons
				DbOperations.countPersons();
				break;
			case 8: // List Persons
				DbOperations.listPersons();
				break;
			case 0:
				scanner.close();
				HibernateUtility.closeSessionFactory();
				exit = true;
				System.out.println("Bye!");
				break;
			default:
				System.out.println("try again");
				break;
			}
		}
	}
}