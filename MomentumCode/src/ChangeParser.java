import java.util.ArrayList;

public class ChangeParser {
	private ArrayList<Number> numbers;
	private ArrayList<Cond> conds;
	private ArrayList<Text> texts;
	private ArrayList<Letter> letters;

	public ChangeParser(ArrayList<Number> numbers) {
		this.numbers = numbers;
	}

	// whole line of code e.g. x = 5:)
	public void change(String change, String dataType) {
		if (dataType.trim().equals("number"))
			changeNumber(change);
		else if (dataType.trim().equals("cond"))
			changeCond(change);
		else if (dataType.trim().equals("text"))
			changeText(change);
		else if (dataType.trim().equals("letter"))
			changeLetter(change);
	}

	public void changeNumber(String change) {
		double d = Double.parseDouble(change.substring(change.indexOf("=") + 1, change.indexOf(":)")));
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
			String name = change.substring(0, change.indexOf("*")).trim();
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
		String s = change.substring(change.indexOf("\""), change.lastIndexOf("\"")).trim();
		if (change.contains("+=")) {
			String name = change.substring(0, change.indexOf("+")).trim();
			for (int i = 0; i < texts.size(); i++) {
				if (texts.get(i).getName().trim().equals(name)) {
					texts.get(i).addText(s);
				}
			}
		} else if (change.contains("=")) {
			String name = change.substring(0, change.indexOf("=")).trim();
			for (int i = 0; i < texts.size(); i++) {
				if (texts.get(i).getName().trim().equals(name)) {
					texts.get(i).setText(s);
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
}