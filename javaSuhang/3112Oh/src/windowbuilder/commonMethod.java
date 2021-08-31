package windowbuilder;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class CommonMethod {
	DataInputStream dis;
	DataOutputStream dos;
	
	String myIp() {
		InetAddress local;
		try {
			local = InetAddress.getLocalHost();
			return local.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return "[오류발생] 관리자에게 문의주세요";
		}
	}
}
