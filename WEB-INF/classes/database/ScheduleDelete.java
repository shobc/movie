package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class ScheduleDelete{
	public static void main(String[] args){
		ScheduleDelete.delete(5);
	}
	
	public static int delete(int dlt){
	//public static int delete(){
		int count = 0; //処理した行数を入れるための変数
		try{
			//Driverインターフェイスを実装するクラスをロードする
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Connectionインターフェイスを実装するクラスの
			//インスタンスを返す
			Connection cn=
				DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl","learn","learn");
			
			//自動コミットをOFFにする
			cn.setAutoCommit(false);
			
			System.out.println("接続完了");
			
			//SQL文を変数に格納する
			String sql="delete from schedule_detail_table where schedule_detail_id='"+dlt+"'";
			//Statementインターフェイスを実装するクラスの
			//インスタンスを取得する
			Statement st=cn.createStatement();
			count = st.executeUpdate(sql);
			//SQLを実行しトランザクションが開始される。処理件数が返される
			// count=st.executeUpdate();
			
			System.out.println(count+"件処理完了");
			System.out.println("上映スケジュールを削除");

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

