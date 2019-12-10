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
		//文字コード
		req.setCharacterEncoding("Windows-31J");
		
		//パラメータを受け取り
		String theater_name = req.getParameter("theater_name");
		String title = req.getParameter("title");

		
		//データベースのデータを削除
		MovieDelete.delete(theater_name,title);

		//パラメータを転送
		req.setAttribute("theater_name",theater_name);
				
		//転送先を指定
		RequestDispatcher dis = req.getRequestDispatcher("/moviesearchservlet");
		
		//転送
		dis.forward(req,res);
	}
}
