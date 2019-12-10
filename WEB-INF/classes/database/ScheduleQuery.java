package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import profile.ScheduleProfile;

public class ScheduleQuery{
	
	public static void main(String[] args){
		
		List<ScheduleProfile> li = getSchedule("青木映画館","なかざわと子犬たち");
		for(int i = 0; i < li.size();i++){
			ScheduleProfile prof = li.get(i);
			System.out.println(prof.getTheater()+"\t"+prof.getTitle()+"\t"+prof.getRelease_period()+"\t"+prof.getStart_time()+"\t"+prof.getEnd_time());
		
		}	
	
	}
	
	
	public static List<ScheduleProfile> getSchedule(String theater_name,String title){

		List<ScheduleProfile> scheduledata = new ArrayList<ScheduleProfile>();

		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//Oracleに接続する
			Connection cn=
				DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","learn","learn");
			System.out.println("接続完了");
			
			//select文
			String sql="SELECT theater,title,"+//SELECT
						"TO_CHAR(start_time,'yyyy/mm/dd'),TO_CHAR(start_time,'hh24:mi'),"+
						"TO_CHAR(end_time,'hh24:mi'),sd.schedule_detail_id "+
						"FROM schedule_detail_table sd "+//FROM
						"LEFT JOIN schedule_table s "+//LEFT JOIN
						"ON sd.SCHEDULE_ID = s.SCHEDULE_ID "+//ON
						"WHERE s.SCHEDULE_ID = "+//WHERE
						"(SELECT schedule_id FROM schedule_table WHERE title = '"+title+"' "+//SELECT　副問い合わせ
						"AND movie_theater_id = (SELECT movie_theater_id FROM movie_theater_table WHERE name = '"+theater_name+"')) "+
						"AND s.movie_theater_id = "+
						"(SELECT movie_theater_id FROM movie_theater_table WHERE name = '"+theater_name+"') "+
						"ORDER BY start_time ASC" ;
			
			System.out.println(theater_name+"の"+title+"で検索開始");

			//Statementインターフェイスを実装するクラスをインスタンス化する
			Statement st=cn.createStatement();
			
			//select文を実行し
			//ResultSetインターフェイスを実装したクラスの
			//インスタンスが返る
			ResultSet rs=st.executeQuery(sql);
			
			//カーソルを一行だけスクロールし、データをフェッチする
			while(rs.next()){
				ScheduleProfile prof = new ScheduleProfile();
				
				String theater = rs.getString(1);//1列目のデータを取得
				String movie_title = rs.getString(2);//2列目のデータを取得
				String release_period = rs.getString(3);//3列目のデータを取得
				String start_time = rs.getString(4);//4列目のデータを取得
				String end_time = rs.getString(5);//5列目のデータを取得
				String schedule_detail_id = rs.getString(6);//6列目のデータを取得

				prof.setTheater(theater);
				prof.setTitle(movie_title);
				prof.setRelease_period(release_period);
				prof.setStart_time(start_time);
				prof.setEnd_time(end_time);
				prof.setSchedule_detail_id(schedule_detail_id);

				scheduledata.add(prof);
				
			}
			System.out.println(theater_name+"の"+title+"で検索完了");			
			
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
		return scheduledata;
		
	}
}











