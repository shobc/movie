package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MovieInsert{
	
	public static void main(String[] args){
		MovieInsert.insert("青木映画館","あおきと子犬たち","1.png","2019-09-18","2019-09-20","あおきと子犬たちのものだたり");
	}
	
	public static int insert(String theater_name,String title,String image,String release_period,String end_period,String Detailed_explanation){
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
			String sql="insert into schedule_table values("+
							"schedule_number.NEXTVAL"+
							",(SELECT movie_theater_id FROM movie_theater_table WHERE name = '"+theater_name+"')"+
							",'"+title+"'"+
							",'"+image+"'"+
							",'"+end_period+"'"+
							",'"+release_period+"'"+
							",'"+Detailed_explanation+"'"+
						")";
			
			//Statementインターフェイスを実装するクラスの
			//インスタンスを取得する
			Statement st=cn.createStatement();
			
			//SQLを実行しトランザクションが開始される。処理件数が返される
			count=st.executeUpdate(sql);
			
			System.out.println(count+"件処理完了");
			System.out.println(theater_name+"の"+title+"に"+image+"と"+release_period+"と"+end_period+"と"+Detailed_explanation+"を追加");

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

