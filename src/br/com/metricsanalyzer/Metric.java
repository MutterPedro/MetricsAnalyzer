package br.com.metricsanalyzer;

public class Metric {
	private String name;
	private String avg;
	private String stddev;
	private String max;
	
	
	
	public Metric(String name, String avg, String stddev, String max) {
		super();
		this.name = name;
		this.avg = avg;
		this.stddev = stddev;
		this.max = max;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAvg() {
		return avg;
	}
	public void setAvg(String avg) {
		this.avg = avg;
	}
	public String getStddev() {
		return stddev;
	}
	public void setStddev(String stddev) {
		this.stddev = stddev;
	}
	public String getMax() {
		return max;
	}
	public void setMax(String max) {
		this.max = max;
	}
	
	
	
}
