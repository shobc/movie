package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ScheduleUpdate{
	public static void main(String[] args){
		ScheduleUpdate.update(14,"2019/12/01 11:00","2019/12/01 13:00","シアター7");
	}
	
	public static int update(int schedule_detail_id,String start_time,String end_time,String theater){
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
			String sql="update schedule_detail_table "+
						"set "+
						"start_time = to_date('"+start_time+"','yyyy/mm/dd hh24:mi'),"+
						"end_time = to_date('"+end_time+"','yyyy/mm/dd hh24:mi'),"+
						"theater = '"+theater+"'" +
						"where schedule_detail_id="+schedule_detail_id;
			
			//Statementインターフェイスを実装するクラスの
			//インスタンスを取得する
			Statement st=cn.createStatement();

			//SQLを実行しトランザクションが開始される。処理件数が返される
			count=st.executeUpdate(sql);
			
			System.out.println(count+"件処理完了");
			System.out.println(start_time+"に変更");
			System.out.println(end_time+"に変更");
			System.out.println(theater+"に変更");

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

