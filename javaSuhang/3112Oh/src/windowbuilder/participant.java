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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
	private JTextField ipText;
	private JTextField portText;
	private JTextField nameText;
	private JTextArea chatSec;
	private JTextField chatText;
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;
	
	CommonMethod common = new CommonMethod();
	String myName = "Client";
	String hostName = "Host";

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
		ipLabel.setFont(new Font("????", Font.BOLD, 12));
		ipLabel.setBounds(39, 30, 32, 15);
		chatInfoSec.add(ipLabel);

		ipText = new JTextField();
		ipText.setBounds(114, 26, 179, 21);
		ipText.setBorder(null);
		chatInfoSec.add(ipText);

		JLabel portLabel = new JLabel("Port");
		portLabel.setFont(new Font("????", Font.BOLD, 12));
		portLabel.setBounds(39, 65, 32, 15);
		chatInfoSec.add(portLabel);

		portText = new JTextField();
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
		outBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if(socket != null) {
						socket.close();
					}else {
						chatSec.append("???????? ?????? ???? ??????????\n");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		JPanel mySec = new JPanel();
		mySec.setLayout(null);
		mySec.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), null), " MY  ",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		mySec.setBounds(20, 133, 563, 100);
		frame.getContentPane().add(mySec);

		JLabel myIpLabel = new JLabel("IP");
		myIpLabel.setFont(new Font("????", Font.BOLD, 12));
		myIpLabel.setBounds(39, 30, 32, 15);
		mySec.add(myIpLabel);

		JTextField myIpText = new JTextField();
		myIpText.setBackground(UIManager.getColor("Button.background"));
		myIpText.setEditable(false);
		myIpText.setBounds(114, 26, 437, 21);
		myIpText.setBorder(new LineBorder(Color.LIGHT_GRAY));
		myIpText.setText(common.myIp());
		mySec.add(myIpText);

		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("????", Font.BOLD, 12));
		nameLabel.setBounds(39, 65, 47, 15);
		mySec.add(nameLabel);

		nameText = new JTextField();
		nameText.setBounds(114, 61, 323, 21);
		nameText.setBorder(null);
		nameText.setText(myName);
		mySec.add(nameText);

		RoundedButton2 changeBtn = new RoundedButton2("CHANGE");
		changeBtn.setBounds(463, 60, 88, 23);
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
		scrollPane.setBounds(22, 255, 560, 235);
		frame.getContentPane().add(scrollPane);

		chatText = new JTextField();
		chatText.setBounds(22, 500, 458, 21);
		chatText.setBorder(null);
		chatText.addKeyListener(new KeyAdapter() {
			@Override

			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);

				// ???????? ???? ????????
				int keyCode = e.getKeyCode();
				switch (keyCode) {	
					case KeyEvent.VK_ENTER:
						sendMessage();
					break;
				}
			}
		});
		frame.getContentPane().add(chatText);

		windowbuilder.RoundedButton2 sendBtn = new windowbuilder.RoundedButton2("SEND");
		sendBtn.setBounds(492, 499, 88, 23);
		frame.getContentPane().add(sendBtn);
		sendBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});

		frame.setTitle("\uCC44\uD305\uCC38\uC5EC");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.WHITE);
		frame.setBounds(720, 120, 609, 573);
	}
	void changeName(String n) {
		myName = nameText.getText();
		
		String msg = n + "," + myName + "";
		chatSec.append("\n" + n + "???? ?????? " + myName + "(??)?? ??????????????.\n");
		
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

	class ClientThread extends Thread {
		String ownerIp;
		int port;

		public ClientThread() {
			ownerIp = ipText.getText();
			port = Integer.parseInt(portText.getText());
		}

		@Override
		public void run() {
			try {
				socket = new Socket(ownerIp, port);
				chatSec.append("???????? ????????????.\n");

				// ?????? ?????? ???? ?????? ????(?????? ????)
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();

				// ?????????????? ???????? ?????????? ?????? ?????? ?????? ?????????? ????
				dis = new DataInputStream(is);
				dos = new DataOutputStream(os);

				while (true) {// ?????? ?????? ????
					String msg = dis.readUTF();
					
					if(msg.contains(hostName + ",")) {
						chatSec.append("\n" + hostName + "???? ?????? ");
						hostName = msg.substring(hostName.length()+1);
						chatSec.append(hostName + "(??)?? ??????????????.\n");
					}
					else
						chatSec.append(" [" + hostName + "] : " + msg + "\n");
					chatSec.setCaretPosition(chatSec.getText().length());
				}
			} catch (UnknownHostException e) {
				chatSec.append("???? ?????? ??????????.\n");
			} catch (IOException e) {
				chatSec.append("?????????? ?????? ??????????.\n");
			}
		}
	}

	// ?????? ???????? ???? ??????
	void sendMessage() {
		String msg = chatText.getText(); // TextField?? ?????? ?????? ????????
		chatText.setText(""); // ???? ?? ????????
		chatSec.append(" [" + myName + "] : " + msg + "\n");// ???????? ????
		chatSec.setCaretPosition(chatSec.getText().length());

		// ?????????? ?????? ????????
		// ?????? ???????? ???? ???????? ?????? ????
		// ???????? ?????? ?????? Thread?? ???? ???? ????
		Thread t = new Thread() {
			@Override
			public void run() {
				try { // UTF = ?????????? ????(????), ???? ?????? ???? ????
					dos.writeUTF(msg);
					dos.flush(); // ???? ???? ???? close()???? ????
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};

		t.start();
	}
}