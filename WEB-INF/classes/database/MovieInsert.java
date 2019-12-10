package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MovieInsert{
	
	public static void main(String[] args){
		MovieInsert.insert("�؉f���","�������Ǝq������","1.png","2019-09-18","2019-09-20","�������Ǝq�������̂��̂�����");
	}
	
	public static int insert(String theater_name,String title,String image,String release_period,String end_period,String Detailed_explanation){
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
			String sql="insert into schedule_table values("+
							"schedule_number.NEXTVAL"+
							",(SELECT movie_theater_id FROM movie_theater_table WHERE name = '"+theater_name+"')"+
							",'"+title+"'"+
							",'"+image+"'"+
							",'"+end_period+"'"+
							",'"+release_period+"'"+
							",'"+Detailed_explanation+"'"+
						")";
			
			//Statement�C���^�[�t�F�C�X����������N���X��
			//�C���X�^���X���擾����
			Statement st=cn.createStatement();
			
			//SQL�����s���g�����U�N�V�������J�n�����B�����������Ԃ����
			count=st.executeUpdate(sql);
			
			System.out.println(count+"����������");
			System.out.println(theater_name+"��"+title+"��"+image+"��"+release_period+"��"+end_period+"��"+Detailed_explanation+"��ǉ�");

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

