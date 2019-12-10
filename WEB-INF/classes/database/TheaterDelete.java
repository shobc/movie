package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TheaterDelete{
	public static void main(String[] args){
		TheaterDelete.delete("青木映画館");
	}
	
	public static int delete(String theater_name){
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
			String sql="delete from schedule_detail_table";

			String sql2="delete from schedule_table";

			String sql3="delete from movie_theater_table where name='"+theater_name+"'";
			//Statementインターフェイスを実装するクラスの
			//インスタンスを取得する
			Statement st=cn.createStatement();
			count = st.executeUpdate(sql);
			count = st.executeUpdate(sql2);
			count = st.executeUpdate(sql3);

			
			System.out.println(count+"件処理完了");
			System.out.println(theater_name+"を削除");

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

