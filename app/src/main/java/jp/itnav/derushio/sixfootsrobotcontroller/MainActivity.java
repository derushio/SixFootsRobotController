package jp.itnav.derushio.sixfootsrobotcontroller;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Set;

import jp.itnav.derushio.bluetoothmanager.BluetoothManagedActivity;


public class MainActivity extends BluetoothManagedActivity {
	private LinearLayout btDevicesHolder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btDevicesHolder = (LinearLayout) findViewById(R.id.paredDevicesHolder);

		Set<BluetoothDevice> paredDevices = getParedDevices();
		for (final BluetoothDevice paredDevice : paredDevices) {
			final TextView textView = new TextView(this);
			textView.setText(paredDevice.getName());
			textView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					setTargetDevice(paredDevice);
					connectDevice();
				}
			});
			btDevicesHolder.addView(textView);
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
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void startRun(View v) {
		writeMessage("f");
	}

	public void stop(View v) {
		writeMessage("o");
	}

	public void back(View v) {
		writeMessage("b");
	}

	public void left(View v) {
		writeMessage("l");
	}

	public void right(View v) {
		writeMessage("r");
	}

	public void hassya(View v) {
		writeMessage("h");
	}

	public void up(View v) {
		writeMessage("u");
	}

	public void down(View v) {
		writeMessage("d");
	}
}
