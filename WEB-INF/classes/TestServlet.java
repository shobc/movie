
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;

public class TestServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException{
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException{
		//�����R�[�h
		req.setCharacterEncoding("Windows-31J");

		String seat = SeatLayout.seatingChart("2");
		
		String data = "<p>"+seat+"</p>";

		req.setAttribute("data",data);
		
		//�]�����JSP���w��
		RequestDispatcher dis = req.getRequestDispatcher("/test");
		
		//JSP�ɓ]��
		dis.forward(req,res);
	}
}

