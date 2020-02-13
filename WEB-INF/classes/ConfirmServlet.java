import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.RequestDispatcher;
import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;


public class ConfirmServlet extends HttpServlet {
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{

        // String sn0 = req.getParameter("seatNo0");
        // String sn1 = req.getParameter("seatNo1");
        // System.out.println(sn0);
        // System.out.println(sn1);

        String schedule_detail_id = req.getParameter("schedule_detail_id");

        int[] seat_id = new int[5];

        ArrayList arraylist = new ArrayList();

        // Map map = req.getParameterMap();
        // Iterator it = map.keySet().iterator();
        // while (it.hasNext()) {
        //     String seatNo = (String)it.next();
        //     String[] hSeatNo = (String[])map.get("seatNo");
        //     for (int i = 0;i<hSeatNo.length;i++) {
        //         System.out.println("seatNo=" + hSeatNo[i]);
        //         seat_id[i] = database.seatNoAdd((String)hSeatNo[i],schedule_detail_id);
                
        //         SeatIdBean sib = new SeatIdBean();
        //         sib.setSeat_number_id(seat_id[i]);
        //         sib.setH_seat_no((String)hSeatNo[i]);
        //         arraylist.add(sib);
        //     }
        // }


        String[] hSeatNo = req.getParameterValues("seatNo");

        for (int i = 0;i<hSeatNo.length;i++) {
            System.out.println("seatNo=" + hSeatNo[i]);
            seat_id[i] = database.seatNoAdd((String)hSeatNo[i],schedule_detail_id);
            
            SeatIdBean sib = new SeatIdBean();
            sib.setSeat_number_id(seat_id[i]);
            sib.setH_seat_no((String)hSeatNo[i]);
            arraylist.add(sib);
        }


        

        // if(!(sn1.equals(null))){
        //     System.out.println("null");
            // int seat_id1 = database.seatNoAdd(sn1);
        // }
        // String seat_id = database.getDB(sn);

        // req.setAttribute("sn0",sn0);
        // req.setAttribute("sn1",sn1);

        req.setAttribute("seat_id",arraylist);

        RequestDispatcher dis = req.getRequestDispatcher("Confirm.jsp");
        dis.forward(req,res);
        
        
    }
}
