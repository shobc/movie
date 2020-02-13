import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;


public class ReservationServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
       req.setCharacterEncoding("windows-31j");

    //    ArrayList arraylist = new ArrayList();

    //     Map map = req.getParameterMap();
    //     Iterator it = map.keySet().iterator();
    //     while (it.hasNext()) {
    //         String seat_id = (String)it.next();
    //         String[] sseat_id = (String[])map.get(seat_id);
    //         System.out.println("seat_id=" + sseat_id);

    //     }

    //    String seat_id = req.getParameter("seat_id");
    //    System.out.println("seat_id="+seat_id);

       String[] sh = req.getParameterValues("seat_id");


       String[] denomination = new String[5];
        for(int i = 0;i < sh.length;i++){
            String d = req.getParameter("denomination"+sh[i]);
            denomination[i] = d;
            System.out.println("denomination"+i+"="+denomination[i]);
     
        }

    //    String denomination = req.getParameter("denomination");
    //    System.out.println("denomination="+denomination);
       String payment = req.getParameter("payment");
       System.out.println("payment="+payment);

       int pay_flag=1;
       if(payment.equals("money")){
           pay_flag=0;
       }

       String automatic_number = "aaaaaaaa";

       

       ArrayList arraylist = new ArrayList();


       for (int i = 0;i<sh.length;i++) {
        
        System.out.println("seatId=" + sh[i]);
        String sSeatId = sh[i];
        int iSeatId = Integer.parseInt(sSeatId);
        String sDenomination = denomination[i];
        SeatBean sBean = new SeatBean();
        sBean.setDenomination(sDenomination);
        sBean.setPayment(payment);
        sBean.setAutomatic_number(automatic_number);
        sBean.setPay_flag(pay_flag);

        arraylist.add(sBean);

        database.seatReservationAdd(sBean,iSeatId,sDenomination);
    }
       
        


       //String sis = (String)req.getParameter("seat_id");
    //    int seat_id = Integer.parseInt(sis);
    //    System.out.println("seat_id="+seat_id);

    //    database.seatReservationAdd(sBean,seat_id);
       req.setAttribute("sBean",arraylist);

	   RequestDispatcher dis = req.getRequestDispatcher("verification.jsp");
	   dis.forward(req,res);

    }
}

// String[] sh = req.getParameterValues("seat_id");
//         for (int i = 0;i<sh.length;i++) {
//             String[] denomination = req.getParameterValues("denomination");
//             System.out.println("denomination[0]="+denomination[0]);
//             System.out.println("denomination[1]="+denomination[1]);
//             for (int j = 0;j<sh.length;j++) {
//                 String denominationString = denomination[j];
//                 String payment = req.getParameter("payment");
//                 System.out.println("payment="+payment);

//                 int pay_flag=1;
//                 if(payment.equals("money")){
//                     pay_flag=0;
//                 }

//                 String automatic_number = "aaaaaaaa";

//                 SeatBean sBean = new SeatBean();
//                 sBean.setDenomination(denominationString);
//                 sBean.setPayment(payment);
//                 sBean.setAutomatic_number(automatic_number);
//                 sBean.setPay_flag(pay_flag);
//             }
//             System.out.println("seatId=" + sh[i]);
//             String sSeatId = sh[i];
//             int iSeatId = Integer.parseInt(sSeatId);
//             database.seatReservationAdd(sBean,iSeatId);
//             req.setAttribute("sBean",sBean);
//         }