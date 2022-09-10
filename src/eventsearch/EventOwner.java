package eventsearch;

import java.util.ArrayList;

public abstract class EventOwner {
	
	public abstract ArrayList<ArrayList<String>> getEvents() throws Exception;

	protected abstract ArrayList<String> segment(String doc);

	
}
