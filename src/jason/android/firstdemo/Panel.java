package jason.android.firstdemo;

import jason.android.firstdemo.listener.NewPaintCreatedListener;

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

	private Paint paint;
	private List<Path> _graphics = new ArrayList<Path>();
	private Path path;
	private List<NewPaintCreatedListener> newPaintCreatedListeners = new ArrayList<NewPaintCreatedListener>();
	
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

	public void addNewPaintCreatedListener(NewPaintCreatedListener toAdd) {
		newPaintCreatedListeners.add(toAdd);
	}
	
	private void init() {
		getHolder().addCallback(this);

		paint = new Paint();
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(3);

		// ondraw won't be called if this is not set to false
		this.setWillNotDraw(false);
	}

	private Bitmap currentPainting;
	
	public Bitmap getCurrentPainting() {
		return currentPainting;
	}
	
	@SuppressLint("DrawAllocation")
	@Override
	public void onDraw(Canvas canvas) {

		// draw background
		canvas.drawColor(Color.WHITE);

		synchronized(_graphics) {
			for(Path path : _graphics) {
				canvas.drawPath(path, paint);
			}
		}
		
		currentPainting = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Config.ARGB_8888);
		canvas.setBitmap(currentPainting);
		
		for (NewPaintCreatedListener l : newPaintCreatedListeners) {
			l.Created(this);
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
				_graphics.add(path);
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
