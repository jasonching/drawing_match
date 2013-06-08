package jason.android.firstdemo;

import jason.android.firstdemo.listener.NewPaintCreatedListener;
import jason.android.helper.PaintCollection;
import jason.android.helper.PaintPath;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Panel extends SurfaceView implements SurfaceHolder.Callback {

	private Paint currentPaint;
	private List<PaintPath> _graphics = new ArrayList<PaintPath>();
	private Path path;
	private List<NewPaintCreatedListener> newPaintCreatedListeners = new ArrayList<NewPaintCreatedListener>();
	private Bitmap currentPainting;
	
	public Panel(Context context) {
		super(context);
		init();
	}

	public Panel(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public Panel(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public Bitmap getCurrentPainting() {
		return currentPainting;
	}
	
	public void addNewPaintCreatedListener(NewPaintCreatedListener toAdd) {
		newPaintCreatedListeners.add(toAdd);
	}
	
	public void setCurrentPaint(Paint paint) {
		currentPaint = paint;
	}
	
	private void init() {
		getHolder().addCallback(this);

		currentPaint = PaintCollection.Black5Stroke;
		/*paint = new Paint();
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(3);*/

		// ondraw won't be called if this is not set to false
		this.setWillNotDraw(false);
	}

	@SuppressLint("DrawAllocation")
	@Override
	public void onDraw(Canvas canvas) {

		drawCanvas(canvas);
				
		//if (_graphics.size() > 3) {
		setCurrentPainting(canvas);
		raiseNewPaintCreated();
		//}
	}
	
	private void setCurrentPainting(Canvas canvas) {
		currentPainting = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Config.RGB_565);
		//currentPainting.setDensity(canvas.getDensity());
		Canvas newCanvas = new Canvas(currentPainting);
		drawCanvas(newCanvas);
	}

	private void raiseNewPaintCreated() {
		for (NewPaintCreatedListener l : newPaintCreatedListeners) {
			l.Created(this);
		}
	}

	private void drawCanvas(Canvas canvas) {
		// draw background
		canvas.drawColor(Color.WHITE);

		synchronized(_graphics) {
			for(PaintPath path : _graphics) {
				canvas.drawPath(path.getPath(), path.getPaint());
			}
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		/*		if (!_thread.isAlive()) {
			_thread = new DrawingThread(getHolder(), this);
			_thread.setRunning(true);
			_thread.start();
		}*/
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		/*		if (_thread.isAlive()) {
			_thread.setRunning(false);
		}*/
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		float x = event.getX();
		float y = event.getY();

		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			path = new Path();
			path.moveTo(x, y);
			path.lineTo(x, y);

			synchronized(_graphics) {
				_graphics.add(new PaintPath(path, currentPaint));
			}
		}
		else if (event.getAction() == MotionEvent.ACTION_UP) {
			path.lineTo(x, y);
		}
		else if (event.getAction() == MotionEvent.ACTION_MOVE) {
			path.lineTo(x, y);
		}

		invalidate();

		return true;
	}
}
