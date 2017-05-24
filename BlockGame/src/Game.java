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

	String s = "Player 1";
	String rules = "RULES:" + "\n -You may take as many blocks from ONE pile as you want but not from multiple piles "
			+ "\n -You must add or take away at least 1 one block per turn"
			+ "\n -Press the SPACEBAR when you are done with your turn"
			+ "\n -The Player who takes the last block LOSES "
			+ "\n -Press the 1, 2, or 3 key to add a block to pile 1, 2, or 3 (you only have 2 extra blocks)";

	boolean gameMode = true;
	Color turnColor = Color.RED;
	Color blockColor = Color.LIMEGREEN;
	Color textColor = Color.BLACK;
	Color loserTextColor = Color.BLUEVIOLET;

	ArrayList<Block> blocks1 = new ArrayList<>();
	ArrayList<Block> blocks2 = new ArrayList<>();
	ArrayList<Block> blocks3 = new ArrayList<>();

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
			blocks1.add(new Block(blockColor, (getWidth() / 2 - (150 / 2) - 300) + (i * 30),
					getHeight() / 2 - (150 / 2) + i * 30, 150, 10));
		}
		for (int j = 0; j < pile2; j++) {
			blocks2.add(new Block(blockColor, (getWidth() / 2 - (150 / 2)) + (j * 30),
					getHeight() / 2 - (150 / 2) + j * 30, 150, 10));
		}
		for (int k = 0; k < pile3; k++) {
			blocks3.add(new Block(blockColor, (getWidth() / 2 - (150 / 2) + 300) + (k * 30),
					getHeight() / 2 - (150 / 2) + k * 30, 150, 10));
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		Text turn = new Text(s + "'s turn", 100, 150, 50, turnColor);
		Text rule = new Text(rules, getWidth() / 2 - 200, 100, 22, textColor);
		Text loser = new Text(s.toUpperCase() + " LOSES!!!", getWidth() / 2 - 225, getHeight() / 2, 60, loserTextColor);
		Text p1xtra = new Text("Player #1 extra Blocks: " + p1Count, 100, getHeight() / 4, 25, textColor);
		Text p2xtra = new Text("Player #2 extra Blocks: " + p2Count, 100, getHeight() / 4 + 30, 25, textColor);
		Text t1 = new Text("#1", (getWidth() / 2 - 300 - 15), getHeight() / 2 - (150 / 2) - 15, 30, textColor);
		Text t2 = new Text("#2", (getWidth() / 2 - 15), getHeight() / 2 - (150 / 2) - 15, 30, textColor);
		Text t3 = new Text("#3", (getWidth() / 2 + 300 - 15), getHeight() / 2 - (150 / 2) - 15, 30, textColor);

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
	}

	@Override
	public void onKeyPressed(KeyEvent ke) {
		if (ke.getText().equals(" ") && mode != 0 && gameMode == true) {
			switchTurns();
		}

		if (turn == 1 && p1Count > 0) {
			if (ke.getText().equals("1")) {
				pile1++;
				mode = 4;
				p1Count--;
				blocks1.add(new Block(blockColor, (getWidth() / 2 - (150 / 2) - 300) + (blocks1.size() * 30),
						getHeight() / 2 - (150 / 2) + blocks1.size() * 30, 150, 10));
			}
			if (ke.getText().equals("2")) {
				pile2++;
				mode = 4;
				p1Count--;
				blocks2.add(new Block(blockColor, (getWidth() / 2 - (150 / 2)) + (blocks2.size() * 30),
						getHeight() / 2 - (150 / 2) + blocks2.size() * 30, 150, 10));
			}
			if (ke.getText().equals("3")) {
				pile3++;
				mode = 4;
				p1Count--;
				blocks3.add(new Block(blockColor, (getWidth() / 2 - (150 / 2) + 300) + (blocks3.size() * 30),
						getHeight() / 2 - (150 / 2) + blocks3.size() * 30, 150, 10));
			}
			/*
			 * addBlock(ke.getText(), "1", pile1, blocks1, -300, "p1");
			 * addBlock(ke.getText(), "2", pile2, blocks2, 0, "p1");
			 * addBlock(ke.getText(), "3", pile3, blocks3, 300, "p1");
			 */
		}

		if (turn == -1 && p2Count > 0) {
			if (ke.getText().equals("1")) {
				pile1++;
				mode = 4;
				p2Count--;
				blocks1.add(new Block(blockColor, (getWidth() / 2 - (150 / 2) - 300) + (blocks1.size() * 30),
						getHeight() / 2 - (150 / 2) + blocks1.size() * 30, 150, 10));
			}
			if (ke.getText().equals("2")) {
				pile2++;
				mode = 4;
				p2Count--;
				blocks2.add(new Block(blockColor, (getWidth() / 2 - (150 / 2)) + (blocks2.size() * 30),
						getHeight() / 2 - (150 / 2) + blocks2.size() * 30, 150, 10));
			}
			if (ke.getText().equals("3")) {
				pile3++;
				mode = 4;
				p2Count--;
				blocks3.add(new Block(blockColor, (getWidth() / 2 - (150 / 2) + 300) + (blocks3.size() * 30),
						getHeight() / 2 - (150 / 2) + blocks3.size() * 30, 150, 10));
				/*
				 * addBlock(ke.getText(), "1", pile1, blocks1, -300, "p2");
				 * addBlock(ke.getText(), "2", pile2, blocks2, 0, "p2");
				 * addBlock(ke.getText(), "3", pile3, blocks3, 300, "p2");
				 */
			}
		}
	}

	@Override
	public void onMousePressed(MouseEvent me) {

		double x = me.getX();
		double y = me.getY();

		if ((mode == 0 || mode == 1) && pile1 > 0 && Math.abs(blocks1.get(blocks1.size() - 1).getX() - me.getX()) < 150
				&& Math.abs(blocks1.get(blocks1.size() - 1).getY() - y) < 150
				&& x > blocks1.get(blocks1.size() - 1).getX() && y > blocks1.get(blocks1.size() - 1).getY()) {
			blocks1.remove(blocks1.size() - 1);
			pile1 = pile1 - 1;
			mode = 1;
		}
		if ((mode == 0 || mode == 2) && pile2 > 0 && Math.abs(blocks2.get(blocks2.size() - 1).getX() - x) < 150
				&& Math.abs(blocks2.get(blocks2.size() - 1).getY() - y) < 150
				&& x > blocks2.get(blocks2.size() - 1).getX() && y > blocks2.get(blocks2.size() - 1).getY()) {
			blocks2.remove(blocks2.size() - 1);
			pile2 = pile2 - 1;
			mode = 2;
		}
		if ((mode == 0 || mode == 3) && pile3 > 0 && Math.abs(blocks3.get(blocks3.size() - 1).getX() - x) < 150
				&& Math.abs(blocks3.get(blocks3.size() - 1).getY() - y) < 150
				&& x > blocks3.get(blocks3.size() - 1).getX() && y > blocks3.get(blocks3.size() - 1).getY()) {
			blocks3.remove(blocks3.size() - 1);
			pile3 = pile3 - 1;
			mode = 3;
		}
		if (pile1 == 0 && pile2 == 0 && pile3 == 0) {
			gameMode = false;
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

	/* public void addBlock(String s, String num, int p, ArrayList<Block> b, int loc, String counter) {
		if (s.equals(num)) {
			p++;
			mode = 4;
			b.add(new Block(blockColor, (getWidth() / 2 - (150 / 2) + loc) + (b.size() * 30),
					getHeight() / 2 - (150 / 2) + b.size() * 30, 150, 10));
			if (counter.equals("p1") == true) {
				p1Count--;
			}
			if (counter.equals("p2") == true) {
				p2Count--;
			}
		}
	}
	*/
}