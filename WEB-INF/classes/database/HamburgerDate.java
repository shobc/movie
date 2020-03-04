package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import profile.TheaterProfile;

public class HamburgerDate{

public static void main(String[] args){

}


public List<TheaterProfile> getTheaterDetails(){

List<TheaterProfile> daydetailsdata = new ArrayList<TheaterProfile>();

try{
    Class.forName("oracle.jdbc.driver.OracleDriver");

    //Oracleに接続する
    
    Connection cn=
    DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","learn","learn");
    System.out.println("接続完了");

    //for(int i = 0;i<=daydetailsdata.size()/6;i++){

    //select文
        String sql="SELECT name FROM movie_theater_table";

    //System.out.println(theater_name+"の"+day+"で検索開始");
    // System.out.println(sql);
    //Statementインターフェイスを実装するクラスをインスタンス化する
    Statement st=cn.createStatement();

    //select文を実行し
    //ResultSetインターフェイスを実装したクラスの
    //インスタンスが返る
    ResultSet rs=st.executeQuery(sql);

    //カーソルを一行だけスクロールし、データをフェッチする
    while(rs.next()){
    TheaterProfile prof = new TheaterProfile();

    String theater = rs.getString(1);//1列目のデータを取得
//    String movie_title = rs.getString(2);//2列目のデータを取得
//    String release_period = rs.getString(3);//3列目のデータを取得
//    String start_time = rs.getString(4);//4列目のデータを取得
//    String end_time = rs.getString(5);//5列目のデータを取得
//    String schedule_detail_id = rs.getString(6);//6列目のデータを取得

    prof.setName(theater);
//    prof.setTitle(movie_title);
//    prof.setRelease_period(release_period);
//    prof.setStart_time(start_time);
//    prof.setEnd_time(end_time);
//    prof.setSchedule_detail_id(schedule_detail_id);

    daydetailsdata.add(prof);

    }
//    System.out.println(theater_name+"の"+day+"で検索完了");			
    //}
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
    return daydetailsdata;

    }
}











