package game;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window.Type;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;

import board.ChessBoard;
import gamelogics.EasyChessGame;
import gamelogics.EasyVsComputerGame;
import gamelogics.HardChessGame;
import gamelogics.HardVsComputerGame;
import players.Computer;
import players.Player;

import javax.swing.JDesktopPane;
import sun.audio.*;
import java.io.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class GameMenu {
	
	private JFrame gameMenuFrame;
	private JButton twoPlayersBtn;
	private JTextField playerTwoNameText;
	private JTextField playerOneNameText;
	private JButton easyLevelBtn;
	private JButton hardLevelBtn;
	private JButton playBtn;
	private JLabel playerTwoLabel;
	private JLabel playerOneLabel;
	private JLabel playerOneIcon;
	private JLabel playerTwoIcon;
	private JButton computerBtn;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JButton backBtn;
	public static boolean checkEasyLevel;
    public static boolean checkHardLevel;
	public static boolean checkComputer;


	public GameMenu() {
		initialize();
	}

	private void initialize() {
		gameMenuFrame = new JFrame("Chess");
		gameMenuFrame.setIconImage(new ImageIcon(getClass().getResource("/assets/game_icon.png")).getImage());
		gameMenuFrame.setType(Type.POPUP);
		gameMenuFrame.setFont(new Font("Arabic Typesetting", Font.BOLD, 19));
		gameMenuFrame.setAlwaysOnTop(true);
		gameMenuFrame.getContentPane().setForeground(new Color(255, 255, 255));
		gameMenuFrame.getContentPane().setLayout(null);
		
		checkEasyLevel = false;
		checkHardLevel = false;
		checkComputer = false;
	
	    computerBtn = new JButton("Computer");
		twoPlayersBtn = new JButton("Two Players");
		easyLevelBtn = new JButton("Easy Level");
		easyLevelBtn.setActionCommand("easy");
		hardLevelBtn = new JButton("Hard Level");
		playBtn = new JButton("PLAY");
		playerTwoNameText = new JTextField();
		playerTwoNameText.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(playerTwoNameText.getText().equals("player 2"))
					playerTwoNameText.setText("");
			}
		});
		playerTwoNameText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				playerTwoNameText.setText("");
				playerTwoNameText.setForeground(new Color(0, 0, 0));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(playerTwoNameText.getText().equals("")||playerTwoNameText.getText().equals("player 2"))
				{
					playerTwoNameText.setForeground(new Color(169, 169, 169));
					playerTwoNameText.setText("player 2");
				}
				else 
					playerTwoNameText.setForeground(new Color(0, 0, 0));
			}
		});
		playerTwoNameText.setForeground(new Color(169, 169, 169));
		playerTwoNameText.setText("player 2");
		playerTwoNameText.setFont(new Font("Microsoft YaHei", Font.BOLD, 18));
		playerOneNameText = new JTextField();
		playerOneNameText.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				if(playerOneNameText.getText().equals("player 1"))
					playerOneNameText.setText("");
			}
		});
		playerOneNameText.setForeground(new Color(169, 169, 169));
		playerOneNameText.setText("player 1");
		playerOneNameText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				playerOneNameText.setText("");
				playerOneNameText.setForeground(new Color(0, 0, 0));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(playerOneNameText.getText().equals("")||playerOneNameText.getText().equals("player 1"))
				{
					playerOneNameText.setForeground(new Color(169, 169, 169));
					playerOneNameText.setText("player 1");
				}
				else 
					playerOneNameText.setForeground(new Color(0, 0, 0));
			}
		});
		playerOneNameText.setFont(new Font("Microsoft YaHei", Font.BOLD, 18));
		playerTwoLabel = new JLabel("PLAYER 2");
	    playerOneLabel = new JLabel("PLAYER 1");
		playBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    if (checkEasyLevel == true && checkComputer == false){
			    	gameMenuFrame.setVisible(false);
			        EasyChessGame easyChessGame = new EasyChessGame(new Player(playerOneNameText.getText() , "White") , new Player(playerTwoNameText.getText() , "Black"));
			    }
			    else if (checkEasyLevel == true && checkComputer == true) {
			    	gameMenuFrame.setVisible(false);
			    	EasyVsComputerGame easyVsComputerGame = new EasyVsComputerGame(new Player(playerOneNameText.getText() , "White") , new Computer());
			    }
			    else if (checkHardLevel == true && checkComputer == false) {
			    	gameMenuFrame.setVisible(false);
			    	HardChessGame hardChessGame = new HardChessGame(new Player(playerOneNameText.getText() , "White") , new Player(playerTwoNameText.getText() , "Black"));
			    }
			    else if (checkHardLevel == true && checkComputer == true)
			    {
			    	gameMenuFrame.setVisible(false);
			    	HardVsComputerGame hardVsComputerGame = new HardVsComputerGame(new Player(playerOneNameText.getText() , "White") , new Computer()); 
			    }
			}
		});
		computerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				twoPlayersBtn.setVisible(false);
				easyLevelBtn.setVisible(false);
				computerBtn.setVisible(false);
				hardLevelBtn.setVisible(false);
				playerOneLabel.setVisible(true);
				playerOneNameText.setVisible(true);
				playerOneIcon.setVisible(true);
				playBtn.setVisible(true);
				checkComputer=true;
			}
		});
		
		twoPlayersBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				twoPlayersBtn.setVisible(false);
				easyLevelBtn.setVisible(false);
				computerBtn.setVisible(false);
				hardLevelBtn.setVisible(false);
				playerOneNameText.setVisible(true);
				playerTwoNameText.setVisible(true);
				playerOneLabel.setVisible(true);
				playerTwoLabel.setVisible(true);
				playerOneIcon.setVisible(true);
				playerTwoIcon.setVisible(true);
				playBtn.setVisible(true);
				playBtn.setBounds(195, 600, 143, 35);
				checkComputer=false;
			}
		});
		
		easyLevelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				twoPlayersBtn.setVisible(true);
				easyLevelBtn.setVisible(false);
				computerBtn.setVisible(true);
				hardLevelBtn.setVisible(false);
				playBtn.setVisible(false);
				checkEasyLevel=true;
				checkHardLevel=false;
			}
		});
		easyLevelBtn.setFont(new Font("Microsoft YaHei", Font.BOLD, 30));
		easyLevelBtn.setBounds(170, 403, 230, 41);
		gameMenuFrame.getContentPane().add(easyLevelBtn);
		twoPlayersBtn.setFont(new Font("Microsoft YaHei", Font.BOLD, 30));
		twoPlayersBtn.setBounds(170, 403, 230, 41);
		gameMenuFrame.getContentPane().add(twoPlayersBtn);
		
		hardLevelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				twoPlayersBtn.setVisible(true);
				easyLevelBtn.setVisible(false);
				computerBtn.setVisible(true);
				hardLevelBtn.setVisible(false);
				playBtn.setVisible(false);
				checkEasyLevel=false;
				checkHardLevel=true;
			}
		});
		
		
		hardLevelBtn.setFont(new Font("Microsoft YaHei", Font.BOLD, 30));
		hardLevelBtn.setBounds(170, 497, 235, 41);
		gameMenuFrame.getContentPane().add(hardLevelBtn);
		
	    
		computerBtn.setFont(new Font("Microsoft YaHei", Font.BOLD, 30));
		computerBtn.setBounds(170, 497, 235, 41);
		gameMenuFrame.getContentPane().add(computerBtn);
		
		
		playerTwoLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 25));
		playerTwoLabel.setForeground(new Color(0, 0, 0));
		playerTwoLabel.setBounds(125, 499, 121, 40);
		gameMenuFrame.getContentPane().add(playerTwoLabel);
		
	
		playerTwoNameText.setBounds(256, 505, 151, 35);
		gameMenuFrame.getContentPane().add(playerTwoNameText);
		playerTwoNameText.setColumns(10);
		
		
		playerOneNameText.setBounds(256, 411, 151, 35);
		gameMenuFrame.getContentPane().add(playerOneNameText);
		playerOneNameText.setColumns(10);
		
		
		playerOneLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 25));
		playerOneLabel.setForeground(Color.white);
		playerOneLabel.setBounds(121, 407, 125, 36);
		gameMenuFrame.getContentPane().add(playerOneLabel);
		
		playerTwoIcon = new JLabel("");
		playerTwoIcon.setIcon(new ImageIcon(getClass().getResource("/assets/small_black_knight.png")));
		playerTwoIcon.setBounds(91, 497, 30, 41);
		gameMenuFrame.getContentPane().add(playerTwoIcon);
		
		playerOneIcon = new JLabel("");
		playerOneIcon.setIcon(new ImageIcon(getClass().getResource("/assets/small_white_knight.png")));
		playerOneIcon.setBounds(91, 403, 30, 35);
		gameMenuFrame.getContentPane().add(playerOneIcon);
		
		
		playBtn.setFont(new Font("Microsoft YaHei", Font.BOLD, 30));
		playBtn.setBounds(215, 562, 143, 35);
		gameMenuFrame.getContentPane().add(playBtn);
		playBtn.setVisible(false);
		playerOneNameText.setVisible(false);
		playerTwoNameText.setVisible(false);
		playerOneLabel.setVisible(false);
		playerTwoLabel.setVisible(false);
		playerOneIcon.setVisible(false);
		playerTwoIcon.setVisible(false);
		
		label = new JLabel("Chess");
		label.setForeground(new Color(0, 0, 0));
		label.setFont(new Font("Algerian", Font.BOLD, 99));
		label.setBounds(125, 22, 317, 111);
		gameMenuFrame.getContentPane().add(label);
		
		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(getClass().getResource("/assets/black_queen.png")));
		label_1.setBounds(71, 51, 50, 82);
		gameMenuFrame.getContentPane().add(label_1);
		
		label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(getClass().getResource("/assets/black_queen.png")));
		label_2.setBounds(444, 50, 50, 82);
		gameMenuFrame.getContentPane().add(label_2);
		
		
		
		JLabel cover = new JLabel("");
		cover.setIcon(new ImageIcon(getClass().getResource("/assets/game_menu_background.jpg")));
		cover.setBounds(0, 0, 554, 702);
		gameMenuFrame.getContentPane().add(cover);
				
		gameMenuFrame.setBounds(390, 0, 570, 730);
		gameMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gameMenuFrame.setVisible(true);
	}
	
	
}

