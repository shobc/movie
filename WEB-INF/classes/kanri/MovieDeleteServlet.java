package kanri;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import database.MovieDelete;

public class MovieDeleteServlet extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	throws IOException,ServletException{
		//�����R�[�h
		req.setCharacterEncoding("Windows-31J");
		
		//�p�����[�^���󂯎��
		String theater_name = req.getParameter("theater_name");
		String title = req.getParameter("title");

		
		//�f�[�^�x�[�X�̃f�[�^���폜
		MovieDelete.delete(theater_name,title);

		//�p�����[�^��]��
		req.setAttribute("theater_name",theater_name);
				
		//�]������w��
		RequestDispatcher dis = req.getRequestDispatcher("/moviesearchservlet");
		
		//�]��
		dis.forward(req,res);
	}
}
