package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import profile.DayProfile;

public class DayQuery{
	
	public static void main(String[] args){
		
		List<DayProfile> li = getDay("�؉f���","2019/11/22");
		for(int i = 0; i < li.size();i++){
			DayProfile prof = li.get(i);
			System.out.println(prof.getTitle()+"\t"+prof.getSchedule_detail_id());
		
		}	
	
	}
	
	
	public static List<DayProfile> getDay(String theater_name,String day){

		List<DayProfile> daydata = new ArrayList<DayProfile>();

		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//Oracle�ɐڑ�����
			Connection cn=
				DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","learn","learn");
			System.out.println("�ڑ�����");
			
			//select��
			// String sql="SELECT theater, title,"+//SELECT
			// 			"sd.schedule_detail_id "+
			// 			"FROM schedule_detail_table sd "+//FROM
			// 			"LEFT JOIN schedule_table s "+//LEFT JOIN
			// 			"ON sd.SCHEDULE_ID = s.SCHEDULE_ID "+//ON
			// 			"WHERE TO_CHAR(start_time,'yyyy/mm/dd') = '"+day+"' AND s.SCHEDULE_ID = "+//WHERE
			// 			"(SELECT schedule_id FROM schedule_table WHERE title = '"+title+"' "+//SELECT�@���₢���킹
			// 			"AND movie_theater_id = (SELECT movie_theater_id FROM movie_theater_table WHERE name = '"+theater_name+"')) "+
			// 			"AND s.movie_theater_id = "+
			// 			"(SELECT movie_theater_id FROM movie_theater_table WHERE name = '"+theater_name+"') "+
			// 			"GROUP BY title HAVING COUNT(title) > 1" ;
			
			String sql = "select distinct title,s.SCHEDULE_ID,detailed_explanation FROM schedule_table s left join "+
			"schedule_detail_table sd on sd.SCHEDULE_ID = s.SCHEDULE_ID "+
			"WHERE TO_CHAR(start_time,'yyyy/mm/dd') = '"+day+"' "+
			"AND movie_theater_id = (SELECT movie_theater_id FROM movie_theater_table WHERE name = '"+theater_name+"')";

			System.out.println(theater_name+"��"+day+"�Ō����J�n");
			//System.out.println(sql);
			//Statement�C���^�[�t�F�C�X����������N���X���C���X�^���X������
			Statement st=cn.createStatement();
			
			//select�������s��
			//ResultSet�C���^�[�t�F�C�X�����������N���X��
			//�C���X�^���X���Ԃ�
			ResultSet rs=st.executeQuery(sql);
			
			//�J�[�\������s�����X�N���[�����A�f�[�^���t�F�b�`����
			while(rs.next()){
				DayProfile prof = new DayProfile();
				
				String movie_title = rs.getString(1);//1��ڂ̃f�[�^���擾
				String schedule_detail_id = rs.getString(2);//2��ڂ̃f�[�^���擾
				String detailed_explanation = rs.getString(3);//3��ڂ̃f�[�^���擾

				prof.setTitle(movie_title);
				prof.setSchedule_detail_id(schedule_detail_id);
				prof.setDetailed_explanation(detailed_explanation);
				daydata.add(prof);
				
			}
			System.out.println(theater_name+"��"+day+"�Ō�������");			
			
			//Oracle����ؒf����
			cn.close();

			System.out.println("�ؒf����");
			System.out.println(" ");

		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("�N���X���Ȃ��݂����B");
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("SQL�֘A�̗�O�݂����B");
		}catch(Exception e){
			e.printStackTrace();
		}
		return daydata;
		
	}
}











