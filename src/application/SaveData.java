package application;

import java.io.Serializable;

public class SaveData implements Serializable {
	private ScheduleData sd;

	public ScheduleData getSd() {
		return sd;
	}

	public void setSd(ScheduleData sd) {
		this.sd = sd;
	}
}
