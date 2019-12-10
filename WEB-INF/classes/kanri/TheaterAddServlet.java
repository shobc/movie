package kanri;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import database.TheaterInsert;

public class TheaterAddServlet extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	throws IOException,ServletException{
		//文字コード
		req.setCharacterEncoding("Windows-31J");
		
		//パラメータを受け取り
		String add = req.getParameter("add");
		
		//データベースに書き込み
		TheaterInsert.insert(add);
		
		//転送先を指定
		RequestDispatcher dis = req.getRequestDispatcher("/theaterbeforechangeservlet");
		
		//転送
		dis.forward(req,res);
	}
	
}
