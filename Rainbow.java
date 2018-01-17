import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class Rainbow {

	static JFrame frame = new JFrame();
	public static State state;
	static int r, g, b;
	static Color current = new Color(r, g, b);

	public static void main(String args[]){
		state = State.RED;
		r = 0;
		makeWindow();
		while(true){
			update(frame);
			System.out.println("r" + r + "g " + g + "b" + b + "State " + state);
		}
	}


	public enum State{
		RED, BLUE, GREEN
	}


	public static void rainbow(){
		delay(1);
		switch(state){
		case RED:
			if(r < 255 && g == 0){
				r++;
			}else if(r >= 255 && g == 0){
				state = State.GREEN;
			}
			if(r > 0 && g >= 255){
				r--;
			}else if(r <= 0 && g >= 255){
				state = State.BLUE;
			}
			if(r >= 255 && g <= 0 && b>= 255){
				state = State.BLUE;
			}
			break;

		case GREEN:
			if(b >= 255 && g > 0){
				g--;
			}else if(b >= 255 && g <= 0){
				state = State.RED;
			}else if(g < 255){
				g++;
			}else if(g >= 255 && r >= 255){
				state = State.RED;
			}
			break;

		case BLUE:
			if(g >= 255 && b < 255){
				b++;
			}else if(b >= 255 && g >= 255){
				state = State.GREEN;
			}
			if(r >= 0 && g <= 0 && b > 0){
				b--;
			}else if(r >= 255 && g <= 0 && b <= 0){
				state = State.RED;
			}
			break;

		default:
			break;

		}
	}

	public static void makeWindow(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize.width, screenSize.height);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.addKeyListener( new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				System.out.println(e.getKeyCode());


				switch(e.getKeyCode()) {

				case 27: System.exit(0);  break; // escape
				}
			}
		});
	}

	public static void update(JFrame frame){
		current = new Color(r, g, b);
		frame.getContentPane().setBackground(current);
		rainbow();
	}

	public static void delay(int d){
		try {
			Thread.sleep(d);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}



}
