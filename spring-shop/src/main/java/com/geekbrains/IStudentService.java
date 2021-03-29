
package com.geekbrains;

public interface IStudentService {

	   public Iterable<Student> listAllStudents();

	   public Student getStudentById(long id);

	   public Student saveStudent(Student Student);
	    
	   public void deleteStudent(long id);
	
}
