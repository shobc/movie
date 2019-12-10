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
		//文字コード
		req.setCharacterEncoding("Windows-31J");
		
		//パラメータを受け取り
		String theater_name = req.getParameter("theater_name");
		String title = req.getParameter("title");
		String image = req.getParameter("image");
		String release_period = req.getParameter("release_period");
		String end_period = req.getParameter("end_period");
		String Detailed_explanation = req.getParameter("Detailed_explanation");

		//データベースに書き込み
		MovieInsert.insert(theater_name,title,image,release_period,end_period,Detailed_explanation);

		//映画館名の保持のため
		req.setAttribute("theater_name",theater_name);
		
		//転送先を指定
		RequestDispatcher dis = req.getRequestDispatcher("/moviesearchservlet");
		
		//転送
		dis.forward(req,res);
	}
	
}
