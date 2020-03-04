
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import database.DayDetailsQuery;
import database.DayQuery;
import profile.DayProfile;
import profile.DayDetailsProfile;

import database.HamburgerDate;
import profile.TheaterProfile;

public class DayServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException{
		doGet(req, res);
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException{
		//文字コード
		req.setCharacterEncoding("windows-31j");

		//セッション生成
		HttpSession session = req.getSession();

		//カレンダー生成
        Calendar cal = Calendar.getInstance();
 
        //今日の日付けを取得　フォーマット　yyyy/MM/dd
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        // System.out.println(sdf.format(cal.getTime()));

		//dayにきょうの日付をいれる
		String day = sdf.format(cal.getTime());

		//
		if(session.getAttribute("date")!=null){
			day = (String)session.getAttribute("date");
			session.removeAttribute("date");
            System.out.println("day"+day);
		}

		//映画館受け取り
		String theater_name = req.getParameter("theater_name");
        if(session.getAttribute("theater_name")!=null){
            theater_name = (String)session.getAttribute("theater_name");
            session.removeAttribute("theater_name");
        }
        System.out.println("theater_name="+theater_name);
        //空文字だと青木映画館
        if(theater_name==null||theater_name.equals("")){
			theater_name = "青木映画館";
            System.out.println("Ifの中theater_name="+theater_name);
        }
		

		


		//データベースからリストをもらいたい
		List<DayProfile> daylist=getDayData(theater_name,day);
		//データベースからリストをもらいたい
		List<DayDetailsProfile> daydetailslist=getDayDetailsData(theater_name,day);
		
		ArrayList<DayProfile> detailsMovie = new ArrayList<DayProfile>();
		Iterator it = daylist.iterator();
		DayProfile dpf= null;
		while(it.hasNext()){
			dpf = (DayProfile)it.next();
			Iterator itr = daydetailslist.iterator();
			while(itr.hasNext()){
				DayDetailsProfile ddp = (DayDetailsProfile)itr.next();
				if(dpf.getTitle().equals(ddp.getTitle())){
					dpf.detailsAdd(ddp);
				}
			}
			detailsMovie.add(dpf);
		}
        HamburgerDate hamburger = new HamburgerDate();
        List<TheaterProfile> hamburgerlist = hamburger.getTheaterDetails();

		//パラメータをJSPに転送したい
		// req.setAttribute("daylist",daylist);
		req.setAttribute("daylist",detailsMovie);
		req.setAttribute("theater_name",theater_name);
        req.setAttribute("hamburger",hamburgerlist);
		//転送先のJSPを指定
		RequestDispatcher dis = req.getRequestDispatcher("/movietop");
		
		//JSPに転送
		dis.forward(req,res);
	}
	
	public List<DayProfile> getDayData(String theater_name,String day){
		List<DayProfile> daylist=DayQuery.getDay(theater_name,day);
			
		return daylist;
	
	}

	public List<DayDetailsProfile> getDayDetailsData(String theater_name,String day){
		List<DayDetailsProfile> daydetailslist=DayDetailsQuery.getDayDetails(theater_name,day);
			
		return daydetailslist;
	
	}
}

