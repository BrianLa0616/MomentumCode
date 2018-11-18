//Made by Kevin Chu for SMHacksII
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MomentumMain {
	private String fileName;
	private String code;
	private JFileChooser chooser;
	private FileNameExtensionFilter filter;
	private JFrame frmMomentum;
	private JTextArea consoleArea;
	private JScrollPane scroll;
	private String[] codeList;
	private ArrayList<Text> text;
	private ArrayList<Letter> letters;
	private ArrayList<Cond> conds;
	private ArrayList<Number> numbers;

	public ArrayList<Text> getText() {
		return text;
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

	@SuppressWarnings("resource")
	public MomentumMain() {
		frmMomentum = new JFrame();
		frmMomentum.setTitle("Momentum");
		frmMomentum.setBounds(100, 100, 450, 300);
		frmMomentum.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMomentum.getContentPane().setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmMomentum.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		tabbedPane.addTab("File Selection", null, panel, null);
		panel.setLayout(null);
		
		JPanel console = new JPanel();
		tabbedPane.addTab("Console", null, console, null);
		console.setLayout(null);
		
		consoleArea = new JTextArea(100,100);
		consoleArea.setBounds(21, 10, 389, 216);
		consoleArea.setBackground(Color.WHITE);
		consoleArea.setEditable(false);
		scroll = new JScrollPane(consoleArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        console.add(scroll);
		console.add(consoleArea);

		JLabel openFiles = new JLabel("Select a .mom file");
		openFiles.setBounds(174, 70, 117, 25);
		panel.add(openFiles);

		JButton fileButton = new JButton("Open");
		fileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chooser = new JFileChooser();
				filter = new FileNameExtensionFilter(".mom files", "mom");
				chooser.setFileFilter(filter);
				int returnedValue = chooser.showOpenDialog(panel);
				if (returnedValue == JFileChooser.APPROVE_OPTION) {
					fileName = chooser.getSelectedFile().getPath();
				}
				try {
					code = new Scanner(new File(fileName)).useDelimiter("\\A").next();
					code = code.replace("\n", "");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

				codeList = code.split(":P");
				for (int i = 0; i < codeList.length; i++) {
					String currentStatement = codeList[i];
					String[] currentKeywords = currentStatement.split(" ");
					String first = currentKeywords[0];
					switch (first) {
						case "Container":
							String second = currentKeywords[1];
							switch (second) {
								case "number":
									if (!currentKeywords[2].contains(".")) {
										currentKeywords[2] = currentKeywords[2] + ".0";
									}
									numbers.add(new Number(second, Double.parseDouble(currentKeywords[2])));
								case "text":
									text.add(new Text(second,currentKeywords[2]));
								case "cond":
									conds.add(new Cond(second,Boolean.parseBoolean(currentKeywords[2])));
								case "letter":
									letters.add(new Letter(second,currentKeywords[2].charAt(0)));
							}
						/*
						case "If":
						case "Out":
						*/
					}
				}
			}
		});
		fileButton.setBounds(167, 101, 117, 25);
		panel.add(fileButton);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MomentumMain window = new MomentumMain();
					window.frmMomentum.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
