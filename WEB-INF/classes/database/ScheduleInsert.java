package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ScheduleInsert{
	
	public static void main(String[] args){
		ScheduleInsert.insert("2019/11/22 11:00","2019/11/22 13:00","シアター2","なかざわと子犬たち","青木映画館");
	}
	
	public static int insert(String start_time,String end_time,String theater,String title,String theater_name){
		int count = 0; //処理した行数を入れるための変数
		try{
			//Driverインターフェイスを実装するクラスをロードする
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Connectionインターフェイスを実装するクラスの
			//インスタンスを返す
			Connection cn=
				DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl","learn","learn");//ユーザーーーーーーーーーーーーーーーーーー
			
			//自動コミットをOFFにする
			cn.setAutoCommit(false);
			
			System.out.println("接続完了");
			
			//SQL文を変数に格納する
			String sql="insert into schedule_detail_table values("+
						"schedule_detail_number.NEXTVAL,"+
						"(SELECT schedule_id FROM schedule_table WHERE title = '"+title+"' "+
						"AND movie_theater_id = (SELECT movie_theater_id FROM movie_theater_table WHERE name = '"+theater_name+"')),"+
						"to_date('"+start_time+"','yyyy/mm/dd hh24:mi'),"+
						"to_date('"+end_time+"','yyyy/mm/dd hh24:mi'),'"+theater+"')" ;

			//Statementインターフェイスを実装するクラスの
			//インスタンスを取得する
			Statement st=cn.createStatement();
			
			//SQLを実行しトランザクションが開始される。処理件数が返される
			count=st.executeUpdate(sql);
			
			System.out.println(count+"件処理完了");
			System.out.println(theater_name+"の"+title+"に"+start_time+"と"+end_time+"と"+theater+"を追加");

			//トランザクションをコミットする
			cn.commit();
			
			//ステートメントをクローズする
			st.close();
			
			//RDBMSから切断する
			cn.close();
			
			System.out.println("切断完了");
			System.out.println(" ");

		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return count;
	}
}

