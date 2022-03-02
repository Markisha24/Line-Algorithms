package basicAlgorithm.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class BasicAlgorithm {
	
	static int x0;
	static int y0;
	static int x1;
	static int y1;
	
	static int x;
	static double y;
	
	static long startTime = 0;
	static long endTime = 0;

	public static void main(String[] args) {
	
		
		SwingUtilities.invokeLater(() -> {
            BasicAlgorithm gui = new BasicAlgorithm();
            gui.basicAlg();
            
        });
    }

	public void basicAlg() {
		
		JFrame mainFrame = new JFrame("Basic Line Algorithm");
	    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    BasicAlg graphics = new BasicAlg();
	    
	    graphics.setPreferredSize((new Dimension(800, 800)));
	    mainFrame.getContentPane().add(graphics);
	    mainFrame.pack();
	    mainFrame.setVisible(true);
	    
	  //number of lines
	  int n = 50;
	  						
	  //minimum value a point can have
	  int min = 0;
			
	  //maximum value a point can have
	  int max = 800;
	  
	  startTime = System.nanoTime();
	  
	  for(int j = 0; j < n; j++) {
  
	  		x0 = (int) Math.floor(Math.random()*(max-min+1)+min);
	  		y0 = (int) Math.floor(Math.random()*(max-min+1)+min);
	  		x1 = (int) Math.floor(Math.random()*(max-min+1)+min);
	  		y1 = (int) Math.floor(Math.random()*(max-min+1)+min);
		  
		  	//x0 = 400;
	  		//y0 = 800;
	  		//x1 = 0;
	  		//y1 = 0;
	    
		    x = x0;
			y = y0;
			
			double deltax;
			double deltay;
			double yinc; //slope
			  
			deltax = x1 - x0;
			deltay = y1 - y0;
			
			
			//if x0 = x1, calculating yinc will cause an error because we are trying to divide by 0
			try {
				
				yinc = deltay / deltax;
			}
			
			catch(ArithmeticException e)
			{
				yinc = 0;
			}
			
			/*System.out.println("y0: " + y0 + " y1: " + y1);
			System.out.println("x0: " + x0 + " x1: " + x1);
			System.out.println("x: " + x + " y: " + y);
			System.out.println("yinc: " + yinc);*/
		
			//painting first pixel
			graphics.paintImmediately(x, (int) Math.round(y), 3, 3);
			
			if(x1 > x0 && y1 > y0 && yinc > 0) {
				//Case 1: x1 > x0, y1 > y0, yinc > 0 (diagonal, bottom left to top right)
				for(int i = 0; i <= (deltax - 1); i++)
				{  
					x = x + 1;
					y = y + yinc;
					//System.out.println("X: " + x + " Y: " + Math.round(y));
					graphics.paintImmediately(x, (int) Math.round(y), 3, 3);
				}
			}
			
			else if(y0 == y1 && x1 > x0 && yinc == 0) {
				//Case 2: y0 = y1, x1 > x0, yinc = 0 (horizontal going to the right)
				for(int i = 0; i <= (deltax - 1); i++)
				{  
					x = x + 1;
					y = y0;
					//System.out.println("X: " + x + " Y: " + Math.round(y));
					graphics.paintImmediately(x, (int) Math.round(y), 3, 3);
				}
			}
			
			else if(y0 == y1 && x0 > x1 && yinc == 0.0) {
				//Case 3: y0 = y1, x0 > x1, yinc = 0 (horizontal going to the left)
				for(int i = x0 - 1; i >= x1; i--)
				{  
					x = x - 1;
					y = y0;
					//System.out.println("X: " + x + " Y: " + Math.round(y));
					graphics.paintImmediately(x, (int) Math.round(y), 3, 3);
				}
			}
			
			else if(x0 == x1 && y0 > y1) {
				//Case 4: x0 = x1, y0 > y1, yinc = undefined (vertical going down)
				for(int i = y0 - 1; i >= y1; i--)
				{  
					x = x0;
					y = y - 1;
					//System.out.println("X: " + x + " Y: " + Math.round(y));
					graphics.paintImmediately(x, (int) Math.round(y), 3, 3);
				}
			}
			
			else if(x0 == x1 && y1 > y0) {
				//Case 5: x0 = x1, y1 > y0, yinc = undefined (vertical going up)
				for(int i = 0; i <= (deltay - 1); i++)
				{  
					x = x0;
					y = y + 1;
					//System.out.println("X: " + x + " Y: " + Math.round(y));
					graphics.paintImmediately(x, (int) Math.round(y), 3, 3);
				}
			}
			
			else if(x0 > x1 && y0 > y1 && yinc > 0) {
				//Case 6: x0 > x1, y0 > y1, yinc < 0 (diagonal, top right to bottom left)
				for(int i = x0 - 1; i >= x1; i--)
				{  
					x = x - 1;
					y = y - yinc;
					//System.out.println("X: " + x + " Y: " + Math.round(y));
					graphics.paintImmediately(x, (int) Math.round(y), 3, 3);
				}
			}
			
			else if(x1 > x0 && y0 > y1 && yinc < 0) {
				//Case 7: x1 > x0, y0 > y1, yinc < 0 (diagonal, top left to bottom right)
				for(int i = 0; i <= (deltax - 1); i++)
				{  
					x = x + 1;
					y = y + yinc;
					//System.out.println("X: " + x + " Y: " + Math.round(y));
					graphics.paintImmediately(x, (int) Math.round(y), 3, 3);
				}
			}
			
			else if(x0 > x1 && y1 > y0 && yinc < 0) {
				//Case 8: x0 > x1, y0 > y1, yinc < 0 (diagonal, bottom right to top left)
				for(int i = x0 - 1; i >= x1; i--)
				{  
					x = x - 1;
					y = y - yinc;
					//System.out.println("X: " + x + " Y: " + Math.round(y));
					graphics.paintImmediately(x, (int) Math.round(y), 3, 3);
				}
			}
			
			else {
				System.out.println("Different case found! Try again!");
				System.exit(0);
			}
	  }
	  
	  endTime = System.nanoTime();
	  System.out.println("Time taken in seconds: " + ((endTime - startTime) / 1000000000));
}
	


static int getXValue() {
	System.out.println("X is: " + x);
	return x;
}

static double getYValue( ) {
	System.out.println("Y is: " + y);
	return y;
}

class BasicAlg extends JPanel {
	
	protected void paintComponent(Graphics g) {
		
		//super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.fillRect(x, (int)y, 3, 3);
		
	}

}
}
