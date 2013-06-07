package jason.android.firstdemo.listener;

import android.graphics.Bitmap;
import jason.android.firstdemo.Panel;

public class NewPaintCreatedResponder implements NewPaintCreatedListener {
	@Override
	public void Created(Panel panel) {
		Bitmap currentPainting = panel.getCurrentPainting();
		
		// match it!
	}
}