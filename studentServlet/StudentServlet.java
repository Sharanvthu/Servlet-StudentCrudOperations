package com.sharan.studentServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sharan.student.Student;
import com.sharan.studentDao.StudentDao;
import com.sharan.studentDao.StudentDaoInter;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDao studentDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentServlet() {
		super();
		this.studentDao= new StudentDao();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getServletPath();
		switch (action) {
		case "/new":
			showForm(request,response);
			break;
		case "/insert":
			insertStudent(request, response);
			break;
		case "/delete":
			try {
				delete(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/edit":
			try {
				showEditForm(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/update":
			try {
				update(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		default:
			// handle list
			try {
				ListStudent(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}

	}

	private void ListStudent(HttpServletRequest request,HttpServletResponse response ) throws SQLException ,IOException, ServletException 
	{
		List<Student> listStudent=studentDao.displayAllSudents();
		request.setAttribute("ListStudent", listStudent);
		RequestDispatcher dispatcher =request.getRequestDispatcher("Student-Info.jsp");
		dispatcher.forward(request, response);
		
	}
	private void showForm(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		RequestDispatcher dispatcher=request.getRequestDispatcher("Studen-info.jsp");
		dispatcher.forward(request, response);
	}

	private void insertStudent(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		int age=Integer.parseInt(request.getParameter("age"));
		float marks=Float.parseFloat(request.getParameter("marks"));
		Student student=new Student(id, name, age, marks);
		studentDao.InsertStudent(student);
		response.sendRedirect("list");
	}
	
	private void showEditForm(HttpServletRequest request,HttpServletResponse response ) throws SQLException,IOException, ServletException 
	{
		int id=Integer.parseInt(request.getParameter("id"));
		Student existedStudent=studentDao.SelectStudentByID(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Student-Info.jsp");
		request.setAttribute("user", existedStudent);
		dispatcher.forward(request, response);
	}
	
	private void delete(HttpServletRequest request ,HttpServletResponse response) throws SQLException,IOException
	{
		int id=Integer.parseInt(request.getParameter("id"));
		studentDao.deleteStudent(id);
		response.sendRedirect("list");
	}
	private void update(HttpServletRequest request,HttpServletResponse response) throws SQLException ,IOException
	{
		int id =Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		int age=Integer.parseInt(request.getParameter("age"));
		float marks=Float.parseFloat(request.getParameter("marks"));
		Student st=new Student(name, age, marks);
		studentDao.updateSTudent(st);
		response.sendRedirect("list");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
