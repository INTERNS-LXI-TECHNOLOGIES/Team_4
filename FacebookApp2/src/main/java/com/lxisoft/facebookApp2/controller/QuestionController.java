package com.lxisoft.facebookApp2.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.lxisoft.facebookApp2.model.*;

/**
 * The QuestionController class is an Controller of Home page
 * 
 * @author MOHAMMED SAHAL
 * @version 1.0
 * @since 2018-07-24
 */

public class QuestionController extends HttpServlet
{
	
	static Logger log = Logger.getLogger(QuestionController.class.getName());
	
	
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{ 
		HttpSession session=request.getSession();
		session.setAttribute("questions",getQuestions(request));
		
		session.setAttribute("username",request.getRemoteUser());
		RequestDispatcher view=request.getRequestDispatcher("HomePage.jsp?count=0");
		view.forward(request,response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		HttpSession session=request.getSession();
		int marks=0;
		ArrayList<QuizResult> qaList=(ArrayList<QuizResult>) session.getAttribute("result");
		for(QuizResult qa:qaList)
		{
			if(qa.getChoice().equals(qa.getQuestion().getAnswer()))
			{
				marks++;
			}
		}
		session.setAttribute("marks",""+marks);
		response.sendRedirect("outputServlet");
		
	}
	
	public ArrayList<Question> getQuestions(HttpServletRequest request)
	{
		ArrayList<Question> questionList=findAll();
		
		Collections.shuffle(questionList);
		ArrayList<Question> questions=new ArrayList<Question>();
		for(int i=0;i<questionList.size();i++)
		{
			questions.add(questionList.get(i));	
		}
		
		HttpSession session=request.getSession();
		session.setAttribute("size",questionList.size());
		
		return questions;
	}
	
	
	public ArrayList<Question> findAll()
	{
		Connection connection = null;
		Statement statement = null;
		ArrayList<Question> questionList=new ArrayList<Question>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "root");
				statement = connection.createStatement();
			
			String sql;
			sql="SELECT * FROM questtables";
			ResultSet rs=statement.executeQuery(sql);
			while (rs.next()) {
			Question question=new Question();
			question.setQuestionId(rs.getInt(1));
			question.setQuestionName(rs.getString(2));
			question.setOption1(rs.getString(3));
			question.setOption2(rs.getString(4));
			question.setOption3(rs.getString(5));
			question.setOption4(rs.getString(6));
			question.setAnswer(rs.getString(7));
			questionList.add(question);
			}
			connection.close();
		}
		 catch (Exception ex) {
            System.err.println(ex);
        }
		
		return questionList;
	}
	
	
}