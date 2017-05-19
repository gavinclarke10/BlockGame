import java.util.ArrayList;
import javafx.scene.input.KeyEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import tsinn.ui.SimpleApp;

public class Game extends SimpleApp {
	int pile1 = 4;
	int pile2 = 5;
	int pile3 = 6;

	int turn = 1;

	int p1Count = 2;
	int p2Count = 2;

	String s = "Player 1";
	String rules = "RULES:" + "\n -You may take as many blocks from ONE pile as you want but not from multiple piles "
			+ "\n -You cannot take zero blocks on a turn" + "\n -Press the SPACEBAR when you are done with your turn"
			+ "\n -The Player who takes the last block loses " 
			+ "\n -Press 1, 2, or 3 key to add a block to pile 1, 2, or 3 (you only have 2 extra blocks)";

	boolean gameMode = true;
	boolean addMode = false;

	Color c = Color.RED;

	ArrayList<Block> blocks1 = new ArrayList<>();
	ArrayList<Block> blocks2 = new ArrayList<>();
	ArrayList<Block> blocks3 = new ArrayList<>();

	int w = 150;
	int mode;
	
	int a = getWidth() / 2 - (w / 2);
	int b = getHeight() / 2 - (w / 2);
	
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void updateAnimation(long time) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupApp(GraphicsContext gc) {
		// TODO Auto-generated method string

		int a = getWidth() / 2 - (w / 2);
		int b = getHeight() / 2 - (w / 2);

		for (int i = 0; i < pile1; i++) {
			blocks1.add(new Block(Color.LIMEGREEN, (a - 300) + (i * 30), b + i * 30, w, 10));
		}
		for (int j = 0; j < pile2; j++) {
			blocks2.add(new Block(Color.LIMEGREEN, (a) + (j * 30), b + j * 30, w, 10));
		}
		for (int k = 0; k < pile3; k++) {
			blocks3.add(new Block(Color.LIMEGREEN, (a + 300) + (k * 30), b + k * 30, w, 10));
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub

		Text t1 = new Text(s + "'s turn", 100, 150, 50, c);
		t1.draw(gc);

		Text t2 = new Text(rules, getWidth() / 2 - 200, 100, 22, Color.BLACK);
		t2.draw(gc);

		Text t3 = new Text(s.toUpperCase() + " LOSES!!!", getWidth() / 2 - 225, getHeight() / 2, 60, Color.BLUEVIOLET);

		Text t4 = new Text("Player #1 extra Blocks: " + p1Count, 100, getHeight() / 4, 25, Color.BLACK);
		t4.draw(gc);

		Text t5 = new Text("Player #2 extra Blocks: " + p2Count, 100, getHeight() / 4 + 30, 25, Color.BLACK);
		t5.draw(gc);
		
		Text t6 = new Text("#1", 100, getHeight() / 4, 25, Color.BLACK);
		t6.draw(gc);
		
		gc.setFill(Color.LIMEGREEN);

		for (Block b : blocks1) {
			b.draw(gc);
		}
		for (Block b : blocks2) {
			b.draw(gc);
		}
		for (Block b : blocks3) {
			b.draw(gc);
		}

		if (gameMode == false) {
			t3.draw(gc);
		}
	}

	@Override
	public void onKeyPressed(KeyEvent ke) {
		if (ke.getText().equals(" ") && mode != 0 && gameMode == true) {
			mode = 0;
			turn = turn * -1;
			if (turn == 1) {
				s = "Player 1";
				c = Color.RED;
			}
			if (turn == -1) {
				s = "Player 2";
				c = Color.DODGERBLUE;
			}
		}

		

		if (turn == 1 && p1Count > 0) {
			if (ke.getText().equals("1")) {
				pile1++;
				p1Count--;
				blocks1.add(
						new Block(Color.LIMEGREEN, (a - 300) + (blocks1.size() * 30), b + blocks1.size() * 30, w, 10));
			}
			if (ke.getText().equals("2")) {
				pile2++;
				p1Count--;
				blocks2.add(new Block(Color.LIMEGREEN, (a) + (blocks2.size() * 30), b + blocks2.size() * 30, w, 10));
			}
			if (ke.getText().equals("3")) {
				pile3++;
				p1Count--;
				blocks3.add(
						new Block(Color.LIMEGREEN, (a + 300) + (blocks3.size() * 30), b + blocks3.size() * 30, w, 10));
			}
		} else if (turn == -1 && p2Count > 0) {
			if (ke.getText().equals("1")) {
				pile2++;
				p2Count--;
				blocks1.add(
						new Block(Color.LIMEGREEN, (a - 300) + (blocks1.size() * 30), b + blocks1.size() * 30, w, 10));
			}
			if (ke.getText().equals("2")) {
				pile2++;
				p2Count--;
				blocks2.add(new Block(Color.LIMEGREEN, (a) + (blocks2.size() * 30), b + blocks2.size() * 30, w, 10));
			}
			if (ke.getText().equals("3")) {
				pile3++;
				p2Count--;
				blocks3.add(
						new Block(Color.LIMEGREEN, (a + 300) + (blocks3.size() * 30), b + blocks3.size() * 30, w, 10));
			}
		}
	}

	@Override
	public void onMousePressed(MouseEvent me) {
		if ((mode == 0 || mode == 1) && pile1 > 0 && Math.abs(blocks1.get(blocks1.size() - 1).getX() - me.getX()) < w
				&& Math.abs(blocks1.get(blocks1.size() - 1).getY() - me.getY()) < w
				&& me.getX() > blocks1.get(blocks1.size() - 1).getX()
				&& me.getY() > blocks1.get(blocks1.size() - 1).getY()) {
			blocks1.remove(blocks1.size() - 1);
			pile1 = pile1 - 1;
			mode = 1;
		}

		if ((mode == 0 || mode == 2) && pile2 > 0 && Math.abs(blocks2.get(blocks2.size() - 1).getX() - me.getX()) < w
				&& Math.abs(blocks2.get(blocks2.size() - 1).getY() - me.getY()) < w
				&& me.getX() > blocks2.get(blocks2.size() - 1).getX()
				&& me.getY() > blocks2.get(blocks2.size() - 1).getY()) {
			blocks2.remove(blocks2.size() - 1);
			pile2 = pile2 - 1;
			mode = 2;
		}

		if ((mode == 0 || mode == 3) && pile3 > 0 && Math.abs(blocks3.get(blocks3.size() - 1).getX() - me.getX()) < w
				&& Math.abs(blocks3.get(blocks3.size() - 1).getY() - me.getY()) < w
				&& me.getX() > blocks3.get(blocks3.size() - 1).getX()
				&& me.getY() > blocks3.get(blocks3.size() - 1).getY()) {
			blocks3.remove(blocks3.size() - 1);
			pile3 = pile3 - 1;
			mode = 3;
		}

		if (pile1 == 0 && pile2 == 0 && pile3 == 0) {
			gameMode = false;
		}
	}
}