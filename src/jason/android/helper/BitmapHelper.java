package jason.android.helper;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import com.googlecode.javacpp.Pointer;
import com.googlecode.javacv.cpp.opencv_core.CvArr;
import com.googlecode.javacv.cpp.opencv_core.CvMat;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_features2d.BFMatcher;
import com.googlecode.javacv.cpp.opencv_features2d.DMatch;
import com.googlecode.javacv.cpp.opencv_features2d.DescriptorExtractor;
import com.googlecode.javacv.cpp.opencv_features2d.FastFeatureDetector;
import com.googlecode.javacv.cpp.opencv_features2d.FeatureDetector;
import com.googlecode.javacv.cpp.opencv_features2d.KeyPoint;
import com.googlecode.javacv.cpp.opencv_features2d.KeyPointVectorVector;
import com.googlecode.javacv.cpp.opencv_features2d.FlannBasedMatcher;
import com.googlecode.javacv.cpp.opencv_features2d.DescriptorMatcher;
import com.googlecode.javacv.cpp.opencv_features2d.BriefDescriptorExtractor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;

public class BitmapHelper {

	private static FeatureDetector detector = new FastFeatureDetector(1, true);
	private static DescriptorExtractor d = new BriefDescriptorExtractor();
	private static BFMatcher matcher = new BFMatcher();
	
	public static byte[] convertToByteArray(BitmapDrawable drawable) {

		Bitmap bitmap = (Bitmap) drawable.getBitmap();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
		return stream.toByteArray();
	}

	public static Bitmap convertToBitmap(byte[] bitmapData) {
		return BitmapFactory.decodeByteArray(bitmapData, 0, bitmapData.length);
	}

	private static IplImage convertToIplImage(Bitmap bitmap) {
		IplImage image = IplImage.create(bitmap.getWidth(), bitmap.getHeight(),
				IPL_DEPTH_8U, 4);
		bitmap.copyPixelsToBuffer(image.getByteBuffer());

		return image;
	}

	public static float match(Drawable drawableA, Drawable drawableB) {
		return match(((BitmapDrawable) drawableA).getBitmap(),
				((BitmapDrawable) drawableB).getBitmap());
	}

	public static float match(Bitmap bitmapA, Bitmap bitmapB) {

//		detector = new FastFeatureDetector(1, true);
//		d = new BriefDescriptorExtractor();
//		matcher = new BFMatcher();

		IplImage imageA = convertToIplImage(bitmapA);
		IplImage imageB = convertToIplImage(bitmapB);
		// detector
		KeyPoint vectorA = new KeyPoint();
		KeyPoint vectorB = new KeyPoint();

		detector.detect(imageA, vectorA, null);
		detector.detect(imageB, vectorB, null);

		// descriptor
		CvMat descriptA = new CvMat(null);
		CvMat descriptB = new CvMat(null);

		d.compute(imageA, vectorA, descriptA);
		d.compute(imageB, vectorB, descriptB);
		
		// matcher
		DMatch matches = new DMatch();
		matcher.match(descriptA, descriptB, matches, null);

		System.out.println(String.format("Number of Key Points for Source: %s" , vectorA.capacity()));
		System.out.println(String.format("Number of Key Points for Target: %s" , vectorB.capacity()));
		System.out.println(String.format("Number of Matches: %s" , matches.capacity()));

		ArrayList<Float> distances = new ArrayList<Float>();

		for(int i=0; i<matches.capacity(); i++) {
			distances.add(matches.position(i).distance());
		}

		return getAverage(distances);
	}
	
	private static float getAverage(ArrayList<Float> list) {
		float sum = 0;

		for(float f : list) {
			sum += f;
		}

		return sum / list.size();
	}
}
