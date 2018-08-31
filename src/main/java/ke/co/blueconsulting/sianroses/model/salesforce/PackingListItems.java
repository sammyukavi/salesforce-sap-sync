package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class PackingListItems implements Serializable {
	
	private static final long serialVersionUID = 986369289632L;
	
	@SerializedName("totalSize")
	@Expose
	private int totalSize;
	
	@SerializedName("records")
	@Expose
	private ArrayList<PackingListItem> records;
	
	@SerializedName("done")
	@Expose
	private boolean done;
	
	public int getTotalSize() {
		return totalSize;
	}
	
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	
	public ArrayList<PackingListItem> getRecords() {
		return records;
	}
	
	public void setRecords(ArrayList<PackingListItem> records) {
		this.records = records;
	}
	
	public boolean isDone() {
		return done;
	}
	
	public void setDone(boolean done) {
		this.done = done;
	}
}