package jason.android.firstdemo;

import jason.android.helper.BitmapHelper;
import jason.android.helper.LogHelper;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class PaintingMainActivity extends Activity {
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
			
			Panel panel = (Panel)findViewById(R.id.drawPanel1);
		}
		catch(Exception ex) {
			Log.e(LogHelper.TAG, "Exception", ex);
		}
	}

	private void setThumb() {
		try {
			byte[] bitmapData = getIntent().getExtras().getByteArray("drawable");
			Bitmap thumb = BitmapHelper.convertToBitmap(bitmapData);

			ImageView thumbView = (ImageView)findViewById(R.id.paintingMainThumb);
			thumbView.setImageBitmap(thumb);
		}
		catch(Exception ex) {
			Log.e(LogHelper.TAG, "Exception", ex);
		}
	}

}