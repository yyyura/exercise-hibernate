package com.yyyura.exercise;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import com.yyyura.exercise.entity.Address;
import com.yyyura.exercise.entity.Person;
import com.yyyura.exercise.service.DbOperations;
import com.yyyura.exercise.utility.HibernateUtility;

/**
 * @author Jurijs Cicelimovs
 */

public class DbOperationsTest {
	private static SessionFactory sessionFactory;
	private Session session;

	@Test
	public void DbOperationsTestSuccess() {

		Person person;
		Address address;
		sessionFactory = HibernateUtility.getSessionFactory();

		/**
		 * test add Person
		 */
		DbOperations.addPerson("John", "Doe");
		DbOperations.addPerson("Mary", "Smith");
		session = sessionFactory.openSession();
		person = session.find(Person.class, 1);
		assertEquals("John", person.getFirstName());
		session.close();

		/**
		 * test edit Person
		 */
		DbOperations.editPerson(1, "Alan", "Doe");
		session = sessionFactory.openSession();
		person = session.find(Person.class, 1);
		assertEquals("Alan", person.getFirstName());
		session.close();

		/**
		 * test delete Person
		 */
		DbOperations.deletePerson(2);
		session = sessionFactory.openSession();
		person = session.find(Person.class, 2);
		assertNull(person);
		session.close();

		/**
		 * test Add Address to Person
		 */
		DbOperations.addAddress(1, "Main", "Dublin", "Ireland", "D24");
		session = sessionFactory.openSession();
		address = session.find(Address.class, 3);
		assertEquals("Main", address.getStreet());
		session.close();

		/**
		 * test edit Address
		 */
		DbOperations.editAddress(3, "Green", "Dublin", "Ireland", "D24");
		session = sessionFactory.openSession();
		address = session.find(Address.class, 3);
		assertEquals("Green", address.getStreet());
		session.close();

		/**
		 * test delete Address
		 */
		DbOperations.deleteAddress(1, 3);
		session = sessionFactory.openSession();
		address = session.find(Address.class, 3);
		assertNull(address);
		session.close();

		HibernateUtility.closeSessionFactory();
	}

}