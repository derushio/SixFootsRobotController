package jp.itnav.derushio.sixfootsrobotcontroller;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import jp.itnav.derushio.bluetoothmanager.BluetoothManagedActivity;


public class ProgramingActivity extends BluetoothManagedActivity {

	private LinearLayout actionHolder;

	private boolean isSelected = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_programing);

		actionHolder = (LinearLayout)findViewById(R.id.action_holder);
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
		getMenuInflater().inflate(R.menu.menu_programing, menu);
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
				disConnectDevice();
				break;
			case R.id.action_controller_mode:
				Intent intent = new Intent(this, MainActivity.class);
				startActivity(intent);
				finish();
				break;
		}

		return super.onOptionsItemSelected(item);
	}
}
