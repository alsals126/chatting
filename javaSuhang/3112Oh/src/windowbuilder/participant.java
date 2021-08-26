package windowbuilder;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JPanel;

import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.border.LineBorder;
import javax.swing.UIManager;

public class participant {

	private JFrame frame;
	private JTextArea chatSec;
	private JTextField chatText;
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					participant window = new participant();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public participant() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);

		JPanel chatInfoSec = new JPanel();
		chatInfoSec.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), null),
				"CHAT INFO  ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		chatInfoSec.setBounds(20, 23, 347, 100);
		frame.getContentPane().add(chatInfoSec);
		chatInfoSec.setLayout(null);

		JLabel ipLabel = new JLabel("IP");
		ipLabel.setFont(new Font("굴림", Font.BOLD, 12));
		ipLabel.setBounds(39, 30, 32, 15);
		chatInfoSec.add(ipLabel);

		JTextField ipText = new JTextField();
		ipText.setBounds(114, 26, 179, 21);
		ipText.setBorder(null);
		chatInfoSec.add(ipText);

		JLabel portLabel = new JLabel("Port");
		portLabel.setFont(new Font("굴림", Font.BOLD, 12));
		portLabel.setBounds(39, 65, 32, 15);
		chatInfoSec.add(portLabel);

		JTextField portText = new JTextField();
		portText.setBounds(114, 61, 179, 21);
		portText.setBorder(null);
		chatInfoSec.add(portText);

		RoundedButton1 inBtn = new RoundedButton1("IN");
		inBtn.setBounds(398, 30, 75, 90);
		RoundedButton1 outBtn = new RoundedButton1("OUT");
		outBtn.setBounds(505, 30, 75, 90);
		frame.getContentPane().add(inBtn);
		frame.getContentPane().add(outBtn);

		inBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClientThread clientThread = new ClientThread();
				clientThread.setDaemon(true);
				clientThread.start();
			}
		});

		JPanel mySec = new JPanel();
		mySec.setLayout(null);
		mySec.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), null), " MY  ",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		mySec.setBounds(20, 133, 563, 100);
		frame.getContentPane().add(mySec);

		JLabel myIpLabel = new JLabel("IP");
		myIpLabel.setFont(new Font("굴림", Font.BOLD, 12));
		myIpLabel.setBounds(39, 30, 32, 15);
		mySec.add(myIpLabel);

		JTextField myIpText = new JTextField();
		myIpText.setBackground(UIManager.getColor("Button.background"));
		myIpText.setEditable(false);
		myIpText.setBounds(114, 26, 437, 21);
		myIpText.setBorder(new LineBorder(Color.LIGHT_GRAY));
		mySec.add(myIpText);

		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("굴림", Font.BOLD, 12));
		nameLabel.setBounds(39, 65, 47, 15);
		mySec.add(nameLabel);

		JTextField nameText = new JTextField();
		nameText.setBounds(114, 61, 323, 21);
		nameText.setBorder(null);
		mySec.add(nameText);

		RoundedButton2 changeBtn = new RoundedButton2("CHANGE");
		changeBtn.setBounds(463, 60, 88, 23);
		mySec.add(changeBtn);

		chatSec = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(chatSec);
		scrollPane.setBounds(22, 255, 560, 235);
		frame.getContentPane().add(scrollPane);

		chatText = new JTextField();
		chatText.setBounds(22, 500, 458, 21);
		chatText.setBorder(null);
		frame.getContentPane().add(chatText);

		windowbuilder.RoundedButton2 sendBtn = new windowbuilder.RoundedButton2("SEND");
		sendBtn.setBounds(492, 499, 88, 23);
		frame.getContentPane().add(sendBtn);

		frame.setTitle("\uCC44\uD305\uCC38\uC5EC");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.WHITE);
		frame.setBounds(420, 120, 609, 573);
	}

	class ClientThread extends Thread {
		@Override
		public void run() {
			try {
				socket = new Socket("10.96.123.23", 7777);
				chatSec.append("서버에 접속됐습니다.\n");

				// 데이터 전송을 위한 스트림 생성(입추력 모두)
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();

				// 보조스트림으로 만들어서 데이터전송 작업을 편하게 ※다른 보조스트림 사용
				dis = new DataInputStream(is);
				dos = new DataOutputStream(os);

				while (true) {// 상대방 메시지 받기
					String msg = dis.readUTF();
					chatSec.append(" [SERVER] : " + msg + "\n");
					chatSec.setCaretPosition(chatSec.getText().length());
				}
			} catch (UnknownHostException e) {
				chatSec.append("서버 주소가 이상합니다.\n");
			} catch (IOException e) {
				chatSec.append("서버와 연결이 끊겼습니다.\n");
			}
		}
	}

	// 메시지 전송하는 기능 메소드
	void sendMessage() {
		String msg = chatText.getText(); // TextField에 써있는 글씨를 얻어오기
		chatText.setText(""); // 입력 후 빈칸으로
		chatSec.append(" [Clinet] : " + msg + "\n");// 1.TextArea(채팅창)에 표시
		chatSec.setCaretPosition(chatSec.getText().length());

		// 2.상대방(Server)에게 메시지 전송하기
		// 아웃풋 스트림을 통해 상대방에 데이터 전송
		// 네트워크 작업은 별도의 Thread가 하는 것이 좋음
		Thread t = new Thread() {
			@Override
			public void run() {
				try { // UTF = 유니코드의 규약(포맷), 한글 깨지지 않게 해줌
					dos.writeUTF(msg);
					dos.flush(); // 계속 채팅 위해 close()하면 안됨
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};

		t.start();
	}
}