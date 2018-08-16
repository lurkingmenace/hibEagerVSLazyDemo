package com.jdivirgilio.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jdivirgilio.hibernate.demo.entity.Course;
import com.jdivirgilio.hibernate.demo.entity.Instructor;
import com.jdivirgilio.hibernate.demo.entity.InstructorDetail;

public class EagerLazyDemo {

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

			// get the instructor from the DB
			int instructorId = 3; // pulled from the db query
			Instructor instructor = session.get(Instructor.class, instructorId);
			
			System.out.println(instructor);
			for (Course course : instructor.getCourses()) {
				System.out.println(course);
			}
			
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}
}
