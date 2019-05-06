package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import extra.Position;
import gamelogics.ChessGameLogic;
import pieces.Bishop;
import pieces.ChessPiece;
import pieces.Knight;
import pieces.Queen;
import pieces.Rook;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import java.awt.Window.Type;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import java.awt.Dialog.ModalExclusionType;

public class Promotion extends JFrame {

	private JPanel contentPane;
    private ChessGameLogic chessGameLogic;	
	
	
	public Promotion(ChessGameLogic chessGameLogic) {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.chessGameLogic = chessGameLogic;
		initUI();
	}
	
	private void initUI()
	{
		setTitle("Promotion");
		setBounds(100, 100, 222, 214);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(new Color(51,102,102));
		
        Border border = BorderFactory.createLineBorder(new Color(128,128,128), 1);
		
		JLabel queenLabel = new JLabel(" Queen          ");
		queenLabel.setBorder(border);
		queenLabel.setForeground(Color.WHITE);
		queenLabel.setBackground(Color.red);
		queenLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 23));
		queenLabel.setBounds(85, 653, 615, 40);
		queenLabel.addMouseListener(new MouseAdapter() {
			 @Override
             public void mouseClicked(MouseEvent e) {
                    chessGameLogic.promote(1);
                    dispose();
			 }
		});
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(queenLabel);
		
		
		JLabel knightLabel = new JLabel(" Knight          ");
		knightLabel.setBorder(border);
		knightLabel.setForeground(Color.WHITE);
		knightLabel.setBackground(Color.BLACK);
		knightLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 23));
		knightLabel.setBounds(85, 653, 615, 40);
		knightLabel.addMouseListener(new MouseAdapter() {
			 @Override
            public void mouseClicked(MouseEvent e) {
                   chessGameLogic.promote(2);
                   dispose();
			 }
		});
		contentPane.add(knightLabel);
		
		JLabel bishopLabel = new JLabel(" Bishop          ");
		bishopLabel.setBorder(border);
		bishopLabel.setForeground(Color.WHITE);
		bishopLabel.setBackground(Color.BLACK);
		bishopLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 23));
		bishopLabel.setBounds(85, 653, 615, 40);
		bishopLabel.addMouseListener(new MouseAdapter() {
			 @Override
            public void mouseClicked(MouseEvent e) {
                   chessGameLogic.promote(3);
                   dispose();
			 }
		});
		contentPane.add(bishopLabel);
		
		JLabel rookLabel = new JLabel(" Rook          ");
		rookLabel.setBorder(border);
		rookLabel.setForeground(Color.WHITE);
		rookLabel.setBackground(Color.BLACK);
		rookLabel.addMouseListener(new MouseAdapter() {
			 @Override
            public void mouseClicked(MouseEvent e) {
                   chessGameLogic.promote(4);
                   dispose();
			 }
		});
		rookLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 23));
		rookLabel.setBounds(85, 653, 615, 40);
		contentPane.add(rookLabel);
		
		setVisible(true);
	}

}
