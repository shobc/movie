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
		//文字コード
		req.setCharacterEncoding("Windows-31J");

		//パラメータの受け取り
		String theater_name = req.getParameter("theater_name");

		//パラメータが送られてこなかったときに映画館名を取得　保持のため
		if(theater_name==null){
			theater_name = (String)req.getAttribute("theater_name");
		}
		
		
		//データベースから映画のデータをもらう
		List<MovieProfile> movielist=getMovieData(theater_name);
		
		//パラメータを転送
		req.setAttribute("movielist",movielist);

		//映画館名の保持のため
		req.setAttribute("theater_name",theater_name);
		
		//転送先を指定
		RequestDispatcher dis = req.getRequestDispatcher("/kanrimovie");
		
		//転送
		dis.forward(req,res);
	}
	
	public List<MovieProfile> getMovieData(String theater_name){
		List<MovieProfile> movielist=MovieQuery.getMovie(theater_name);
			
		return movielist;
	
	}
}
