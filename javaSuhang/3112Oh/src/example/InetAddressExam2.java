package example;
import java.net.InetAddress;

// Ư�� ������ IP�ּҿ� ������ �̸��� �˷��ִ� ����
// (������ ����)
public class InetAddressExam2 {

	public static void main(String[] args) throws Exception {
		InetAddress ia= InetAddress.getByName("e-mirim.hs.kr");
		System.out.println(" IP �ּ�: " + ia.getHostAddress());
		System.out.println(" ������ �̸�: " + ia.getHostName());
	}

}