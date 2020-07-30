
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
public class mainServer {
	public static ArrayList<Socket> socketList = new ArrayList<Socket>();//创建socket的list数组
    public static void main(String[] args)throws IOException
    {
        ServerSocket ss = new ServerSocket(8888);//服务套接字对象
        System.out.println("Mobile Server starting");
        
        while(true)
        {
        	System.out.println("Mobile Server running");
           Socket s = ss.accept();//等待连接
           socketList.add(s);  
           
           new Thread(new ServerThread(s)).start();
        
        }
    }
}     
