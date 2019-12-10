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
		//文字コード
		req.setCharacterEncoding("Windows-31J");
		
		//パラメータを受け取り
		String schedule_detail = req.getParameter("schedule_detail_id");
		int schedule_detail_id = Integer.parseInt(schedule_detail);

		String theater_name = req.getParameter("theater_name");
		String title = req.getParameter("title");

		
		//データベースの上映スケジュールを削除
		ScheduleDelete.delete(schedule_detail_id);

		//映画館と映画タイトル保持
		req.setAttribute("theater_name",theater_name);
		req.setAttribute("title",title);
		
		//転送先を指定
		RequestDispatcher dis = req.getRequestDispatcher("/schedulesearchservlet");
		
		//転送
		dis.forward(req,res);
	}
}
