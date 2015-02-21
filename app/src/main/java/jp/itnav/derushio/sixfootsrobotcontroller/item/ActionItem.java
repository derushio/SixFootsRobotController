package jp.itnav.derushio.sixfootsrobotcontroller.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import jp.itnav.derushio.sixfootsrobotcontroller.R;

/**
 * Created by derushio on 15/02/13.
 */
public class ActionItem extends LinearLayout {
	private Context context;
	private TextView actionNameText;
	private TextView actionOptionText;

	private ITEM item;

	public ActionItem(Context context, ITEM item) {
		super(context);

		this.context = context;
		this.item = item;

		LayoutInflater.from(context).inflate(R.layout.layout_action_item, this);

		actionNameText = (TextView) findViewById(R.id.action_name);
		actionOptionText = (TextView) findViewById(R.id.action_option);

		actionNameText.setText(item.ACTION_NAME);

		switch (item.ACTION_OPTION) {
			case 0:
				actionOptionText.setText("停止");
				break;
			case 1:
				actionOptionText.setText("正転");
				break;
			case -1:
				actionOptionText.setText("逆転");
				break;
			case 2:
				actionOptionText.setText("前進");
				break;
			case -2:
				actionOptionText.setText("後進");
				break;
			case 3:
				actionOptionText.setText("左回転");
				break;
			case -3:
				actionOptionText.setText("右回転");
				break;
			case 4:
				actionOptionText.setText("上昇");
				break;
			case -4:
				actionOptionText.setText("下降");
				break;
			default:
				actionOptionText.setText("" + item.ACTION_OPTION);
				break;
		}
	}

	public ITEM getItem() {
		return item;
	}
}
