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

			//Oracle�ɐڑ�����
			Connection cn=
				DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","learn","learn");
			System.out.println("�ڑ�����");
			
			//select��
			String sql="select name from movie_theater_table";

			//Statement�C���^�[�t�F�C�X����������N���X���C���X�^���X������
			Statement st=cn.createStatement();

			//select�������s��
			//ResultSet�C���^�[�t�F�C�X�����������N���X��
			//�C���X�^���X���Ԃ�
			ResultSet rs=st.executeQuery(sql);

			//�J�[�\������s�����X�N���[�����A�f�[�^���t�F�b�`����
			while(rs.next()){
				TheaterProfile prof = new TheaterProfile();
				
				String name = rs.getString(1);
				prof.setName(name);
				
				theaterdata.add(prof);

			}
			System.out.println("�f��ف@�S�\��");

			
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
		return theaterdata;
		
	}
}











