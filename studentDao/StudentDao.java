package com.sharan.studentDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import com.sharan.student.Student;


public class StudentDao implements StudentDaoInter 
{

	String Driver="oracle.jdbc.driver.OracleDriver";
	String URL="jdbc:oracle:thin:@localhost:1521:ORCLE123";
	String UserName="SCOTT";
	String Password="tiger";
	private static final String InsertStudentDetails="insert into StudentDao values (?,?,?,?)";
	private static final String UpdateStudentDetails="update  StudentDao NAME=?,AGE=?,MARKS=? where id=?";
	private static final String DeleteStudentDetails="DELETE FROM StudentDao WHERE ID=?";
	private static final String DisplayAllStudents="SELECT * FROM StudentDao";
	private static final String SelectStudentsByID="SELECT * FROM StudentDao where id=?";
	protected Connection getConnection() throws ClassNotFoundException, SQLException
	{
		Connection con=null;
		try{
			Class.forName(Driver);
			con=DriverManager.getConnection
					(URL,UserName,Password);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	@Override
	public void InsertStudent(Student st){
		try (
			Connection connection=getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(InsertStudentDetails))
				{
			preparedStatement.setInt(1, st.getSid());
			preparedStatement.setString(2, st.getSname());
			preparedStatement.setInt(3, st.getSage());
			preparedStatement.setFloat(4, st.getMarks());
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	@Override
	public boolean updateSTudent(Student st) {
		boolean updated = false; 
		try (
				Connection connection=getConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(InsertStudentDetails))
					{
				preparedStatement.setInt(1, st.getSid());
				preparedStatement.setString(2, st.getSname());
				preparedStatement.setInt(3, st.getSage());
				preparedStatement.setFloat(4, st.getMarks());
				updated= preparedStatement.executeUpdate()>0;
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
		return updated;
		
	}
	@Override
	public Student SelectStudentByID(int id){
		Student student=null;
		try(Connection connection=getConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(SelectStudentsByID);){
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs=preparedStatement.executeQuery();
			while (rs.next()) {
				String name=rs.getString("NAME");
				int age=rs.getInt("AGE");
				float marks=rs.getFloat("MARKS");
				student=new Student(name, age, marks);
				
			}
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return student;
	}
	@Override
	public boolean deleteStudent(int id) {
		boolean rowdeleted=false;
		try(Connection connection =getConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(DeleteStudentDetails);
				)
		
		{
			preparedStatement.setInt(1, id);
			rowdeleted=preparedStatement.executeUpdate()>0;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return rowdeleted;
	}
	@Override
	public List<Student> displayAllSudents() {
		List<Student> student=new ArrayList<>();
		try(Connection connection=getConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(DisplayAllStudents);){
			System.out.println(preparedStatement);
			ResultSet rs=preparedStatement.executeQuery();
			while (rs.next()) {
				int id=rs.getInt("ID");
				String name=rs.getString("NAME");
				int age=rs.getInt("AGE");
				float marks=rs.getFloat("MARKS");
				student.add(new Student(id,name, age, marks));
				
				
			}
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return student;
		
	}


}

