package API;
/*This class greets the user and explains the program briefly.
 * 
 * 
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.*;

/**
   Coded by Ayush Satyavarpu
   
   //number, text, cond, letter
   currstate nums
   0: menu
   1:readme / intro
   
   
 */
public class APIPanel extends JPanel implements MouseListener, ActionListener, MouseMotionListener
{
  // TODO Your Instance Variables Here
  
  
  private final Color lb, back, dgrn, orn, db, y;

  private TextButton source, oapi, readme, vars, print, change, loops, input, ifs, btm;
  
  
  private int currState; 
  

  public APIPanel () {
	  super();
	  
	  currState = 0;
	  //colors
	  lb = new Color(66, 125, 244);
	  back = new Color(47,47,47);
	  dgrn = new Color(88, 182, 66);
	  orn = new Color(198, 105, 42);
	  db = new Color(41, 79, 155);
	  y = new Color(236, 241, 47);
	  
	  setBackground(back);

	 
  	  source = new TextButton(15,100,135,40,22,125, lb, Color.BLACK, "Source code", new Font(Font.MONOSPACED, Font.BOLD, 17));
  	  oapi = new TextButton(180,100,135,40,187,125, lb, Color.BLACK, "Online API", new Font(Font.MONOSPACED, Font.BOLD, 17));
  	  readme = new TextButton(15, 170, 300, 40, 22, 195, lb, Color.BLACK, "ReadMe: Highly Recommended", new Font(Font.MONOSPACED, Font.BOLD, 18));   
  	  vars = new TextButton(15,220,135,40,35,245, lb, Color.BLACK, "Variables", new Font(Font.MONOSPACED, Font.BOLD, 17));
 	  print = new TextButton(180,220,135,40,203,245, lb, Color.BLACK, "Printing", new Font(Font.MONOSPACED, Font.BOLD, 17));
 	  change = new TextButton(15,270,135,40,18,295, lb, Color.BLACK, "Changing Vars", new Font(Font.MONOSPACED, Font.BOLD, 17));
	  loops = new TextButton(180,270,135,40,183,295, lb, Color.BLACK, "Simple Loops", new Font(Font.MONOSPACED, Font.BOLD, 17));  
	  ifs = new TextButton(15,320,135,40,18,345, lb, Color.BLACK, "If Statements", new Font(Font.MONOSPACED, Font.BOLD, 17));
	  input = new TextButton(180,320,135,40,183,345, lb, Color.BLACK, "Taking Input", new Font(Font.MONOSPACED, Font.BOLD, 17));  
	  btm = new TextButton(15,500, 135, 40, 18,520, lb, Color.BLACK, "Back to Menu", new Font(Font.MONOSPACED, Font.BOLD, 17));
  
  }
  
  
  
  
  

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);  // Call JPanel's paintComponent method to paint the background

    int width = getWidth();
    int height = getHeight();
    
    
    Graphics2D g2 = (Graphics2D)g;
    
    g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
	g.setColor(db);
	g.drawString("Momentum API", 60, 25);
	
	
	
    switch(currState) {
    case 0:
    	
       drawMenu(g);
       break;
    case 1:
    	drawReadMe(g);
    	break;
    case 2:
    	drawVar(g);
    	break;
    case 3:
    	drawPrint(g);
    	break;
    case 4:
    	drawLoop(g);
    	break;
    case 5:
    	drawIf(g);
    	break;
    case 6:
    	drawInput(g);
    	break;
    case 7:
    	drawChange(g);
    	break;
    }
    
    
   
    
    
  }
  
  
 

  private void drawChange(Graphics g) {
	  g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));

	  g.setColor(orn);
	  g.drawString("Changing Page", 80, 50);
	  g.setColor(dgrn);
	  g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
	  g.drawString("The Change keyword is used to ", 15, 80);
	  g.drawString("change variable values. Depending ", 15, 100);
	  g.drawString("on the data type you want to modify,", 15, 120);
	  g.drawString("the operations that you can do vary. ", 15, 140);
	  g.setColor(Color.RED);
	  g.drawString("Numbers: +=, -=, *=, /=, =", 15, 160);
	  g.drawString("Text: +=, =", 15, 180);
	  g.drawString("Conds and Letters: =", 15, 200);
	  g.drawString("Examples", 15, 240);
	  g.setColor(dgrn);

	  g.drawString("Change num(prev 5) += 5 --> 10", 15, 260);
	  g.drawString("Change num -= 5 --> 5", 15, 280);
	  g.drawString("Change num *= 5 --> 25", 15, 300);
	  g.drawString("Change num /= 5 --> 5", 15, 320);
	  g.drawString("Change num = 72 --> 72", 15, 340);
	  g.drawString("Change w(prev hi) += \"ya\" --> hiya", 15, 360);
	  g.drawString("Change w = \"hello\" --> hello", 15, 380);
	  g.drawString("Change let = \'d\' --> d", 15, 400);
	  g.drawString("Change bool = true --> true", 15, 420);

	  btm.draw(g);
  }

  private void drawInput(Graphics g) {
	  g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));

	  g.setColor(orn);
	  g.drawString("Input Page", 110, 50);
	  g.setColor(dgrn);
	  g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
	  g.drawString("The Input keyword is used to get", 15, 80);
	  g.drawString(" user input from the user of your", 15, 100);
	  g.drawString("program. Simply start with Input", 15, 120);
	  g.drawString("then the data type you would like", 15, 140);
	  g.drawString("then the variable name that", 15, 160);
	  g.drawString("the input should be stored in ", 15, 180);
	  g.setColor(Color.RED);
	  g.drawString("Examples:", 15, 210);
	  g.setColor(dgrn);
	  g.drawString("Input number num", 15, 240);
	  g.drawString("Input text words", 15, 260);
	  g.drawString("Input letter let", 15, 280);
	  g.drawString("Input cond bool", 15, 300);
	 
	  btm.draw(g);

  }

  private void drawVar(Graphics g) {
	  // TODO Auto-generated method stub
	    g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));

	  	g.setColor(orn);
	  	g.drawString("Variables Page", 80, 50);
	  	
	  	g.setColor(dgrn);
	  	g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
	  	g.drawString("Making variables is simple.", 15, 80);
	  	g.drawString("Simply state what data type", 15, 100);
	  	g.drawString("the variable should be:", 15, 120);
	  	g.setColor(Color.RED);
	  	g.drawString("Letter, Text, Number, or Condition", 15, 140);
	  	g.setColor(dgrn);
	  	g.drawString("Then state the name, and then \'=\'" , 15, 160);
	  	g.drawString("Then, state the value.", 15, 180);
	  	g.setColor(Color.RED);
	  	g.drawString("Examples", 15, 200);
	  	g.setColor(dgrn);
	  	g.drawString("Letter test = \'a\'", 15, 220);
	  	g.drawString("Number num = 3", 15, 240);
	  	g.drawString("Text test = \"Momentum is awesome!\"", 15, 260);
	  	g.drawString("Cond(condition) name = 3 > 2", 15, 280);
	  	
	  	g.drawString("Data Structures", 15, 320);
	  	g.drawString("Text-represents words", 15, 340);
	  	g.drawString("Number-represents numbers", 15, 360);
	  	g.drawString("Letter-represents letters", 15, 380);
	  	g.drawString("Cond-represents true or false", 15, 400);


	  	
	  	btm.draw(g);



	  	
  }

  private void drawLoop(Graphics g) {
	  g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));

	  g.setColor(orn);
	  g.drawString("Looping Page", 80, 50);
	  g.setColor(dgrn);
	  g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
	  g.drawString("Loops are used for running the ", 15, 80);
	  g.drawString("same lines of code multiple times", 15, 100);
	  g.drawString("Looping in Momentum is harder", 15, 120);
	  g.drawString("than creating variables or printing,", 15, 140);
	  g.drawString("but all it takes is 2 parts:", 15, 160);
	  g.setColor(Color.RED);
	  g.drawString("The Loop and end statements", 15, 180);
	  g.setColor(dgrn);
	  g.drawString("Say Loop, then the number of times,", 15, 200);
	  g.drawString("then the code you want looped", 15, 220);
	  g.drawString("and then say end.", 15, 240);
	  g.setColor(Color.RED);
	  g.drawString("Examples", 15, 260);
	  g.setColor(dgrn);
	  g.drawString("Loop 4", 15, 280);
	  g.drawString("Print \"This code will be run x4\"", 15, 300);
	  g.drawString("End", 15, 320);
	  
	  btm.draw(g);

	  
  }

  private void drawIf(Graphics g) {
	  
	  
	g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
	g.setColor(orn);
	g.drawString("If Statements Page", 80, 50);
	g.setColor(dgrn);
   	g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
   	g.drawString("If statements similar to loops", 15, 80);
   	g.drawString("because they are also not confined", 15, 100);
   	g.drawString("to one line of code.", 15, 120);
   	g.drawString("If statements are used to run ", 15, 140);
   	g.drawString("certain lines of code based", 15, 160);
   	g.drawString("on the value of a cond.", 15, 180);
   	g.drawString("Simply put If, then the condition,", 15, 200);
   	g.drawString("either a cond variable or a number", 15, 220);
   	g.drawString("expression, and put the word tag.", 15, 240);
   	g.drawString("Next to the word tag, put your line ", 15, 260);
   	g.drawString("of code", 15, 280);
   	g.setColor(Color.RED);
   	g.drawString("Examples", 15, 300);
   	g.setColor(dgrn);
   	g.drawString("If cond Print \"hi\"", 15, 320);
   	g.drawString("If 3>2 Print \"this will print\"", 15, 340);

   	
   	btm.draw(g);
  }
  
  private void drawPrint(Graphics g) {
	  g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));

	  g.setColor(orn);
	  g.drawString("Printing Page", 80, 50);
	  g.setColor(dgrn);
	  g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
	  g.drawString("Printing in Momentum is quite", 15, 80);
	  g.drawString("simple, as all one has to do", 15, 100);
	  g.drawString("is write Print and then the ", 15, 120);
	  g.drawString("variable name or text.", 15, 140);
	  g.setColor(Color.red);
	  g.drawString("Examples", 15, 160);
	  g.setColor(dgrn);
	  g.drawString("Print \"Momentum\"", 15, 180);
	  g.drawString("Print variablename", 15, 200);

	  btm.draw(g);

  }

  private void drawMenu(Graphics g) {
	g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));

  	g.setColor(orn);
  	g.drawString("Quick Links Page", 80, 50);
  	
  	g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 17));
  	g.setColor(y);
  	g.drawString("Momentum's Web links", 75, 80);
  	
  	
  	
  	source.draw(g);
  	oapi.draw(g);
  	
  	g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 17));
  	g.setColor(y);
  	g.drawString("API Guide", 115, 160);
  	
  	readme.draw(g);
  	
  	vars.draw(g);
  	print.draw(g);
  	
  	change.draw(g);
  	loops.draw(g);
  	
  	input.draw(g);
  	ifs.draw(g);
  }
  
  private void drawReadMe(Graphics g) {
	    g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));

	  	g.setColor(orn);
	  	g.drawString("ReadMe Page: Momentum Intro", 5, 50); 
	  	
	  	g.setColor(dgrn);
	  	g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
	  	g.drawString("Many beginner programmers have been \n", 15, 80);
	  	g.drawString("told to begin coding with Scratch",15, 100);
	  	g.drawString("from MIT. However, after Scratch,", 15, 120);
	  	g.drawString("where do beginners go from there?", 15, 140);
	  	g.drawString("Python and JavaScript have lots ", 15, 160);
	  	g.drawString("of scary exceptions, and Java", 15, 180);
	  	g.drawString("and the C languages have too much", 15, 200);
	  	g.drawString("object oriented structure to", 15, 220);
	  	g.drawString("accomodate beginner coders.", 15, 240);
	  	g.drawString("The Momentum Programming Language", 15, 260);
	  	g.drawString("successfully bridges this gap", 15, 280);
	  	g.drawString("between block/drag and drop coding ", 15, 300);
	  	g.drawString("and complex programming.", 15, 320);
	  	
	  	g.drawString("In terms of Momentum specific", 15, 360);
	  	g.drawString("syntax, there is only one rule.", 15, 380);
	  	g.drawString("Each line of code in Momentum", 15, 400);
	  	g.drawString("starts with a specific keyword,", 15, 420);
	  	g.drawString("such as If, Loop, etc.", 15, 440);
	  	
	  	btm.draw(g);

	  	
  }
  

    public void actionPerformed(ActionEvent e) {

    	
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(source.isOn(e.getX()-7, e.getY() -83)) {
			if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
    			
    		    try {
					Desktop.getDesktop().browse(new URI("https://github.com/EylamTagor/MomentumCode"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    		}		
			else {
				
				
			}
		}
		
		
		
		if(oapi.isOn(e.getX()-7, e.getY() -83)) {
			if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
    			
    		    try {
					Desktop.getDesktop().browse(new URI("https://eylamtagor.github.io/MomentumCode-API-Documentation/"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    		}		
			else {
				
				
			}
		}
		
		if(readme.isOn(e.getX()-7, e.getY() -83)) {
			currState = 1;  
		}
		if(btm.isOn(e.getX()-7, e.getY() -83)) {
			currState = 0;  
		}
		if(vars.isOn(e.getX()-7, e.getY() -83)) {
			currState = 2;  
		}
		if(print.isOn(e.getX()-7, e.getY() -83)) {
			currState = 3;  
		}
		if(loops.isOn(e.getX()-7, e.getY() -83)) {
			currState = 4;  
		}
		if(change.isOn(e.getX()-7, e.getY() -83)) {
			currState = 7;  
		}
		if(ifs.isOn(e.getX()-7, e.getY() -83)) {
			currState = 5;  
		}
		if(input.isOn(e.getX()-7, e.getY() -83)) {
			currState = 6;  
		}
		
		
		repaint();
			
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}






	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}






	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(source.isOn(e.getX()-7, e.getY() -83)) {
			source.setRColor(db);
		}else {
			source.setRColor(lb);

		}
		
		if(oapi.isOn(e.getX()-7, e.getY() -83)) {
			oapi.setRColor(db);
		}else {
			oapi.setRColor(lb);

		}
		
		if(readme.isOn(e.getX()-7, e.getY() -83)) {
			readme.setRColor(db);
		}else {
			readme.setRColor(lb);

		}
		
		if(vars.isOn(e.getX()-7, e.getY() -83)) {
			vars.setRColor(db);
		}else {
			vars.setRColor(lb);

		}
		
		if(print.isOn(e.getX()-7, e.getY() -83)) {
			print.setRColor(db);
		}else {
			print.setRColor(lb);

		}
		
		
		if(change.isOn(e.getX()-7, e.getY() -83)) {
			change.setRColor(db);
		}else {
			change.setRColor(lb);

		}
		
		if(loops.isOn(e.getX()-7, e.getY() -83)) {
			loops.setRColor(db);
		}else {
			loops.setRColor(lb);

		}
		
		if(input.isOn(e.getX()-7, e.getY() -83)) {
			input.setRColor(db);
		}else {
			input.setRColor(lb);

		}
		
		if(ifs.isOn(e.getX()-7, e.getY() -83)) {
			ifs.setRColor(db);
		}else {
			ifs.setRColor(lb);

		}
		
		if(btm.isOn(e.getX()-7, e.getY() -83)) {
			btm.setRColor(db);
		}else {
			btm.setRColor(lb);

		}
		
		
		
		repaint();
	}
	
	
	
	/*
	 * change
	 * change var = 3
	 * change str += = 
	 * chang cond or letter =
	 * 
	 * If cond tag action
	 * 
	 * Input datatype var;
	 * 
	 * 
	 * 
	 */
	
	
	
}