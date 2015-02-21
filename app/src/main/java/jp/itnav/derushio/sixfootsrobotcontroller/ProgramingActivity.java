package jp.itnav.derushio.sixfootsrobotcontroller;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

import jp.itnav.derushio.bluetoothmanager.BluetoothManagedActivity;
import jp.itnav.derushio.sixfootsrobotcontroller.item.ActionItem;
import jp.itnav.derushio.sixfootsrobotcontroller.item.ITEM;
import jp.itnav.derushio.sixfootsrobotcontroller.item.ItemSelectDialog;
import jp.itnav.derushio.sixfootsrobotcontroller.item.OnItemSelectListener;


public class ProgramingActivity extends BluetoothManagedActivity implements OnItemSelectListener {

	private LinearLayout actionHolder;
	private ArrayList<ActionItem> actionItems;

	private boolean isSelected = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_programing);

		actionHolder = (LinearLayout) findViewById(R.id.action_holder);
		actionItems = new ArrayList<>();

		initProgram(null);
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

	public void initProgram(View v) {
		actionItems.clear();
		actionHolder.removeAllViews();

		ActionItem actionItem = new ActionItem(this, new ITEM(ITEM.ACTION_STOP, 0));
		actionItems.add(actionItem);

		Button button = new Button(this);
		button.setText("新規動作");
		button.setTextSize(20);
		button.setTextColor(Color.WHITE);
		button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ItemSelectDialog itemSelectDialog = new ItemSelectDialog(ProgramingActivity.this);
				itemSelectDialog.setOnItemSelectListener(ProgramingActivity.this);
				itemSelectDialog.show();
			}
		});
		button.setBackgroundResource(R.drawable.selector_button_background);

		actionHolder.addView(button);
	}

	public void runProgram(View v) {
		for (ActionItem actionItem : actionItems) {
			executeAction(actionItem.getItem());
		}
	}

	private boolean executeAction(ITEM item) {
		try {
			if (item.ACTION_NAME.equals(ITEM.ACTION_MOVE)) {
				switch (item.ACTION_OPTION) {
					case 0:
						writeMessage("so");
						break;
					case 2:
						writeMessage("fw");
						break;
					case -2:
						writeMessage("bc");
						break;
				}
			} else if (item.ACTION_NAME.equals(ITEM.ACTION_TURN)) {
				switch (item.ACTION_OPTION) {
					case 0:
						writeMessage("ss");
						break;
					case 3:
						writeMessage("le");
						break;
					case -3:
						writeMessage("ri");
						break;
				}
			} else if (item.ACTION_NAME.equals(ITEM.ACTION_UP_DOWN)) {
				switch (item.ACTION_OPTION) {
					case 0:
						writeMessage("st");
						break;
					case 4:
						writeMessage("up");
						break;
					case -4:
						writeMessage("dw");
						break;
				}
			} else if (item.ACTION_NAME.equals(ITEM.ACTION_CYCLE)) {
				switch (item.ACTION_OPTION) {
					case 0:
						writeMessage("sf");
						break;
					case 1:
						writeMessage("sh");
						break;
				}
			} else if (item.ACTION_NAME.equals(ITEM.ACTION_WAIT)) {
				sleep(item.ACTION_OPTION);
			} else if (item.ACTION_NAME.equals(ITEM.ACTION_STOP)) {
				writeMessage("sossstsf");
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public void onItemSelected(ITEM item) {
		ActionItem actionItem = new ActionItem(this, item);
		actionHolder.addView(actionItem, actionItems.size() - 1);
		actionItems.add(actionItems.size() - 1, actionItem);
	}

	public synchronized void sleep(long delayMilliSec) {
		try {
			wait(delayMilliSec);
		} catch (InterruptedException e) {
		}
	}
}
