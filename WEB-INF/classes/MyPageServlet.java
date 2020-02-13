import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class MyPageServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
       req.setCharacterEncoding("windows-31j");
       ArrayList result = new ArrayList();

       result = database.getMyPage();
       
       System.out.println("result="+result);

       req.setAttribute("result",result);

	   RequestDispatcher dis = req.getRequestDispatcher("myPage.jsp");
	   dis.forward(req,res);

    }
}