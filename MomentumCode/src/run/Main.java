package run;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import variables.Cond;
import variables.Letter;
import variables.Number;
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

	public Main() {
		numbers = new ArrayList<Number>();
		texts = new ArrayList<Text>();
		conds = new ArrayList<Cond>();
		letters = new ArrayList<Letter>();
		frmMomentum = new JFrame();
		frmMomentum.setTitle("Momentum");
		frmMomentum.setBounds(100, 100, 1100, 700);
		frmMomentum.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMomentum.getContentPane().setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmMomentum.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel console = new JPanel();
		tabbedPane.addTab("Console", null, console, null);
		console.setLayout(null);

		JLabel consoleLabel = new JLabel("Console");
		consoleLabel.setBounds(690, 0, 100, 50);
		console.add(consoleLabel);

		consoleArea = new JTextArea(100, 100);
		consoleArea.setBounds(400, 45, 589, 316);
		consoleArea.setBackground(Color.WHITE);
		consoleArea.setEditable(false);
		scroll = new JScrollPane(consoleArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		console.add(scroll);
		console.add(consoleArea);

		editor = new JTextArea(100, 100);
		editor.setBounds(21, 100, 316, 400);
		editor.setBackground(Color.WHITE);
		scroll2 = new JScrollPane(editor);
		scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		console.add(scroll2);
		console.add(editor);

		JLabel openFiles = new JLabel("Select a .mc or .txt file");
		openFiles.setBounds(21, 10, 137, 25);
		console.add(openFiles);

		JButton fileButton = new JButton("Open");
		JButton runButton = new JButton("Run");

		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reset();

				reset();
				codeList = editor.getText().split("\n");

				for (int i = 0; i < codeList.length; i++) {
					currentStatement = codeList[i].replace("\r", "");
					currentStatement = currentStatement.replace("\n", "");

					ck = currentStatement.split(" ");

					/*
					for (int j = 0; j < ck.length; j++) {
						System.out.println(ck[j]);
					}
					 */
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
						processChange();
					} else if (tag.equals("If")) {
						processIf();
					}
				}
			}
		});

		fileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chooser = new JFileChooser();
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
		fileButton.setBounds(21, 45, 117, 25);
		console.add(fileButton);

		runButton.setBounds(200, 45, 117, 25);
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
		if (cond.contains(">")) {
			op = ">";
			opNum = 1;
			//			System.out.println(1);
		} else if (cond.contains("<")) {
			op = "<";
			opNum = 2;
			//			System.out.println(2);
		} else if (cond.contains(">=")) {
			op = ">=";
			opNum = 3;
			//			System.out.println(3);
		} else if (cond.contains("<=")) {
			op = "<=";
			opNum = 4;
			//			System.out.println(4);
		} else if (cond.contains("==")) {
			op = "==";
			opNum = 5;
			//			System.out.println(5);
		} else if (cond.contains("!=")) {
			op = "!=";
			opNum = 6;
			//			System.out.println(6);
		}

		String s1 = cond.substring(0, cond.indexOf(op)).trim(),
				s2 = cond.substring(cond.indexOf(op) + op.length()).trim();
		//		System.out.println("s1 = " + s1);
		//		System.out.println("s2 = " + s2);
		boolean s1hardCoded = true, s2hardCoded = true;

		for (int i = 0; i < s1.length(); i++) {
			if (!Character.isDigit(s1.charAt(i)) && s1.charAt(i) != '.')
				s1hardCoded = false;
		}

		//		System.out.println("s1hc" + s1hardCoded);

		for (int i = 0; i < s2.length(); i++) {
			if (!Character.isDigit(s2.charAt(i)) && s2.charAt(i) != '.') {
				//				System.out.println("bjafobsnfobsanfobjfsa " + s2.charAt(i));
				s2hardCoded = false;
			}
		}

		//		System.out.println("s2hc" + s2hardCoded);

		if (s1hardCoded) {
			d1 = Double.parseDouble(s1);
			//			System.out.println("hd1 = " + d1);
		} else {
			for (int i = 0; i < numbers.size(); i++) {
				if (s1.contains(numbers.get(i).getName()))
					d1 = numbers.get(i).getValue();
			}
		}

		if (s2hardCoded) {
			d2 = Double.parseDouble(s2);
			//			System.out.println("hd2 = " + d2);
		} else {
			for (int i = 0; i < numbers.size(); i++) {
				if (s2.contains(numbers.get(i).getName()))
					d2 = numbers.get(i).getValue();
			}
		}

		//		System.out.println("d1 = " + d1);
		//		System.out.println("d2 = " + d2);

		switch (opNum) {
		case 1:
			//			System.out.println(d1 > d2);
			return d1 > d2;
		case 2:
			//			System.out.println(d1 < d2);
			return d1 < d2;
		case 3:
			//			System.out.println(d1 >= d2);
			return d1 >= d2;
		case 4:
			//			System.out.println(d1 <= d2);
			return d1 <= d2;
		case 5:
			//			System.out.println(Math.abs(d1 - d2) < 0.00001);
			return Math.abs(d1 - d2) < 0.00001;
		case 6:
			//			System.out.println(d1 != d2);
			return d1 != d2;
		default:
			throw new IllegalArgumentException("Invalid operation");
		}
	}

	public void changeNumber() {
		if (ck[2].equals("=")) {
			for (int i = 0; i < numbers.size(); i++) {
				if (numbers.get(i).getName().trim().equals(ck[1]))
					numbers.get(i).setValue(Double.parseDouble(ck[3]));
			}
		} else if (ck[2].equals("+=")) {
			for (int i = 0; i < numbers.size(); i++) {
				if (numbers.get(i).getName().trim().equals(ck[1]))
					numbers.get(i).add(Double.parseDouble(ck[3]));
			}
		} else if (ck[2].equals("-=")) {
			for (int i = 0; i < numbers.size(); i++) {
				if (numbers.get(i).getName().trim().equals(ck[1]))
					numbers.get(i).subtract(Double.parseDouble(ck[3]));
			}
		} else if (ck[2].equals("*=")) {
			for (int i = 0; i < numbers.size(); i++) {
				if (numbers.get(i).getName().trim().equals(ck[1]))
					numbers.get(i).multiplyBy(Double.parseDouble(ck[3]));
			}
		} else if (ck[2].equals("/=")) {
			for (int i = 0; i < numbers.size(); i++) {
				if (numbers.get(i).getName().trim().equals(ck[1]))
					numbers.get(i).divideBy(Double.parseDouble(ck[3]));
			}
		}
	}

	public void changeCond() {
		if (ck[3].equals("true")) {
			for (int i = 0; i < conds.size(); i++) {
				if (conds.get(i).getName().trim().equals(ck[1]))
					conds.get(i).setCond(true);
			}
		} else if (ck[3].equals("false")) {
			for (int i = 0; i < conds.size(); i++) {
				if (conds.get(i).getName().trim().equals(ck[1]))
					conds.get(i).setCond(false);
			}
		}
	}

	public void changeText() {
		//		String s = change.substring(change.indexOf("\""), change.lastIndexOf("\"")).trim();
		//		if (change.contains("+=")) {
		//			String name = change.substring(8, change.indexOf("+")).trim();
		//			for (int i = 0; i < texts.size(); i++) {
		//				if (texts.get(i).getName().trim().equals(name)) {
		//					texts.get(i).addText(s);
		//				}
		//			}
		//		} else if (change.contains("=")) {
		//			String name = change.substring(8, change.indexOf("=")).trim();
		//			for (int i = 0; i < texts.size(); i++) {
		//				if (texts.get(i).getName().trim().equals(name)) {
		//					texts.get(i).setText(s.substring(1));
		//				}
		//			}
		//		}
		/*
		String s;
		boolean isQuote = false;
		if (ck[2].trim().equals("=")) {
			for (int i = 0; i < texts.size(); i++) {
				if (texts.get(i).getName().trim().equals(ck[1])) {
					s = "";

		} else if (ck[2].trim().equals("+=")) {
			for (int i = 0; i < texts.size(); i++) {
				if (texts.get(i).getName().trim().equals(ck[1])) {
					System.out.println(texts.get(i).getText());
					texts.get(i).setText(texts.get(i).getText() + currentStatement.substring(currentStatement.indexOf("\""), currentStatement.lastIndexOf("\"") + 1));
				}
			}
		}


		 */

		String s = "";
		boolean isQuote = false;
		int index = 0;
		for (int i = 0; i < texts.size(); i++) {
			if (texts.get(i).getName().trim().equals(ck[1])) {
				index = i;
				if (ck[2].equals("=")) {
					s = "";
				} else if (ck[2].equals("+=")) {
					s = texts.get(i).getText();
				}
				isQuote =true;
			}
		}

		if (isQuote) {
			isQuote = false;
			for (int i = 3; i < ck.length; i++) {
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

	public void changeLetter() {
		//		char c = change.charAt(change.lastIndexOf("\'") - 1);
		//		String name = change.substring(0, change.indexOf("=")).trim();
		//		for (int i = 0; i < letters.size(); i++) {
		//			if (letters.get(i).getName().trim().equals(name)) {
		//				letters.get(i).setLetter(c);
		//			}
		//		}

		for (int i = 0; i < letters.size(); i++) {
			if (letters.get(i).getName().trim().equals(ck[1]))
				letters.get(i).setLetter(ck[3].charAt(1));
		}
	}

	//	public void processContainer(int index, String dataType) {
	//		if (isNewVariable(ck[1 + index])) {
	//			if (dataType.equals("number")) {
	//				if (!ck[3 + index].contains("."))
	//					ck[3 + index] = ck[3 + index] + ".0";
	//				numbers.add(new Number(ck[2 + index], Double.parseDouble(ck[4 + index])));
	//			} else if (dataType.equals("text")) {
	//				String txt = ck[3 + index];
	//				for (int j = 4; j < ck.length; j++)
	//					txt += " " + ck[j + index];
	//				texts.add(new Text(ck[1 + index], txt));
	//
	//			} else if (dataType.equals("cond")) {
	//				if (ck[3 + index].equals("true"))
	//					conds.add(new Cond(ck[1 + index], true));
	//				else
	//					conds.add(new Cond(ck[1 + index], false));
	//			}
	//			if (dataType.equals("letter"))
	//				letters.add(new Letter(ck[1 + index], ck[3 + index].charAt(0)));
	//		}
	//	}

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

			for (int i = 3+index; i < ck.length; i++) {
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
		//		System.out.println(ck[1] + " " + ck[2] + " " + ck[3]);
		//		System.out.println(getNumberCondition(ck[1] + " " + ck[2] + " " + ck[3]));
		if (getNumberCondition(ck[1] + " " + ck[2] + " " + ck[3])) {
			String statementTag = ck[4];
			if (statementTag.equals("Change"))
				processChange();
			else if (ck[4].equals("Print"))
				processPrint(4);
			//			else if (ck[4].equals("Container"))
			//				processContainer(4, ck[5]);
		}
	}

	public void processChange() {
		String dataType = "";
		for (int b = 0; b < numbers.size(); b++) {
			if (numbers.get(b).getName().trim().equals(ck[1])) {
				dataType = "number";
				changeNumber();
			}
		}
		for (int y = 0; y < conds.size(); y++) {
			if (conds.get(y).getName().trim().equals(ck[1])) {
				dataType = "cond";
				changeCond();
			}
		}
		for (int x = 0; x < texts.size(); x++) {
			if (texts.get(x).getName().trim().equals(ck[1])) {
				dataType = "text";
				changeText();
			}
		}
		for (int e = 0; e < letters.size(); e++) {
			if (letters.get(e).getName().trim().equals(ck[1])) {
				dataType = "letter";
				changeLetter();
			}
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
		return "";
	}
	private void reset() {
		conds.clear();
		texts.clear();
		numbers.clear();
		letters.clear();
		consoleArea.setText(null);
	}
}