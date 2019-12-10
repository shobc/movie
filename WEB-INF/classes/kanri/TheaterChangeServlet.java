package kanri;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import database.TheaterUpdate;
import database.TheaterQuery;
import profile.TheaterProfile;

public class TheaterChangeServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException{
		//文字コード
		req.setCharacterEncoding("Windows-31J");
		//パラメータを受け取り
		
		String a[] = req.getParameterValues("beforechange");
		String beforechange = a[0];
		String afterchange = req.getParameter("afterchange");

		//データベースに書き込み
		TheaterUpdate.theaterupdate(beforechange,afterchange);
		
		//データベースからリストをもらう
		List<TheaterProfile> theaterlist=getBeforeData();
		
		//パラメータを転送
		req.setAttribute("theaterlist",theaterlist);
		
		//転送先を指定
		RequestDispatcher dis = req.getRequestDispatcher("/kanrimovietheater");
		
		//転送
		dis.forward(req,res);
	}
	
	public List<TheaterProfile> getBeforeData(){
		List<TheaterProfile> theaterlist=TheaterQuery.getTheater();
			
		return theaterlist;
	
	}
}
