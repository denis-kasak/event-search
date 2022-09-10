package eventsearch;

import java.util.ArrayList;

public abstract class EventOwner {
	
	public abstract ArrayList<ArrayList<String>> getEvents(ArrayList<String> segments) throws Exception;

	protected abstract ArrayList<String> segment(String doc);

	
}
