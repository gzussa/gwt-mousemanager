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
package org.gz.gwt.mousemanager.client.event;

import com.google.gwt.dom.client.Element;
/**
 * Represents a double click event. Decorator class of a 
 * <a href="http://google-web-toolkit.googlecode.com/svn/javadoc/2.0/com/google/gwt/event/dom/client/DoubleClickEvent.html">Native Double Click Event</a> fired by a 
 * <a href="http://google-web-toolkit.googlecode.com/svn/javadoc/2.0/com/google/gwt/user/client/Element.html">Dom Element</a>
 * 
 * @see <a href="http://google-web-toolkit.googlecode.com/svn/javadoc/2.0/com/google/gwt/event/dom/client/DoubleClickEvent.html">com.google.gwt.event.dom.client.DoubleClickEvent</a>
 * @see <a href="http://google-web-toolkit.googlecode.com/svn/javadoc/2.0/com/google/gwt/user/client/Element.html">com.google.gwt.dom.client.Element</a>
 * 
 * @author gregory.zussa
 */
public class DoubleClickEvent extends MouseEvent<DoubleClickHandler> {
	
	/**
	 * Event type for double click events. 
	 */
	public static Type<DoubleClickHandler> TYPE = new Type<DoubleClickHandler>();
	
	/**
	 * Constructor, use <a href="http://google-web-toolkit.googlecode.com/svn/javadoc/2.0/com/google/gwt/event/shared/HandlerManager.html#fireEvent(com.google.gwt.event.shared.GwtEvent)">HandlerManager#fireEvent</a>
	 * to fire double click events.
	 */
	public DoubleClickEvent(com.google.gwt.event.dom.client.DoubleClickEvent event, Element target) {
		super(event, target);
	}
	
	/**
	 * Simple Constructor
	 */
	public DoubleClickEvent(int clientX, int clientY, int nativeButton,
			int relativeX, int relativeY, int screenX, int screenY, int x,
			int y) {
		super(clientX,
				clientY,
				nativeButton,
				relativeX,
				relativeY,
				screenX,
				screenY,
				x,
				y);
		
	}
	
	/**
	 * Gets the event type associated with double click events.
	 * 
	 * @return the handler type
	 */
	@Override
	public Type<DoubleClickHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(DoubleClickHandler handler) {
		handler.onDoubleClick(this);
	}
}
