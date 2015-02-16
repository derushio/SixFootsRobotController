package jp.itnav.derushio.sixfootsrobotcontroller.item;

/**
 * Created by derushio on 15/02/13.
 */

public class ITEM {
	public static final String ACTION_STOP = "stop";
	public static final String ACTION_MOVE = "move";
	public static final String ACTION_TURN = "turn";
	public static final String ACTION_UP_DOWN = "up_down";
	public static final String ACTION_CYCLE = "cycle";
	public static final String ACTION_WAIT = "wait";

	public String ACTION_NAME;
	public int ACTION_OPTION;

	public ITEM(String ACTION_NAME, int ACTION_OPTION) {
		this.ACTION_NAME = ACTION_NAME;
		this.ACTION_OPTION = ACTION_OPTION;
	}
}
