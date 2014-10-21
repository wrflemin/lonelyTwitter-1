package ca.ualberta.cs.lonelytwitter.test;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;
import ca.ualberta.cs.lonelytwitter.IntentReaderActivity;


public class IntentReaderActivityTest extends
		ActivityInstrumentationTestCase2<IntentReaderActivity>
{
	public IntentReaderActivityTest(){
		super(IntentReaderActivity.class);
	}
	
	public void testSendText(){
		Intent intent = new Intent();
		intent.putExtra(IntentReaderActivity.TEXT_KEY, "test string ");
		setActivityIntent(intent);
		IntentReaderActivity activity = (IntentReaderActivity) getActivity();
		assertEquals("test string ", activity.getText());
	}
	
	public void testDoubleText(){
		Intent intent = new Intent();
		intent.putExtra(IntentReaderActivity.TEXT_KEY, "test string ");
		intent.putExtra(IntentReaderActivity.TRANSFORM_KEY, IntentReaderActivity.DOUBLE);
		setActivityIntent(intent);
		IntentReaderActivity activity = (IntentReaderActivity) getActivity();
		assertEquals("test string test string ", activity.getText());
	}
	public void testDisplayText(){
		Intent intent = new Intent();
		intent.putExtra(IntentReaderActivity.TEXT_KEY, "test string ");
		setActivityIntent(intent);
		IntentReaderActivity activity = (IntentReaderActivity) getActivity();
		TextView textView = (TextView) activity.findViewById(ca.ualberta.cs.lonelytwitter.R.id.intentText);
		assertEquals("test string ", textView.getText()); //inquiring with the actual widget
	}
	public void testDisplayTextDirect() throws Throwable{
		IntentReaderActivity activity = (IntentReaderActivity) getActivity();
		TextView textView = (TextView) activity.findViewById(ca.ualberta.cs.lonelytwitter.R.id.intentText);
		runTestOnUiThread(new Runnable()
		{
			@Override
			public void run()
			{
				IntentReaderActivity activity = (IntentReaderActivity) getActivity();
				TextView textView = (TextView) activity.findViewById(ca.ualberta.cs.lonelytwitter.R.id.intentText);
				textView.setText("test string ");
			}
		});
		
		assertEquals("test string ", textView.getText()); //Test code is in a different thread. Fails initially
	}
	public void testReverseText(){
		Intent intent = new Intent();
		intent.putExtra(IntentReaderActivity.TEXT_KEY, "test string ");
		intent.putExtra(IntentReaderActivity.TRANSFORM_KEY, IntentReaderActivity.REVERSE);
		setActivityIntent(intent);
		IntentReaderActivity activity = (IntentReaderActivity) getActivity();
		assertEquals(" gnirts tset", activity.getText());
	}
	public void testDefaultMessagEmptyInetText(){
		Intent intent = new Intent();
		setActivityIntent(intent);
		IntentReaderActivity activity = (IntentReaderActivity) getActivity();
		assertEquals(null, activity.getText());
	}

}
