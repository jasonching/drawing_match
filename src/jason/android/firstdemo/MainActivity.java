package jason.android.firstdemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.content.Intent;

public class MainActivity extends Activity {

	static {
		//Thread.setDefaultUncaughtExceptionHandler(new DefaultExceptionHandler());
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       requestWindowFeature(Window.FEATURE_NO_TITLE);
       this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
			
       setContentView(R.layout.activity_main);
       
       /*jason.android.helper.BitmapHelper.match(
    		   getResources().getDrawable(R.drawable.square),
    		   getResources().getDrawable(R.drawable.square_handdraw));*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void palyIt(View view) {
    	startActivity(new Intent(this, LevelsActivity.class)); 
    }
}