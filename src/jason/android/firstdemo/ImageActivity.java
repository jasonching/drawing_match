//package jason.android.firstdemo;
//
//import jason.android.helper.BitmapHelper;
//import android.app.Activity;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.drawable.BitmapDrawable;
//import android.os.Bundle;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.ImageView;
//
//public class ImageActivity extends Activity implements View.OnClickListener {
//	
//	private ImageView imageView;
//	
////	/** Called when the activity is first created. */
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//		
//		imageView = new ImageView(this);
//		
//		Intent intent = getIntent();
//		byte[] bitmapData = intent.getExtras().getByteArray("drawable");
//		
//		Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapData , 0, bitmapData .length);
//		
//		imageView.setImageBitmap(bitmap);
//		imageView.setClickable(true);
//		imageView.setOnClickListener(this);
//		
//		setContentView(imageView);
//	}
//	
//	public void onClick(View view) {
//		
//		Intent intent = new Intent(this, PaintingMainActivity.class);
//		intent.putExtra("drawable", BitmapHelper.convertToByteArray((BitmapDrawable)imageView.getDrawable()));
//		
//		startActivity(intent);
//	}
//}
