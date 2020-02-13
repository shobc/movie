import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.CallableStatement;
import java.sql.Types;


public class database {
  // public static void main(String[] args) {
  //   String a = null;
  //   a = database.getReservedSeatDB();
  //   System.out.println(a);
  // }
  // public static String getDB(String sn){
  //   String reservationSeat = "";
  //   Statement st = null;//変数定義
    
  //   //DB接続情報を設定する
  //   String path = "jdbc:oracle:thin:@localhost:1521:orcl";  //接続パス
  //   String id = "learn";    //ログインID
  //   String ps = "learn";  //ログインパスワード

  //   //SQL文を定義する
  //   String sql = "SELECT seat_number_id FROM seat_number_table WHERE seat_number = "+sn;
  //     try{
  //       //JDBCドライバをロードする
  //       Class.forName("oracle.jdbc.driver.OracleDriver");

  //       //DBへのコネクションを作成する
  //       Connection cn=
  //       DriverManager.getConnection(path,id,ps);
  //       System.out.println("接続完了");

  //       //実行するSQL文とパラメータを指定する
  //       st = cn.createStatement();
  //       //SELECTを実行する
  //       ResultSet  rs = st.executeQuery(sql);
          
  //       while (rs.next()) {
  //         String seat = rs.getString(1);

  //         reservationSeat = seat;
  //       }
  //       System.out.println("reservationSeat="+reservationSeat);
  //     }catch(ClassNotFoundException e){
  //       System.out.println(e.getMessage());
  //       e.printStackTrace();
  //     }catch(SQLException e){
  //       System.out.println(e.getMessage());
  //       e.printStackTrace();
  //     }catch(Exception ex){
  //       //例外発生時の処理
  //       ex.printStackTrace();  //エラー内容をコンソールに出力する
  //     }
  //   return reservationSeat;
  // }
  public static String getReservedSeatDB(String schedule_detail_id){
    String reservationSeat = "";
    Statement st = null;//変数定義
    
    //DB接続情報を設定する
    String path = "jdbc:oracle:thin:@localhost:1521:orcl";  //接続パス
    String id = "learn";    //ログインID
    String ps = "learn";  //ログインパスワード

    //SQL文を定義する
    String sql = "SELECT seat_number FROM seat_number_table WHERE schedule_detail_id = "+schedule_detail_id;
      try{
        //JDBCドライバをロードする
        Class.forName("oracle.jdbc.driver.OracleDriver");

        //DBへのコネクションを作成する
        Connection cn=
        DriverManager.getConnection(path,id,ps);
        System.out.println("接続完了");

        //実行するSQL文とパラメータを指定する
        st = cn.createStatement();
        //SELECTを実行する
        ResultSet  rs = st.executeQuery(sql);
          
        while (rs.next()) {
          String seat = rs.getString(1);

          reservationSeat += seat+",";
        }
        System.out.println("reservationSeat="+reservationSeat);
      }catch(ClassNotFoundException e){
        System.out.println(e.getMessage());
        e.printStackTrace();
      }catch(SQLException e){
        System.out.println(e.getMessage());
        e.printStackTrace();
      }catch(Exception ex){
        //例外発生時の処理
        ex.printStackTrace();  //エラー内容をコンソールに出力する
      }
    return reservationSeat;
  }

  //個人ページーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー
  public static ArrayList getMyPage(){
    ArrayList result = new ArrayList();
    Statement st = null;//変数定義
    
    //DB接続情報を設定する
    String path = "jdbc:oracle:thin:@localhost:1521:orcl";  //接続パス
    String id = "learn";    //ログインID
    String ps = "learn";  //ログインパスワード

    //SQL文を定義する
    String sql = "SELECT snt.seat_number_id,snt.seat_number,"+
                 "sdt.schedule_detail_id,sdt.schedule_id,"+
                 "TO_CHAR(start_time,'yyyy/mm/dd hh24:mi'),TO_CHAR(end_time,'yyyy/mm/dd hh24:mi'),sdt.theater"+
                 " FROM seat_number_table snt LEFT JOIN schedule_detail_table sdt"+
                 " ON snt.schedule_detail_id = sdt.schedule_detail_id"+
                 " WHERE snt.seat_number_id"+
                 " IN (SELECT seat_number_id FROM purchase_information_table  WHERE user_id = "+
                 "(SELECT user_id FROM user_table WHERE user_id = 1))";
    System.out.println(sql);

    try{
      //JDBCドライバをロードする
      Class.forName("oracle.jdbc.driver.OracleDriver");

      //DBへのコネクションを作成する
      Connection cn=
      DriverManager.getConnection(path,id,ps);
      System.out.println("接続完了");

      //実行するSQL文とパラメータを指定する
      st = cn.createStatement();
      //SELECTを実行する
      ResultSet  rs = st.executeQuery(sql);
      
      while (rs.next()) {
        MyPageBean myBean = new MyPageBean();

        int seat_number_id  = Integer.parseInt(rs.getString(1));
        String seat_number  = rs.getString(2);
        int schedule_detail_id  = Integer.parseInt(rs.getString(3));
        int schedule_id  = Integer.parseInt(rs.getString(4));
        String start_time  = rs.getString(5);
        String end_time  = rs.getString(6);
        String theater  = rs.getString(7);

        myBean.setSeat_number_id(seat_number_id);
        myBean.setSeat_number(seat_number);
        myBean.setSchedule_detail_id(schedule_detail_id);
        myBean.setSchedule_id(schedule_id);
        myBean.setStart_time(start_time);
        myBean.setEnd_time(end_time);
        myBean.setTheater(theater);

        result.add(myBean);

      }
    }catch(ClassNotFoundException e){
      System.out.println(e.getMessage());
      e.printStackTrace();
    }catch(SQLException e){
      System.out.println(e.getMessage());
      e.printStackTrace();
    }catch(Exception ex){
      //例外発生時の処理
      ex.printStackTrace();  //エラー内容をコンソールに出力する
    }
    return result;
  }

  
    
  public static void seatReservationAdd(SeatBean sBean,int seat_id,String denomination){
    Statement st = null;//変数定義
    //DB接続情報を設定する
    String path = "jdbc:oracle:thin:@localhost:1521:orcl";  //接続パス
    String id = "learn";    //ログインID
    String ps = "learn";  //ログインパスワード

    int seat_number_id = seat_id;
    System.out.println(seat_number_id);
    int user_id = 1;
    // String denomination = sBean.getDenomination();
    String payment = sBean.getPayment();
    String automatic_number = sBean.getAutomatic_number();
    int pay_flag = sBean.getPay_flag();
    
    String sql = "insert into purchase_information_table "+
                 "values(purchase_information_sequence.nextval,"+seat_number_id+","+user_id+",'"+denomination+"','"+payment+"','"+automatic_number+"',"+pay_flag+")";
    System.out.println(sql);
    try{
      //JDBCドライバをロードする
      Class.forName("oracle.jdbc.driver.OracleDriver");
      //DBへのコネクションを作成する
      Connection cn = DriverManager.getConnection(path,id,ps);
      System.out.println("接続完了");
      //実行するSQL文とパラメータを指定する
      st = cn.createStatement();
      //SELECTを実行する
      st.executeUpdate(sql);

    }catch(ClassNotFoundException e){
      System.out.println(e.getMessage());
      e.printStackTrace();
    }catch(SQLException e){
      System.out.println(e.getMessage());
      e.printStackTrace();
    }catch(Exception ex){
      //例外発生時の処理
      ex.printStackTrace();  //エラー内容をコンソールに出力する
    }
  }
  
  // public static void seatNoAdd(String sn){
  //   Statement st = null;//変数定義
  //   //DB接続情報を設定する
  //   String path = "jdbc:oracle:thin:@localhost:1521:orcl";  //接続パス
  //   String id = "learn";    //ログインID
  //   String ps = "learn";  //ログインパスワード
    
  //   String sql = "insert into seat_number_table values(seat_number_sequence.nextval,1,'"+sn+"')";
    
  //   try{
  //     //JDBCドライバをロードする
  //     Class.forName("oracle.jdbc.driver.OracleDriver");
  //     //DBへのコネクションを作成する
  //     Connection cn = DriverManager.getConnection(path,id,ps);
  //     System.out.println("接続完了");
  //     //実行するSQL文とパラメータを指定する
  //     st = cn.createStatement();
  //     //SELECTを実行する
  //     st.executeUpdate(sql);

  //   }catch(ClassNotFoundException e){
  //     System.out.println(e.getMessage());
  //     e.printStackTrace();
  //   }catch(SQLException e){
  //     System.out.println(e.getMessage());
  //     e.printStackTrace();
  //   }catch(Exception ex){
  //     //例外発生時の処理
  //     ex.printStackTrace();  //エラー内容をコンソールに出力する
  //   }
  // }

  public static int seatNoAdd(String sn,String schedule_detail_id){
    CallableStatement stat = null;
    int seat_id = 0;
    //DB接続情報を設定する
    String path = "jdbc:oracle:thin:@localhost:1521:orcl";  //接続パス
    String id = "learn";    //ログインID
    String ps = "learn";  //ログインパスワード
    
    String sql = "begin\n "+
                 "insert into seat_number_table "+
                 "values(seat_number_sequence.nextval,"+schedule_detail_id+",?) "+
                 "returning seat_number_id into ?;\n "+
                 "end;";

    
                 
    try{

      //JDBCドライバをロードする
      Class.forName("oracle.jdbc.driver.OracleDriver");
      //DBへのコネクションを作成する
      Connection cn = DriverManager.getConnection(path,id,ps);
      System.out.println("接続完了");


      stat = cn.prepareCall(sql);
      stat.setString(1,sn);
      stat.registerOutParameter(2, Types.INTEGER);
      stat.execute();

      seat_id = Integer.parseInt(stat.getString(2));
      System.out.println("seat_id="+seat_id);

    }catch(ClassNotFoundException e){
      System.out.println(e.getMessage());
      e.printStackTrace();
    }catch(SQLException e){
      System.out.println(e.getMessage());
      e.printStackTrace();
    }catch(Exception ex){
      //例外発生時の処理
      ex.printStackTrace();  //エラー内容をコンソールに出力する
    }
    return seat_id;
  }

}
