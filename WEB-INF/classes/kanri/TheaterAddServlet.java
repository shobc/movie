package kanri;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import database.TheaterInsert;

public class TheaterAddServlet extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	throws IOException,ServletException{
		//�����R�[�h
		req.setCharacterEncoding("Windows-31J");
		
		//�p�����[�^���󂯎��
		String add = req.getParameter("add");
		
		//�f�[�^�x�[�X�ɏ�������
		TheaterInsert.insert(add);
		
		//�]������w��
		RequestDispatcher dis = req.getRequestDispatcher("/theaterbeforechangeservlet");
		
		//�]��
		dis.forward(req,res);
	}
	
}
