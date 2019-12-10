package kanri;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import database.ScheduleInsert;

public class ScheduleAddServlet extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	throws IOException,ServletException{
		//�����R�[�h
		req.setCharacterEncoding("Windows-31J");
		
		//�p�����[�^���󂯎��
		String start_time = req.getParameter("start_time");
		String end_time = req.getParameter("end_time");
		String theater = req.getParameter("theater");
		String title = req.getParameter("title");
		
		String theater_name = req.getParameter("theater_name");

		//�f�[�^�x�[�X�ɏ�������
		ScheduleInsert.insert(start_time,end_time,theater,title,theater_name);

		//�f��قƉf��^�C�g����ێ�
		req.setAttribute("theater_name",theater_name);
		req.setAttribute("title",title);
		
		//�]������w��
		RequestDispatcher dis = req.getRequestDispatcher("/schedulesearchservlet");
		
		//�]��
		dis.forward(req,res);
	}
	
}
