import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import database.HamburgerDate;
import profile.TheaterProfile;



public class HamburgerServlet extends HttpServlet{
public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
    
//    //データベースからリストをもらいたい
//    List<DayProfile> daylist=getDayData(theater_name,day);
    //データベースからリストをもらいたい
    HamburgerDate hamburger = new HamburgerDate();
    List<TheaterProfile> daydetailslist = hamburger.getTheaterDetails();

//    ArrayList<DayProfile> detailsMovie = new ArrayList<DayProfile>();
//    Iterator it = daylist.iterator();
//    DayProfile dpf= null;
//    while(it.hasNext()){
//        dpf = (DayProfile)it.next();
//        Iterator itr = daydetailslist.iterator();
//        while(itr.hasNext()){
//            DayDetailsProfile ddp = (DayDetailsProfile)itr.next();
//            if(dpf.getTitle().equals(ddp.getTitle())){
//                dpf.detailsAdd(ddp);
//            }
//        }
//        detailsMovie.add(dpf);
//    }
    

    req.setAttribute("hamburger",daydetailslist);
RequestDispatcher dis = req.getRequestDispatcher("movieTop.jsp");
dis.forward(req,res);

}
}