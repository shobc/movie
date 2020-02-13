package kanri;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import database.ScheduleInsert;
import java.util.ArrayList;


public class ScheduleAddServlet extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	throws IOException,ServletException{
		//�����R�[�h
		req.setCharacterEncoding("Windows-31J");
		
		// //�p�����[�^���󂯎��
		// String start_time = req.getParameter("start_time");
		// String end_time = req.getParameter("end_time");
		// String theater = req.getParameter("theater");


		//�p�����[�^���󂯎��
		String title = req.getParameter("title");
		String theater_name = req.getParameter("theater_name");
		
		//���t
		String date = req.getParameter("date");

		//��T�ԃf�[�^ArrayList
		ArrayList weekDate = new ArrayList();
		weekDate = ScheduleInsert.getWeek(date);

		//�p�����[�^�[�A�󂯎��
		String[] h_start_time = req.getParameterValues("start_time");
		String[] h_end_time = req.getParameterValues("end_time");
		String[] h_theater = req.getParameterValues("theater");

		//�p�����[�^�[�A�ꌏ���C���T�[�g
		for(int i = 0;i < h_start_time.length;i++){
			for(int j = 0; j < 7;j++){
				String plusDate = (String) weekDate.get(j);
				String start_time = plusDate +" "+ h_start_time[i];
				String end_time = plusDate +" "+ h_end_time[i];
				String theater = h_theater[i];

				// �f�[�^�x�[�X�ɏ�������
				ScheduleInsert.insert(start_time,end_time,theater,title,theater_name);
			}
		}


		

		//�f��قƉf��^�C�g����ێ�
		req.setAttribute("theater_name",theater_name);
		req.setAttribute("title",title);
		
		//�]������w��
		RequestDispatcher dis = req.getRequestDispatcher("/schedulesearchservlet");
		
		//�]��
		dis.forward(req,res);
	}
	
}
