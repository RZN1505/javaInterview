
package com.geekbrains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class StudentServiceImpl implements IStudentService {

	private IStudent StudentRepo;
	@Autowired
	public void setStudentRepo(IStudent StudentRepo) {
		this.StudentRepo = StudentRepo;
	}

	@Override
	public Iterable<Student> listAllStudents() {
		return StudentRepo.findAll();
	}

	@Override
	public Student getStudentById(long id) {
		return StudentRepo.findById(id).get();
	}

	@Override
	public Student saveStudent(Student Student) {
		return StudentRepo.save(Student);
	}

	@Override
	public void deleteStudent(long id) {
		Student Student = StudentRepo.findById(id).get();
		 StudentRepo.delete(Student);
		
	}

	
}
