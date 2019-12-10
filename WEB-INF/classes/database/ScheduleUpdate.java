package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ScheduleUpdate{
	public static void main(String[] args){
		ScheduleUpdate.update(14,"2019/12/01 11:00","2019/12/01 13:00","�V�A�^�[7");
	}
	
	public static int update(int schedule_detail_id,String start_time,String end_time,String theater){
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
			String sql="update schedule_detail_table "+
						"set "+
						"start_time = to_date('"+start_time+"','yyyy/mm/dd hh24:mi'),"+
						"end_time = to_date('"+end_time+"','yyyy/mm/dd hh24:mi'),"+
						"theater = '"+theater+"'" +
						"where schedule_detail_id="+schedule_detail_id;
			
			//Statement�C���^�[�t�F�C�X����������N���X��
			//�C���X�^���X���擾����
			Statement st=cn.createStatement();

			//SQL�����s���g�����U�N�V�������J�n�����B�����������Ԃ����
			count=st.executeUpdate(sql);
			
			System.out.println(count+"����������");
			System.out.println(start_time+"�ɕύX");
			System.out.println(end_time+"�ɕύX");
			System.out.println(theater+"�ɕύX");

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

