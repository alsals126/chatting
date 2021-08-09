package example;
import java.net.InetAddress;

// 특정 서버의 IP주소와 도메인 이름을 알려주는 예제
// (선생님 제공)
public class InetAddressExam2 {

	public static void main(String[] args) throws Exception {
		InetAddress ia= InetAddress.getByName("e-mirim.hs.kr");
		System.out.println(" IP 주소: " + ia.getHostAddress());
		System.out.println(" 도메인 이름: " + ia.getHostName());
	}

}
