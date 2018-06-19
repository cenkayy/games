package tictactoe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.*;

public class start extends JFrame implements ActionListener{

	JButton[] jbtn = new JButton[9];
	JButton[][] buttonGrid=new JButton[3][3];
	JPanel jpn1,jpn2;
	int counter=0;
	Color blue = Color.BLUE;
	Color green = Color.GREEN;
	ArrayList<Integer> arr = new ArrayList<>();
	
	Image img;

	public start(){
		
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);
		this.setMinimumSize(new Dimension(300, 300));
		this.setTitle("X's Turn");
		createPanel();
		createButtons1();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	
		
	}
	public void XorO(JButton jbtn,int counter)
	{		try {
				if(counter%2==0)
				{
					img = ImageIO.read(getClass().getResource("/X.png"));
					this.setTitle("O's Turn");
					jbtn.setIcon(new ImageIcon(img));
					//jbtn.setName("X");
					jbtn.setBackground(blue);
				}
				else
				{
					img = ImageIO.read(getClass().getResource("/O.png"));
					this.setTitle("X's Turn");
					jbtn.setIcon(new ImageIcon(img));
					//jbtn.setName("O");
					jbtn.setBackground(green);
				}
				
			} catch (Exception e) {
				
			}
	}
	public void checkWinner()
	{
		if(buttonGrid[0][0].getBackground()==blue && buttonGrid[0][1].getBackground()==blue && buttonGrid[0][2].getBackground()==blue
				||
		   buttonGrid[1][0].getBackground()==blue && buttonGrid[1][1].getBackground()==blue && buttonGrid[1][2].getBackground()==blue
		        ||
		   buttonGrid[2][0].getBackground()==blue && buttonGrid[2][1].getBackground()==blue && buttonGrid[2][2].getBackground()==blue
		   		||
		   buttonGrid[0][0].getBackground()==blue && buttonGrid[1][0].getBackground()==blue && buttonGrid[2][0].getBackground()==blue
				||
	       buttonGrid[0][1].getBackground()==blue && buttonGrid[1][1].getBackground()==blue && buttonGrid[2][1].getBackground()==blue
	            ||
	       buttonGrid[0][2].getBackground()==blue && buttonGrid[1][2].getBackground()==blue && buttonGrid[2][2].getBackground()==blue
				||
		   buttonGrid[0][0].getBackground()==blue && buttonGrid[1][1].getBackground()==blue && buttonGrid[2][2].getBackground()==blue
		        ||
		   buttonGrid[0][2].getBackground()==blue && buttonGrid[1][1].getBackground()==blue && buttonGrid[2][0].getBackground()==blue
				)
		{
			int rematch = JOptionPane.showConfirmDialog(null, "X Wins!\nRematch?","Game Over",JOptionPane.YES_NO_OPTION);
			if(rematch==JOptionPane.YES_OPTION)
			{
				jpn1.removeAll();
				createButtons1();
				jpn1.revalidate();
				jpn1.repaint();
				arr.clear();
			}
			else
			{
				this.setVisible(false);
				this.dispose();
			}
			
		}
		if(buttonGrid[0][0].getBackground()==green && buttonGrid[0][1].getBackground()==green && buttonGrid[0][2].getBackground()==green
				||
		   buttonGrid[1][0].getBackground()==green && buttonGrid[1][1].getBackground()==green && buttonGrid[1][2].getBackground()==green
		        ||
		   buttonGrid[2][0].getBackground()==green && buttonGrid[2][1].getBackground()==green && buttonGrid[2][2].getBackground()==green
		   		||
		   buttonGrid[0][0].getBackground()==green && buttonGrid[1][0].getBackground()==green && buttonGrid[2][0].getBackground()==green
				||
	       buttonGrid[0][1].getBackground()==green && buttonGrid[1][1].getBackground()==green && buttonGrid[2][1].getBackground()==green
	            ||
	       buttonGrid[0][2].getBackground()==green && buttonGrid[1][2].getBackground()==green && buttonGrid[2][2].getBackground()==green
				||
		   buttonGrid[0][0].getBackground()==green && buttonGrid[1][1].getBackground()==green && buttonGrid[2][2].getBackground()==green
		        ||
		   buttonGrid[0][2].getBackground()==green && buttonGrid[1][1].getBackground()==green && buttonGrid[2][0].getBackground()==green
				)
		{
			int rematch = JOptionPane.showConfirmDialog(null, "O Wins!\nRematch?","Game Over",JOptionPane.YES_NO_OPTION);
			if(rematch==JOptionPane.YES_OPTION)
			{
				jpn1.removeAll();
				createButtons1();
				jpn1.revalidate();
				jpn1.repaint();
				arr.clear();
			}
			else
			{
				this.setVisible(false);
				this.dispose();
			}
			
		}
	}
	public void createPanel()
	{
		jpn1=new JPanel();
		jpn1.setMinimumSize(new Dimension(300, 300));
		jpn1.setLayout(new GridLayout(3, 3));
		this.add(jpn1);
	}

	public void createButtons1()
	{
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				buttonGrid[i][j]=new JButton("");
				buttonGrid[i][j].setPreferredSize(new Dimension(10, 10));
				buttonGrid[i][j].addActionListener(this);
				jpn1.add(buttonGrid[i][j]);
				
			}
		}
	}
	public void isFull()
	{
		
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			if(buttonGrid[i][j].getIcon()!=null)
			{
				arr.add(i);
				if(arr.size()==45)
				{
			
					int dialog=JOptionPane.showConfirmDialog(null, "ROUND DRAW!\nNEW GAME?", "DRAW", JOptionPane.YES_NO_OPTION);
					if(dialog==JOptionPane.YES_OPTION)
					{
						jpn1.removeAll();
						createButtons1();
						jpn1.revalidate();
						jpn1.repaint();
						arr.clear();
					}
					else
					{
						this.setVisible(false);
						this.dispose();
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		new start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(((JButton) e.getSource()).getIcon()==null)
		{
			XorO(((JButton)e.getSource()), counter);
			counter++;
			isFull();
			checkWinner();
		}
		
	}
}
