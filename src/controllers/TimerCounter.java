package controllers;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class TimerCounter implements Runnable {

	private int minute;
	private int second;
	public boolean isRunning;
	private Label label;
	
	public TimerCounter(Label label) {
		this.label = label;
	}
	
	@Override
	public void run() {
		minute = 0;
		second = 0;
		isRunning = true;
		while(isRunning) {
			try {
				second++;
				if(second == 59) {
					minute++;
					second = 0;
				}
				Platform.runLater(() -> {
                    label.setText(getTime());
                });
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}
	
	public String getTime() {
		if(minute < 10) {
			if(second < 10) {
				return "0" + minute + ":0" + second;
			} else {
				return "0" + minute + ":" + second;
			}
		} else {
			if(second < 10) {
				return minute + ":0" + second;
			} else {
				return minute + ":" + second;
			}
		}
	}
}
