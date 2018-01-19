package ua.kiev.shuriken.blueprint.entity.production;

import ua.kiev.shuriken.blueprint.Condition;
import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class OffshorePump extends Entity {
	
	public static final int DIRECTION_NORTH = 4;
	public static final int DIRECTION_EAST = 6;
	public static final int DIRECTION_SOUTH = 0;
	public static final int DIRECTION_WEST = 2;
	

	public OffshorePump(float x, float y, int direction) {
		super(x, y, direction);
	}

	public OffshorePump(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.OFFSHORE_PUMP;
	}
	
	
	private Condition enableCondition;
	
	/**
	 * Gets enable/disable condition. May return "null" if this function is disabled.
	 * @return enable condition or "null"
	 */
	public Condition getEnableCondition() {
		return enableCondition;
	}
	
	/**
	 * Sets enable/disable condition or disables this function.
	 * @param condition enable condition or "null" to disable it
	 */
	public void setEnableCondition(Condition condition) {
		this.enableCondition = condition;
	}
	
	
	@Override
	protected String setupToString() {
		if(hasConnections() && enableCondition != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("\"control_behavior\":{");
			sb.append(enableCondition.toString());
			sb.append('}');
			return sb.toString();
		} else {
			return null;
		}
	}

}
