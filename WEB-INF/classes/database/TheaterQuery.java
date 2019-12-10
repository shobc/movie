package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import profile.TheaterProfile;

public class TheaterQuery{
	
	public static void main(String[] args){
		
		List<TheaterProfile> li = getTheater();

		for(int i = 0; i < li.size();i++){
			TheaterProfile prof = li.get(i);
			System.out.println(prof.getName());
		
		}
	
	
	}
	
	public static List<TheaterProfile> getTheater(){

		List<TheaterProfile> theaterdata = new ArrayList<TheaterProfile>();

		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//Oracleに接続する
			Connection cn=
				DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","learn","learn");
			System.out.println("接続完了");
			
			//select文
			String sql="select name from movie_theater_table";

			//Statementインターフェイスを実装するクラスをインスタンス化する
			Statement st=cn.createStatement();

			//select文を実行し
			//ResultSetインターフェイスを実装したクラスの
			//インスタンスが返る
			ResultSet rs=st.executeQuery(sql);

			//カーソルを一行だけスクロールし、データをフェッチする
			while(rs.next()){
				TheaterProfile prof = new TheaterProfile();
				
				String name = rs.getString(1);
				prof.setName(name);
				
				theaterdata.add(prof);

			}
			System.out.println("映画館　全表示");

			
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
		return theaterdata;
		
	}
}











