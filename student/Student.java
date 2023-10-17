package com.sharan.student;

public class Student {
	private int Sid;
	private String Sname;
	private int Sage;
	private float marks;
	
	
	
	public Student(int sid, String sname, int sage, float marks) {
		Sid = sid;
		Sname = sname;
		Sage = sage;
		this.marks = marks;
	}
	
	
	public Student(String sname, int sage, float marks) {
		super();
		Sname = sname;
		Sage = sage;
		this.marks = marks;
	}


	public int getSid() {
		return Sid;
	}
	public void setSid(int sid) {
		Sid = sid;
	}
	public String getSname() {
		return Sname;
	}
	public void setSname(String sname) {
		Sname = sname;
	}
	public int getSage() {
		return Sage;
	}
	public void setSage(int sage) {
		Sage = sage;
	}
	public float getMarks() {
		return marks;
	}
	public void setMarks(float marks) {
		this.marks = marks;
	}
	
	

}
