import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import com.sun.javafx.tk.Toolkit;

import javafx.scene.control.ToggleButton;

@SuppressWarnings("unused")
public class createGuiThreeTeam implements Observer {
	private JFrame aliasx4;
	private int clueCount;
	board ssr = new board();
	private JFrame win;  // declare win frame so You can dispose it in restart button
   
	public createGuiThreeTeam() {
		restart();
	}

	
	public void restart() {
		MakeGui();
	}

	public void MakeGui() {

		main m = new main();
		m.gameStartThreeTeam();

		JFrame turn;

		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem start = new JMenuItem("Start New Game");
		JMenuItem exit = new JMenuItem("Exit Game");
		JMenuItem startTwoTeam = new JMenuItem("Start 2 Player Game");
		menuBar.add(menu);
		menu.add(start);
		menu.add(exit);
		menu.add(startTwoTeam);

		JMenuBar menuBar2 = new JMenuBar();
		JMenu menu2 = new JMenu("File");
		JMenuItem exit2 = new JMenuItem("Exit Spymaster");
		menuBar2.add(menu2);
		menu2.add(exit2);

		JFrame x3 = new JFrame("CodeName game");
		x3.setJMenuBar(menuBar);
		x3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// x3.setSize(1000,1000);
		x3.setExtendedState(JFrame.MAXIMIZED_BOTH);
		x3.setUndecorated(true);
		x3.setVisible(true);

		GridLayout inX = new GridLayout(2, 2);
		x3.setLayout(inX);
		JPanel panelTop = new JPanel(new GridLayout(6, 6));
		JPanel panelBottom = new JPanel(new GridLayout(5, 5));
		JLabel labelCode = new JLabel();
		JLabel labelNumber = new JLabel();
		labelCode.setText("Hint: ");
		labelNumber.setText("Number: ");
		labelCode.setFont(new Font("Serif", Font.PLAIN, 40));
		labelNumber.setFont(new Font("Serif", Font.PLAIN, 40));

		ActionListener exit3 = new ActionListener() {// menu exit
			@Override
			public void actionPerformed(ActionEvent e) {
				x3.dispose();
				if(aliasx4 != null) {
				aliasx4.dispose();   			// double check if aliasx4 is referenced dispose it to avoid error
				}
			}
		};
		exit.addActionListener(exit3);

		ActionListener exit4 = new ActionListener() {// menu exit
			@Override
			public void actionPerformed(ActionEvent e) {
				if(aliasx4 != null) {
				aliasx4.dispose();
				}
			}
		};
		exit2.addActionListener(exit4);

		ActionListener start2 = new ActionListener() {// menu restart
			@Override
			public void actionPerformed(ActionEvent e) {
				x3.getContentPane().removeAll();
				x3.dispose();
				createGUI twoPlayerFrame = new createGUI();
			}
		};
		start.addActionListener(start2);
		
		ActionListener start3 = new ActionListener() {// menu restart 3 player team
			@Override
			public void actionPerformed(ActionEvent e) {
				x3.getContentPane().removeAll();
				x3.dispose();
				restart();
			}
		};
		startTwoTeam.addActionListener(start2);
		
		JButton restart = new JButton("Restart");

		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == restart) {
					x3.getContentPane().removeAll();
					x3.dispose();
					if (aliasx4 != null) {
						aliasx4.dispose();	//dispose spymaster frame when restart to avoid old spymaster fields
					}
					if (win != null) {
						win.dispose();		//dispose win frame when restart to avoid win frame builds up
					}
					restart();

				}
			}
		});

		panelTop.add(restart);

		JFrame x4 = new JFrame("Spy Master Window");
		aliasx4 = x4;
		JPanel panelRight = new JPanel();
		// panelRight.setLayout(new GridLayout(0,1));
		JPanel panelLeft = new JPanel();
		panelLeft.setLayout(new GridLayout(5, 5));
		JLabel label1 = new JLabel();
		JLabel label2 = new JLabel();
		JButton jb = new JButton("Enter");


		JTextField textField = new JTextField("Input Clue", 20);
		JTextField numField = new JTextField("Input number", 20);
		if (textField.getText().equals("Input Clue")) {
			textField.setForeground(Color.LIGHT_GRAY);
		}
		if (numField.getText().equals("Input number")) {
			numField.setForeground(Color.LIGHT_GRAY);
		}

		FocusListener ghost1 = new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				textField.setText("");
				textField.setForeground(Color.black);
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textField.getText().equals("")) {
					textField.setText("Input Clue");
					textField.setForeground(Color.LIGHT_GRAY);
				}
			}
		};

		FocusListener ghost2 = new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				numField.setText("");
				numField.setForeground(Color.black);
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (numField.getText().equals("")) {
					numField.setText("Input number");
					numField.setForeground(Color.LIGHT_GRAY);
				}
			}
		};
		textField.addFocusListener(ghost1);
		numField.addFocusListener(ghost2);

		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = textField.getText();
				try {
					int x = Integer.parseInt(numField.getText());
					clueCount = x;
					} catch (NumberFormatException a) {
						label1.setText("Invalid clue or number Please enter again!");
					}
				if (m.Legality(input) == true && m.legalityNumThree(clueCount)) {
					textField.setText("Input Clue");
					textField.setForeground(Color.LIGHT_GRAY);
					numField.setText("Input number");
					numField.setForeground(Color.LIGHT_GRAY);

					x4.setVisible(false);  
					label1.setText(input);
					label2.setText(numField.getText());
					labelCode.setText("Hint: " + input);
					labelNumber.setText("Number: " + clueCount);
					labelCode.setFont(new Font("Serif", Font.PLAIN, 40));
					labelNumber.setFont(new Font("Serif", Font.PLAIN, 40));
					if (input.equalsIgnoreCase("exam")) {
						JFrame frame = new JFrame("EasterEgg");
						frame.setLocation(460, 250);
						ImageIcon icon = new ImageIcon("src/IMG_2829.jpg");
						JLabel label = new JLabel(icon);

						frame.add(label);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.pack();

						frame.setVisible(true);
					}

					
					if (input.equalsIgnoreCase("hertz")) {
						JFrame frame = new JFrame("EasterEgg");
						frame.setLocation(450, 250);
						ImageIcon icon = new ImageIcon("src/hertz.jpg");
						JLabel label = new JLabel(icon);

						frame.add(label);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.pack();

						frame.setVisible(true);
					}

				}

				else if (m.Legality(input) && !m.legalityNum(clueCount)) {
					labelCode.setText("Hint: " + input);
					labelNumber.setText("Number: Invalid Number");
					label1.setText("Invalid number Please enter again!");
				} else if (!m.Legality(input) && m.legalityNum(clueCount)) {
					labelCode.setText("Hint: Invalid Hint");
					labelNumber.setText("Number: " + clueCount);
					label1.setText("Invalid clue  Please enter again!");
				} else {
					labelNumber.setText("Number: Invalid Number");
					labelCode.setText("Hint: Invalid Hint");
					label1.setText("Invalid clue and number Please enter again!");
				}

			}

		});

		// notifyObserver();
		panelRight.add(jb);
		panelRight.add(textField);
		panelRight.add(numField);
		panelRight.add(label1);
		panelRight.add(label2);
		
		x4.setJMenuBar(menuBar2);
		// x4.setSize(750, 750);

		GridLayout inY = new GridLayout(0, 2);
		x4.setLayout(inY);
		for (int i = 0; i < 25; i++) {
			String temp = m.allLocations.get(i).getCodeName();
			JButton j = new JButton(temp);
			if (m.allLocations.get(i).getTeam() == "Red") {
				j.setBackground(Color.RED);

			} else if (m.allLocations.get(i).getTeam() == "Blue") {
				j.setBackground(Color.BLUE);
			
			} else if (m.allLocations.get(i).getTeam() == "Green") {
				j.setBackground(Color.GREEN);

			} else if (m.allLocations.get(i).getTeam() == "Bystander") {
				j.setBackground(Color.YELLOW);

			} else if (m.allLocations.get(i).getTeam() == "Assassin") {
				j.setBackground(Color.PINK);

			}
			panelLeft.add(j);

		}
		x4.setExtendedState(JFrame.MAXIMIZED_BOTH);
		x4.setUndecorated(true);
		x4.setVisible(true);
		x4.getContentPane().add(panelRight, BorderLayout.EAST);
		x4.add(panelLeft);
		
		x4.setVisible(false);


		JButton Spy = new JButton("Spy Master");
		Spy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				x4.setVisible(true);
			}});


		panelTop.add(Spy);
		panelTop.add(labelCode);
		panelTop.add(labelNumber);


		JLabel disturn = new JLabel();
			disturn.setText("Turn : Team Red");
			disturn.setForeground(Color.red);
			disturn.setFont(new Font("Serif", Font.PLAIN, 40));

		panelTop.add(disturn);
		
		JButton tend = new JButton("End turn");// lets make this the the button that ends turns
		tend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent t) {
				ssr.changeTurn3();
				labelCode.setText("Hint: ");
				labelNumber.setText("Number: ");
				clueCount = 0;  	// update the label Clue count
				if (ssr.getTurn() == 1) {				
					disturn.setText("Turn : Team Red");
					disturn.setForeground(Color.red);
				}

				if (ssr.getTurn() == 0) {
					disturn.setText("Turn : Team Blue");
					disturn.setForeground(Color.blue);
				}
				if (ssr.getTurn() == 2) {
					disturn.setText("Turn : Team Green");
					disturn.setForeground(Color.GREEN);
				}

				// labelCode.setFont(new Font("Serif", Font.PLAIN, 40));
				// labelNumber.setFont(new Font("Serif", Font.PLAIN, 40));

			}
		});
		panelTop.add(tend);
		// instruction here

		JLabel redScore = new JLabel("Red Team Points: " + m.getNumRed());
		redScore.setForeground(Color.red);
		redScore.setFont(new Font("Serif", Font.PLAIN, 40));
		panelTop.add(redScore);

		JLabel blueScore = new JLabel("Blue Team Points: " + m.getNumBlue());
		blueScore.setForeground(Color.blue);
		blueScore.setFont(new Font("Serif", Font.PLAIN, 40));
		panelTop.add(blueScore);
		
		JLabel greenScore = new JLabel("Green Team Points: " + m.getNumGreen());
		greenScore.setForeground(Color.GREEN);
		greenScore.setFont(new Font("Serif", Font.PLAIN, 40));
		panelTop.add(greenScore);
		
		JLabel eliminationBoard = new JLabel("Teams Eliminated: " + m.whoElim());
		eliminationBoard.setForeground(Color.PINK);
		eliminationBoard.setFont(new Font("Serif", Font.PLAIN, 40));
		panelTop.add(eliminationBoard);

		// JTextArea countField = new JTextArea();
		// countField.setText("this field is for red and blue counter");
		// panelTop.add(countField);

		/*
		 * //hasan JTextArea clueAndCount = new JTextArea() ; String x =
		 * "set equal to clue getter, " ;//need getters String g =
		 * "set equal to number of cards the clue applies to" ;//need getters
		 * clueAndCount.setText(x + g); panelTop.add(clueAndCount); //end
		 */

		// hh

		for (int i = 0; i < 25; i++) {
			String temp = m.allLocations.get(i).getCodeName();
			JButton j = new JButton(temp);
			String team = m.allLocations.get(i).getTeam();

			panelBottom.add(j);
			j.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent E) {
					boolean turnOver = false;
//					if (clueCount != 0) {
//					clueCount = clueCount - 1;
//					}
					j.setMultiClickThreshhold(1000000000);

					if (team == "Red") {
						if (m.getRedAssassin() == 0) {
						if (ssr.getTurn() != 1) {
//						ssr.changeTurn3();
							clueCount = 0;
						}
						
						j.setForeground(Color.red);
						
						m.setNumRed(m.getNumRed()-1);
						redScore.setText("Red Team Points: " + m.getNumRed());
						clueCount = clueCount - 1;
						if (clueCount <= 0) {	//moved clue count change here..
							ssr.changeTurn3();
							turnOver = true;

						}
						if(m.getNumRed() == 0 && m.getRedAssassin() == 0) {
							win = new JFrame("Game Won");
							win.setLocation(450, 250);
							ImageIcon icon = new ImageIcon("src/red.jpg");
							JLabel label = new JLabel(icon);

							win.add(label);
							win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							win.pack();
							win.setVisible(true);
						}
						} else {
							ssr.changeTurn3();
						}
					}  if (team == "Blue") {
						if (m.getBlueAssassin() == 0)
						if (ssr.getTurn() != 0) {
//							ssr.changeTurn3();
							clueCount = 0;
						}
						
						j.setForeground(Color.blue);
						m.setNumBlue(m.getNumBlue()-1);
						blueScore.setText("Blue Team Points: " + m.getNumBlue());
						clueCount = clueCount - 1;
						if (clueCount <= 0) {	//moved clue count change here..
							ssr.changeTurn3();
							turnOver = true;

						}
						if(m.getNumBlue() == 0 && m.getBlueAssassin() == 0) {
							win = new JFrame("Game Won");
							win.setLocation(450, 250);
							ImageIcon icon = new ImageIcon("src/blue.jpg");
							JLabel label = new JLabel(icon);

							win.add(label);
							win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							win.pack();
							win.setVisible(true);
						
							
							
						}
					}  if (team == "Green") {
						if (ssr.getTurn() != 2) {
//							ssr.changeTurn3();
							clueCount = 0;
						}
						
						j.setForeground(Color.GREEN);
						m.setNumGreen(m.getNumGreen()-1);
						greenScore.setText("Green Team Points: " + m.getNumGreen());
						clueCount = clueCount - 1;
						if (clueCount <= 0) {	//moved clue count change here..
							ssr.changeTurn3();
							turnOver = true;

						}
						if(m.getNumGreen() == 0 && m.getGreenAssassin() == 0) {
							win = new JFrame("Game Won");
							win.setLocation(450, 250);
							ImageIcon icon = new ImageIcon("src/green.jpg");
							JLabel label = new JLabel(icon);

							win.add(label);
							win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							win.pack();
							win.setVisible(true);							
						}
					} 
					else if (team == "Bystander") {
						j.setForeground(Color.yellow);
						if (turnOver == false) {
							ssr.changeTurn3();
							clueCount = 0;
						}

					} else if (team == "Assassin") {
						j.setForeground(Color.PINK);
						m.setNumAssassin(m.getNumAssassin()-1);
						if(ssr.getTurn() == 0) {
							m.setBlueAssassin(1);
						}
						if(ssr.getTurn() == 1) {
							m.setRedAssassin(1);
						}
						if(ssr.getTurn() == 2) {
							m.setGreenAssassin(1);
						}
						eliminationBoard.setText("Teams Eliminated: " + m.whoElim());
						if(m.assWinThree() > -1) {
						if(m.assWinThree() == 0) {
							ssr.setTurn(0);
							win = new JFrame("Game Won");
							win.setLocation(450, 250);
							ImageIcon icon = new ImageIcon("src/blue.jpg");
							JLabel label = new JLabel(icon);

							win.add(label);
							win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							win.pack();
							win.setVisible(true);
						}
							if(m.assWinThree() == 1) {
								ssr.setTurn(1);
								JFrame q = new JFrame("Game Won");
								q.setLocation(450, 250);
								ImageIcon w = new ImageIcon("src/red.jpg");
								JLabel y = new JLabel(w);

								q.add(y);
								q.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
								q.pack();
								q.setVisible(true);								
							}
							if(m.assWinThree() == 2) {
								ssr.setTurn(2);
								JFrame q = new JFrame("Game Won");
								q.setLocation(450, 250);
								ImageIcon w = new ImageIcon("src/green.jpg");
								JLabel y = new JLabel(w);

								q.add(y);
								q.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
								q.pack();
								q.setVisible(true);								
							}
						}
						ssr.changeTurn3();
						labelCode.setText("Hint: ");
						labelNumber.setText("Number: ");
						clueCount = 0;
					}
					
					labelNumber.setText("Number: " + clueCount);   	// update the label Clue count
					if (ssr.getTurn() == 1) {				
						disturn.setText("Turn : Team Red");
						disturn.setForeground(Color.red);
					}

					if (ssr.getTurn() == 0) {
						disturn.setText("Turn : Team Blue");
						disturn.setForeground(Color.blue);

					}
					if (ssr.getTurn() == 2) {
						disturn.setText("Turn : Team Green");
						disturn.setForeground(Color.green);
					}
				}
			});

			// ll
		}
		x3.add(panelTop);
		x3.add(panelBottom);
		x3.setVisible(true);
		
		
		
	}
	//}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}
}