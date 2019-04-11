mainGUI.java
D
K
P
R
Type
Java
Size
30 KB (30,502 bytes)
Storage used
0 bytesOwned by utexas.edu
Location
FINAL CODE
Creator
Divya Chandrupatla
Modified
2:12 PM by Divya Chandrupatla
Opened
2:14 PM by me
Created
2:12 PM
Add a description
Viewers can download
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.*;

public class mainGUI {

	public static final Font font = new Font("Arial", Font.BOLD, 40);
	
	//Create two players
	public static player p1 = new player("Player 1");
	public static player p2 = new player("Player 2");
	public static player currPlayer = p1;

	
	//Category Names
	public static final String COne = "Encryption Algorithms";
	public static final String CTwo = "DoS Attacks";
	public static final String CThree = "RSA Encryption";
	public static final String CFour = "Malicious Software";
	public static final String CFive = "User Authentication";
	//Game Title
	public static final String GAME_TITLE = "Security Jeopardy";

	// Question Strings
	public static List<String> categoryOne = new ArrayList<String>();
	public static List<String> categoryTwo = new ArrayList<String>();
	public static List<String> categoryThree = new ArrayList<String>();
	public static List<String> categoryFour = new ArrayList<String>();
	public static List<String> categoryFive = new ArrayList<String>();

	public final Random random = new Random();
	public static String answer;
	public static String question;

	// q frame
	public static String qTitle;
	public static JTextField userAnswer = new JTextField();
	public static JButton userAnswer2 = new JButton("Answer");
	public static JButton showAnswer = new JButton("Show the answer");
	public static JFrame qframe = new JFrame("question");

	// points counter
	public static int pointsBig = 0; // points for a specific question
	public static JLabel pointsP1Label = new JLabel("p1 points: " + p1.getPoints() + "     ");
	public static JLabel pointsP2Label = new JLabel("p2 points: " + p2.getPoints() + "     ");
	public static JLabel currentPlayerLabel = new JLabel(": " + currPlayer.getName());
	public static JButton answerButton = new JButton("Answer");
	public static JButton returnButton = new JButton("return to main board");

	// answer frame
	public static JFrame aframe = new JFrame("answer");
	public static JLabel questionTitle = new JLabel(qTitle);
	public static JLabel questionLabel = new JLabel("Question: " + question);

	// answer frame
	public static JLabel answerTitle = new JLabel("Answer");
	public static JLabel answerLabel = new JLabel();

	// spacers for header on main frame
	public static Component spacer4 = Box.createHorizontalStrut(75);
	public static Component spacer5 = Box.createHorizontalStrut(150);

	// standard file that it inputted into the file
	public static String iFile = "/Users/divyachandrupatla/Documents/workspace/security-jepoardy/src/questionFile.txt";


	// Colors to be used for the boxes
	static Color Turquoise = new Color(64, 224, 208);
	static Color Turqmed = new Color(72, 209, 204);
	static Color TurqDark = new Color(0, 206, 209);
	static Color Turqlight = new Color(175, 238, 238);
	static Color paleBlue = new Color(176, 224, 230);

	// insert question boxes for categoryOne
	final static JLabel COneLabel = new JLabel("<HTML>Encryption<br>Algorithm</HTML>");
	final static JButton COne100 = new JButton("100");
	final static JButton COne200 = new JButton("200");
	final static JButton COne300 = new JButton("300");
	final static JButton COne400 = new JButton("400");
	final static JButton COne500 = new JButton("500");

	// insert question boxes for categoryTwo
	final JLabel CTwoLabel = new JLabel("<HTML>DoS<br>Attacks</HTML>");
	final static JButton CTwo100 = new JButton("100");
	final static JButton CTwo200 = new JButton("200");
	final static JButton CTwo300 = new JButton("300");
	final static JButton CTwo400 = new JButton("400");
	final static JButton CTwo500 = new JButton("500");

	// insert question boxes for categoryThree
	final JLabel CThreeLabel = new JLabel("<HTML>RSA<br>Encryption</HTML>");
	final static JButton CThree100 = new JButton("100");
	final static JButton CThree200 = new JButton("200");
	final static JButton CThree300 = new JButton("300");
	final static JButton CThree400 = new JButton("400");
	final static JButton CThree500 = new JButton("500");

	// insert question boxes for categoryFour
	final JLabel CFourLabel = new JLabel("<HTML>Malicious<br>Software</HTML>");
	final static JButton CFour100 = new JButton("100");
	final static JButton CFour200 = new JButton("200");
	final static JButton CFour300 = new JButton("300");
	final static JButton CFour400 = new JButton("400");
	final static JButton CFour500 = new JButton("500");

	// insert question boxes for categoryFive
	final JLabel CFiveLabel = new JLabel("<HTML>User<br>Authentication</HTML>");
	final static JButton CFive100 = new JButton("100");
	final static JButton CFive200 = new JButton("200");
	final static JButton CFive300 = new JButton("300");
	final static JButton CFive400 = new JButton("400");
	final static JButton CFive500 = new JButton("500");

	static JFrame frame = new JFrame("frame");
	static JLabel title = new JLabel(GAME_TITLE);
	static JButton resetButton = new JButton("reset");
	static Dimension d = new Dimension(800, 300);
	static Dimension e = new Dimension(100, 75);
	
	HashMap<String, Integer> pointSystem = new HashMap<String, Integer>();
	

	public mainGUI() {
		
		loadAndReplace(iFile);
		
		// Load Point System
		loadPoints(pointSystem);

		// set main Frame specifications
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(625, 450));
		frame.setLocation(555, 250);

		Box headerBox = Box.createHorizontalBox();
		headerBox.add(pointsP1Label);
		headerBox.add(pointsP2Label);
		headerBox.add(currentPlayerLabel);
		headerBox.add(spacer5);
		headerBox.add(resetButton);

		Box bigBox = Box.createVerticalBox();
		Box allCategories = Box.createHorizontalBox();

		// Create category one Column
		Box C1Box = Box.createVerticalBox();

		COne100.setBackground(paleBlue);
		COne100.setOpaque(true);
		COne100.setBorderPainted(false);
		COne100.setMaximumSize(e);

		COne200.setBackground(Turqlight);
		COne200.setOpaque(true);
		COne200.setBorderPainted(false);
		COne200.setMaximumSize(e);

		COne300.setBackground(Turquoise);
		COne300.setOpaque(true);
		COne300.setBorderPainted(false);
		COne300.setMaximumSize(e);

		COne400.setBackground(Turqmed);
		COne400.setOpaque(true);
		COne400.setBorderPainted(false);
		COne400.setMaximumSize(e);

		COne500.setBackground(TurqDark);
		COne500.setOpaque(true);
		COne500.setBorderPainted(false);
		COne500.setMaximumSize(e);

		// Create category two Column
		Box C2Box = Box.createVerticalBox();

		CTwo100.setBackground(paleBlue);
		CTwo100.setOpaque(true);
		CTwo100.setBorderPainted(false);
		CTwo100.setMaximumSize(e);

		CTwo200.setBackground(Turqlight);
		CTwo200.setOpaque(true);
		CTwo200.setBorderPainted(false);
		CTwo200.setMaximumSize(e);

		CTwo300.setBackground(Turquoise);
		CTwo300.setOpaque(true);
		CTwo300.setBorderPainted(false);
		CTwo300.setMaximumSize(e);

		CTwo400.setBackground(Turqmed);
		CTwo400.setOpaque(true);
		CTwo400.setBorderPainted(false);
		CTwo400.setMaximumSize(e);

		CTwo500.setBackground(TurqDark);
		CTwo500.setOpaque(true);
		CTwo500.setBorderPainted(false);
		CTwo500.setMaximumSize(e);

		// Create category three Column
		Box C3Box = Box.createVerticalBox();

		CThree100.setBackground(paleBlue);
		CThree100.setOpaque(true);
		CThree100.setBorderPainted(false);
		CThree100.setMaximumSize(e);

		CThree200.setBackground(Turqlight);
		CThree200.setOpaque(true);
		CThree200.setBorderPainted(false);
		CThree200.setMaximumSize(e);

		CThree300.setBackground(Turquoise);
		CThree300.setOpaque(true);
		CThree300.setBorderPainted(false);
		CThree300.setMaximumSize(e);

		CThree400.setBackground(Turqmed);
		CThree400.setOpaque(true);
		CThree400.setBorderPainted(false);
		CThree400.setMaximumSize(e);

		CThree500.setBackground(TurqDark);
		CThree500.setOpaque(true);
		CThree500.setBorderPainted(false);
		CThree500.setMaximumSize(e);

		// Create category four Column
		Box C4Box = Box.createVerticalBox();

		CFour100.setBackground(paleBlue);
		CFour100.setOpaque(true);
		CFour100.setBorderPainted(false);
		CFour100.setMaximumSize(e);

		CFour200.setBackground(Turqlight);
		CFour200.setOpaque(true);
		CFour200.setBorderPainted(false);
		CFour200.setMaximumSize(e);

		CFour300.setBackground(Turquoise);
		CFour300.setOpaque(true);
		CFour300.setBorderPainted(false);
		CFour300.setMaximumSize(e);

		CFour400.setBackground(Turqmed);
		CFour400.setOpaque(true);
		CFour400.setBorderPainted(false);
		CFour400.setMaximumSize(e);

		CFour500.setBackground(TurqDark);
		CFour500.setOpaque(true);
		CFour500.setBorderPainted(false);
		CFour500.setMaximumSize(e);

		// Create category five Column
		final Box C5Box = Box.createVerticalBox();

		CFive100.setBackground(paleBlue);
		CFive100.setOpaque(true);
		CFive100.setBorderPainted(false);
		CFive100.setMaximumSize(e);

		CFive200.setBackground(Turqlight);
		CFive200.setOpaque(true);
		CFive200.setBorderPainted(false);
		CFive200.setMaximumSize(e);

		CFive300.setBackground(Turquoise);
		CFive300.setOpaque(true);
		CFive300.setBorderPainted(false);
		CFive300.setMaximumSize(e);

		CFive400.setBackground(Turqmed);
		CFive400.setOpaque(true);
		CFive400.setBorderPainted(false);
		CFive400.setMaximumSize(e);

		CFive500.setBackground(TurqDark);
		CFive500.setOpaque(true);
		CFive500.setBorderPainted(false);
		CFive500.setMaximumSize(e);

		// When a category button is pressed, the button is enabled false, and a
		// populated question frame appears
		COne100.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				answeredQuestion(COne100);
				COne100.setEnabled(false);
				qTitle = COne + " 100";
				pickAQuestion(categoryOne);
				pointsBig = pointSystem.get(qTitle);
				createQFrame();

			}

		});

		COne200.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				answeredQuestion(COne200);
				COne200.setEnabled(false);
				qTitle = COne + " 200";
				pickAQuestion(categoryOne);
				pointsBig = pointSystem.get(qTitle);
				createQFrame();

			}

		});

		COne300.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				answeredQuestion(COne300);
				COne300.setEnabled(false);
				qTitle = COne + " 300";
				pickAQuestion(categoryOne);
				pointsBig = pointSystem.get(qTitle);
				createQFrame();

			}

		});

		COne400.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				answeredQuestion(COne400);
				COne400.setEnabled(false);
				qTitle = COne + " 400";
				pickAQuestion(categoryOne);
				pointsBig = pointSystem.get(qTitle);
				createQFrame();

			}

		});

		COne500.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				answeredQuestion(COne500);
				COne500.setEnabled(false);
				qTitle = COne + " 500";
				pickAQuestion(categoryOne);
				pointsBig = pointSystem.get(qTitle);
				createQFrame();

			}

		});

		CTwo100.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				answeredQuestion(CTwo100);
				CTwo100.setEnabled(false);
				qTitle = CTwo + " 100";
				pickAQuestion(categoryTwo);
				pointsBig = pointSystem.get(qTitle);
				createQFrame();

			}

		});

		CTwo200.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				answeredQuestion(CTwo200);
				CTwo200.setEnabled(false);
				qTitle = CTwo + " 200";
				pickAQuestion(categoryTwo);
				pointsBig = pointSystem.get(qTitle);
				createQFrame();

			}

		});

		CTwo300.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				answeredQuestion(CTwo300);
				CTwo300.setEnabled(false);
				qTitle = CTwo + " 300";
				pickAQuestion(categoryTwo);
				pointsBig = pointSystem.get(qTitle);
				createQFrame();

			}

		});

		CTwo400.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				answeredQuestion(CTwo400);
				CTwo400.setEnabled(false);
				qTitle = CTwo + " 400";
				pickAQuestion(categoryTwo);
				pointsBig = pointSystem.get(qTitle);
				createQFrame();

			}

		});

		CTwo500.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				answeredQuestion(CTwo500);
				CTwo500.setEnabled(false);
				qTitle = CTwo + " 500";
				pickAQuestion(categoryTwo);
				pointsBig = pointSystem.get(qTitle);
				createQFrame();

			}

		});

		CThree100.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				answeredQuestion(CThree100);
				CThree100.setEnabled(false);
				qTitle = CThree + " 100";
				pickAQuestion(categoryThree);
				pointsBig = pointSystem.get(qTitle);
				createQFrame();

			}

		});

		CThree200.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				answeredQuestion(CThree200);
				CThree200.setEnabled(false);
				qTitle = CThree + " 200";
				pickAQuestion(categoryThree);
				pointsBig = pointSystem.get(qTitle);
				createQFrame();

			}

		});

		CThree300.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				answeredQuestion(CThree300);
				CThree300.setEnabled(false);
				qTitle = CThree + " 300";
				pickAQuestion(categoryThree);
				pointsBig = pointSystem.get(qTitle);
				createQFrame();

			}

		});

		CThree400.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				answeredQuestion(CThree400);
				CThree400.setEnabled(false);
				qTitle = CThree + " 400";
				pickAQuestion(categoryThree);
				pointsBig = pointSystem.get(qTitle);
				createQFrame();

			}

		});

		CThree500.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				answeredQuestion(CThree500);
				CThree500.setEnabled(false);
				qTitle = CThree + " 500";
				pickAQuestion(categoryThree);
				pointsBig = pointSystem.get(qTitle);
				createQFrame();

			}

		});

		CFour100.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				answeredQuestion(CFour100);
				CFour100.setEnabled(false);
				qTitle = CFour + " 100";
				pickAQuestion(categoryFour);
				pointsBig = pointSystem.get(qTitle);
				createQFrame();

			}

		});

		CFour200.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				answeredQuestion(CFour200);
				CFour200.setEnabled(false);
				qTitle = CFour + " 200";
				pickAQuestion(categoryFour);
				pointsBig = pointSystem.get(qTitle);
				createQFrame();

			}

		});

		CFour300.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				answeredQuestion(CFour300);
				CFour300.setEnabled(false);
				qTitle = CFour + "s 300";
				pickAQuestion(categoryFour);
				pointsBig = pointSystem.get(qTitle);
				createQFrame();

			}

		});

		CFour400.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				answeredQuestion(CFour400);
				CFour400.setEnabled(false);
				qTitle = CFour + " 400";
				pickAQuestion(categoryFour);
				pointsBig = pointSystem.get(qTitle);
				createQFrame();

			}

		});

		CFour500.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				answeredQuestion(CFour500);
				CFour500.setEnabled(false);
				qTitle = CFour + " 500";
				pickAQuestion(categoryFour);
				pointsBig = pointSystem.get(qTitle);
				createQFrame();

			}

		});

		CFive100.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				answeredQuestion(CFive100);
				CFive100.setEnabled(false);
				qTitle = CFive + " 100";
				pickAQuestion(categoryFive);
				pointsBig = pointSystem.get(qTitle);
				createQFrame();

			}

		});

		CFive200.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				answeredQuestion(CFive200);
				CFive200.setEnabled(false);
				qTitle = CFive + " 200";
				pickAQuestion(categoryFive);
				pointsBig = pointSystem.get(qTitle);
				createQFrame();

			}

		});

		CFive300.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				answeredQuestion(CFive300);
				CFive300.setEnabled(false);
				qTitle = CFive + " 300";
				pickAQuestion(categoryFive);
				pointsBig = pointSystem.get(qTitle);
				createQFrame();

			}

		});

		CFive400.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				answeredQuestion(CFive400);
				CFive400.setEnabled(false);
				qTitle = CFive + " 400";
				pickAQuestion(categoryFive);
				pointsBig = pointSystem.get(qTitle);
				createQFrame();

			}

		});

		CFive500.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				answeredQuestion(CFive500);
				CFive500.setEnabled(false);
				qTitle = CFive + " 500";
				pickAQuestion(categoryFive);
				pointsBig = pointSystem.get(qTitle);
				createQFrame();

			}

		});

		// when reset button clicked, the program resets
		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(p1.getPoints() > p2.getPoints())
				{
					JOptionPane.showMessageDialog(frame, "P1 Wins!");
				}else if(p2.getPoints() > p1.getPoints())
				{
					JOptionPane.showMessageDialog(frame, "P2 Wins!");
				} else {
					JOptionPane.showMessageDialog(frame, "It's a tie!");
				}
				reset();

			}
		});

		showAnswer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				createAFrame();
				userAnswer.setText(null);
				qframe.dispose();
			}

		});

		// when the answer button is clicked, the question frame is disposed and
		// the answer frame is created
		answerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				checkAnswer();
				qframe.setAlwaysOnTop(true);
			}

		});
		
		userAnswer2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				checkAnswer2();
				qframe.setAlwaysOnTop(true);
			}
		});

		// dispose of the answer frame and moves the user back to the main board
		returnButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				answerButton.setVisible(true);
				aframe.dispose();

			}

		});

		// set question Frame specifications
		qframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		qframe.setPreferredSize(new Dimension(900, 200));
		qframe.setLocation(10, 10);

		Box qBox = Box.createVerticalBox();
		questionTitle.setText(qTitle);
		questionLabel.setText("Question: " + question);

		// adding elements to the question box
		qBox.add(questionTitle);
		qBox.add(questionLabel);
		qBox.add(userAnswer);
		qBox.add(answerButton);
		qBox.add(showAnswer);
		qBox.add(userAnswer2);
		userAnswer2.setVisible(false);
		showAnswer.setVisible(false);
		qframe.add(qBox);
		qframe.pack();
		qframe.setVisible(false);

		// set answer Frame specifications
		aframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aframe.setPreferredSize(new Dimension(900, 100));
		aframe.setLocation(200, 300);

		Box aBox = Box.createVerticalBox();

		// add all necessary elements to the answer frame
		aBox.add(answerTitle);
		aBox.add(answerLabel);
		aBox.add(returnButton);
		aframe.add(aBox);
		aframe.pack();
		aframe.setVisible(false);

		// add elements to C1Box
		C1Box.add(COneLabel);
		C1Box.add(COne500);
		C1Box.add(COne400);
		C1Box.add(COne300);
		C1Box.add(COne200);
		C1Box.add(COne100);

		// add elements to C2Box
		C2Box.add(CTwoLabel);
		C2Box.add(CTwo500);
		C2Box.add(CTwo400);
		C2Box.add(CTwo300);
		C2Box.add(CTwo200);
		C2Box.add(CTwo100);

		// add elements to C3Box
		C3Box.add(CThreeLabel);
		C3Box.add(CThree500);
		C3Box.add(CThree400);
		C3Box.add(CThree300);
		C3Box.add(CThree200);
		C3Box.add(CThree100);

		// add elements to C4Box
		C4Box.add(CFourLabel);
		C4Box.add(CFour500);
		C4Box.add(CFour400);
		C4Box.add(CFour300);
		C4Box.add(CFour200);
		C4Box.add(CFour100);

		// add elements to C5Box
		C5Box.add(CFiveLabel);
		C5Box.add(CFive500);
		C5Box.add(CFive400);
		C5Box.add(CFive300);
		C5Box.add(CFive200);
		C5Box.add(CFive100);

		// create spacers in between categories
		Component spacer1 = Box.createHorizontalStrut(5);
		Component spacer2 = Box.createHorizontalStrut(5);
		Component spacer3 = Box.createHorizontalStrut(5);
		Component spacer4 = Box.createHorizontalStrut(5);

		// add all columns to the allCategories box
		allCategories.add(C1Box);
		allCategories.add(spacer1);
		allCategories.add(C2Box);
		allCategories.add(spacer2);
		allCategories.add(C3Box);
		allCategories.add(spacer3);
		allCategories.add(C4Box);
		allCategories.add(spacer4);
		allCategories.add(C5Box);
		allCategories.setPreferredSize(d);

		// adding all of the sub elements to the bigBox
		bigBox.add(headerBox);
		bigBox.add(allCategories);

		frame.add(bigBox);
		frame.pack();
		frame.setVisible(true);
	}

	// changes a category button from blue to yellow when clicked
	public void answeredQuestion(JButton clicked) {
		clicked.setBackground(Color.YELLOW);
		clicked.setOpaque(true);
		clicked.setBorderPainted(false);

	}

	// Creates the question frame that occurs after a category button(ex:
	// Presidents 100) is pressed
	public void createQFrame() {
		System.out.println("in q frame");
		questionTitle.setText(qTitle);
		questionLabel.setText("Question: " + question);
		showAnswer.setVisible(false);
		qframe.setVisible(true);
	}
	
	public void switchPlayer(){
		if (currPlayer.getName().equals(p1.getName())) {
			currPlayer = p2;
		}
		else {
			currPlayer = p1;
		}
		currentPlayerLabel.setText("current player: " + currPlayer.getName());
	}
	
	public void checkAnswer(){
		if (userAnswer.getText() != null) {
			if (userAnswer.getText().equals(answer)) {
				JOptionPane.showMessageDialog(frame, currPlayer.getName() + " got answer correct!");
				currPlayer.addPoints(pointsBig);
				pointsP1Label.setText("p1 points: " + p1.getPoints() + "     ");
				pointsP2Label.setText("p2 points: " + p2.getPoints() + "     ");
				switchPlayer();
				answerButton.setVisible(true);
				userAnswer.setText(null);
				answerButton.repaint();
				qframe.dispose();
				return;
			}
			else {
				qframe.dispose();
				switchPlayer();
				JOptionPane.showMessageDialog(frame, "Answer incorrect, " + currPlayer.getName() + "'s turn!");
				userAnswer.setText(null);
				answerButton.setVisible(false);
				answerButton.repaint();
				userAnswer2.setVisible(true);
				createQFrame();
			}
		}
		return;
	}
	
	public void checkAnswer2()
	{
		if (userAnswer.getText().equals(answer)) {
			JOptionPane.showMessageDialog(frame, currPlayer.getName() + " got answer correct!");
			currPlayer.addPoints(pointsBig);
			pointsP1Label.setText("p1 points: " + p1.getPoints() + "     ");
			pointsP2Label.setText("p2 points: " + p2.getPoints() + "     ");
			switchPlayer();
			userAnswer2.setVisible(false);
			answerButton.setVisible(true);
			userAnswer.setText(null);
			answerButton.repaint();
			qframe.dispose();
			return;
		}
		else {
			JOptionPane.showMessageDialog(frame, "nobody gets the points :(");
			showAnswer.setVisible(true);
			userAnswer2.setVisible(false);
			answerButton.setVisible(false);
			userAnswer.setText(null);
			answerButton.repaint();
			return;
		}
	}


	// creates the answer frame that pops up after the question frame
	public void createAFrame() {
		// System.out.println("answer: " + answer);
		answerLabel.setText(answer);
		answerLabel.repaint();
		aframe.setVisible(true);
	}

	// randomly picks a question from a specific category and separates the
	// question and answer
	public void pickAQuestion(List<String> category) {

		// randomly pulls one string of questions from the category list
		String random = category.get(new Random().nextInt(category.size()));
		random.toString();

		// Parses the String to separate the question and answer
		String[] tokens = random.split("=");

		// removes the question from the category list so that it is not
		// repeated in one game
		category.remove(random);

		// sets the values for the question and answer
		question = tokens[0];
		answer = tokens[1];

	}

	// Resets the entire game by reloading the questions and changing the color
	// of the buttons
	public static void reset() {
		categoryOne.clear();
		categoryFour.clear();
		categoryFive.clear();
		categoryTwo.clear();
		categoryThree.clear();

		loadAndReplace(iFile);
		COne100.setBackground(paleBlue);
		COne100.setOpaque(true);
		COne100.setBorderPainted(false);
		COne100.setEnabled(true);

		COne200.setBackground(Turqlight);
		COne200.setOpaque(true);
		COne200.setBorderPainted(false);
		COne200.setEnabled(true);

		COne300.setBackground(Turquoise);
		COne300.setOpaque(true);
		COne300.setBorderPainted(false);
		COne300.setEnabled(true);

		COne400.setBackground(Turqmed);
		COne400.setOpaque(true);
		COne400.setBorderPainted(false);
		COne400.setEnabled(true);

		COne500.setBackground(TurqDark);
		COne500.setOpaque(true);
		COne500.setBorderPainted(false);
		COne500.setEnabled(true);

		CTwo100.setBackground(paleBlue);
		CTwo100.setOpaque(true);
		CTwo100.setBorderPainted(false);
		CTwo100.setEnabled(true);

		CTwo200.setBackground(Turqlight);
		CTwo200.setOpaque(true);
		CTwo200.setBorderPainted(false);
		CTwo200.setEnabled(true);

		CTwo300.setBackground(Turquoise);
		CTwo300.setOpaque(true);
		CTwo300.setBorderPainted(false);
		CTwo300.setEnabled(true);

		CTwo400.setBackground(Turqmed);
		CTwo400.setOpaque(true);
		CTwo400.setBorderPainted(false);
		CTwo400.setEnabled(true);

		CTwo500.setBackground(TurqDark);
		CTwo500.setOpaque(true);
		CTwo500.setBorderPainted(false);
		CTwo500.setEnabled(true);

		CThree100.setBackground(paleBlue);
		CThree100.setOpaque(true);
		CThree100.setBorderPainted(false);
		CThree100.setEnabled(true);

		CThree200.setBackground(Turqlight);
		CThree200.setOpaque(true);
		CThree200.setBorderPainted(false);
		CThree200.setEnabled(true);

		CThree300.setBackground(Turquoise);
		CThree300.setOpaque(true);
		CThree300.setBorderPainted(false);
		CThree300.setEnabled(true);

		CThree400.setBackground(Turqmed);
		CThree400.setOpaque(true);
		CThree400.setBorderPainted(false);
		CThree400.setEnabled(true);

		CThree500.setBackground(TurqDark);
		CThree500.setOpaque(true);
		CThree500.setBorderPainted(false);
		CThree500.setEnabled(true);

		CFour100.setBackground(paleBlue);
		CFour100.setOpaque(true);
		CFour100.setBorderPainted(false);
		CFour100.setEnabled(true);

		CFour200.setBackground(Turqlight);
		CFour200.setOpaque(true);
		CFour200.setBorderPainted(false);
		CFour200.setEnabled(true);

		CFour300.setBackground(Turquoise);
		CFour300.setOpaque(true);
		CFour300.setBorderPainted(false);
		CFour300.setEnabled(true);

		CFour400.setBackground(Turqmed);
		CFour400.setOpaque(true);
		CFour400.setBorderPainted(false);
		CFour400.setEnabled(true);

		CFour500.setBackground(TurqDark);
		CFour500.setOpaque(true);
		CFour500.setBorderPainted(false);
		CFour500.setEnabled(true);

		CFive100.setBackground(paleBlue);
		CFive100.setOpaque(true);
		CFive100.setBorderPainted(false);
		CFive100.setEnabled(true);

		CFive200.setBackground(Turqlight);
		CFive200.setOpaque(true);
		CFive200.setBorderPainted(false);
		CFive200.setEnabled(true);

		CFive300.setBackground(Turquoise);
		CFive300.setOpaque(true);
		CFive300.setBorderPainted(false);
		CFive300.setEnabled(true);

		CFive400.setBackground(Turqmed);
		CFive400.setOpaque(true);
		CFive400.setBorderPainted(false);
		CFive400.setEnabled(true);

		CFive500.setBackground(TurqDark);
		CFive500.setOpaque(true);
		CFive500.setBorderPainted(false);
		CFive500.setEnabled(true);

		//reset player points to 0
		p1.resetPlayer();
		p2.resetPlayer();
		pointsP1Label.setText("p1 points: " + p1.getPoints() + "     ");
		pointsP2Label.setText("p2 points: " + p2.getPoints());
	}

	// loads in a text file and inserts the data into the correct category
	// strings
	static int loadAndReplace(String inputFile) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(inputFile));
			String s = null;
			// Array of Categories + Delimiter
			String[][] Questions = {{COne,":"},{CTwo,":"},{CThree,":"},{CFour,":"},{CFive,":"}};
			
			String[] s1 = null;
			String[] s2 = null;

			while ((s = in.readLine()) != null) {
				s1 = s.split("/n");

				for (int i = 0; i < s1.length; i++) {
					// System.out.println(s1[0]);
					if (s1[i].contains(Questions[0][0])) {

						s2 = s1[i].split(Questions[0][1]);
						categoryOne.add(s2[1]);
						// System.out.println("President List: " + categoryOne);

					} else if (s1[i].contains(Questions[1][0])) {
						s2 = s1[i].split(Questions[1][1]);
						categoryTwo.add(s2[1]);
						// System.out.println("Members List: " + categoryTwo);
					} else if (s1[i].contains(Questions[2][0])) {
						s2 = s1[i].split(Questions[2][1]);
						categoryThree.add(s2[1]);
						// System.out.println("News List: " + categoryThree);
					} else if (s1[i].contains(Questions[3][0])) {
						s2 = s1[i].split(Questions[3][1]);
						categoryFour.add(s2[1]);
						// System.out.println("Activity List: " + categoryFour);
					} else if (s1[i].contains(Questions[4][0])) {
						s2 = s1[i].split(Questions[4][1]);
						categoryFive.add(s2[1]);
						// System.out.println("Misc List: " + categoryFive);
					}
				}
			}
			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;

	}
	
	void loadPoints(HashMap <String,Integer> pointHash) {
		String[] Categories = {COne,CTwo,CThree,CFour, CFive};
				
		for (int i = 0; i < 5; i++ )
		{
			for (int j = 1; j< 6; j++ ) {
			pointHash.put(Categories[i] + " " + (j) * 100, (j) * 100);
			}
		}
	}

}
