package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TheaterInsert{
	public static void main(String[] args){
		TheaterInsert.insert("中澤映画館");
	}
	
	public static int insert(String add){
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
			String sql="insert into movie_theater_table(movie_theater_id,name) values(theater_number.NEXTVAL,'"+add+"')";
			
			//Statementインターフェイスを実装するクラスの
			//インスタンスを取得する
			Statement st=cn.createStatement();
			
			//SQLを実行しトランザクションが開始される。処理件数が返される
			count=st.executeUpdate(sql);
			
			System.out.println(count+"件処理完了");
			System.out.println(add+"を追加");
			
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

