package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import profile.MovieProfile;

public class MovieQuery{
	
	public static void main(String[] args){
		
		List<MovieProfile> li = getMovie("青木映画館");
	
	}
	
	
	public static List<MovieProfile> getMovie(String theater_name){

		List<MovieProfile> moviedata = new ArrayList<MovieProfile>();

		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//Oracleに接続する
			Connection cn=
				DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","learn","learn");
			System.out.println("接続完了");
			
			//select文
			String sql="select title,image,to_char(end_period,'yyyy/mm/dd'),to_char(release_period,'yyyy/mm/dd'),Detailed_explanation,schedule_id "+
						"from schedule_table "+
						"where movie_theater_id = (select movie_theater_id from movie_theater_table where name = '"+theater_name+"')" ;
			
			System.out.println(theater_name+"で検索開始");

			//Statementインターフェイスを実装するクラスをインスタンス化する
			Statement st=cn.createStatement();
			
			//select文を実行し
			//ResultSetインターフェイスを実装したクラスの
			//インスタンスが返る
			ResultSet rs=st.executeQuery(sql);
			
			//カーソルを一行だけスクロールし、データをフェッチする
			while(rs.next()){
				MovieProfile prof = new MovieProfile();
				
				String title = rs.getString(1);//1列目のデータを取得
				String image = rs.getString(2);//2列目のデータを取得
				String end_period = rs.getString(3);//3列目のデータを取得
				String release_period = rs.getString(4);//4列目のデータを取得
				String Detailed_explanation = rs.getString(5);//5列目のデータを取得
				String schedule_id = rs.getString(6);//6列目のデータを取得

				prof.setTitle(title);
				prof.setImage(image);
				prof.setEnd_period(end_period);
				prof.setRelease_period(release_period);
				prof.setDetailed_explanation(Detailed_explanation);
				prof.setSchedule_id(schedule_id);

				moviedata.add(prof);
				
			}
			System.out.println(theater_name+"で検索完了");
			System.out.println(moviedata.get(1));			
			
			//Oracleから切断する
			cn.close();

			System.out.println("切断完了");
			System.out.println(" ");

		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("クラスがないみたい。");
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("SQL関連の例外みたい。");
		}catch(Exception e){
			e.printStackTrace();
		}
		return moviedata;
		
	}
}











