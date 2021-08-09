package example;
import java.net.InetAddress;

// 현재 컴퓨터의 IP주소를 알려주는 예제
// (선생님 제공)
public class InetAddressExam {

	public static void main(String[] args) throws Exception {
		InetAddress ia= InetAddress.getLocalHost();
		System.out.println(" IP 주소  :"+ia.getHostAddress());
	}

}
