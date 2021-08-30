package windowbuilder;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JPanel;

import javax.swing.border.TitledBorder;

import javax.swing.border.EtchedBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class Host {
	private JFrame frame;
	private JTextField ipText;
	private JTextField portText;
	private JTextField nameText;
	private JTextArea chatSec;
	private JTextField chatText;
	ServerSocket serverSocket;
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;
	
	CommonMethod common = new CommonMethod();
	String myName = "Host";
	String clientName = "Client";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Host window = new Host();
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
	public Host() {
		initialize();
	}

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

		ipText = new JTextField();
		ipText.setBounds(114, 26, 179, 21);
		ipText.setBorder(null);
		ipText.setText(common.myIp());
		chatInfoSec.add(ipText);

		JLabel portLabel = new JLabel("Port");
		portLabel.setFont(new Font("굴림", Font.BOLD, 12));
		portLabel.setBounds(39, 65, 32, 15);
		chatInfoSec.add(portLabel);

		portText = new JTextField();
		portText.setBounds(114, 61, 179, 21);
		portText.setBorder(null);
		chatInfoSec.add(portText);

		RoundedButton1 openBtn = new RoundedButton1("OPEN");
		openBtn.setBounds(398, 30, 75, 90);
		RoundedButton1 closeBtn = new RoundedButton1("CLOSE");
		closeBtn.setBounds(505, 30, 75, 90);
		frame.getContentPane().add(openBtn);
		frame.getContentPane().add(closeBtn);

		openBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ServerThread serverThread = new ServerThread();
				serverThread.setDaemon(true); // 메인 끝나면 같이 종료
				serverThread.start();
			}
		});

		JPanel mySec = new JPanel();
		mySec.setLayout(null);
		mySec.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), null), " MY  ",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		mySec.setBounds(20, 133, 563, 69);
		frame.getContentPane().add(mySec);

		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("굴림", Font.BOLD, 12));
		nameLabel.setBounds(39, 30, 47, 15);
		mySec.add(nameLabel);

		nameText = new JTextField();
		nameText.setBounds(114, 26, 323, 21);
		nameText.setBorder(null);
		nameText.setText(myName);
		mySec.add(nameText);

		RoundedButton2 changeBtn = new RoundedButton2("CHANGE");
		changeBtn.setBounds(460, 25, 88, 23);
		mySec.add(changeBtn);
		changeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeName(myName);
			}
		});

		chatSec = new JTextArea();
		chatSec.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(chatSec);
		scrollPane.setBounds(22, 230, 560, 260);
		frame.getContentPane().add(scrollPane);

		chatText = new JTextField();
		chatText.setBounds(22, 500, 458, 21);
		chatText.setBorder(null);
		frame.getContentPane().add(chatText);

		windowbuilder.RoundedButton2 sendBtn = new windowbuilder.RoundedButton2("SEND");
		sendBtn.setBounds(492, 499, 88, 23);
		frame.getContentPane().add(sendBtn);
		sendBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sendMessage();
			}
		});

		frame.setTitle("\uCC44\uD305\uBC29 \uC8FC\uC778");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 120, 609, 573);
	}
	
	void changeName(String n) {
		myName = nameText.getText();
		
		String msg = n + "," + myName + "";
		chatSec.append("\n" + n + "님의 이름이 " + myName + "(으)로 변경되었습니다.\n");
		
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					dos.writeUTF(msg);
					dos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
	}
	
	class ServerThread extends Thread{
		int port;
		
		public ServerThread() {
			port = Integer.parseInt(portText.getText());
		}

		@Override
		public void run() {
			try {
				serverSocket = new ServerSocket(port);
				chatSec.append("클라이언트의 접속을 기다립니다..\n");
				socket = serverSocket.accept();// 클라이언트가 접속할때까지 커서(스레드)가 대기
				chatSec.append(socket.getInetAddress().getHostAddress() + "님이 접속하셨습니다.\n");

				// 통신을 위한 스트림 생성
				dis = new DataInputStream(socket.getInputStream());
				dos = new DataOutputStream(socket.getOutputStream());

				while (true) {
					// 상대방이 보내온 데이터를 읽기
					String msg = dis.readUTF();// 상대방이 보낼때까지 대기
					
					if(msg.contains(clientName + ",")) {
						chatSec.append("\n" + clientName + "님의 이름이 ");
						clientName = msg.substring(clientName.length()+1);
						chatSec.append(clientName + "(으)로 변경되었습니다.\n");
					}
					else
						chatSec.append(" [" + clientName + "] : " + msg + "\n");
					chatSec.setCaretPosition(chatSec.getText().length());
				}
			} catch (IOException e) {
				chatSec.append("클라이언트가 나갔습니다.\n");
			}
		}	
	}
	// 메시지 전송하는 기능 메소드
	void sendMessage() {
		String msg = chatText.getText(); // TextField에 써있는 글씨를 얻어오기
		chatText.setText(""); // 입력 후 빈칸으로
		chatSec.append(" [" + myName + "] : " + msg + "\n");// 채팅창에 표시
		chatSec.setCaretPosition(chatSec.getText().length()); // 스크롤 따라가게

		// 상대방에게 메시지 전송하기
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					dos.writeUTF(msg);
					dos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
	}	
}