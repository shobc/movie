package kanri;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import database.ScheduleUpdate;

public class ScheduleChangeServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException{
		//文字コード
		req.setCharacterEncoding("Windows-31J");

		//パラメータを受け取り
		String schedule_detail = req.getParameter("schedule_detail_id");
		int schedule_detail_id = Integer.parseInt(schedule_detail);
		String release_period = req.getParameter("release_period");
		String start_time = req.getParameter("start_time");
		String end_time = req.getParameter("end_time");
		String theater = req.getParameter("theater");

		String theater_name = req.getParameter("theater_name");
		String title = req.getParameter("title");


		start_time = release_period + " " + start_time;
		//System.out.println(start_time);
		end_time = release_period + " " + end_time;
		//System.out.println(end_time);

		//データベースを書き変える
		ScheduleUpdate.update(schedule_detail_id,start_time,end_time,theater);
		
		//パラメータを転送
		req.setAttribute("theater_name",theater_name);
		req.setAttribute("title",title);
		
		//転送先を指定
		RequestDispatcher dis = req.getRequestDispatcher("/schedulesearchservlet");
		
		//転送
		dis.forward(req,res);
	}
}
