package com.jdivirgilio.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.jdivirgilio.hibernate.demo.entity.Course;
import com.jdivirgilio.hibernate.demo.entity.Instructor;
import com.jdivirgilio.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

	public static void main(String[] args) {
		// Create Session Factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml") // Default name of file. Not
																					// necessary to include here.
																					// Must be in class path though!
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)	 
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		try {

			session.beginTransaction();

			// Hibernate query with HQL
			
			// get the instructor from the DB
			int instructorId = 3; // pulled from the db query
			Query<Instructor> query = session.createQuery("select i from Instructor i " 
										+ "JOIN FETCH i.courses "
										+ "where i.id=:instructorId",
										Instructor.class);
			query.setParameter("instructorId", instructorId);
			
			// Execute query
			Instructor instructor = query.getSingleResult();

			System.out.println(instructor);
			
			System.out.println(instructor.getCourses());

			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}
}
