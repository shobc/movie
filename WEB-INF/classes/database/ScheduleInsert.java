package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ScheduleInsert{
	
	public static void main(String[] args){
		ScheduleInsert.insert("2019/11/22 11:00","2019/11/22 13:00","�V�A�^�[2","�Ȃ�����Ǝq������","�؉f���");
	}
	
	public static int insert(String start_time,String end_time,String theater,String title,String theater_name){
		int count = 0; //���������s�������邽�߂̕ϐ�
		try{
			//Driver�C���^�[�t�F�C�X����������N���X�����[�h����
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Connection�C���^�[�t�F�C�X����������N���X��
			//�C���X�^���X��Ԃ�
			Connection cn=
				DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl","learn","learn");//���[�U�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[�[
			
			//�����R�~�b�g��OFF�ɂ���
			cn.setAutoCommit(false);
			
			System.out.println("�ڑ�����");
			
			//SQL����ϐ��Ɋi�[����
			String sql="insert into schedule_detail_table values("+
						"schedule_detail_number.NEXTVAL,"+
						"(SELECT schedule_id FROM schedule_table WHERE title = '"+title+"' "+
						"AND movie_theater_id = (SELECT movie_theater_id FROM movie_theater_table WHERE name = '"+theater_name+"')),"+
						"to_date('"+start_time+"','yyyy/mm/dd hh24:mi'),"+
						"to_date('"+end_time+"','yyyy/mm/dd hh24:mi'),'"+theater+"')" ;

			//Statement�C���^�[�t�F�C�X����������N���X��
			//�C���X�^���X���擾����
			Statement st=cn.createStatement();
			
			//SQL�����s���g�����U�N�V�������J�n�����B�����������Ԃ����
			count=st.executeUpdate(sql);
			
			System.out.println(count+"����������");
			System.out.println(theater_name+"��"+title+"��"+start_time+"��"+end_time+"��"+theater+"��ǉ�");

			//�g�����U�N�V�������R�~�b�g����
			cn.commit();
			
			//�X�e�[�g�����g���N���[�Y����
			st.close();
			
			//RDBMS����ؒf����
			cn.close();
			
			System.out.println("�ؒf����");
			System.out.println(" ");

		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return count;
	}
}

