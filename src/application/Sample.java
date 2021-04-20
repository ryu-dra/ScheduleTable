package application;

import java.time.LocalTime;

public class Sample {
	public static void main(String[] args) {
		ScheduleData sd = new ScheduleData("部活",LocalTime.of(12,30),LocalTime.of(16,0),"だるい");
		System.out.println(sd.titleProperty());
	}
}
