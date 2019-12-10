package kanri;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import database.MovieUpdate;

public class MovieChangeServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException{
		//文字コード
		req.setCharacterEncoding("Windows-31J");

		//パラメータを受け取り
		String schedule = req.getParameter("schedule_id");
		int schedule_id = Integer.parseInt(schedule);
		String title = req.getParameter("title");
		String image = req.getParameter("image");
		String release_period = req.getParameter("release_period");
		String end_period = req.getParameter("end_period");
		String Detailed_explanation = req.getParameter("Detailed_explanation");

		String theater_name = req.getParameter("theater_name");

		//データベースを書き変える
		MovieUpdate.update(schedule_id,title,image,release_period,end_period,Detailed_explanation);
		
		//パラメータを転送
		req.setAttribute("theater_name",theater_name);
		
		//転送先を指定
		RequestDispatcher dis = req.getRequestDispatcher("/moviesearchservlet");
		
		//転送
		dis.forward(req,res);
	}
}
