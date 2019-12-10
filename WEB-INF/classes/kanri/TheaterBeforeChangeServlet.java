package kanri;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import database.TheaterQuery;
import profile.TheaterProfile;

public class TheaterBeforeChangeServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException{
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException{
		//�����R�[�h
		req.setCharacterEncoding("Windows-31J");
		
		//�f�[�^�x�[�X���烊�X�g�����炤
		List<TheaterProfile> theaterlist=getBeforeData();
		
		//�p�����[�^��]��
		req.setAttribute("theaterlist",theaterlist);
		
		//�]������w��
		RequestDispatcher dis = req.getRequestDispatcher("/kanrimovietheater");
		
		//�]��
		dis.forward(req,res);
	}
	
	public List<TheaterProfile> getBeforeData(){
		List<TheaterProfile> theaterlist=TheaterQuery.getTheater();
			
		return theaterlist;
	
	}
}
