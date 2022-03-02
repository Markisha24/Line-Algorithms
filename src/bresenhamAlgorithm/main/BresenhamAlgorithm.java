/*coordinates not quite right but close*/

package bresenhamAlgorithm.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BresenhamAlgorithm {

	static int x0;
	static int y0;
	static int x1;
	static int y1;
	
	static int x;
	static int y;
	
	static long startTime = 0;
	static long endTime = 0;

	public static void main(String[] args) {
	
		
		SwingUtilities.invokeLater(() -> {
			BresenhamAlgorithm gui = new BresenhamAlgorithm();
            gui.bresenhamAlg();
            
        });
    }

	public void bresenhamAlg() {
		
		JFrame mainFrame = new JFrame("Bresenham's Algorithm");
	    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    BresenhamAlg graphics = new BresenhamAlg();
	    
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
		  
		  	//x0 = 500;
	  		//y0 = 100;
	  		//x1 = 200;
	  		//y1 = 700;
	    
		    x = x0;
			y = y0;
			
			int deltax;
			int deltay;
			double yinc; //slope
			  
			deltax = x1 - x0;
			deltay = y1 - y0;
			
			 //slope error
			 int e = 2 * deltay - deltax;
			 
			 //increment 1
			 int inc1 = 2 * deltay;
			 
			 //increment 2
			 int inc2 = 2 * (deltay - deltax);
			
			
			//if x0 = x1, calculating yinc will cause an error because we are trying to divide by 0
			try {
				
				yinc = (double) deltay / (double) deltax;
			}
			
			catch(ArithmeticException a)
			{
				yinc = 0;
			}
			
			/*System.out.println("y0: " + y0 + " y1: " + y1);
			System.out.println("x0: " + x0 + " x1: " + x1);
			System.out.println("x: " + x + " y: " + y);
			System.out.println("yinc: " + yinc);*/
			
			//painting first pixel
			//graphics.paintImmediately(x, y, 3, 3);
			
			if(x1 > x0 && y1 > y0 && yinc > 0) {
				//Case 1: x1 > x0, y1 > y0, yinc > 0 (diagonal, bottom left to top right)
				
				//slope less than 1 but greater than 0
				if(deltax > deltay) {
					//System.out.println("Case 1, 0 < slope < 1");
					while(x < x1) {
						//System.out.println("X: " + x + " Y: " + y);
						graphics.paintImmediately(x, y, 3, 3);
						if(e < 0) {
							e = e + inc1;
						}
						else {
							y = y + 1;
							e = e + inc2;
						}
						x = x + 1;
					}
				}
				
				//slope greater than or equal to 1
				else if (yinc > 1){
					//System.out.println("Case 1, slope > 1");
					while(y <= y1) {
						//System.out.println("X: " + x + " Y: " + y);
						graphics.paintImmediately(x, y, 3, 3);
						if(e < 0) {
							e = e + inc2;
						}
						else {
							x = x + 1;
							e = e - inc1;
						}
						y = y + 1;
					}
				}
			}
			
			else if(y0 == y1 && x1 > x0 && yinc == 0) {
				//System.out.println("Case 2");
				//Case 2: y0 = y1, x1 > x0, yinc = 0 (horizontal going to the right)
				for(int i = x0; i < x1; i++) {
					//System.out.println("X: " + x + " Y: " + y);
					graphics.paintImmediately(x, y, 3, 3);
					x = x + 1;
				}
			}
			
			else if(y0 == y1 && x0 > x1 && yinc == 0.0) {
				//System.out.println("Case 3");
				//Case 3: y0 = y1, x0 > x1, yinc = 0 (horizontal going to the left)
				for(int i = x0; i > x1; i--) {
					//System.out.println("X: " + x + " Y: " + y);
					graphics.paintImmediately(x, y, 3, 3);
					x = x - 1;
				}
			}
			
			else if(x0 == x1 && y0 > y1) {
				//System.out.println("Case 4");
				//Case 4: x0 = x1, y0 > y1, yinc = undefined (vertical going down)
				for(int i = y0; i > y1; i--) {
					//System.out.println("X: " + x + " Y: " + y);
					graphics.paintImmediately(x, y, 3, 3);
					y = y - 1;
				}
			}
			
			else if(x0 == x1 && y1 > y0) {
				//System.out.println("Case 5");
				//Case 5: x0 = x1, y1 > y0, yinc = undefined (vertical going up)
				for(int i = y0; i < y1; i++) {
					//System.out.println("X: " + x + " Y: " + y);
					graphics.paintImmediately(x, y, 3, 3);
					y = y + 1;
				}
			}
			
			else if(x0 > x1 && y0 > y1 && yinc > 0) {
				//Case 6: x0 > x1, y0 > y1, yinc > 0 (diagonal, top right to bottom left)
				
				//slope less than 1 but greater than 0
				if(Math.abs(deltax) > Math.abs(deltay)) {
					//System.out.println("Case 6, 0 < slope < 1");
					while(x > x1) {
						//System.out.println("X: " + x + " Y: " + y);
						graphics.paintImmediately(x, y, 3, 3);
						if(e < 0) {
							e = e - inc1;
						}
						else {
							y = y - 1;
							e = e - inc2;
						}
						x = x - 1;
					}
				}
				
				//slope greater than 1
				else {
					//System.out.println("Case 6, slope >= 1");
					while(y >= y1) {
						//System.out.println("X: " + x + " Y: " + y);
						graphics.paintImmediately(x, y, 3, 3);
						if(e > 0) {
							e = e + inc2;
						}
						else {
							x = x - 1;
							e = e - inc1;
						}
						y = y - 1;
					}
				}
			}
			
			else if(x1 > x0 && y0 > y1 && yinc < 0) {
				//Case 7: x1 > x0, y0 > y1, yinc < 0 (diagonal, top left to bottom right)
				//slope less than 1 but greater than 0
				
				if(Math.abs(deltax) > Math.abs(deltay)) {
					//System.out.println("Case 7, -1 < slope < 0");
					while(x < x1) {
						//System.out.println("X: " + x + " Y: " + y);
						graphics.paintImmediately(x, y, 3, 3);
						if(e < 0) {
							e = e + inc1;
						}
						else {
							y = y - 1;
							e = e + inc2;
						}
						x = x + 1;
					}
				}
				
				//slope less than -1
				else {
					//System.out.println("Case 7, slope <= -1");
					while(y >= y1) {
						//System.out.println("X: " + x + " Y: " + y);
						graphics.paintImmediately(x, y, 3, 3);
						if(e > 0) {
							e = e + inc1;
						}
						else {
							x = x + 1;
							e = e - inc2;
						}
						y = y - 1;
					}
				}
			}
			
			else if(x0 > x1 && y1 > y0 && yinc < 0) {
				//System.out.println("Case 8");
				//Case 8: x0 > x1, y0 > y1, yinc < 0 (diagonal, bottom right to top left)
				if(Math.abs(deltax) > Math.abs(deltay)) {
					//System.out.println("Case 8, -1 < slope < 0");
					while(x > x1) {
						//System.out.println("X: " + x + " Y: " + y);
						graphics.paintImmediately(x, y, 3, 3);
						if(e < 0) {
							e = e + inc1;
						}
						else {
							y = y + 1;
							e = e - inc2;
						}
						x = x - 1;
					}
				}
				
				//slope less than -1
				else {
					//System.out.println("Case 8, slope <= -1");
					while(y < y1) {
						//System.out.println("X: " + x + " Y: " + y);
						graphics.paintImmediately(x, y, 3, 3);
						if(e < 0) {
							e = e + inc1;
						}
						else {
							x = x - 1;
							e = e - inc2;
						}
						y = y + 1;
					}
				}
			}
			
			else {
				System.out.println("Case not found!");
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

class BresenhamAlg extends JPanel {
	
	protected void paintComponent(Graphics g) {
		
		//super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.fillRect(x, (int)y, 3, 3);
		
	}

}
}

