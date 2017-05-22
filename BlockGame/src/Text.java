import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Text {
	private String s;
	private int x;
	private int y;
	private int size;
	private Color c;

	public Text(String s, int x, int y, int size, Color c) {
		this.s = s;
		this.x = x;
		this.y = y;
		this.size = size;
		this.c = c;
	}

	public void draw(GraphicsContext gc) {
		gc.setFill(c);
		gc.setFont(javafx.scene.text.Font.font(size));
		gc.fillText(s, x, y);
	}
}