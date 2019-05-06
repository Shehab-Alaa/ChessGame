package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

public class SplashScreen extends JFrame {

	private JPanel contentPane;
	private JProgressBar progressBar;
	private JLabel loading;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JLabel label;
	private JLabel lblNewLabel_1;
	
	public SplashScreen() {
		
		this.setTitle("Chess");
		setIconImage(new ImageIcon(getClass().getResource("/assets/game_icon.png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(390, 0, 570, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		loading = new JLabel("Loading.....");
		loading.setBounds(76, 408, 449, 40);
		contentPane.add(loading);
		loading.setFont(new Font("Microsoft YaHei", Font.BOLD, 22));
		loading.setForeground(new Color(255, 255, 255));
		
		progressBar = new JProgressBar();
		progressBar.setBounds(41, 489, 471, 40);
		contentPane.add(progressBar);
		progressBar.setBackground(new Color(255, 255, 255));
		progressBar.setForeground(new Color(102, 0, 51));
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource("/assets/small_white_king.png")));
		label.setBounds(512, 489, 32, 40);
		contentPane.add(label);
		
		lblNewLabel_1 = new JLabel("Chess");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Algerian", Font.BOLD, 99));
		lblNewLabel_1.setBounds(122, 30, 317, 111);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(getClass().getResource("/assets/snow_flake_icon.png")));
		lblNewLabel_4.setBounds(0, 651, 46, 40);
		contentPane.add(lblNewLabel_4);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(getClass().getResource("/assets/snow_flake_icon.png")));
		label_2.setBounds(508, 651, 46, 40);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(getClass().getResource("/assets/snow_flake_icon.png")));
		label_3.setBounds(508, 0, 46, 40);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(getClass().getResource("/assets/snow_flake_icon.png")));
		label_4.setBounds(0, 0, 46, 40);
		contentPane.add(label_4);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(getClass().getResource("/assets/small_white_king.png")));
		lblNewLabel_2.setBounds(10, 489, 32, 40);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/assets/splash_screen_background.jpg")));
		lblNewLabel.setBounds(0, 0, 554, 691);
		contentPane.add(lblNewLabel);
	}

	public void run() throws InterruptedException {
		for(int i=0;i<=100;i++)
		{
			Thread.sleep(100);
			setVisible(true);
			loading.setText("                 Loading.......");
			if (i>=10&&i<=30)
			{
			   loading.setText("  Chess is the best game in the World");
			   loading.setFont(new Font("Microsoft YaHei", Font.BOLD, 22));
			}
			if (i>=31&&i<=50)
			{
			    loading.setText("Developed By 'NOT FOUND' Team");
				loading.setFont(new Font("Microsoft YaHei", Font.BOLD, 20));
			}
			if (i>=51&&i<=70)
			{
				loading.setText("                 King Mate!");
				loading.setFont(new Font("Microsoft YaHei", Font.BOLD, 22));
			}
			if (i>=71&&i<=90)
			{
				loading.setText("                 Loading"+i+"%.......");
				loading.setFont(new Font("Microsoft YaHei", Font.BOLD, 22));
			}
			if (i>=91&&i<=99)
			{
				loading.setText("  Chess is the best game in the World");
				loading.setFont(new Font("Microsoft YaHei", Font.BOLD, 22));
			}
			    progressBar.setValue(i);
			if(i==100)
			{
				GameMenu gameMenu = new GameMenu();
				setVisible(false);	
			}
		}
	}
}

