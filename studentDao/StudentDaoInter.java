package com.sharan.studentDao;

import java.util.List;

import com.sharan.student.Student;

public interface StudentDaoInter {
public void InsertStudent(Student st);
 public boolean updateSTudent(Student st);
 public Student SelectStudentByID(int id);
 public boolean deleteStudent(int id);
public List<Student> displayAllSudents();
}
