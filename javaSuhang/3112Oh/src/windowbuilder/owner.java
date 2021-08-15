package windowbuilder;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Window.Type;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Component;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.Icon;
import javax.swing.JTextPane;
import javax.swing.JButton;

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
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), null), "CHAT INFO  ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(20, 23, 347, 100);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("IP");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
		lblNewLabel.setBounds(39, 30, 32, 15);
		panel.add(lblNewLabel);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(114, 26, 179, 21);
		panel.add(textPane);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setFont(new Font("굴림", Font.BOLD, 12));
		lblPort.setBounds(39, 65, 32, 15);
		panel.add(lblPort);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(114, 61, 179, 21);
		panel.add(textPane_1);
		
		RoundedButton btnNewButton1 = new RoundedButton("OPEN");
		btnNewButton1.setBounds(393, 30, 75, 90);
		RoundedButton btnNewButton2 = new RoundedButton("CLOSE");
		btnNewButton2.setBounds(505, 30, 75, 90);
		frame.getContentPane().add(btnNewButton1);
		frame.getContentPane().add(btnNewButton2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), null), "CHAT INFO  ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(20, 154, 560, 69);
		frame.getContentPane().add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 12));
		lblNewLabel_1.setBounds(39, 30, 47, 15);
		panel_1.add(lblNewLabel_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setBounds(114, 26, 323, 21);
		panel_1.add(textPane_2);
		
		RoundedButton btnNewButton1_1 = new RoundedButton("CHANGE");
		btnNewButton1_1.setBounds(460, 29, 88, 15);
		panel_1.add(btnNewButton1_1);
		
		frame.setTitle("\uCC44\uD305\uBC29 \uC8FC\uC778");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 609, 538); 	
	}
}

//버튼 디자인
class RoundedButton extends JButton {
   public RoundedButton() { super(); decorate(); } 
   public RoundedButton(String text) { super(text); decorate(); } 
   public RoundedButton(Action action) { super(action); decorate(); } 
   public RoundedButton(Icon icon) { super(icon); decorate(); } 
   public RoundedButton(String text, Icon icon) { super(text, icon); decorate(); } 
   protected void decorate() { setBorderPainted(false); setOpaque(false); }
   @Override 
   protected void paintComponent(Graphics g) {
      Color c=new Color(255,255,255); //배경색 결정
      Color o=new Color(0,0,0); //글자색 결정
      int width = getWidth(); 
      int height = getHeight(); 
      Graphics2D graphics = (Graphics2D) g; 
      graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
      if (getModel().isArmed()) { graphics.setColor(c.darker()); } 
      else if (getModel().isRollover()) { graphics.setColor(c.brighter()); } 
      else { graphics.setColor(c); } 
      graphics.fillRoundRect(0, 0, width, height, 25, 25); 
      FontMetrics fontMetrics = graphics.getFontMetrics(); 
      Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds(); 
      int textX = (width - stringBounds.width) / 2; 
      int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent(); 
      graphics.setColor(o); 
      graphics.setFont(getFont()); 
      graphics.drawString(getText(), textX, textY); 
      graphics.dispose(); 
      super.paintComponent(g); 
      }
   }