package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TheaterDelete{
	public static void main(String[] args){
		TheaterDelete.delete("�؉f���");
	}
	
	public static int delete(String theater_name){
	//public static int delete(){
		int count = 0; //���������s�������邽�߂̕ϐ�
		try{
			//Driver�C���^�[�t�F�C�X����������N���X�����[�h����
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Connection�C���^�[�t�F�C�X����������N���X��
			//�C���X�^���X��Ԃ�
			Connection cn=
				DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl","learn","learn");
			
			//�����R�~�b�g��OFF�ɂ���
			cn.setAutoCommit(false);
			
			System.out.println("�ڑ�����");
			
			//SQL����ϐ��Ɋi�[����
			String sql="delete from schedule_detail_table";

			String sql2="delete from schedule_table";

			String sql3="delete from movie_theater_table where name='"+theater_name+"'";
			//Statement�C���^�[�t�F�C�X����������N���X��
			//�C���X�^���X���擾����
			Statement st=cn.createStatement();
			count = st.executeUpdate(sql);
			count = st.executeUpdate(sql2);
			count = st.executeUpdate(sql3);

			
			System.out.println(count+"����������");
			System.out.println(theater_name+"���폜");

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

