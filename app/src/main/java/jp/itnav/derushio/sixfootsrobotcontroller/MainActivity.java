package jp.itnav.derushio.sixfootsrobotcontroller;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import jp.itnav.derushio.bluetoothmanager.BluetoothManagedActivity;


public class MainActivity extends BluetoothManagedActivity {

	private OnButtonTouchListener onButtonTouchListener = new OnButtonTouchListener();

	private Button buttonForward;
	private Button buttonBack;
	private Button buttonLeft;
	private Button buttonRight;
	private Button buttonUp;
	private Button buttonDown;
	private Button buttonLaunch;
	private Button buttonStop;

	private boolean isSelected = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		buttonForward = (Button) findViewById(R.id.buttonForward);
		buttonBack = (Button) findViewById(R.id.buttonBack);
		buttonLeft = (Button) findViewById(R.id.buttonLeft);
		buttonRight = (Button) findViewById(R.id.buttonRight);
		buttonUp = (Button) findViewById(R.id.buttonUp);
		buttonDown = (Button) findViewById(R.id.buttonDown);
		buttonLaunch = (Button) findViewById(R.id.buttonLaunch);
		buttonStop = (Button) findViewById(R.id.buttonStop);

		buttonForward.setOnTouchListener(onButtonTouchListener);
		buttonBack.setOnTouchListener(onButtonTouchListener);
		buttonLeft.setOnTouchListener(onButtonTouchListener);
		buttonRight.setOnTouchListener(onButtonTouchListener);
		buttonUp.setOnTouchListener(onButtonTouchListener);
		buttonDown.setOnTouchListener(onButtonTouchListener);
		buttonLaunch.setOnTouchListener(onButtonTouchListener);
		buttonStop.setOnTouchListener(onButtonTouchListener);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

		if (hasFocus && !isSelected) {
			showDeviceSelectDialog();
			isSelected = true;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
			case R.id.action_select_device:
				showDeviceSelectDialog();
				break;
			case R.id.action_reconnect_device:
				reConnectDevice();
				break;
			case R.id.action_disconnect_device:
				disConnectDevices();
				break;
			case R.id.action_programing_mode:
				break;
			case R.id.action_settings:
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	private class OnButtonTouchListener implements View.OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					switch (v.getId()) {
						case R.id.buttonForward:
							writeMessage("fw");
							break;
						case R.id.buttonBack:
							writeMessage("bc");
							break;
						case R.id.buttonLeft:
							writeMessage("le");
							break;
						case R.id.buttonRight:
							writeMessage("ri");
							break;
						case R.id.buttonUp:
							writeMessage("up");
							break;
						case R.id.buttonDown:
							writeMessage("dw");
							break;
						case R.id.buttonLaunch:
							writeMessage("sh");
							break;
						case R.id.buttonStop:
							break;
					}
					break;
				case MotionEvent.ACTION_UP:
					switch (v.getId()) {
						case R.id.buttonForward:
						case R.id.buttonBack:
							writeMessage("so");
							break;
						case R.id.buttonLeft:
						case R.id.buttonRight:
							writeMessage("ss");
							break;
						case R.id.buttonUp:
						case R.id.buttonDown:
							writeMessage("st");
							break;
						case R.id.buttonLaunch:
							writeMessage("sf");
							break;
						case R.id.buttonStop:
							break;
					}
					break;
			}
			return false;
		}
	}
}
