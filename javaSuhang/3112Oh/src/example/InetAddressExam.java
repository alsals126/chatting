package example;
import java.net.InetAddress;

// ���� ��ǻ���� IP�ּҸ� �˷��ִ� ����
// (������ ����)
public class InetAddressExam {

	public static void main(String[] args) throws Exception {
		InetAddress ia= InetAddress.getLocalHost();
		System.out.println(" IP �ּ�  :"+ia.getHostAddress());
	}

}
