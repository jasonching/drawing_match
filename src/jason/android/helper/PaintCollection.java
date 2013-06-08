package jason.android.helper;

import android.graphics.Color;
import android.graphics.Paint;

public class PaintCollection {
	public static final Paint Black5Stroke;
	public static final Paint Eraser5Stroke;
	
	static {
		
		Black5Stroke = new Paint();
		Black5Stroke.setStyle(Paint.Style.STROKE);
		Black5Stroke.setColor(Color.BLUE);
		Black5Stroke.setStrokeWidth(5);
		
		Eraser5Stroke = new Paint();
		Eraser5Stroke.setStyle(Paint.Style.STROKE);
		Eraser5Stroke.setColor(Color.WHITE);
		Eraser5Stroke.setStrokeWidth(50);
	}
}
