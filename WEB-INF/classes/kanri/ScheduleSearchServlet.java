package kanri;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import database.ScheduleQuery;
import profile.ScheduleProfile;

public class ScheduleSearchServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException{
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException{
		//文字コード
		req.setCharacterEncoding("Windows-31J");

		//パラメータ受け取り
		String theater_name = req.getParameter("theater_name");
		String title = req.getParameter("title");

		if(theater_name==null){
			theater_name = (String)req.getAttribute("theater_name");
		}
		if(title==null){
			title = (String)req.getAttribute("title");
		}
		
		//データベースからリストをもらう
		List<ScheduleProfile> schedulelist=getScheduleData(theater_name,title);
		
		//パラメータ転送
		req.setAttribute("schedulelist",schedulelist);
		req.setAttribute("theater_name",theater_name);
		req.setAttribute("title",title);
		
		//転送先を指定
		RequestDispatcher dis = req.getRequestDispatcher("/kanrimovieschedule");
		
		//転送
		dis.forward(req,res);
	}
	
	public List<ScheduleProfile> getScheduleData(String theater_name,String title){
		List<ScheduleProfile> schedulelist=ScheduleQuery.getSchedule(theater_name,title);
			
		return schedulelist;
	
	}
}
