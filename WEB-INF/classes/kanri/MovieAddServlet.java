package kanri;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import database.MovieInsert;

public class MovieAddServlet extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	throws IOException,ServletException{
		//�����R�[�h
		req.setCharacterEncoding("Windows-31J");
		
		//�p�����[�^���󂯎��
		String theater_name = req.getParameter("theater_name");
		String title = req.getParameter("title");
		String image = req.getParameter("image");
		String release_period = req.getParameter("release_period");
		String end_period = req.getParameter("end_period");
		String Detailed_explanation = req.getParameter("Detailed_explanation");

		//�f�[�^�x�[�X�ɏ�������
		MovieInsert.insert(theater_name,title,image,release_period,end_period,Detailed_explanation);

		//�f��ٖ��̕ێ��̂���
		req.setAttribute("theater_name",theater_name);
		
		//�]������w��
		RequestDispatcher dis = req.getRequestDispatcher("/moviesearchservlet");
		
		//�]��
		dis.forward(req,res);
	}
	
}
