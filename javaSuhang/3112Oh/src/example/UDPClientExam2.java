package example;
import java.io.*;
import java.net.*;

public class UDPClientExam2 {

	public static void main(String[] args) throws Exception{
		while(true){
			System.out.print("���� �޽��� : ");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String msg = in.readLine();
			DatagramPacket dp =new DatagramPacket(msg.getBytes(),
		        msg.getBytes().length, InetAddress.getByName("127.0.0.1"),3000);
			DatagramSocket ds = new DatagramSocket();
			ds.send(dp);
		}
	}

}