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
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

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
				codeList = editor.getText().split(":\\)");

				for (int i = 0; i < codeList.length; i++) {
					currentStatement = codeList[i].replace("\r", "");
					currentStatement = currentStatement.replace("\n", "");

					ck = currentStatement.split(" ");

					String tag = ck[0];

					if (tag.equals("Container")) {
						processContainer(0);
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

	public void changeNumber(String change) {
		double d = Double.parseDouble(change.substring(change.indexOf("=") + 1, change.length() - 1).trim());
		if (change.contains("+=")) {
			String name = change.substring(0, change.indexOf("+")).trim();
			for (int i = 0; i < numbers.size(); i++) {
				if (numbers.get(i).getName().trim().equals(name)) {
					numbers.get(i).add(d);
				}
			}
		} else if (change.contains("-=")) {
			String name = change.substring(0, change.indexOf("-")).trim();
			for (int i = 0; i < numbers.size(); i++) {
				if (numbers.get(i).getName().trim().equals(name)) {
					numbers.get(i).subtract(d);
				}
			}
		} else if (change.contains("*=")) {
			String name = change.substring(7, change.indexOf("*")).trim();
			for (int i = 0; i < numbers.size(); i++) {
				if (numbers.get(i).getName().trim().equals(name)) {
					numbers.get(i).multiplyBy(d);
				}
			}
		} else if (change.contains("/=")) {
			String name = change.substring(0, change.indexOf("/")).trim();
			for (int i = 0; i < numbers.size(); i++) {
				if (numbers.get(i).getName().trim().equals(name)) {
					numbers.get(i).divideBy(d);
				}
			}
		} else if (change.contains("=")) {
			String name = change.substring(0, change.indexOf("=")).trim();
			for (int i = 0; i < numbers.size(); i++) {
				if (numbers.get(i).getName().trim().equals(name)) {
					numbers.get(i).setValue(d);
				}
			}
		}
	}

	public void changeCond(String change) {
		boolean b = false;
		String name = change.substring(0, change.indexOf("=")).trim();
		for (int i = 0; i < conds.size(); i++) {
			if (conds.get(i).getName().trim().equals(name)) {
				conds.get(i).setCond(b);
			}
		}
	}

	public void changeText(String change) {
//		System.out.println(change);
		String s = change.substring(change.indexOf("\""), change.lastIndexOf("\"")).trim();
		if (change.contains("+=")) {
			String name = change.substring(8, change.indexOf("+")).trim();
			for (int i = 0; i < texts.size(); i++) {
				if (texts.get(i).getName().trim().equals(name)) {
					texts.get(i).addText(s);
				}
			}
		} else if (change.contains("=")) {
			String name = change.substring(8, change.indexOf("=")).trim();
			for (int i = 0; i < texts.size(); i++) {
				if (texts.get(i).getName().trim().equals(name)) {
					texts.get(i).setText(s.substring(1));
				}
			}
		}
	}

	public void changeLetter(String change) {
		char c = change.charAt(change.lastIndexOf("\'") - 1);
		String name = change.substring(0, change.indexOf("=")).trim();
		for (int i = 0; i < letters.size(); i++) {
			if (letters.get(i).getName().trim().equals(name)) {
				letters.get(i).setLetter(c);
			}
		}
	}

	public void processContainer(int index) {
		String dataType = ck[1 + index];
		if (isNewVariable(ck[2 + index])) {
			if (dataType.equals("number")) {
				if (!ck[4 + index].contains("."))
					ck[4 + index] = ck[4 + index] + ".0";
				numbers.add(new Number(ck[2 + index], Double.parseDouble(ck[4 + index])));
			} else if (dataType.equals("text")) {
				String txt = ck[4 + index];
				for (int j = 5; j < ck.length; j++)
					txt += " " + ck[j + index];
				texts.add(new Text(ck[2 + index], txt));

			} else if (dataType.equals("cond")) {
				if (ck[4 + index].equals("true"))
					conds.add(new Cond(ck[2 + index], true));
				else
					conds.add(new Cond(ck[2 + index], false));
			}
			if (dataType.equals("letter"))
				letters.add(new Letter(ck[2 + index], ck[4 + index].charAt(0)));
		}
	}

	public void processPrint(int index) {
		boolean isQuote = false;
		for (int j = 1; j < ck.length; j++) {
			if (isQuote) {
				if (ck[j = index].charAt(ck[j + index].length() - 1) == '"') {
					isQuote = !isQuote;
					consoleArea.append(ck[j + index].substring(0, ck[j + index].length() - 1) + " ");
				} else
					consoleArea.append(ck[j + index] + " ");
			} else {

				if (ck[j + index].charAt(0) == '"') {
					isQuote = !isQuote;
					if (ck[j + index].charAt(ck[j + index].length() - 1) == '"') {
						consoleArea.append(ck[j + index].substring(1, ck[j + index].length() - 1) + " ");
						isQuote = !isQuote;
					} else
						consoleArea.append(ck[j + index].substring(1) + " ");
				} else
					printVariable(ck[j + index]);
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
			else if (ck[4].equals("Container"))
				processContainer(4);
		}
	}

	public void processChange() {
		String dataType = "";
		for (int b = 0; b < numbers.size(); b++) {
			if (numbers.get(b).getName().trim().equals(ck[1])) {
				dataType = "number";
				changeNumber(currentStatement);
			}
		}
		for (int y = 0; y < conds.size(); y++) {
			if (conds.get(y).getName().trim().equals(ck[1])) {
				dataType = "cond";
				changeCond(currentStatement);
			}
		}
		for (int x = 0; x < texts.size(); x++) {
			if (texts.get(x).getName().trim().equals(ck[1])) {
				dataType = "text";
				changeText(currentStatement);
			}
		}
		for (int e = 0; e < letters.size(); e++) {
			if (letters.get(e).getName().trim().equals(ck[1])) {
				dataType = "letter";
				changeLetter(currentStatement);
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
				consoleArea.append(conds.get(i).getCond() + " ");
				printed = true;
			}
		}
		if (!printed) {
			for (int i = 0; i < texts.size(); i++) {
				if (name.equals(texts.get(i).getName())) {
					consoleArea.append(texts.get(i).getText().substring(1, texts.get(i).getText().length() - 1) + " ");
					printed = true;
				}
			}
		}
		if (!printed) {
			for (int i = 0; i < numbers.size(); i++) {
				if (name.equals(numbers.get(i).getName())) {
					consoleArea.append(numbers.get(i).getValue() + " ");
					printed = true;
				}
			}
		}
		if (!printed) {
			for (int i = 0; i < letters.size(); i++) {
				if (name.equals(letters.get(i).getName())) {
					consoleArea.append(letters.get(i).getLetter() + " ");

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