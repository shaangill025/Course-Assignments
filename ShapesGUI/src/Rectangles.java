import java.awt.Color;
import java.awt.Graphics;


public class Rectangles implements Shapes{
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private Color color;
	
	public Rectangles(){
		x1 = 0;
		y1 = 0;
		x2 = 0;
		y2 = 0;
		color = Color.orange;
	}
	
	public Rectangles(int firstX, int firstY,int secondX,int secondY, Color input){
		x1 = firstX;
		y1 = firstY;
		x2 = secondX;
		y2 = secondY;
		color = input;
	}
	
	@Override
	public int getX1() {
		return x1;
	}

	@Override
	public void setX1(int x1) {
		this.x1 = x1;
	}

	@Override
	public int getY1() {
		return y1;
	}

	@Override
	public void setY1(int y1) {
		this.y1 = y1;
	}

	@Override
	public int getX2() {
		return x2;
	}

	@Override
	public void setX2(int x2) {
		this.x2 = x2;
	}

	@Override
	public int getY2() {
		return y2;
	}

	@Override
	public void setY2(int y2) {
		this.y2 = y2;
	}
	
	@Override
	public void setColor(Color inp){
		this.color = inp;
	}

	@Override
	public void draw(Graphics g){
		int upperLeftX = Math.min(getX1(), getX2());
		int upperLeftY = Math.min(getY1(), getY2());
		int width = Math.abs(getX1() - getX2());
		int height = Math.abs(getY1() - getY2());

		g.setColor(color);
		g.fillRect(upperLeftX, upperLeftY, width, height);
		//g.fillRect(upperLeftX, upperLeftY, 10, 15);
	}
}
