package jason.android.helper;

import android.graphics.Paint;
import android.graphics.Path;

public class PaintPath  {

	private final Path path;
	private final Paint paint;
	
	public Path getPath() {
		return path;
	}
	
	public Paint getPaint() {
		return paint;
	}
	
	public PaintPath(Path path, Paint paint) {
		this.path = path;
		this.paint = paint;
	}
}
