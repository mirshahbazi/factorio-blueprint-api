package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Condition;
import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class TrainStop extends Entity {
	
	public static final int DIRECTION_NORTH = 0;
	public static final int DIRECTION_EAST = 2;
	public static final int DIRECTION_SOUTH = 4;
	public static final int DIRECTION_WEST = 6;
	

	public TrainStop(float x, float y, int direction) {
		super(x, y, direction);
	}
	
	public TrainStop(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.TRAIN_STOP;
	}
	
	
	private Condition enableCondition;
	
	/**
	 * Gets station's enable condition. May return "null" if "enable/disable" function is disabled
	 * @return condition or "null" if it's disabled
	 */
	public Condition getEnableCondition() {
		return enableCondition;
	}
	
	/**
	 * Sets station's enable condition or disables it.
	 * @param condition condition or "null" to disable it.
	 */
	public void setEnableCondition(Condition condition) {
		enableCondition = condition;
	}
	
	
	private boolean sendSignalsToTrain = true;
	
	/**
	 * Checks if "Send to train" flag is on
	 * @return state of "Send to train" flag.
	 */
	public boolean isSendingSignalsToTrain() {
		return sendSignalsToTrain;
	}
	
	/**
	 * Sets "Send to train" flag.
	 * @param send "true" to enable and "false" to disable
	 */
	public void setSendingSignalsToTrain(boolean send) {
		sendSignalsToTrain = send;
	}
	
	
	private boolean readTrainsContents = false;
	
	/**
	 * Checks if "Read train contents" flag is on
	 * @return state of "Read train contents" flag.
	 */
	public boolean isReadingTrainsContents() {
		return readTrainsContents;
	}
	
	/**
	 * Sets "Read train contents" flag.
	 * @param send "true" to enable and "false" to disable
	 */
	public void setReadingTrainContents(boolean read) {
		readTrainsContents = read;
	}
	
	
	private String trainStoppedSignal;
	
	/**
	 * Gets output signal that will be used for stopped train
	 * @return output signal or "null" if "Read stopped train" flag is off
	 */
	public String getTrainStoppedSignal() {
		return trainStoppedSignal;
	}
	
	/**
	 * Sets output signal that will be used for stopped train or disable this function
	 * @param signal output signal or "null" to disable "Read stopped train" flag
	 */
	public void setTrainStoppedSignal(String signal) {
		trainStoppedSignal = signal;
	}
	
	
	@Override
	protected String setupToString() {
		if(!hasConnections()) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		
		sb.append("\"control_behavior\":{");
		boolean firstPartPassed = false;
		
		if(enableCondition != null) {
			sb.append(enableCondition.toString());
			sb.append(",\"circuit_enable_disable\":true");
			firstPartPassed = true;
		}
		
		if(!sendSignalsToTrain) {
			if(firstPartPassed) {
				sb.append(',');
			} else {
				firstPartPassed = true;
			}
			sb.append("\"send_to_train\":false");
		}
		
		if(readTrainsContents) {
			if(firstPartPassed) {
				sb.append(',');
			} else {
				firstPartPassed = true;
			}
			sb.append("\"read_from_train\":true");
		}
		
		if(trainStoppedSignal != null) {
			if(firstPartPassed) {
				sb.append(',');
			} else {
				firstPartPassed = true;
			}
			sb.append("\"read_stopped_train\":true,");
			sb.append("\"train_stopped_signal\":{\"type\":\"");
			sb.append(Signals.getType(trainStoppedSignal));
			sb.append("\",\"name\":\"");
			sb.append(trainStoppedSignal);
			sb.append("\"}");
		}
		
		sb.append('}');
		if(sb.length() < 25) {
			return null;
		} else {
			return sb.toString();
		}
	}
	
	
	private String stationName;
	
	public String getStationName() {
		return stationName;
	}
	
	public void setStationName(String name) {
		this.stationName = name;
	}
	
	
	@Override
	protected String advancedSetupToString() {
		if(stationName == null) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("\"station\":\"");
		sb.append(stationName);
		sb.append("\"");
		return sb.toString();
	}
	
}
