/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 Gregory
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package org.gz.gwt.mousemanager.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.ContextMenuHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasContextMenuHandlers;
import com.google.gwt.event.dom.client.HasDoubleClickHandlers;
import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseOutHandlers;
import com.google.gwt.event.dom.client.HasMouseOverHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.event.dom.client.HasMouseWheelHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.DomEvent.Type;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Timer;
import com.google.web.bindery.event.shared.Event;

/**
 * Manager responsible for adding handlers to event sources and firing those
 * handlers on passed in events after some analyzing them. 
 * Browser behaviors is not the same when it comes to fire mouse and click events.
 * This manager fires events in the same order cross browsers while browsers tend to fire events in different orders
 * 
 * @author gregory.zussa
 */
public class MouseHandlerManager extends HandlerManager{
	//Event list
	private List<String> eventList = new ArrayList<String>();
	private int button = 0;
	//Timer
	private double TIMEOUT = 0.3; 
	private Timer timeoutTimer;
    
	private Element target;
    //Events cache
	private org.gz.gwt.mousemanager.client.event.DoubleClickEvent doubleClickEvent;
	private org.gz.gwt.mousemanager.client.event.ClickEvent clickEvent;
	private org.gz.gwt.mousemanager.client.event.ContextMenuEvent contextMenuEvent;
	private org.gz.gwt.mousemanager.client.event.MouseUpEvent mouseUpEvent;
	private org.gz.gwt.mousemanager.client.event.MouseDownEvent mouseDownEvent;
	private org.gz.gwt.mousemanager.client.event.MouseMoveEvent mouseMoveEvent;
	private org.gz.gwt.mousemanager.client.event.MouseOutEvent mouseOutEvent;
	private org.gz.gwt.mousemanager.client.event.MouseOverEvent mouseOverEvent;
	private org.gz.gwt.mousemanager.client.event.MouseWheelEvent mouseWheelEvent;
    
    //Mouse State
	private boolean isMouseDown = false;
    
    /**
     * Creates a mouse event handler manager with the given source, specifying the order in
     * which handlers are fired.
     * 
     * @param source the event source
     * @param fireInReverseOrder true to fire handlers in reverse order
     */
    public MouseHandlerManager(Object source, boolean fireInReverseOrder) {
		super(source, fireInReverseOrder);	
	}

    /**
     * Creates a mouse event handler manager with a source to be set on all events fired via
     * <a href="http://google-web-toolkit.googlecode.com/svn/javadoc/2.0/com/google/gwt/event/shared/HandlerManager.html#fireEvent(com.google.gwt.event.shared.GwtEvent)">HandlerManager#fireEvent</a>. Handlers will be fired in the order that they
     * are added.
     * 
     * @param source the default event source
     */
	public MouseHandlerManager(Object source) {
		super(source);
	}
	
	/**
	 * Set target that will be used to get relative mouse position from native mouse events
	 * @see <a href="http://google-web-toolkit.googlecode.com/svn/javadoc/2.0/com/google/gwt/event/dom/client/MouseEvent.html#getRelativeX(com.google.gwt.dom.client.Element)">MouseEvent#getRelativeX</a>
	 * @see <a href="http://google-web-toolkit.googlecode.com/svn/javadoc/2.0/com/google/gwt/event/dom/client/MouseEvent.html#getRelativeY(com.google.gwt.dom.client.Element)">MouseEvent#getRelativeY</a>
	 * @param target the dom element
	 */
    public void setTarget(Element target){
    	this.target = target;
    }

    /**
     * Set source object that will be set on all events fired
     * @param source the default event source
     */
    public void setObject(Object source){
    	//Add DoubleClick listener
    	if(source instanceof HasDoubleClickHandlers){
    		((HasDoubleClickHandlers) source).addDoubleClickHandler(new DoubleClickHandler() {
//    			@Override
    			public void onDoubleClick(DoubleClickEvent event) {
    				analyseEvents(event);
    			}
    		});
    	}
    	//Add Click listener
    	if(source instanceof HasClickHandlers){
    		((HasClickHandlers) source).addClickHandler(new ClickHandler() {
//    			@Override
    			public void onClick(ClickEvent event) {
    				analyseEvents(event);
    			}
    		});
    	}
    	//Add MouseUp listener
    	if(source instanceof HasMouseUpHandlers){
    		((HasMouseUpHandlers) source).addMouseUpHandler(new MouseUpHandler() {
//				@Override
				public void onMouseUp(MouseUpEvent event) {
					analyseEvents(event);
				}
    		});
    	}
    	//Add MouseDown listener
    	if(source instanceof HasMouseDownHandlers){
    		((HasMouseDownHandlers) source).addMouseDownHandler(new MouseDownHandler() {
//    			@Override
    			public void onMouseDown(MouseDownEvent event) {
    				analyseEvents(event);
    			}
    		});
    	}
    	//Add MouseMove listener
    	if(source instanceof HasMouseMoveHandlers){
    		((HasMouseMoveHandlers) source).addMouseMoveHandler(new MouseMoveHandler() {
//    			@Override
    			public void onMouseMove(MouseMoveEvent event) {
    				analyseEvents(event);
    			}
    		});
    	}	
    	//Add MouseOver listener
    	if(source instanceof HasMouseOverHandlers){
    		((HasMouseOverHandlers) source).addMouseOverHandler(new MouseOverHandler() {
//    			@Override
    			public void onMouseOver(MouseOverEvent event) {
    				analyseEvents(event);
    			}
    		});
    	}	
    	//Add MouseOut listener
    	if(source instanceof HasMouseOutHandlers){
    		((HasMouseOutHandlers) source).addMouseOutHandler(new MouseOutHandler() {
//    			@Override
    			public void onMouseOut(MouseOutEvent event) {
    				analyseEvents(event);
    			}
    		});
    	}	
    	//Add MouseWheel listener
    	if(source instanceof HasMouseWheelHandlers){
    		((HasMouseWheelHandlers) source).addMouseWheelHandler(new MouseWheelHandler() {
//    			@Override
    			public void onMouseWheel(MouseWheelEvent event) {
    				analyseEvents(event);
    			}
    		});
    	}
    	//Add ContextMenu listener
    	if(source instanceof HasContextMenuHandlers){
    		((HasContextMenuHandlers) source).addContextMenuHandler(new ContextMenuHandler() {
//    			@Override
    			public void onContextMenu(ContextMenuEvent event) {
    				analyseEvents(event);
    			}
    		});
    	}
    }
    
    /**
     * Events analyzer that figure out what event should be fired
     * @param event
     */
	private void analyseEvents(Event<?> event){  
        //If function is called by timer (with null parameters) we re-initialize the event list and we don't fire any event
        if(event == null){
        	analyseEvents();
        	return;
        }
        
        //We get the button value using MouseDownEvent since all browsers return a value for MouseDownEvent
        //TODO UpdateButtonValue();
        if(event.getAssociatedType().equals(MouseDownEvent.getType())){
        	button = ((MouseDownEvent)event).getNativeButton();
        }
        
        //Otherwise we add event to the event list and we try to determine the user action
    	eventList.add(convertNativeEventToStringIdentifier(event));
    	
    	//Check mouse state
    	checkMouseState(event);
    	
    	//We keep the last event of each type that will be fire later by the timer.
    	updateEventCaches(event);
		//If the event is a MouseMoveEvent, we fire a MouseMoveEvent
		if(eventList.size() == 1 && MouseMoveEvent.getType().getName().equals(convertNativeEventToStringIdentifier(event))){
			eventList.remove(eventList.size()-1);
			//Fire mouse move event			
			fireEvent(mouseMoveEvent);
			mouseMoveEvent = null;
		}else
		if(eventList.size() > 1 && MouseMoveEvent.getType().getName().equals(convertNativeEventToStringIdentifier(event)) &&
				eventList.get(eventList.size() - 2).equals(MouseMoveEvent.getType().getName())){
			eventList.remove(eventList.size()-1);
			eventList.remove(eventList.size()-1);
			//Fire mouse move event			
			fireEvent(mouseMoveEvent);
			mouseMoveEvent = null;
		}else
		//If the event is a MouseOverEvent, we fire a MouseOverEvent
		if(MouseOverEvent.getType().getName().equals(convertNativeEventToStringIdentifier(event))){
			eventList.remove(eventList.size()-1);
			//Fire mouse over event			
			fireEvent(mouseOverEvent);
			mouseOverEvent = null;
		}else
		//If the event is a MouseOutEvent, we fire a MouseOutEvent
		if(MouseOutEvent.getType().getName().equals(convertNativeEventToStringIdentifier(event))){
			eventList.remove(eventList.size()-1);
			//Fire mouse out event			
			fireEvent(mouseOutEvent);
			mouseOutEvent = null;
		}else
		//If the event is a MouseWheelEvent, we fire a MouseWheelEvent
		if(MouseWheelEvent.getType().getName().equals(convertNativeEventToStringIdentifier(event))){
			eventList.remove(eventList.size()-1);
			//Fire mouse wheel event			
			fireEvent(mouseWheelEvent);
			mouseWheelEvent = null;
		}  
		
		//Note: We don't fire a event if we get a MouseMoveEvent because some browser (DOM Level 3) fire a MouseMoveEvent 
		//in the middle of a DoubleCLickEvent
		//See http://www.w3.org/TR/DOM-Level-3-Events/#events-mouseevent-event-order
		//If we get two consecutive MouseMoveEvent, It means we need to do some analysis right away
		if((eventList.size() >= 2 && eventList.get(eventList.size()-1).equals(MouseMoveEvent.getType().getName()) && 
				eventList.get(eventList.size()-2).equals(MouseMoveEvent.getType().getName()))){
			analyseEvents();
		}else
		//If we have a Double click, It means we need to do some analysis right away
		if(contains(eventList, DoubleClickEvent.getType())){			
			analyseEvents();
		}else
		//If the list doesn't contain any of the following events (Click, DblClick, MouseUp, MouseDown, ContextMenu), 
		//we reset the list with the latest event
		if(!contains(eventList, MouseUpEvent.getType()) &&
				!contains(eventList, MouseDownEvent.getType()) &&
				!contains(eventList, ClickEvent.getType()) &&
				!contains(eventList, DoubleClickEvent.getType()) &&
				!contains(eventList, ContextMenuEvent.getType())){
			//This code should never be executed
			eventList.clear();
			cleanEventCaches();
			updateEventCaches(event);
			eventList.add(convertNativeEventToStringIdentifier(event));
		}else{
			//We initialize the timer for further analysis on what is left in the list
			if(timeoutTimer == null){
				initializeTimer();
			}	
		}
    }
	
	/**
	 * Events Analyzer method used when no events has been fired 
	 */
	private void analyseEvents() {
		cancelTimer();
		//If we get a double click we fire a double click because it's one of the most complex event to catch by browsers
    	if(eventList.size() >= 1 && contains(eventList, DoubleClickEvent.getType())){
    		eventList.clear();
    		//Fire double click event
    		fireEvent(doubleClickEvent);
    		cleanEventCaches();
    	}else
    		
    	//If list contains a MOUSE DOWN but no MOUSE UP, we fire a mouse DOWN event
    	//TODO Take care of Opera < 8.0 for Click and Double Click Events. 
    	if(eventList.size() >= 1 && contains(eventList, MouseDownEvent.getType()) && !contains(eventList, MouseUpEvent.getType())){
    		eventList.clear();
        	fireEvent(mouseDownEvent);
        	cleanEventCaches();
    	}else
    		
    	//If list contains a MOUSE UP but no MOUSE DOWN, we fire a mouse UP event
    	if(eventList.size() >= 1 && !contains(eventList, MouseDownEvent.getType()) && contains(eventList, MouseUpEvent.getType())){
    		eventList.clear();
    		//Fire mouse up event
    		fireEvent(mouseUpEvent); 
    		cleanEventCaches();
    	}else{
    		//First we check for DOUBLE CLICK event
    		if(checkDoucleClickEventSequencesMatch(eventList)){
    			eventList.clear();
        		//Fire double click event
    			if(doubleClickEvent != null){ 
    				fireEvent(doubleClickEvent);  
    			}else{
    				fireEvent(new org.gz.gwt.mousemanager.client.event.DoubleClickEvent(mouseUpEvent.getClientX(),
    								mouseUpEvent.getClientY(),
    								mouseUpEvent.getNativeButton(),
    								mouseUpEvent.getRelativeX(),
    								mouseUpEvent.getRelativeY(),
    								mouseUpEvent.getScreenX(),
    								mouseUpEvent.getScreenY(),
    								mouseUpEvent.getX(),
    								mouseUpEvent.getY()));
    			}
    			//Fire context menu event if needed
    			if(contextMenuEvent != null){
    				fireEvent(contextMenuEvent); 
    			}
    			cleanEventCaches();
    		}else if(checkClickEventSequencesMatch(eventList)){
    			eventList.clear();
        		//Fire click event
    			if(clickEvent != null){
    				fireEvent(clickEvent);    				
    			}else{
    				fireEvent(new org.gz.gwt.mousemanager.client.event.ClickEvent(mouseUpEvent.getClientX(),
							mouseUpEvent.getClientY(),
							mouseUpEvent.getNativeButton(),
							mouseUpEvent.getRelativeX(),
							mouseUpEvent.getRelativeY(),
							mouseUpEvent.getScreenX(),
							mouseUpEvent.getScreenY(),
							mouseUpEvent.getX(),
							mouseUpEvent.getY()));
    			}
    			//Fire context menu event if needed
    			if(contextMenuEvent != null){
    				fireEvent(contextMenuEvent);    				
    			}
    			cleanEventCaches();
    		}else{
    			//If we were not able to figure out about the event we schedule another timer
        		initializeTimer();
    		}   	    		
    	}
    	
    	return;
	}
	
	/**
	 * Check if the eventList match double click EventSequences
	 * @See {@link EventSequences}
	 * @param eventList
	 * @return true if the event list match one sequences, otherwise return false.
	 */
	public static boolean checkDoucleClickEventSequencesMatch(List<String> eventList){
		if(checkEventSequencesMatch(eventList, EventSequences.doubleClick_Sequence_1)){
			return true;
		}else if(checkEventSequencesMatch(eventList, EventSequences.doubleClick_Sequence_2)){
			return true;
		}else if(checkEventSequencesMatch(eventList, EventSequences.doubleClick_Sequence_3)){
			return true;
		}else if(checkEventSequencesMatch(eventList, EventSequences.doubleClick_Sequence_4)){
			return true;
		}else if(checkEventSequencesMatch(eventList, EventSequences.doubleClick_Sequence_5)){
			return true;
		}else if(checkEventSequencesMatch(eventList, EventSequences.doubleClick_Sequence_6)){
			return true;
		}else if(checkEventSequencesMatch(eventList, EventSequences.doubleClick_Sequence_7)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Check if the eventList match single click EventSequences
	 * @See {@link EventSequences}
	 * @param eventList
	 * @return true if the event list match one sequences, otherwise return false.
	 */
	public static boolean checkClickEventSequencesMatch(List<String> eventList){
		if(checkEventSequencesMatch(eventList, EventSequences.click_Sequence_1)){
			return true;
		}else if(checkEventSequencesMatch(eventList, EventSequences.click_Sequence_2)){
			return true;
		}else if(checkEventSequencesMatch(eventList, EventSequences.click_Sequence_3)){
			return true;
		}else if(checkEventSequencesMatch(eventList, EventSequences.click_Sequence_4)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Check if the eventList match the eventSequences passed to the function. We tolerate MouseMoveEvent between each event in the eventList
	 * @param eventList
	 * @param eventSequences
	 * @return
	 */
	private static boolean checkEventSequencesMatch(List<String> eventList, ArrayList<String> eventSequences){
		boolean isMatching = false;
		boolean performSequenceCheck = false;
		int eventListMatchIndex = 0;
		boolean isMoveEvent = false;
		
		eventListLoop : for(int eventListIndex = 0; eventListIndex < eventList.size(); eventListIndex++){
			String currentEventListEvent = eventList.get(eventListIndex);
			//Initialize variables
			performSequenceCheck = false;
			isMatching = false;
			isMoveEvent = false;
			sequenceLoop: for(int sequenceIndex = 0 ; sequenceIndex < eventSequences.size(); sequenceIndex++){
				String currentSequenceEvent = eventSequences.get(sequenceIndex);
				//Check the first event to figure out if we should check the entire sequence
				if(currentEventListEvent.equals(currentSequenceEvent) && sequenceIndex == 0){
					eventListMatchIndex = eventListIndex;
					isMatching = true;
					isMoveEvent = false;
					performSequenceCheck = true;
					continue sequenceLoop;
				}
				//If we are performing the matching check
				if(performSequenceCheck){
					eventListMatchIndex++;
					if(eventListMatchIndex>=eventList.size()){
						//Reach the end of the event list without finding any match
						isMatching = false;
						isMoveEvent = false;
						continue eventListLoop;
					}
					String currentEventListEventMatch = eventList.get(eventListMatchIndex);
					if(currentEventListEventMatch.equals(currentSequenceEvent)){
						//Event match so we keep going
						isMatching = true;
						isMoveEvent = false;
					}else if(currentEventListEventMatch.equals(MouseMoveEvent.getType().getName())){
						if(!isMoveEvent){
							if(sequenceIndex>0){
								sequenceIndex--;
							}
							isMoveEvent = true;
						}else{
							//We don't tolerate 2 consecutive MouseMoveEvent
							isMatching = false;
							isMoveEvent = false;
							continue eventListLoop; 
						}
					}else{
						//Event is not matching at all
						isMatching = false;
						isMoveEvent = false;
						continue eventListLoop; 
					}
				}else{
					//The first event doesn't match
					isMatching = false;
					isMoveEvent = false;
					continue eventListLoop;
				}
			}
			//If we found the sequence, we stop searching 
			if(isMatching){
				break eventListLoop; 
			}	
		}
		if(isMatching){
			return true; 
		}else{
			return false;
		}		
	}
	
	/**
	 * Return Event object string identifier 
	 * @param event
	 * @return Type name of the event object
	 */
	private static String convertNativeEventToStringIdentifier(Event<?> event){
		if(event == null){
			return "";
		}
		if(event instanceof DoubleClickEvent){
			return DoubleClickEvent.getType().getName();
		}
		if(event instanceof ClickEvent){
			return ClickEvent.getType().getName();
		}		
		if(event instanceof MouseUpEvent){
			return MouseUpEvent.getType().getName();
		}
		if(event instanceof MouseDownEvent){
			return MouseDownEvent.getType().getName();
		} 
		if(event instanceof MouseMoveEvent){
			return MouseMoveEvent.getType().getName();
		}
		if(event instanceof MouseOverEvent){
			return MouseOverEvent.getType().getName();
		}
		if(event instanceof MouseOutEvent){
			return MouseOutEvent.getType().getName();
		}
		if(event instanceof MouseWheelEvent){
			return MouseWheelEvent.getType().getName();
		}
		if(event instanceof ContextMenuEvent){
			return ContextMenuEvent.getType().getName();
		}
		return "";
	}

	/**
	 * Update event caches to keep track of the latest event of each instance.
	 * @param the event to cache
	 */
	private void updateEventCaches(Event<?> event){
		if(event == null){
			return;
		}
		if(event instanceof DoubleClickEvent){
			doubleClickEvent = new org.gz.gwt.mousemanager.client.event.DoubleClickEvent((DoubleClickEvent)event, target);
		}
		if(event instanceof ClickEvent){
			clickEvent = new org.gz.gwt.mousemanager.client.event.ClickEvent((ClickEvent)event, target);
		}		
		if(event instanceof MouseUpEvent){
			mouseUpEvent = new org.gz.gwt.mousemanager.client.event.MouseUpEvent((MouseUpEvent)event, target);
		}
		if(event instanceof MouseDownEvent){
			mouseDownEvent = new org.gz.gwt.mousemanager.client.event.MouseDownEvent((MouseDownEvent)event, target);
		} 
		if(event instanceof MouseMoveEvent){
			mouseMoveEvent = new org.gz.gwt.mousemanager.client.event.MouseMoveEvent((MouseMoveEvent)event, target);
		}
		if(event instanceof MouseOverEvent){
			mouseOverEvent = new org.gz.gwt.mousemanager.client.event.MouseOverEvent((MouseOverEvent)event, target);
		}
		if(event instanceof MouseOutEvent){
			mouseOutEvent = new org.gz.gwt.mousemanager.client.event.MouseOutEvent((MouseOutEvent)event, target);
		}
		if(event instanceof MouseWheelEvent){
			mouseWheelEvent = new org.gz.gwt.mousemanager.client.event.MouseWheelEvent((MouseWheelEvent)event, target);
		}
		if(event instanceof ContextMenuEvent){
			contextMenuEvent = new org.gz.gwt.mousemanager.client.event.ContextMenuEvent();
		}
	}
	
	/**
	 * Clean event caches variables
	 */
	private void cleanEventCaches(){
		doubleClickEvent = null;
		clickEvent = null;
		contextMenuEvent = null;
		mouseUpEvent = null;
		mouseDownEvent = null;
		mouseMoveEvent = null;
		mouseOutEvent = null;
		mouseOverEvent = null;
		mouseWheelEvent = null;
	}
	
	/**
	 * Check if a event of the specified type is contains within the event List
	 * @param the eventList history
	 * @param the type of the event 
	 * @return true if the event list contains a event of the specified type, otherwise it returns false 
	 */
	private boolean contains(List<String> eventList, Type<?> type) {
		if(eventList == null){
			return false;
		}
		for(int i = 0; i < eventList.size(); i++){
			if(eventList.get(i).equals(type.getName())){
				return true;
			}
		}
		return false;
	}

	/**
	 * Initialize a timer to execute {@link MouseHandlerManager#analyseEvents()}} if no events are fired for a long time
	 */
	private void initializeTimer() {
    	if(timeoutTimer != null){
    		timeoutTimer.cancel();
    	}
    	timeoutTimer = null;
    	
        timeoutTimer = new Timer() {
        	public void run(){
        		//We call the same function with null as parameter 
        		analyseEvents();
        	}
        };
        timeoutTimer.schedule(Double.valueOf(TIMEOUT * 1000).intValue()); //Timeout is in milliseconds	
	}

	/**
	 * cancel Timer if exist
	 */
	private void cancelTimer(){
		if(timeoutTimer != null){
			timeoutTimer.cancel();
			timeoutTimer = null;
		}	
	}
	
	/**
	 * Refresh internal variable isMouseDown based on the specified Event
	 * If the event is a <a href="http://google-web-toolkit.googlecode.com/svn/javadoc/2.0/com/google/gwt/event/dom/client/MouseDownEvent.html">Native Mouse Down Event</a>, isMouseDown will be set to true
	 * If the event is a <a href="http://google-web-toolkit.googlecode.com/svn/javadoc/2.0/com/google/gwt/event/dom/client/MouseUpEvent.html">Native Mouse Up Event</a>, isMouseDown will be set to false
	 * 
	 * @param event
	 */
	private void checkMouseState(Event<?> event) {
		if(event == null){
			return;
		}
		if(event.getAssociatedType().equals(MouseDownEvent.getType())){
    		isMouseDown = true;
    	}
    	if(event.getAssociatedType().equals(MouseUpEvent.getType())){
    		isMouseDown = false;
    	}
	}

	/**
	 * @return isMouseDown value
	 */
	public boolean isMouseDown() {
		return isMouseDown;
	}
	
	/**
	 * @return string representation of the user agent
	 */
	public static native String getUserAgent() /*-{
    	return navigator.userAgent.toLowerCase();
 	}-*/;	
	
}
