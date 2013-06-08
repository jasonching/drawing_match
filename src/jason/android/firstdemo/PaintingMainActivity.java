package jason.android.firstdemo;

import jason.android.firstdemo.listener.NewPaintCreatedListener;
import jason.android.helper.BitmapHelper;
import jason.android.helper.LogHelper;
import jason.android.helper.PaintCollection;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class PaintingMainActivity extends Activity implements NewPaintCreatedListener {
	
	private Bitmap sourcePainting;
	private TextView matchedText;
	private TextView machingFactor;
	private Panel drawingPanel;
	
	//	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		try {

			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

			try {
				setContentView(R.layout.activity_painting_main);
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
			setThumb();
			
			drawingPanel = (Panel)findViewById(R.id.drawPanel1);
			//NewPaintCreatedResponder responder = new NewPaintCreatedResponder();
			drawingPanel.addNewPaintCreatedListener(this);
			
			machingFactor = (TextView)findViewById(R.id.matchingFactorTextView);
			
			matchedText = (TextView)findViewById(R.id.matchedTextView1);
			matchedText.setVisibility(View.INVISIBLE);
		}
		catch(Exception ex) {
			Log.e(LogHelper.TAG, "Exception", ex);
		}
	}
	
	@Override
	public void Created(Panel panel) {
		try {
			Bitmap currentPainting = panel.getCurrentPainting();

			// match it!
			float matchNumber = BitmapHelper.match(sourcePainting, 
					//sourcePainting);
					currentPainting);

			machingFactor.setText(Float.toString(matchNumber));
			matchedText.setVisibility(matchNumber <= 420 ? View.VISIBLE : View.INVISIBLE);
		}
		catch(Throwable e) {
			Log.e(LogHelper.TAG, e.toString());
		}
	}
	
	public void clickEraser(View view) {
		drawingPanel.setCurrentPaint(PaintCollection.Eraser5Stroke);
	}
	
	public void clickPencil(View view) {
		drawingPanel.setCurrentPaint(PaintCollection.Black5Stroke);
	}

	private void setThumb() {
		try {
			byte[] bitmapData = getIntent().getExtras().getByteArray("drawable");
			sourcePainting = BitmapHelper.convertToBitmap(bitmapData);

			ImageView thumbView = (ImageView)findViewById(R.id.paintingMainThumb);
			thumbView.setImageBitmap(sourcePainting);
		}
		catch(Exception ex) {
			Log.e(LogHelper.TAG, "Exception", ex);
		}
	}

}