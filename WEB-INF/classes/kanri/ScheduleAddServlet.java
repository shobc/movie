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
		//文字コード
		req.setCharacterEncoding("Windows-31J");
		
		// //パラメータを受け取り
		// String start_time = req.getParameter("start_time");
		// String end_time = req.getParameter("end_time");
		// String theater = req.getParameter("theater");


		//パラメータを受け取り
		String title = req.getParameter("title");
		String theater_name = req.getParameter("theater_name");
		
		//日付
		String date = req.getParameter("date");

		//一週間データArrayList
		ArrayList weekDate = new ArrayList();
		weekDate = ScheduleInsert.getWeek(date);

		//パラメーター、受け取り
		String[] h_start_time = req.getParameterValues("start_time");
		String[] h_end_time = req.getParameterValues("end_time");
		String[] h_theater = req.getParameterValues("theater");

		//パラメーター、一件ずつインサート
		for(int i = 0;i < h_start_time.length;i++){
			for(int j = 0; j < 7;j++){
				String plusDate = (String) weekDate.get(j);
				String start_time = plusDate +" "+ h_start_time[i];
				String end_time = plusDate +" "+ h_end_time[i];
				String theater = h_theater[i];

				// データベースに書き込み
				ScheduleInsert.insert(start_time,end_time,theater,title,theater_name);
			}
		}


		

		//映画館と映画タイトルを保持
		req.setAttribute("theater_name",theater_name);
		req.setAttribute("title",title);
		
		//転送先を指定
		RequestDispatcher dis = req.getRequestDispatcher("/schedulesearchservlet");
		
		//転送
		dis.forward(req,res);
	}
	
}
