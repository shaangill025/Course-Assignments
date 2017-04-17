import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawingShapes extends JFrame{
	private JPanel controlsJPanel;
	private PaintJPanel painterPaintJPanel;
	private String[] selectColor = new String[]{"white","blue","black","red","green","yellow"};
	private Color[] colors = new Color[]{Color.WHITE,Color.BLUE,Color.BLACK,
			Color.RED,Color.GREEN,Color.YELLOW};

	public DrawingShapes(){
		createUI();
	}
	
	private void createUI(){
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		controlsJPanel = new JPanel();
		controlsJPanel.setBounds(0, 0, 650, 80);
		controlsJPanel.setLayout(null);
		
		JButton shape1 = new JButton("Circle");
		shape1.setEnabled(true);
		shape1.setBounds(0, 5, 100, 24);
		JButton shape2 = new JButton("Triangle");
		shape2.setEnabled(true);
		shape2.setBounds(125, 5, 100, 24);
		JButton shape3 = new JButton("Rectangle");
		shape3.setEnabled(true);
		shape3.setBounds(250, 5, 100, 24);
		JButton clear = new JButton("Clear All");
		clear.setEnabled(true);
		clear.setBounds(375, 5, 100, 24);
		
		JComboBox colors = new JComboBox(selectColor);
		colors.setSelectedItem("black");
		colors.setBounds(550, 5, 100, 24);
		colors.setEnabled(true);
		
		

		controlsJPanel.add(shape1);
		controlsJPanel.add(shape2);
		controlsJPanel.add(shape3);
		controlsJPanel.add(clear);
		controlsJPanel.add(colors);

		
		shape1.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						shapeButtonActionPerformed("Circle");
					}
				}
		);
		
		shape2.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						shapeButtonActionPerformed("Triangle");
					}
				}
		);
		
		shape3.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						shapeButtonActionPerformed("Rectangle");
					}
				}
		);
		
		clear.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						clearAll();
					}
				}
		);
		
		colors.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						selectColor(colors.getSelectedIndex());
					}
				}
		);
		
	
		painterPaintJPanel = new PaintJPanel();
		painterPaintJPanel.setBounds(0, 80, 750, 600);
		painterPaintJPanel.setBackground(Color.white);
		contentPane.add(painterPaintJPanel);
		contentPane.add(controlsJPanel);

		setTitle("Drawing Shapes");
		setSize(800,800);
		setVisible(true);
	}
	
	private void shapeButtonActionPerformed(String type){
		painterPaintJPanel.setCurrentShapeType(type);
	}
	
	private void selectColor(int index){
		Color color = colors[index];
		painterPaintJPanel.setColor(color);
	}
	
	private void clearAll(){
		painterPaintJPanel.emptyList();	
		repaint();
	}
}