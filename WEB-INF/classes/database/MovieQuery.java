package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import profile.MovieProfile;

public class MovieQuery{
	
	public static void main(String[] args){
		
		List<MovieProfile> li = getMovie("�؉f���");
	
	}
	
	
	public static List<MovieProfile> getMovie(String theater_name){

		List<MovieProfile> moviedata = new ArrayList<MovieProfile>();

		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//Oracle�ɐڑ�����
			Connection cn=
				DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","learn","learn");
			System.out.println("�ڑ�����");
			
			//select��
			String sql="select title,image,to_char(end_period,'yyyy/mm/dd'),to_char(release_period,'yyyy/mm/dd'),Detailed_explanation,schedule_id "+
						"from schedule_table "+
						"where movie_theater_id = (select movie_theater_id from movie_theater_table where name = '"+theater_name+"')" ;
			
			System.out.println(theater_name+"�Ō����J�n");

			//Statement�C���^�[�t�F�C�X����������N���X���C���X�^���X������
			Statement st=cn.createStatement();
			
			//select�������s��
			//ResultSet�C���^�[�t�F�C�X�����������N���X��
			//�C���X�^���X���Ԃ�
			ResultSet rs=st.executeQuery(sql);
			
			//�J�[�\������s�����X�N���[�����A�f�[�^���t�F�b�`����
			while(rs.next()){
				MovieProfile prof = new MovieProfile();
				
				String title = rs.getString(1);//1��ڂ̃f�[�^���擾
				String image = rs.getString(2);//2��ڂ̃f�[�^���擾
				String end_period = rs.getString(3);//3��ڂ̃f�[�^���擾
				String release_period = rs.getString(4);//4��ڂ̃f�[�^���擾
				String Detailed_explanation = rs.getString(5);//5��ڂ̃f�[�^���擾
				String schedule_id = rs.getString(6);//6��ڂ̃f�[�^���擾

				prof.setTitle(title);
				prof.setImage(image);
				prof.setEnd_period(end_period);
				prof.setRelease_period(release_period);
				prof.setDetailed_explanation(Detailed_explanation);
				prof.setSchedule_id(schedule_id);

				moviedata.add(prof);
				
			}
			System.out.println(theater_name+"�Ō�������");
			System.out.println(moviedata.get(1));			
			
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
		return moviedata;
		
	}
}











