package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MovieUpdate{
	public static void main(String[] args){
		MovieUpdate.update(40,"�Ȃ�����Ǝq������","1.png","2019/12/01 11:00","2019/12/01 13:00","�Ȃ�����Ǝq�������̂��̂�����");
	}
	
	public static int update(int schedule_id,String title,String image,String release_period,String end_period,String Detailed_explanation){
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
			String sql="update schedule_table "+
						"set "+
						"title = '"+title+"',"+
						"image = '"+image+"',"+
						"release_period = to_date('"+release_period+"','yyyy/mm/dd')," +
						"end_period = to_date('"+end_period+"','yyyy/mm/dd'),"+
						"Detailed_explanation = '"+Detailed_explanation+"'"+
						"where schedule_id="+schedule_id;
			
			//Statement�C���^�[�t�F�C�X����������N���X��
			//�C���X�^���X���擾����
			Statement st=cn.createStatement();

			//SQL�����s���g�����U�N�V�������J�n�����B�����������Ԃ����
			count=st.executeUpdate(sql);
			
			System.out.println(count+"����������");
			System.out.println(title+"�ɕύX");
			System.out.println(image+"�ɕύX");
			System.out.println(release_period+"�ɕύX");
			System.out.println(end_period+"�ɕύX");
			System.out.println(Detailed_explanation+"�ɕύX");

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

