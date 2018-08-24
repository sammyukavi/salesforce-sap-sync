package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.ArrayList;

@Generated("com.robohorse.robopojogenerator")
public class PackingListItems {
	
	@SerializedName("totalSize")
	private int totalSize;
	
	@SerializedName("records")
	private ArrayList<PackingListItem> records;
	
	@SerializedName("done")
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