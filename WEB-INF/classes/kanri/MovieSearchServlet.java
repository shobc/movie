package kanri;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import database.MovieQuery;
import profile.MovieProfile;

public class MovieSearchServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException{
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException{
		//�����R�[�h
		req.setCharacterEncoding("Windows-31J");

		//�p�����[�^�̎󂯎��
		String theater_name = req.getParameter("theater_name");

		//�p�����[�^�������Ă��Ȃ������Ƃ��ɉf��ٖ����擾�@�ێ��̂���
		if(theater_name==null){
			theater_name = (String)req.getAttribute("theater_name");
		}
		
		
		//�f�[�^�x�[�X����f��̃f�[�^�����炤
		List<MovieProfile> movielist=getMovieData(theater_name);
		
		//�p�����[�^��]��
		req.setAttribute("movielist",movielist);

		//�f��ٖ��̕ێ��̂���
		req.setAttribute("theater_name",theater_name);
		
		//�]������w��
		RequestDispatcher dis = req.getRequestDispatcher("/kanrimovie");
		
		//�]��
		dis.forward(req,res);
	}
	
	public List<MovieProfile> getMovieData(String theater_name){
		List<MovieProfile> movielist=MovieQuery.getMovie(theater_name);
			
		return movielist;
	
	}
}
