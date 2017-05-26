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
	int mode;
	int d = 150 / 2;

	String s = "Player 1";
	String rules = "RULES:" + "\n -You may take as many blocks from ONE pile as you want but not from multiple piles "
			+ "\n -You must add or take away at least 1 one block per turn"
			+ "\n -Press the SPACEBAR when you are done with your turn"
			+ "\n -The Player who takes the last block LOSES "
			+ "\n -drag and drop your 2 extra blocks onto the pile you want to add to ";

	boolean gameMode = true;
	boolean dragMode = false;
	boolean dragMode2 = false;

	Color turnColor = Color.RED;
	Color blockColor = Color.LIMEGREEN;
	Color textColor = Color.BLACK;
	Color loserTextColor = Color.BLUEVIOLET;

	ArrayList<Block> blocks1 = new ArrayList<>();
	ArrayList<Block> blocks2 = new ArrayList<>();
	ArrayList<Block> blocks3 = new ArrayList<>();
	ArrayList<Block> p1 = new ArrayList<>();
	ArrayList<Block> p2 = new ArrayList<>();

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
		for (int i = 0; i < pile1; i++) {
			blocks1.add(new Block(blockColor, (getWidth() / 2 - d - 300) + (i * 30), getHeight() / 2 - d + i * 30,
					d * 2, 10));
		}
		for (int j = 0; j < pile2; j++) {
			blocks2.add(
					new Block(blockColor, (getWidth() / 2 - d) + (j * 30), getHeight() / 2 - d + j * 30, d * 2, 10));
		}
		for (int k = 0; k < pile3; k++) {
			blocks3.add(new Block(blockColor, (getWidth() / 2 - d + 300) + (k * 30), getHeight() / 2 - d + k * 30,
					d * 2, 10));
		}

		for (int k = 0; k < p1Count; k++) {
			p1.add(new Block(Color.RED, 150 - (40 * k), getHeight() - d + (30 * k), d * 2, 10));
		}

		for (int k = 0; k < p2Count; k++) {
			p2.add(new Block(Color.DODGERBLUE, getWidth() - 150 - (40 * k) - d, getHeight() - d + (30 * k), d * 2, 10));
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub

		Text turn = new Text(s + "'s turn", 100, d * 2, 50, turnColor);
		Text rule = new Text(rules, getWidth() / 2 - 200, 100, 22, textColor);
		Text loser = new Text(s.toUpperCase() + " LOSES!!!", getWidth() / 2 - 225, getHeight() / 2, 60, loserTextColor);
		Text p1xtra = new Text("Player #1 extra Blocks: " + p1Count, d * 2 - 75, getHeight() - d - 15, 25, Color.RED);
		Text p2xtra = new Text("Player #2 extra Blocks: " + p2Count, getWidth() - d * 2 - d - 75, getHeight() - d - 15,
				25, Color.DODGERBLUE);
		Text t1 = new Text("#1", (getWidth() / 2 - 300 - 15), getHeight() / 2 - d - 15, 30, textColor);
		Text t2 = new Text("#2", (getWidth() / 2 - 15), getHeight() / 2 - d - 15, 30, textColor);
		Text t3 = new Text("#3", (getWidth() / 2 + 300 - 15), getHeight() / 2 - d - 15, 30, textColor);

		turn.draw(gc);
		rule.draw(gc);
		p1xtra.draw(gc);
		p2xtra.draw(gc);
		t1.draw(gc);
		t2.draw(gc);
		t3.draw(gc);

		if (gameMode == false) {
			loser.draw(gc);
		}

		gc.setFill(blockColor);

		for (Block b : blocks1) {
			b.draw(gc);
		}
		for (Block b : blocks2) {
			b.draw(gc);
		}
		for (Block b : blocks3) {
			b.draw(gc);
		}

		gc.setFill(Color.RED);
		for (Block b : p1) {
			b.draw(gc);
		}
		gc.setFill(Color.DODGERBLUE);
		for (Block b : p2) {
			b.draw(gc);
		}
	}

	@Override
	public void onKeyPressed(KeyEvent ke) {
		if (ke.getText().equals(" ") && mode != 0 && gameMode == true) {
			switchTurns();
		}
	}

	/*----- OLD 1 2 3 key pressing method -----
	 * if (turn == 1 && p1Count > 0) { if (ke.getText().equals("1")) { pile1++;
	 * mode = 4; p1Count--; p1.remove(p1.size() - 1); blocks1.add(new
	 * Block(blockColor, (getWidth() / 2 - (150 / 2) - 300) + (blocks1.size() *
	 * 30), getHeight() / 2 - (150 / 2) + blocks1.size() * 30, 150, 10)); } if
	 * (ke.getText().equals("2")) { pile2++; mode = 4; p1Count--;
	 * p1.remove(p1.size() - 1); blocks2.add(new Block(blockColor, (getWidth() /
	 * 2 - (150 / 2)) + (blocks2.size() * 30), getHeight() / 2 - (150 / 2) +
	 * blocks2.size() * 30, 150, 10)); } if (ke.getText().equals("3")) {
	 * pile3++; mode = 4; p1Count--; p1.remove(p1.size() - 1); blocks3.add(new
	 * Block(blockColor, (getWidth() / 2 - (150 / 2) + 300) + (blocks3.size() *
	 * 30), getHeight() / 2 - (150 / 2) + blocks3.size() * 30, 150, 10)); }
	 * 
	 * addBlock(ke.getText(), "1", pile1, blocks1, -300, "p1");
	 * addBlock(ke.getText(), "2", pile2, blocks2, 0, "p1");
	 * addBlock(ke.getText(), "3", pile3, blocks3, 300, "p1");
	 * 
	 * } if (turn == -1 && p2Count > 0) { if (ke.getText().equals("1")) {
	 * pile1++; mode = 4; p2Count--; p2.remove(p2.size() - 1); blocks1.add(new
	 * Block(blockColor, (getWidth() / 2 - (150 / 2) - 300) + (blocks1.size() *
	 * 30), getHeight() / 2 - (150 / 2) + blocks1.size() * 30, 150, 10)); } if
	 * (ke.getText().equals("2")) { pile2++; mode = 4; p2Count--;
	 * p2.remove(p2.size() - 1); blocks2.add(new Block(blockColor, (getWidth() /
	 * 2 - (150 / 2)) + (blocks2.size() * 30), getHeight() / 2 - (150 / 2) +
	 * blocks2.size() * 30, 150, 10)); } if (ke.getText().equals("3")) {
	 * pile3++; mode = 4; p2Count--; p2.remove(p2.size() - 1); blocks3.add(new
	 * Block(blockColor, (getWidth() / 2 - (150 / 2) + 300) + (blocks3.size() *
	 * 30), getHeight() / 2 - (150 / 2) + blocks3.size() * 30, 150, 10)); /*
	 * addBlock(ke.getText(), "1", pile1, blocks1, -300, "p2");
	 * addBlock(ke.getText(), "2", pile2, blocks2, 0, "p2");
	 * addBlock(ke.getText(), "3", pile3, blocks3, 300, "p2");
	 */

	@Override
	public void onMouseDragged(MouseEvent me) {
		if (dragMode == true) {
			p1.get(p1.size() - 1).setX(me.getX() - d);
			p1.get(p1.size() - 1).setY(me.getY() - d);
		}
		if (dragMode2 == true) {
			p2.get(p2.size() - 1).setX(me.getX() - d);
			p2.get(p2.size() - 1).setY(me.getY() - d);
		}
	}

	public boolean draggedOn1(int x) {
		if (p1Count > 0 && p1.get(p1.size() - 1).getX() > getWidth() / 2 + x - d
				&& p1.get(p1.size() - 1).getX() < getWidth() / 2 + x - d + 200
				&& p1.get(p1.size() - 1).getY() > getHeight() / 2 - 200
				&& p1.get(p1.size() - 1).getY() < getHeight() / 2 + 200) {
			return true;
		}
		return false;
	}

	public boolean draggedOn2(int x) {
		if (p2Count > 0 && p2.get(p2.size() - 1).getX() > getWidth() / 2 + x - d
				&& p2.get(p2.size() - 1).getX() < getWidth() / 2 + x - d + 200
				&& p2.get(p2.size() - 1).getY() > getHeight() / 2 - 200
				&& p2.get(p2.size() - 1).getY() < getHeight() / 2 + 200) {
			return true;
		}
		return false;
	}

	public void onMousePressed(MouseEvent me) {
		double x = me.getX();
		double y = me.getY();

		if (p1Count > 0 && turn == 1) {
			if (p1.get(p1.size() - 1).getX() + d * 2 > me.getX() && p1.get(p1.size() - 1).getY() + d * 2 > me.getY()
					&& p1.get(p1.size() - 1).getX() < me.getX() && p1.get(p1.size() - 1).getY() < me.getY()) {
				dragMode = true;
			} else {
				dragMode = false;
			}
		}
		if (p2Count > 0 && turn == -1) {
			if (p2.get(p2.size() - 1).getX() + d * 2 > me.getX() && p2.get(p2.size() - 1).getY() + d * 2 > me.getY()
					&& p2.get(p2.size() - 1).getX() < me.getX() && p2.get(p2.size() - 1).getY() < me.getY()) {
				dragMode2 = true;
			} else {
				dragMode2 = false;
			}
		}

		if (removeBlock(1, pile1, blocks1, x, y) == true) {
			blocks1.remove(blocks1.size() - 1);
			pile1 = pile1 - 1;
			mode = 1;
		}
		if (removeBlock(2, pile2, blocks2, x, y) == true) {
			blocks2.remove(blocks2.size() - 1);
			pile2 = pile2 - 1;
			mode = 2;
		}
		if (removeBlock(3, pile3, blocks3, x, y) == true) {
			blocks3.remove(blocks3.size() - 1);
			pile3 = pile3 - 1;
			mode = 3;
		}
		if (pile1 == 0 && pile2 == 0 && pile3 == 0) {
			gameMode = false;
		}
	}

	public void onMouseReleased(MouseEvent m3) {
		if (draggedOn1(-300)==true) {
			System.out.println("yyeyeyey");
			pile1++;
			mode = 4;
			p1Count--;
			p1.remove(p1.size() - 1);
			blocks1.add(new Block(blockColor, getWidth() / 2 - d - 300 + (blocks1.size() * 30),
					getHeight() / 2 - d + (blocks1.size() * 30), d * 2, 10));
			dragMode = false;
		}
		if (p2Count > 0 && p2.get(p2.size() - 1).getX() > getWidth() / 2 - 300 - d
				&& p2.get(p2.size() - 1).getX() < getWidth() / 2 - 300 - d + 200
				&& p2.get(p2.size() - 1).getY() > getHeight() / 2 - 200
				&& p2.get(p2.size() - 1).getY() < getHeight() / 2 + 200) {
			pile1++;
			mode = 4;
			p2Count--;
			p2.remove(p2.size() - 1);
			blocks1.add(new Block(blockColor, (getWidth() / 2 - d - 300) + (blocks1.size() * 30),
					getHeight() / 2 - d + blocks1.size() * 30, d * 2, 10));
			dragMode2 = false;
		}

		if (p1Count > 0 && p1.get(p1.size() - 1).getX() > getWidth() / 2 - d
				&& p1.get(p1.size() - 1).getX() < getWidth() / 2 - d + 200
				&& p1.get(p1.size() - 1).getY() > getHeight() / 2 - 200
				&& p1.get(p1.size() - 1).getY() < getHeight() / 2 + 200) {
			pile2++;
			mode = 4;
			p1Count--;
			p1.remove(p1.size() - 1);
			blocks2.add(new Block(blockColor, (getWidth() / 2 - d) + (blocks2.size() * 30),
					getHeight() / 2 - d + blocks2.size() * 30, d * 2, 10));
			dragMode = false;
		}
		if (p2Count > 0 && p2.get(p2.size() - 1).getX() > getWidth() / 2 - d
				&& p2.get(p2.size() - 1).getX() < getWidth() / 2 - d + 200
				&& p2.get(p2.size() - 1).getY() > getHeight() / 2 - 200
				&& p2.get(p2.size() - 1).getY() < getHeight() / 2 + 200) {
			pile2++;
			mode = 4;
			p2Count--;
			p2.remove(p2.size() - 1);
			blocks2.add(new Block(blockColor, (getWidth() / 2 - d) + (blocks2.size() * 30),
					getHeight() / 2 - d + blocks2.size() * 30, d * 2, 10));
			dragMode2 = false;
		}

		if (p1Count > 0 && p1.get(p1.size() - 1).getX() > getWidth() / 2 + 300 - d
				&& p1.get(p1.size() - 1).getX() < getWidth() / 2 + 300 - d + 200
				&& p1.get(p1.size() - 1).getY() > getHeight() / 2 - 200
				&& p1.get(p1.size() - 1).getY() < getHeight() / 2 + 200) {
			pile3++;
			mode = 4;
			p1Count--;
			p1.remove(p1.size() - 1);
			blocks3.add(new Block(blockColor, (getWidth() / 2 - d + 300) + (blocks3.size() * 30),
					getHeight() / 2 - d + blocks3.size() * 30, d * 2, 10));
			dragMode = false;
		}
		if (p2Count > 0 && p2.get(p2.size() - 1).getX() > getWidth() / 2 + 300 - d
				&& p2.get(p2.size() - 1).getX() < getWidth() / 2 + 300 - d + 200
				&& p2.get(p2.size() - 1).getY() > getHeight() / 2 - 200
				&& p2.get(p2.size() - 1).getY() < getHeight() / 2 + 200) {
			pile3++;
			mode = 4;
			p2Count--;
			p2.remove(p2.size() - 1);
			blocks3.add(new Block(blockColor, (getWidth() / 2 - d + 300) + (blocks3.size() * 30),
					getHeight() / 2 - d + (blocks3.size() * 30), d * 2, 10));
			dragMode2 = false;
		}

		if (p1Count > 0) {
			p1.get(p1.size() - 1).setX(150 - (40));
			p1.get(p1.size() - 1).setY(getHeight() - d + (30));
		}

		if (p2Count > 0) {
			p2.get(p2.size() - 1).setX(getWidth() - d * 2 - (40) - 150 / 2);
			p2.get(p2.size() - 1).setY(getHeight() - d + (30));
		}
	}

	public void switchTurns() {
		mode = 0;
		turn = turn * -1;
		if (turn == 1) {
			s = "Player 1";
			turnColor = Color.RED;
		}
		if (turn == -1) {
			s = "Player 2";
			turnColor = Color.DODGERBLUE;
		}
	}

	public boolean removeBlock(int m, int p, ArrayList<Block> b, double x, double y) {
		if ((mode == 0 || mode == m) && p > 0 && Math.abs(b.get(b.size() - 1).getX() - x) < d * 2
				&& Math.abs(b.get(b.size() - 1).getY() - y) < d * 2 && x > b.get(b.size() - 1).getX()
				&& y > b.get(b.size() - 1).getY()) {
			return true;
		}
		return false;
	}
}