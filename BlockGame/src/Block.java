import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Block {
	private int x;
	private int y;
	private int d;
	private int line;
	private Color c;

	public Block(Color c, int x, int y, int d, int line) {
		this.x = x;
		this.y = y;
		this.d = d;
		this.line = line;
		this.c = c;
	}

	public void draw(GraphicsContext gc) {
		gc.fillRect(x, y, d, d);
		gc.setFill(c);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(line);
		gc.strokeRoundRect(x, y, d, d, line, line);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void addBlock() {
		
	}
}