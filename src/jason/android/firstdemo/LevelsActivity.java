package jason.android.firstdemo;

import jason.android.helper.BitmapHelper;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class LevelsActivity extends Activity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
        setContentView(R.layout.activity_levels);        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void clickLevel(View view) {
    	
    	ImageView imgView = (ImageView)view;
    	
//    	Bitmap bitmap = (Bitmap)((BitmapDrawable)imgView.getDrawable()).getBitmap();
//    	ByteArrayOutputStream stream = new ByteArrayOutputStream();
//    	bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//    	byte[] bitmapdata = stream.toByteArray();
    	
//    	Intent intent = new Intent(this, ImageActivity.class);
//    	intent.putExtra("drawable", bitmapdata);
//    	
//    	startActivity(intent);
//    	
    	Intent intent = new Intent(this, PaintingMainActivity.class);
		intent.putExtra("drawable", BitmapHelper.convertToByteArray((BitmapDrawable)imgView.getDrawable()));
		
		startActivity(intent);
    }
    
	public static byte[] convertToByteArray(BitmapDrawable drawable) {

		Bitmap bitmap = (Bitmap) drawable.getBitmap();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
		return stream.toByteArray();
	}
}