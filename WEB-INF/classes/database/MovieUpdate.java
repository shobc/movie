package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MovieUpdate{
	public static void main(String[] args){
		MovieUpdate.update(40,"なかざわと子犬たち","1.png","2019/12/01 11:00","2019/12/01 13:00","なかざわと子犬たちのものだたり");
	}
	
	public static int update(int schedule_id,String title,String image,String release_period,String end_period,String Detailed_explanation){
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
			String sql="update schedule_table "+
						"set "+
						"title = '"+title+"',"+
						"image = '"+image+"',"+
						"release_period = to_date('"+release_period+"','yyyy/mm/dd')," +
						"end_period = to_date('"+end_period+"','yyyy/mm/dd'),"+
						"Detailed_explanation = '"+Detailed_explanation+"'"+
						"where schedule_id="+schedule_id;
			
			//Statementインターフェイスを実装するクラスの
			//インスタンスを取得する
			Statement st=cn.createStatement();

			//SQLを実行しトランザクションが開始される。処理件数が返される
			count=st.executeUpdate(sql);
			
			System.out.println(count+"件処理完了");
			System.out.println(title+"に変更");
			System.out.println(image+"に変更");
			System.out.println(release_period+"に変更");
			System.out.println(end_period+"に変更");
			System.out.println(Detailed_explanation+"に変更");

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

