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
import javax.swing.border.LineBorder;
import javax.swing.UIManager;

public class participant{

	private JFrame frame;

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
		
		RoundedButton1 inBtn = new RoundedButton1("IN");
		inBtn.setBounds(398, 30, 75, 90);
		RoundedButton1 outBtn = new RoundedButton1("OUT");
		outBtn.setBounds(505, 30, 75, 90);
		frame.getContentPane().add(inBtn);
		frame.getContentPane().add(outBtn);
		
		JPanel mySec = new JPanel();
		mySec.setLayout(null);
		mySec.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), null), " MY  ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		mySec.setBounds(20, 133, 563, 100);
		frame.getContentPane().add(mySec);
		
		JLabel myIpLabel = new JLabel("IP");
		myIpLabel.setFont(new Font("����", Font.BOLD, 12));
		myIpLabel.setBounds(39, 30, 32, 15);
		mySec.add(myIpLabel);
		
		JTextField myIpText = new JTextField();
		myIpText.setBackground(UIManager.getColor("Button.background"));
		myIpText.setEditable(false);
		myIpText.setBounds(114, 26, 437, 21);
		myIpText.setBorder(new LineBorder(Color.LIGHT_GRAY));
		mySec.add(myIpText);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("����", Font.BOLD, 12));
		nameLabel.setBounds(39, 65, 47, 15);
		mySec.add(nameLabel);
		
		JTextField nameText = new JTextField();
		nameText.setBounds(114, 61, 323, 21);
		nameText.setBorder(null);
		mySec.add(nameText);
		
		RoundedButton2 changeBtn = new RoundedButton2("CHANGE");
		changeBtn.setBounds(463, 60, 88, 23);
		mySec.add(changeBtn);
		
		JTextArea chatSec = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(chatSec);
		scrollPane.setBounds(22, 255, 560, 235);
		frame.getContentPane().add(scrollPane);
		
		JTextField chatText = new JTextField();
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
}