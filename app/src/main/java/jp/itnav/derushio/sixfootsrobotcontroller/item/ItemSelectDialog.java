package jp.itnav.derushio.sixfootsrobotcontroller.item;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import jp.itnav.derushio.sixfootsrobotcontroller.R;

/**
 * Created by derushio on 15/02/16.
 */
public class ItemSelectDialog extends Dialog {

	OnItemSelectListener onItemSelectListener = new OnItemSelectListener() {
		@Override
		public void onItemSelected(ITEM item) {

		}
	};

	private Spinner itemMain;
	private Spinner itemOption;
	private Button buttonOK;

	public ItemSelectDialog(final Context context) {
		super(context);
		setContentView(R.layout.dialog_action_select);

		itemMain = (Spinner) findViewById(R.id.item_main);
		itemOption = (Spinner) findViewById(R.id.item_option);

		ArrayAdapter<String> adapterMain = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item);
		adapterMain.add(ITEM.ACTION_STOP);
		adapterMain.add(ITEM.ACTION_MOVE);
		adapterMain.add(ITEM.ACTION_TURN);
		adapterMain.add(ITEM.ACTION_UP_DOWN);
		adapterMain.add(ITEM.ACTION_CYCLE);
		adapterMain.add(ITEM.ACTION_WAIT);

		itemMain.setAdapter(adapterMain);

		itemMain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				TextView textView = (TextView) view;
				String mainItem = textView.getText().toString();
				ArrayAdapter<String> adapterOption = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item);

				if (mainItem.equals(ITEM.ACTION_STOP)) {
					adapterOption.add("停止");
				} else if (mainItem.equals(ITEM.ACTION_MOVE) || mainItem.equals(ITEM.ACTION_TURN) || mainItem.equals(ITEM.ACTION_UP_DOWN)) {
					adapterOption.add("停止");
					adapterOption.add("正転");
					adapterOption.add("逆転");
				} else if (mainItem.equals(ITEM.ACTION_CYCLE)) {
					adapterOption.add("停止");
					adapterOption.add("正転");
				} else if (mainItem.equals(ITEM.ACTION_WAIT)) {
					adapterOption.add("500ms");
					adapterOption.add("1000ms");
					adapterOption.add("3000ms");
					adapterOption.add("5000ms");
				}

				itemOption.setAdapter(adapterOption);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				onItemSelectListener.onItemSelected(new ITEM(getItemMain(), getItemOption()));
				dismiss();
			}
		});

		buttonOK = (Button) findViewById(R.id.button_ok);
		buttonOK.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onItemSelectListener.onItemSelected(new ITEM(getItemMain(), getItemOption()));
			}
		});
	}

	public void setOnItemSelectListener(OnItemSelectListener onItemSelectListener) {
		this.onItemSelectListener = onItemSelectListener;
	}

	public String getItemMain() {
		return (String) itemMain.getSelectedItem();
	}

	public int getItemOption() {
		String selectedItem = (String) itemOption.getSelectedItem();

		if (selectedItem.equals("停止")) {
			return 0;
		} else if (selectedItem.equals("正転")) {
			return 1;
		} else if (selectedItem.equals("逆転")) {
			return -1;
		} else if (selectedItem.equals("500ms")) {
			return 500;
		} else if (selectedItem.equals("1000ms")) {
			return 1000;
		} else if (selectedItem.equals("3000ms")) {
			return 3000;
		} else if (selectedItem.equals("5000ms")) {
			return 5000;
		}

		return 0;
	}
}
