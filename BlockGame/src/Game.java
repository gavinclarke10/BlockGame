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
			+ "\n -You must add or take away at least 1 one block per turn"
			+ "\n -Press the SPACEBAR when you are done with your turn"
			+ "\n -The Player who takes the last block LOSES "
			+ "\n -Press the 1, 2, or 3 key to add a block to pile 1, 2, or 3 (you only have 2 extra blocks)";

	boolean gameMode = true;
	boolean addMode = false;

	Color c = Color.RED;

	ArrayList<Block> blocks1 = new ArrayList<>();
	ArrayList<Block> blocks2 = new ArrayList<>();
	ArrayList<Block> blocks3 = new ArrayList<>();

	int w = 150;
	int mode;

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

		int x = getHeight() / 2 - (w / 2);

		Text t1 = new Text(s + "'s turn", 100, 150, 50, c);
		t1.draw(gc);

		Text t2 = new Text(rules, getWidth() / 2 - 200, 100, 22, Color.BLACK);
		t2.draw(gc);

		Text t3 = new Text(s.toUpperCase() + " LOSES!!!", getWidth() / 2 - 225, getHeight() / 2, 60, Color.BLUEVIOLET);

		Text t4 = new Text("Player #1 extra Blocks: " + p1Count, 100, getHeight() / 4, 25, Color.BLACK);
		t4.draw(gc);

		Text t5 = new Text("Player #2 extra Blocks: " + p2Count, 100, getHeight() / 4 + 30, 25, Color.BLACK);
		t5.draw(gc);

		Text t6 = new Text("#1", (getWidth() / 2 - 300 - 15), x - 15, 30, Color.BLACK);
		t6.draw(gc);

		Text t7 = new Text("#2", (getWidth() / 2 - 15), x - 15, 30, Color.BLACK);
		t7.draw(gc);

		Text t8 = new Text("#3", (getWidth() / 2 + 300 - 15), x - 15, 30, Color.BLACK);
		t8.draw(gc);

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

			addBlockP1(ke.getText(), "1", pile1, 4, blocks1, -300, "p1");
			addBlockP1(ke.getText(), "2", pile2, 4, blocks2, 0, "p1");
			addBlockP1(ke.getText(), "3", pile3, 4, blocks3, 300, "p1");
		}

		if (turn == -1 && p2Count > 0) {
			addBlockP1(ke.getText(), "1", pile1, 4, blocks1, -300, "p2");
			addBlockP1(ke.getText(), "2", pile2, 4, blocks2, 0, "p2");
			addBlockP1(ke.getText(), "3", pile3, 4, blocks3, 300, "p2");
		}
	}

	@Override
	public void onMousePressed(MouseEvent me) {

		removeBlock(1, pile1, me.getX(), me.getY(), blocks1);
		removeBlock(2, pile2, me.getX(), me.getY(), blocks2);
		removeBlock(3, pile3, me.getX(), me.getY(), blocks3);

		if (pile1 == 0 && pile2 == 0 && pile3 == 0) {
			gameMode = false;
		}
	}

	public void addBlockP1(String s, String num, int p, int m, ArrayList<Block> b, int loc, String c) {
		if (s.equals(num)) {
			p++;
			//c--;
			mode = m;
			b.add(new Block(Color.LIMEGREEN, (getWidth() / 2 - (w / 2) + loc) + (b.size() * 30),
					getHeight() / 2 - (w / 2) + b.size() * 30, w, 10));
			if (c.equals("p1") == true)
			{
				p1Count--;
			}
			if (c.equals("p2") == true)
			{
				p2Count--;
			}
		}
	}

	public void addBlockP2(String s, String num, int p, int m, ArrayList<Block> b, int loc) {
		if (s.equals(num)) {
			p++;
			p2Count--;
			mode = m;
			b.add(new Block(Color.LIMEGREEN, (getWidth() / 2 - (w / 2) + loc) + (b.size() * 30),
					getHeight() / 2 - (w / 2) + b.size() * 30, w, 10));
		}
	}

	public void removeBlock(int m, int p, double x, double y, ArrayList<Block> b) {
		if ((mode == 0 || mode == m) && p > 0 && Math.abs(b.get(b.size() - 1).getX() - x) < w
				&& Math.abs(b.get(b.size() - 1).getY() - y) < w && x > b.get(b.size() - 1).getX()
				&& y > b.get(b.size() - 1).getY()) {
			b.remove(b.size() - 1);
			p = p - 1;
			mode = m;
		}
	}
}