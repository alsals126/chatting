package windowbuilder;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JPanel;

import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class owner{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					owner window = new owner();
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
	public owner() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		
		JPanel chatInfoSec = new JPanel();
		chatInfoSec.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), null), "CHAT INFO  ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		chatInfoSec.setBounds(20, 23, 347, 100);
		frame.getContentPane().add(chatInfoSec);
		chatInfoSec.setLayout(null);
		
		JLabel ipLabel = new JLabel("IP");
		ipLabel.setFont(new Font("����", Font.BOLD, 12));
		ipLabel.setBounds(39, 30, 32, 15);
		chatInfoSec.add(ipLabel);
		
		JTextField ipText = new JTextField();
		ipText.setBounds(114, 26, 179, 21);
		ipText.setBorder(null);
		chatInfoSec.add(ipText);
		
		JLabel portLabel = new JLabel("Port");
		portLabel.setFont(new Font("����", Font.BOLD, 12));
		portLabel.setBounds(39, 65, 32, 15);
		chatInfoSec.add(portLabel);
		
		JTextField portText = new JTextField();
		portText.setBounds(114, 61, 179, 21);
		portText.setBorder(null);
		chatInfoSec.add(portText);
		
		RoundedButton1 openBtn = new RoundedButton1("OPEN");
		openBtn.setBounds(398, 30, 75, 90);
		RoundedButton1 closeBtn = new RoundedButton1("CLOSE");
		closeBtn.setBounds(505, 30, 75, 90);
		frame.getContentPane().add(openBtn);
		frame.getContentPane().add(closeBtn);
		
		JPanel mySec = new JPanel();
		mySec.setLayout(null);
		mySec.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), null), " MY  ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		mySec.setBounds(20, 133, 563, 69);
		frame.getContentPane().add(mySec);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("����", Font.BOLD, 12));
		nameLabel.setBounds(39, 30, 47, 15);
		mySec.add(nameLabel);
		
		JTextField nameText = new JTextField();
		nameText.setBounds(114, 26, 323, 21);
		nameText.setBorder(null);
		mySec.add(nameText);
		
		RoundedButton2 changeBtn = new RoundedButton2("CHANGE");
		changeBtn.setBounds(460, 25, 88, 23);
		mySec.add(changeBtn);
		
		JTextArea chatSec = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(chatSec);
		scrollPane.setBounds(22, 230, 560, 260);
		frame.getContentPane().add(scrollPane);
		
		JTextField chatText = new JTextField();
		chatText.setBounds(22, 500, 458, 21);
		chatText.setBorder(null);
		frame.getContentPane().add(chatText);
		
		windowbuilder.RoundedButton2 sendBtn = new windowbuilder.RoundedButton2("SEND");
		sendBtn.setBounds(492, 499, 88, 23);
		frame.getContentPane().add(sendBtn);
		
		frame.setTitle("\uCC44\uD305\uBC29 \uC8FC\uC778");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.WHITE);
		frame.setBounds(400, 120, 609, 573); 	
	}
}