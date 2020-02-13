import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class AccessSeatServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
	   String theater = req.getParameter("theater");
	   theater = theater.substring(8,9);
	   System.out.println(theater);
	   String schedule_detail_id = req.getParameter("schedule_detail_id");
	   String seat = CreateSeat.seatingChart(theater,schedule_detail_id);//プロパティファイルのキー
	   req.setAttribute("seat",seat);
	   req.setAttribute("schedule_detail_id",schedule_detail_id);
	   RequestDispatcher dis = req.getRequestDispatcher("seat.jsp");
	   dis.forward(req,res);

    }
}