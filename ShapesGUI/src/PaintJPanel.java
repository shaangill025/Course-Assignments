import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;


public class PaintJPanel extends JPanel{
	
	private ArrayList<Shapes> shapesList = new ArrayList<Shapes>();
	private Shapes currentShape;
	private Color color;
	private String type = "";
	
	public PaintJPanel(){
		addMouseListener(
			new MouseAdapter(){
				public void mousePressed(MouseEvent event){
					paintJPanelMousePressed(event);
				}
			}
		);
		
		addMouseListener(
			new MouseAdapter(){
				public void mouseReleased(MouseEvent event){
					paintJPanelMouseReleased(event);
				}
			}
		);
	}
	
	public void emptyList(){
		shapesList = new ArrayList<Shapes>();
	}
	
	public void setCurrentShapeType(String shape){
		type = shape;
	}
	
	public void setColor(Color inp){
		color = inp;
	}
	
	public void paintJPanelMousePressed(MouseEvent event){
		if(type.equals("Rectangle")){
			currentShape = new Rectangles();
			currentShape.setX1(event.getX());
			currentShape.setY1(event.getY());
		}
		
		else if(type.equals("Triangle")){
			currentShape = new Triangle();
			currentShape.setX1(event.getX());
			currentShape.setY1(event.getY());
		}
		
		else if(type.equals("Circle")){
			currentShape = new Circle();
			currentShape.setX1(event.getX());
			currentShape.setY1(event.getY());
		}
		currentShape.setColor(color);
	}
	
	public void paintJPanelMouseReleased(MouseEvent event){
		currentShape.setX2(event.getX());
		currentShape.setY2(event.getY());
		shapesList.add(currentShape);
		repaint();
	}

	@Override
	public void paintComponent(Graphics g){		
		Shapes nextShape;
		Iterator<Shapes> shapesIterator = shapesList.iterator();
		
		while(shapesIterator.hasNext()){
			nextShape = shapesIterator.next();
			nextShape.draw(g);
		}
	}
}
