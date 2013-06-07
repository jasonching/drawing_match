package jason.android.firstdemo;

import jason.android.helper.LogHelper;

import java.lang.Thread.UncaughtExceptionHandler;

import android.util.Log;

public class DefaultExceptionHandler implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		Log.e(LogHelper.TAG, "Exception", ex);
	}
}
