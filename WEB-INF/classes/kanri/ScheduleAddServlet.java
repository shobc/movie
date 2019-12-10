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
		//文字コード
		req.setCharacterEncoding("Windows-31J");
		
		//パラメータを受け取り
		String start_time = req.getParameter("start_time");
		String end_time = req.getParameter("end_time");
		String theater = req.getParameter("theater");
		String title = req.getParameter("title");
		
		String theater_name = req.getParameter("theater_name");

		//データベースに書き込み
		ScheduleInsert.insert(start_time,end_time,theater,title,theater_name);

		//映画館と映画タイトルを保持
		req.setAttribute("theater_name",theater_name);
		req.setAttribute("title",title);
		
		//転送先を指定
		RequestDispatcher dis = req.getRequestDispatcher("/schedulesearchservlet");
		
		//転送
		dis.forward(req,res);
	}
	
}
