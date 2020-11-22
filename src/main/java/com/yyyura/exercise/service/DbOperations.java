package com.yyyura.exercise.service;

import java.util.List;

import org.hibernate.Session;

import com.yyyura.exercise.entity.Address;
import com.yyyura.exercise.entity.Person;
import com.yyyura.exercise.utility.HibernateUtility;

/**
 * @author Jurijs Cicelimovs
 */

public class DbOperations {

	/**
	 * Add Person
	 */
	public static void addPerson(String firstName, String lastName) {
		try {
			Person person = new Person(firstName, lastName);
			Session session = HibernateUtility.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(person);
			session.getTransaction().commit();
			System.out.println("New person added: " + person.toString());
			session.close();
		} catch (Exception sqlException) {
			System.err.println("sqlException" + sqlException);
		}
	}

	/**
	 * Edit Person
	 */
	public static void editPerson(Integer personId, String firstName, String lastName) {
		try {
			Session session = HibernateUtility.getSessionFactory().openSession();
			session.beginTransaction();
			Person personObject = (Person) session.get(Person.class, personId);
			personObject.setFirstName(firstName);
			personObject.setLastName(lastName);
			session.getTransaction().commit();
			System.out.println("Name Edited");
			session.close();
		} catch (Exception e) {
			System.err.println("Failed to Edite name - no such id in DB");
		}
	}

	/**
	 * Delete Person
	 */
	public static void deletePerson(Integer person_id) {
		try {
			Session session = HibernateUtility.getSessionFactory().openSession();
			session.beginTransaction();
			Person findPersonObj = (Person) session.load(Person.class, person_id);
			session.delete(findPersonObj);
			session.getTransaction().commit();
			System.out.println("Person Deleted");
			session.close();
		} catch (Exception e) {
			System.err.println("Failed to delete no such ID");
		}
	}

	/**
	 * Add Address to Person
	 */
	public static void addAddress(Integer userID, String street, String city, String state, String postalCode) {
		try {
			Address addr = new Address(street, city, state, postalCode);

			Session session = HibernateUtility.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(addr);
			System.out.println("New address saved to DB");

			Person findPersonObj = (Person) session.load(Person.class, userID);
			findPersonObj.setAddresses(addr);
			System.out.println("Address added to person adressess");
			session.getTransaction().commit();

			@SuppressWarnings("unchecked")
			List<Address> addresses = session.createQuery("FROM Address").list();
			System.out.println("All addresses in DB:");
			addresses.forEach((x) -> System.out.printf("- %s%n", x));

			System.out.println(findPersonObj.getFirstName() + "'s addresses:");
			List<Address> list = findPersonObj.getAddresses();
			list.forEach((x) -> System.out.printf("- %s%n", x));

			session.close();
		} catch (Exception e) {
			System.err.println("Failed to Add address");
		}
	}

	/**
	 * Edit Address
	 */
	public static void editAddress(Integer addressId, String street, String city, String state, String postalCode) {
		try {
			Session session = HibernateUtility.getSessionFactory().openSession();
			session.beginTransaction();
			Address findAddressObj = (Address) session.load(Address.class, addressId);
			findAddressObj.setStreet(street);
			findAddressObj.setCity(city);
			findAddressObj.setState(state);
			findAddressObj.setPostalCode(postalCode);
			session.getTransaction().commit();
			System.out.println("Address Edited");
			session.close();
		} catch (Exception e) {
			System.err.println("Failed to Edite name - no such id in DB");
		}
	}

	/**
	 * Delete Address
	 */
	public static void deleteAddress(Integer person_id, Integer address_id) {
		try {
			Session session = HibernateUtility.getSessionFactory().openSession();
			session.beginTransaction();
			Person findPersonObj = (Person) session.load(Person.class, person_id);
			Address findAddressObj = (Address) session.load(Address.class, address_id);
			findPersonObj.getAddresses().remove(findAddressObj);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			System.out.println("Failed to remove");
		}
	}

	/**
	 * Count Persons
	 */
	public static void countPersons() {
		Session session = HibernateUtility.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<Person> persons = session.createQuery("FROM Person").list();
		System.out.println("Number of Persons with unique ID in DB: " + persons.size());
		session.close();
	}

	/**
	 * List Persons
	 */
	public static void listPersons() {
		Session session = HibernateUtility.getSessionFactory().openSession();

		@SuppressWarnings("unchecked")
		List<Person> persons = session.createQuery("FROM Person").list();
		System.out.println("List of Persons:");
		persons.forEach((x) -> System.out.printf("- %s%n", x));

//		@SuppressWarnings("unchecked")
//		List<Address> addresses = session.createQuery("FROM Address").list();
//		System.out.println("List of all Addresses in DB:");
//		addresses.forEach((x) -> System.out.printf("- %s%n", x));

		session.close();
	}
}