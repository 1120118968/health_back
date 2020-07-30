import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.text.AbstractDocument.Content;

public class ServerThread implements Runnable {
public String content = null;
Socket s = null;
BufferedReader br = null;
public ServerThread() {
}
public ServerThread(Socket s) throws IOException {
	this.s = s;
	br = new BufferedReader(new InputStreamReader(s.getInputStream(),"utf-8")); 
}
public void run() {
	try {
		while ((content = readFromClient()) != null) {
			String socketuser = content.split(" ")[4];
			for (Socket s : mainServer.socketList) {
			if(s.getInetAddress().getHostAddress().equals(socketuser)){
				OutputStream os = s.getOutputStream();
				String[] strarray = content.split(" ");
				for (int i = 0; i < strarray.length; i++)
					System.out.println(strarray[i] + " ");
				System.out.println("用户开始登录，登录信息为"+content);
		        String user = strarray[0];
				String school = strarray[1];
				String login_psd = strarray[2];
				String command = strarray[3];

	
					switch (command) {
					case "登录":
						 try {
						 if(Login(user,school,login_psd)==true){
						 os.write((user+ " "+school+" "+"Y" +"\n").getBytes("utf-8"));
						 System.out.println("登录成功，信息返回成功");
						 }else{
						 os.write((user+ " "+school+" "+"N" +"\n").getBytes("utf-8"));
						 System.out.println("登陆失败，信息返回成功");
						 }
						 } catch (SQLException e) {
						 e.printStackTrace();
						 } catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;

					case "注册":
						 Register(user,school,login_psd);
						 os.write((user+ " "+school+" "+"YR" + "\n").getBytes("utf-8"));
						 System.out.println("注册成功，信息返回成功");
						 break;
					}
			}
			}
		}
	}catch (IOException e)
	{
		e.printStackTrace();
	} 
}
		private String readFromClient()
		{
			try
			{
				return br.readLine();
			}
			catch (IOException e)
			{
				mainServer.socketList.remove(s);
			}
			return null;
		}
		public boolean Login(String user, String school, String login_psd) throws SQLException, ClassNotFoundException {
			Mysql m = new Mysql(user, school, login_psd);
			if (m.selectSql(user, school, login_psd) == true) {
				//user = null ;school = null 
				return true;
			} else {
				return false;
			}
		}
		public void Register(String user, String school, String login_psd) {
		
		//	student st = new student(user,school,login_psd);
		//	st.setLoginTimer(time);
			Mysql.insertData(user, school, login_psd);
		}
}  

