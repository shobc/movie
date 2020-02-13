
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

public class DayServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException{
		doGet(req, res);
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException{
		//�����R�[�h
		req.setCharacterEncoding("windows-31j");

		//�Z�b�V��������
		HttpSession session = req.getSession();

		//�J�����_�[����
        Calendar cal = Calendar.getInstance();
 
        //�����̓��t�����擾�@�t�H�[�}�b�g�@yyyy/MM/dd
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        // System.out.println(sdf.format(cal.getTime()));

		//day�ɂ��傤�̓��t�������
		String day = sdf.format(cal.getTime());

		//
		if(session.getAttribute("date")!=null){
			day = (String)session.getAttribute("date");
			session.removeAttribute("date");
		}

		//�f��َ󂯎��
		String theater_name = req.getParameter("theater_name");

		//�󕶎����Ɛ؉f���
		if(theater_name.equals("")){
			theater_name = "�؉f���";
		}
		

		


		//�f�[�^�x�[�X���烊�X�g�����炢����
		List<DayProfile> daylist=getDayData(theater_name,day);
		//�f�[�^�x�[�X���烊�X�g�����炢����
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

		//�p�����[�^��JSP�ɓ]��������
		// req.setAttribute("daylist",daylist);
		req.setAttribute("daylist",detailsMovie);
		req.setAttribute("theater_name",theater_name);	
		
		//�]�����JSP���w��
		RequestDispatcher dis = req.getRequestDispatcher("/movietop");
		
		//JSP�ɓ]��
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

