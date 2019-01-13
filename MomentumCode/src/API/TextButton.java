package API;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TextButton {
	private int x, y, width, height, sx ,sy;
	private Color color, scolor;
	private String text;
	private Font font;
	public TextButton(int x, int y, int width, int heights, int sx, int sy, Color color,
			Color scolor, String text, Font font) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = heights;
		this.sx = sx;
		this.sy = sy;
		this.color = color;
		this.scolor = scolor;
		this.text = text;
		this.font = font;
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x,y,width,height);
		g.setFont(font);
		g.setColor(scolor);
		g.drawString(text, sx, sy);
		
		
		
	}
	
	public boolean isOn(int mouseX, int mouseY) {
		//System.out.println(mouseX + " " + mouseY + " " + x + " " + y + " " + width + " " + height);
		
		if(mouseX>x && mouseX<x+width && mouseY>y && mouseY<y+height) {
			return true;
		}else {
			return false;
		}
	}
	
	public void setRColor(Color newc) {
		color = newc;
	}
	
}
	
	
	
	
	