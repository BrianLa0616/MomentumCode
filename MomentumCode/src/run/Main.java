package run;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import variables.Number;
import variables.Cond;
import variables.Letter;
import variables.Text;

public class Main extends JPanel {
	private String fileName;
	private String code;
	private JFileChooser chooser;
	private FileNameExtensionFilter filter;
	private JFrame frmMomentum;
	private JTextArea consoleArea;
	private JTextArea editor;
	private JScrollPane scroll;
	private JScrollPane scroll2;
	private String[] codeList;
	private String[] ck;
	private String currentStatement;
	private ArrayList<Text> texts;
	private ArrayList<Letter> letters;
	private ArrayList<Cond> conds;
	private ArrayList<Number> numbers;
	private Color c = new Color(47, 47, 47);
	private Color y = new Color(100, 221, 247); // Text
	private Color p = new Color(255, 0, 0); // Highlight

	public Main() {
		numbers = new ArrayList<Number>();
		texts = new ArrayList<Text>();
		conds = new ArrayList<Cond>();
		letters = new ArrayList<Letter>();
		frmMomentum = new JFrame();
		frmMomentum.setTitle("Momentum IDE for Beginning Developers");
		frmMomentum.setBounds(100, 100, 1100, 700);
		frmMomentum.setBackground(c);
		frmMomentum.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMomentum.getContentPane().setLayout(new BorderLayout(0, 0));
		frmMomentum.setResizable(false);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(c);
		frmMomentum.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel console = new JPanel();
//		Icon icon = new ImageIcon("m.gif");
		console.setBackground(c);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("cursor.gif");

		Cursor cursor = toolkit.createCustomCursor(image, new Point(console.getX(), console.getY()), "img");
		console.setCursor(cursor);
		tabbedPane.addTab("Momentum", null, console, null);
		console.setLayout(null);

		JLabel editorLabel = new JLabel("Editor");
		editorLabel.setForeground(Color.WHITE);
		editorLabel.setBounds(675, 10, 100, 50);
		editorLabel.setFont(editorLabel.getFont().deriveFont(Font.BOLD, 14f));
		console.add(editorLabel);

		JLabel consoleLabel = new JLabel("Console");
		consoleLabel.setBounds(675, 450, 100, 50);
		consoleLabel.setForeground(Color.WHITE);
		consoleLabel.setFont(consoleLabel.getFont().deriveFont(Font.BOLD, 14f));
		console.add(consoleLabel);

		JLabel ceLine = new JLabel("");
		ceLine.setBounds(350, 433, 720, 1);
		ceLine.setBackground(Color.WHITE);
		ceLine.setOpaque(true);
		console.add(ceLine);

		JLabel top = new JLabel("");
		top.setBounds(350, 49, 720, 1);
		top.setBackground(Color.WHITE);
		top.setOpaque(true);
		console.add(top);

		JLabel bottom = new JLabel("");
		bottom.setBounds(350, 625, 720, 1);
		bottom.setBackground(Color.WHITE);
		bottom.setOpaque(true);
		console.add(bottom);

		JLabel left = new JLabel("");
		left.setBounds(349, 50, 1, 384);
		left.setBackground(Color.WHITE);
		left.setOpaque(true);
		console.add(left);

		JLabel right = new JLabel("");
		right.setBounds(1070, 50, 1, 384);
		right.setBackground(Color.WHITE);
		right.setOpaque(true);
		console.add(right);

		JLabel cleft = new JLabel("");
		cleft.setBounds(349, 490, 1, 135);
		cleft.setBackground(Color.WHITE);
		cleft.setOpaque(true);
		console.add(cleft);

		JLabel cright = new JLabel("");
		cright.setBounds(1070, 490, 1, 135);
		cright.setBackground(Color.WHITE);
		cright.setOpaque(true);
		console.add(cright);

		JLabel ctop = new JLabel("");
		ctop.setBounds(350, 489, 720, 1);
		ctop.setBackground(Color.WHITE);
		ctop.setOpaque(true);
		console.add(ctop);

		consoleArea = new JTextArea(100, 100);
		consoleArea.setBounds(350, 490, 720, 135);
		consoleArea.setBackground(Color.WHITE);
		consoleArea.setEditable(false);
		consoleArea.setCursor(cursor);
		consoleArea.setSelectedTextColor(p);
		consoleArea.setSelectionColor(c);
		consoleArea.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
		consoleArea.setForeground(Color.WHITE);

		JScrollPane consolePane = new JScrollPane(editor, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		consoleArea.setBackground(c);
//		scroll = new JScrollPane(consoleArea);
//		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		console.add(scroll);
		console.add(consolePane);
		console.add(consoleArea);

		editor = new JTextArea(100, 100);
		editor.setBounds(350, 50, 720, 383);
		editor.setBackground(c);
		editor.setSelectedTextColor(p);
		editor.setSelectionColor(c);
		editor.setForeground(y);
		editor.setCursor(cursor);
		editor.setCaretColor(Color.RED);
		editor.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
		JScrollPane editorPane = new JScrollPane(editor, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		scroll2 = new JScrollPane(editor);
//		scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		console.add(scroll2);
		console.add(editorPane, BorderLayout.EAST);
		console.add(editor);

		JLabel openFiles = new JLabel("Select a .mc or .txt file or start directly");
		JLabel t = new JLabel("programming in the editor!");
		t.setBounds(85, 15, 800, 50);
		openFiles.setBounds(50, 0, 800, 50);
		openFiles.setForeground(Color.WHITE);
		t.setForeground(Color.WHITE);
		console.add(t);
		console.add(openFiles);

		Icon i = new ImageIcon("dl.gif");
		JButton fileButton = new JButton("Open an Existing File", i);

		Icon export = new ImageIcon("export.gif");
		JButton saveButton = new JButton("Save the Current File", export);

		Icon run = new ImageIcon("run.gif");
		JButton runButton = new JButton("Run", run);

		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reset();

				reset();
				codeList = editor.getText().split("\n");

				for (int i = 0; i < codeList.length; i++) {
					currentStatement = codeList[i].replace("\r", "");
					currentStatement = currentStatement.replace("\n", "");

					String[] temp = currentStatement.split("\\s");

					int numWords = 0;
					for (int j = 0; j < temp.length; j++) {
						if (!temp[j].equals("")) {
							numWords++;
						}
					}

					ck = new String[numWords];

					numWords = 0;
					for (int j = 0; j < temp.length; j++) {
						if (!temp[j].equals("")) {
							ck[numWords] = temp[j];
							numWords++;
						}
					}

					if (numWords == 0) {
						continue;
					}
					String tag = ck[0];

					if (tag.equals("Number")) {
						processNumber(0);
					} else if (tag.equals("Text")) {
						processText(0);
					} else if (tag.equals("Letter")) {
						processLetter(0);
					} else if (tag.equals("Cond")) {
						processCond(0);
					} else if (tag.equals("Print")) {
						processPrint(0);
					} else if (tag.equals("Change")) {
						processChange(0);
					} else if (tag.equals("If")) {
						processIf();
					} else if (tag.equals("Loop")) {
						processLoop(i);
					} else if (tag.equals("Input")) {
						processInput(0);
					}
				}
			}
		});

		fileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chooser = new JFileChooser();
				chooser.setCursor(cursor);
				filter = new FileNameExtensionFilter(".mc or .txt files", "txt", "mc");
				chooser.setFileFilter(filter);
				int returnedValue = chooser.showOpenDialog(console);
				if (returnedValue == JFileChooser.APPROVE_OPTION) {
					fileName = chooser.getSelectedFile().getPath();
				}
				try {
					code = new Scanner(new File(fileName)).useDelimiter("\\A").next();
					editor.setText(code);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

			}
		});

		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pathname = System.getProperty("user.dir") + "/";
				JFileChooser fileChooser = new JFileChooser(pathname);
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int result = fileChooser.showSaveDialog(null);
				if (result == JFileChooser.CANCEL_OPTION)
					return;

				File file = fileChooser.getSelectedFile();
				if (file != null) {
					pathname = file.getAbsolutePath();
					PrintWriter fileOut;
					try {
						fileOut = new PrintWriter(new FileWriter(file));
					} catch (IOException ex) {
						System.out.println("*** Can't create file ***");
						return;
					}

					fileOut.close();
				}
			}

		});
		fileButton.setBounds(15, 55, 150, 25);
		console.add(fileButton);

		saveButton.setBounds(180, 55, 150, 25);
		console.add(saveButton);

		runButton.setBounds(349, 25, 117, 20);
		runButton.setForeground(new Color(87, 182, 65));
		console.add(runButton);

	}

	public ArrayList<Text> getText() {
		return texts;
	}

	public ArrayList<Letter> getLetters() {
		return letters;
	}

	public ArrayList<Cond> getConds() {
		return conds;
	}

	public ArrayList<Number> getNumbers() {
		return numbers;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawLine(300, 0, 300, 1000);
	}

	public boolean getNumberCondition(String cond) {
		double d1 = 0, d2 = 0;
		String op = "";
		int opNum = 0;
		if (cond.contains(">=")) {
			op = ">=";
			opNum = 3;
		} else if (cond.contains("<=")) {
			op = "<=";
			opNum = 4;
		} else if (cond.contains(">")) {
			op = ">";
			opNum = 1;
		} else if (cond.contains("<")) {
			op = "<";
			opNum = 2;
		} else if (cond.contains("==")) {
			op = "==";
			opNum = 5;
		} else if (cond.contains("!=")) {
			op = "!=";
			opNum = 6;
		}

		String s1 = cond.substring(0, cond.indexOf(op)).trim(),
				s2 = cond.substring(cond.indexOf(op) + op.length()).trim();
		boolean s1hardCoded = true, s2hardCoded = true;

		for (int i = 0; i < s1.length(); i++) {
			if (!Character.isDigit(s1.charAt(i)) && s1.charAt(i) != '.')
				s1hardCoded = false;
		}

		for (int i = 0; i < s2.length(); i++) {
			if (!Character.isDigit(s2.charAt(i)) && s2.charAt(i) != '.') {
				s2hardCoded = false;
			}
		}

		if (s1hardCoded) {
			d1 = Double.parseDouble(s1);
		} else {
			for (int i = 0; i < numbers.size(); i++) {
				if (s1.contains(numbers.get(i).getName()))
					d1 = numbers.get(i).getValue();
			}
		}

		if (s2hardCoded) {
			d2 = Double.parseDouble(s2);
		} else {
			for (int i = 0; i < numbers.size(); i++) {
				if (s2.contains(numbers.get(i).getName()))
					d2 = numbers.get(i).getValue();
			}
		}

		switch (opNum) {
		case 1:
			return d1 > d2;
		case 2:
			return d1 < d2;
		case 3:
			return d1 >= d2;
		case 4:
			return d1 <= d2;
		case 5:
			return Math.abs(d1 - d2) < 0.00001;
		case 6:
			return d1 != d2;
		default:
			throw new IllegalArgumentException("Invalid operation");
		}
	}

	public void changeNumber(int index) {
		System.out.println(index);
		if (ck[2 + index].trim().equals("=")) {
			for (int i = 0; i < numbers.size(); i++) {
				if (numbers.get(i).getName().trim().equals(ck[1 + index]))
					numbers.get(i).setValue(Double.parseDouble(ck[3 + index]));
			}
		} else if (ck[2 + index].trim().equals("+=")) {
			for (int i = 0; i < numbers.size(); i++) {
				if (numbers.get(i).getName().trim().equals(ck[1 + index]))
					numbers.get(i).add(Double.parseDouble(ck[3 + index]));
			}
		} else if (ck[2 + index].trim().equals("-=")) {
			for (int i = 0; i < numbers.size(); i++) {
				if (numbers.get(i).getName().trim().equals(ck[1 + index]))
					numbers.get(i).subtract(Double.parseDouble(ck[3 + index]));
			}
		} else if (ck[2 + index].trim().equals("*=")) {
			for (int i = 0; i < numbers.size(); i++) {
				if (numbers.get(i).getName().trim().equals(ck[1 + index]))
					numbers.get(i).multiplyBy(Double.parseDouble(ck[3 + index]));
			}
		} else if (ck[2 + index].trim().equals("/=")) {
			for (int i = 0; i < numbers.size(); i++) {
				if (numbers.get(i).getName().trim().equals(ck[1 + index]))
					numbers.get(i).divideBy(Double.parseDouble(ck[3 + index]));
			}
		}
	}

	public void changeCond(int index) {
		if (ck[3 + index].equals("true")) {
			for (int i = 0; i < conds.size(); i++) {
				if (conds.get(i).getName().trim().equals(ck[1 + index]))
					conds.get(i).setCond(true);
			}
		} else if (ck[3 + index].equals("false")) {
			for (int i = 0; i < conds.size(); i++) {
				if (conds.get(i).getName().trim().equals(ck[1 + index]))
					conds.get(i).setCond(false);
			}
		}
	}

	public void changeText(int ind) {
		String s = "";
		boolean isQuote = false;
		int index = 0;
		for (int i = 0; i < texts.size(); i++) {
			if (texts.get(i).getName().trim().equals(ck[1 + ind])) {
				index = i;
				if (ck[2 + ind].equals("=")) {
					s = "";
				} else if (ck[2 + ind].equals("+=")) {
					s = texts.get(i).getText();
				}
				isQuote = true;
			}
		}

		if (isQuote) {
			isQuote = false;
			for (int i = 3 + ind; i < ck.length; i++) {
				if (isQuote) {
					if (ck[i].equals("")) {
						s += (" ");
					} else if (ck[i].charAt(ck[i].length() - 1) == '"') {
						isQuote = !isQuote;
						s += (ck[i].substring(0, ck[i].length() - 1) + "");
					} else
						s += (ck[i] + " ");
				} else {

					if (!ck[i].equals("") && ck[i].charAt(0) == '"') {
						isQuote = !isQuote;
						if (ck[i].charAt(ck[i].length() - 1) == '"' && ck[i].length() != 1) {
							s += (ck[i].substring(1, ck[i].length() - 1) + "");
							isQuote = !isQuote;
						} else
							s += (ck[i].substring(1) + " ");
					} else
						s += getVariable(ck[i]);
				}

			}
			texts.get(index).setText(s);
		}
	}

	public void changeLetter(int index) {
		for (int i = 0; i < letters.size(); i++) {
			if (letters.get(i).getName().trim().equals(ck[1 + index]))
				letters.get(i).setLetter(ck[3 + index].charAt(1));
		}
	}

	public void processNumber(int index) {
		if (isNewVariable(ck[1 + index])) {
			if (!ck[3 + index].contains("."))
				ck[3 + index] += ".0";
			numbers.add(new Number(ck[1 + index], Double.parseDouble(ck[3 + index])));
		}
	}

	public void processText(int index) {
		if (isNewVariable(ck[1 + index])) {
			String txt = "";
			boolean isQuote = false;

			for (int i = 3 + index; i < ck.length; i++) {
				if (isQuote) {
					if (ck[i].equals("")) {
						txt += (" ");
					} else if (ck[i].charAt(ck[i].length() - 1) == '"') {
						isQuote = !isQuote;
						txt += (ck[i].substring(0, ck[i].length() - 1) + "");
					} else
						txt += (ck[i] + " ");
				} else {

					if (!ck[i].equals("") && ck[i].charAt(0) == '"') {
						isQuote = !isQuote;
						if (ck[i].charAt(ck[i].length() - 1) == '"' && ck[i].length() != 1) {
							txt += (ck[i].substring(1, ck[i].length() - 1) + "");
							isQuote = !isQuote;
						} else
							txt += (ck[i].substring(1) + " ");
					} else
						txt += getVariable(ck[i]);
				}

			}
			texts.add(new Text(ck[1 + index], txt));

		}
	}

	private String getVariable(String name) {
		for (int i = 0; i < conds.size(); i++) {

			if (name.equals(conds.get(i).getName())) {
				return conds.get(i).getCond() + "";
			}
		}
		for (int i = 0; i < texts.size(); i++) {
			if (name.equals(texts.get(i).getName())) {
				return texts.get(i).getText().substring(1, texts.get(i).getText().length() - 1) + "";
			}
		}

		for (int i = 0; i < numbers.size(); i++) {
			if (name.equals(numbers.get(i).getName())) {
				return (numbers.get(i).getValue() + "");
			}
		}

		for (int i = 0; i < letters.size(); i++) {
			if (name.equals(letters.get(i).getName())) {
				return (letters.get(i).getLetter() + "");

			}
		}
		return "none";
	}

	public void processCond(int index) {
		if (isNewVariable(ck[1 + index])) {
			if (ck[3 + index].equals("true"))
				conds.add(new Cond(ck[1 + index], true));
			else
				conds.add(new Cond(ck[1 + index], false));
		}
	}

	public void processLetter(int index) {
		if (isNewVariable(ck[1 + index])) {
			letters.add(new Letter(ck[1 + index], ck[3 + index].charAt(1)));
		}
	}

	public void processPrint(int index) {
		boolean isQuote = false;
		for (int j = 1 + index; j < ck.length; j++) {
			if (isQuote) {
				if (ck[j].equals("")) {
					consoleArea.append(" ");
				} else if (ck[j].charAt(ck[j].length() - 1) == '"') {
					isQuote = !isQuote;
					consoleArea.append(ck[j].substring(0, ck[j].length() - 1) + "");
				} else
					consoleArea.append(ck[j] + " ");
			} else {
				if (!ck[j].equals("") && ck[j].charAt(0) == '"') {
					isQuote = !isQuote;
					if (ck[j].charAt(ck[j].length() - 1) == '"' && ck[j].length() != 1) {
						consoleArea.append(ck[j].substring(1, ck[j].length() - 1) + "");
						isQuote = !isQuote;
					} else
						consoleArea.append(ck[j].substring(1) + " ");
				} else {
					printVariable(ck[j]);
				}
			}
		}

		consoleArea.append("\n");
	}

	public void processIf() {
		if (getNumberCondition(ck[1] + " " + ck[2] + " " + ck[3])) {
			String statementTag = ck[4];
			if (statementTag.equals("Change"))
				processChange(4);
			else if (ck[4].equals("Print"))
				processPrint(4);
			else if (ck[4].equals("Input"))
				processInput(4);
			else if (ck[4].equals("Number"))
				processNumber(4);
			else if (ck[4].equals("Text"))
				processText(4);
			else if (ck[4].equals("Cond"))
				processCond(4);
			else if (ck[4].equals("Letter"))
				processLetter(4);
		}
	}

	public void processChange(int index) {
		String dataType = "";
		for (int b = 0; b < numbers.size(); b++) {
			if (numbers.get(b).getName().trim().equals(ck[1 + index])) {
				dataType = "number";
				changeNumber(index);
			}
		}
		for (int y = 0; y < conds.size(); y++) {
			if (conds.get(y).getName().trim().equals(ck[1 + index])) {
				dataType = "cond";
				changeCond(index);
			}
		}
		for (int x = 0; x < texts.size(); x++) {
			if (texts.get(x).getName().trim().equals(ck[1 + index])) {
				dataType = "text";
				changeText(index);
			}
		}
		for (int e = 0; e < letters.size(); e++) {
			if (letters.get(e).getName().trim().equals(ck[1 + index])) {
				dataType = "letter";
				changeLetter(index);
			}
		}
	}

	public void processLoop(int j) {
		int times = Integer.parseInt(ck[1]);
		int original = j;
		for (int i = 1; i < times; i++) {
			while (!currentStatement.equals("End")) {
				currentStatement = codeList[j].replace("\r", "");
				currentStatement = currentStatement.replace("\n", "");
				ck = currentStatement.split(" ");

				String tag = ck[0];

				if (tag.equals("Number")) {
					processNumber(0);
				} else if (tag.equals("Text")) {
					processText(0);
				} else if (tag.equals("Letter")) {
					processLetter(0);
				} else if (tag.equals("Cond")) {
					processCond(0);
				} else if (tag.equals("Print")) {
					processPrint(0);
				} else if (tag.equals("Change")) {
					processChange(0);
				} else if (tag.equals("If")) {
					processIf();
				} else if (tag.equals("Input")) {
					processInput(0);
				}

				j++;
			}

			j = original;
			currentStatement = "";
		}
	}

	public void processInput(int index) {
		if (ck[1 + index].equals("number")) {
			double d = Double.parseDouble(JOptionPane.showInputDialog("Enter value for " + ck[2 + index]));
			numbers.add(new Number(ck[2 + index], d));
		} else if (ck[1 + index].equals("text")) {
			String s = JOptionPane.showInputDialog("Enter value for " + ck[2 + index]);
			texts.add(new Text(ck[2 + index], s));
		} else if (ck[1 + index].equals("cond")) {
			boolean b = Boolean.parseBoolean(JOptionPane.showInputDialog("Enter value for " + ck[2 + index]));
			conds.add(new Cond(ck[2 + index], b));
		} else if (ck[1 + index].equals("letter")) {
			char l = JOptionPane.showInputDialog("Enter value for " + ck[2 + index]).charAt(0);
			letters.add(new Letter(ck[2 + index], l));
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmMomentum.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private boolean isNewVariable(String name) {
		boolean newVar = true;
		for (int i = 0; i < conds.size(); i++) {
			if (name.equals(conds.get(i).getName())) {
				newVar = false;
			}
		}
		for (int i = 0; i < texts.size(); i++) {
			if (name.equals(texts.get(i).getName())) {
				newVar = false;
			}

		}
		for (int i = 0; i < numbers.size(); i++) {
			if (name.equals(numbers.get(i).getName())) {
				newVar = false;
			}
		}
		for (int i = 0; i < letters.size(); i++) {
			if (name.equals(letters.get(i).getName())) {
				newVar = false;
			}
		}

		if (!newVar) {
			consoleArea.append("The variable name " + name + " is already being used \n");

		}
		return newVar;
	}

	private void printVariable(String name) {
		boolean printed = false;
		for (int i = 0; i < conds.size(); i++) {

			if (name.equals(conds.get(i).getName())) {
				consoleArea.append(conds.get(i).getCond() + "");
				printed = true;
			}
		}
		if (!printed) {
			for (int i = 0; i < texts.size(); i++) {
				if (name.equals(texts.get(i).getName())) {
					consoleArea.append(texts.get(i).getText().substring(0, texts.get(i).getText().length()) + "");
					printed = true;
				}
			}
		}
		if (!printed) {
			for (int i = 0; i < numbers.size(); i++) {
				if (name.equals(numbers.get(i).getName())) {
					consoleArea.append(numbers.get(i).getValue() + "");
					printed = true;
				}
			}
		}
		if (!printed) {
			for (int i = 0; i < letters.size(); i++) {
				if (name.equals(letters.get(i).getName())) {
					consoleArea.append(letters.get(i).getLetter() + "");

					printed = true;
				}
			}
		}
	}

	private void reset() {
		conds.clear();
		texts.clear();
		numbers.clear();
		letters.clear();
		consoleArea.setText(null);
	}
}