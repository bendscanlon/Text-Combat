import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

public class Keyboard extends Core implements KeyListener {

	public static void main(String[] args) {

		new Keyboard().run();

	}

	private String mess = "";
	private Image bg;
	private Animation a;
	private Sprites sprite;

	// init also init from superclass

	public void init() {

		super.init();
		Window w = s.getFullScreenWindow();
		w.setFocusTraversalKeysEnabled(false);
		w.addKeyListener(this);
		mess = "press escape to exit";

		bg = new ImageIcon("E:\\Pictures\\batman.jpg").getImage().getScaledInstance(3840, 2160, 0);
		Image banner1 = new ImageIcon("E:\\Pictures\\logo.png").getImage();
		a = new Animation();

		a.addScene(banner1, 250);
		a.addScene(banner1, 250);
		sprite = new Sprites(a);

	}

	// key pressed

	public void keyPressed(KeyEvent e) {

		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_ESCAPE) {

			stop();
		} else {

			mess = "Pressed: " + KeyEvent.getKeyText(keyCode);
			e.consume();
		}

		switch (keyCode) {

		case KeyEvent.VK_S:
			sprite.setVelocityY(0.5f);
			e.consume();
			break;
		case KeyEvent.VK_W:
			sprite.setVelocityY(-0.5f);
			e.consume();
			break;

		case KeyEvent.VK_D:
			sprite.setVelocityX(0.5f);
			e.consume();
			break;

		case KeyEvent.VK_A:
			sprite.setVelocityX(-0.5f);
			e.consume();
			break;

		}

	}

	// key released

	public void keyReleased(KeyEvent e) {

		int keyCode = e.getKeyCode();
		mess = "Released: " + KeyEvent.getKeyText(keyCode);
		e.consume();

		switch (keyCode) {

		case KeyEvent.VK_S:
			sprite.setVelocityY(0);
			e.consume();
			break;
		case KeyEvent.VK_W:
			sprite.setVelocityY(0);
			e.consume();
			break;

		case KeyEvent.VK_D:
			sprite.setVelocityX(0);
			e.consume();
			break;

		case KeyEvent.VK_A:
			sprite.setVelocityX(0);
			e.consume();
			break;

		}
	}

	// last method from interface

	public void keyTyped(KeyEvent e) {

		e.consume();
	}

	// draw

	public synchronized void draw(Graphics2D g) {

		Window w = s.getFullScreenWindow();

		g.setColor(w.getBackground());
		g.fillRect(0, 0, s.getWidth(), s.getHeight());
		g.drawString(mess, 1000, 1000);
		g.drawImage(bg, 0, 0, null);
		
		g.drawImage(sprite.getImage(), Math.round(sprite.getX()), Math.round(sprite.getY()), null);
	}

	public void update(long timePassed) {

		 if (sprite.getX() < 0) {
		
		 sprite.setVelocityX(0);
		 sprite.setX(1);
		 
		 }else if(sprite.getX() + sprite.getWidth() >= s.getWidth()) {
		
		 sprite.setVelocityX(0);
		 sprite.setX((s.getWidth() - 1) - sprite.getWidth());
		 
		
		 }
		
		 if (sprite.getY() < 0) {
		
		 sprite.setVelocityY(0);
		 sprite.setY(1);
		
		 }else if(sprite.getY()+ sprite.getHeight() >= s.getHeight()) {
		
		 sprite.setVelocityY(0);
		 sprite.setY((s.getHeight() - 1) - sprite.getHeight());
		
		 }

		sprite.update(timePassed);

	}

}
