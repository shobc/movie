package kanri;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import database.ScheduleDelete;

public class ScheduleDeleteServlet extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	throws IOException,ServletException{
		//�����R�[�h
		req.setCharacterEncoding("Windows-31J");
		
		//�p�����[�^���󂯎��
		String schedule_detail = req.getParameter("schedule_detail_id");
		int schedule_detail_id = Integer.parseInt(schedule_detail);

		String theater_name = req.getParameter("theater_name");
		String title = req.getParameter("title");

		
		//�f�[�^�x�[�X�̏�f�X�P�W���[�����폜
		ScheduleDelete.delete(schedule_detail_id);

		//�f��قƉf��^�C�g���ێ�
		req.setAttribute("theater_name",theater_name);
		req.setAttribute("title",title);
		
		//�]������w��
		RequestDispatcher dis = req.getRequestDispatcher("/schedulesearchservlet");
		
		//�]��
		dis.forward(req,res);
	}
}
