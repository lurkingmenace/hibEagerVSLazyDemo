package com.jdivirgilio.hibernate.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="instructor_detail")
public class InstructorDetail {
	
	// Annotate the class as an entity and map to the db table
	
	// define the fields
	
	// annotate the fields iwth db column names
	
	// create constructors
	
	//generate get/sets
	
	// generate toString() method

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="youtube_channel")
	private String youTubeChannel;
	
	@Column(name="hobby")
	private String hobby;
	
	// This is also bidirectional because of cascade all
	@OneToOne(mappedBy="instructorDetail", // Mapped to the instructorDetail property in the instructor class
			  cascade=CascadeType.ALL)     // mappedBy tells Hibernate to
										   // * Look at the instructorDetail property in the Instrctor class
										   // * Use information from the Instructor class @JoinColumn
										   // * To help find the associated instructor
	private Instructor instructor;
	
	public InstructorDetail() {
		
	}
	
	/**
	 * @param youTubeChannel
	 * @param hobby
	 */
	public InstructorDetail(String youTubeChannel, String hobby) {
		this.youTubeChannel = youTubeChannel;
		this.hobby = hobby;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getYouTubeChannel() {
		return youTubeChannel;
	}

	public void setYouTubeChannel(String youTubeChannel) {
		this.youTubeChannel = youTubeChannel;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	@Override
	public String toString() {
		return "InstructorDetail [id=" + id + ", youTubeChannel=" + youTubeChannel + ", hobby=" + hobby + "]";
	}
	
	
	
}
