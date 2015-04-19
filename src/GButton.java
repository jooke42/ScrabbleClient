


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
//import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class GButton{
	private int x;
	private int y;
	private int w;
	private int h;
	private String text;
	private Color colorB;
	private Color colorT;
	private Color colorP;
	public GButton(int x,int y, int w,int h,String text,Color colorB,Color colorT, Color colorP){

		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.text=text;
		this.colorB=colorB;
		this.colorT=colorT;
		this.colorP=colorP;
	}
	public void draw(Graphics g,Input input){
		if(input.getMouseX()>x&&input.getMouseX()<x+w&&input.getMouseY()>y&&input.getMouseY()<y+h)
			g.setColor(colorP);
		else
		g.setColor(colorB);
		g.fillRect(x, y, w, h);
		g.setColor(colorT);
		g.drawString(text, x+w/2-g.getFont().getWidth(text)/2, h/2+y-g.getFont().getHeight(text)/2);
	}
	public boolean onIt(Input input){
		if(input.getMouseX()>x&&input.getMouseX()<x+w&&input.getMouseY()>y&&input.getMouseY()<y+h)
			return true;
		else
			return false;
	}
	
}
