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
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
/**
 * Abstract class representing mouse events.
 * 
 * @param <H> handler type
 * 
 * @author gregory.zussa
 */
public abstract class MouseEvent<H extends EventHandler> extends GwtEvent<H>{
	  /**
	   * The left mouse button.
	   */
	  public static final int BUTTON_LEFT = 0;

	  /**
	   * The middle mouse button.
	   */
	  public static final int BUTTON_MIDDLE = 1;

	  /**
	   * The right mouse button.
	   */
	  public static final int BUTTON_RIGHT = 2;
	
	/**
	 * the mouse x-position within the browser window's client area.
	 */
	private int clientX;
	/**
	 * the mouse y-position within the browser window's client area.
	 */
	private int clientY;
	/**
	 * the button value
	 * @see <a href="http://google-web-toolkit.googlecode.com/svn/javadoc/2.0/com/google/gwt/dom/client/NativeEvent.html#BUTTON_LEFT">BUTTON_LEFT</a>
	 * @see <a href="http://google-web-toolkit.googlecode.com/svn/javadoc/2.0/com/google/gwt/dom/client/NativeEvent.html#BUTTON_LEFT">BUTTON_RIGHT</a>
	 * @see <a href="http://google-web-toolkit.googlecode.com/svn/javadoc/2.0/com/google/gwt/dom/client/NativeEvent.html#BUTTON_LEFT">BUTTON_MIDDLE</a>
	 */
	private int nativeButton;
	/**
	 * the mouse x-position relative to a given element.
	 */
	private int relativeX;
	/**
	 * the mouse y-position relative to a given element.
	 */
	private int relativeY;
	/**
	 * the mouse x-position on the user's display.
	 */
	private int screenX;
	/**
	 * the mouse y-position on the user's display.
	 */
	private int screenY;
	/**
	 * the mouse x-position relative to the event's current target element.
	 */
	private int x;
	/**
	 * the mouse y-position relative to the event's current target element.
	 */
	private int y;
	
	/**
	 * constructor, create a Mouse Event associated to a <a href="http://google-web-toolkit.googlecode.com/svn/javadoc/2.0/com/google/gwt/dom/client/MouseEvent.html">Native Mouse Event</a> fired by a 
	 * <a href="http://google-web-toolkit.googlecode.com/svn/javadoc/2.0/com/google/gwt/user/client/Element.html">com.google.gwt.dom.client.Element</a> 
	 * @param the native mouse event
	 * @param target
	 */
	@SuppressWarnings("rawtypes")
	public MouseEvent(com.google.gwt.event.dom.client.MouseEvent event, Element target) {
		this(event.getClientX(),
				event.getClientY(),
				event.getNativeButton(),
				event.getRelativeX(target),
				event.getRelativeY(target),
				event.getScreenX(),
				event.getScreenY(),
				event.getX(),
				event.getY());
	}
	
	/**
	 * Simple Constructor
	 */
	public MouseEvent(int clientX, int clientY, int nativeButton,
			int relativeX, int relativeY, int screenX, int screenY, int x,
			int y) {
		this.clientX = clientX;
		this.clientY = clientY;
		this.nativeButton = nativeButton;
		this.relativeX = relativeX;
		this.relativeY = relativeY;
		this.screenX = screenX;
		this.screenY = screenY;
		this.x = x;
		this.y = y;
		
	}
	
	/**
	 * Gets the mouse x-position within the browser window's client area.
	 * 
	 * @return the mouse x-position
	 */
	public int getClientX() {
		return clientX;
	}

	/**
	 * Gets the mouse y-position within the browser window's client area.
	 * 
	 * @return the mouse y-position
	 */
	public int getClientY() {
		return clientY;
	}

	/**
	 * Gets the button value. Compare it to
	 * <a href="http://google-web-toolkit.googlecode.com/svn/javadoc/2.0/com/google/gwt/dom/client/NativeEvent.html#BUTTON_LEFT">BUTTON_LEFT</a>,
	 * <a href="http://google-web-toolkit.googlecode.com/svn/javadoc/2.0/com/google/gwt/dom/client/NativeEvent.html#BUTTON_LEFT">BUTTON_RIGHT</a>,
	 * <a href="http://google-web-toolkit.googlecode.com/svn/javadoc/2.0/com/google/gwt/dom/client/NativeEvent.html#BUTTON_LEFT">BUTTON_MIDDLE</a>
	 * 
	 * @return the button value
	 */
	public int getNativeButton() {
		return nativeButton;
	}

	/**
	 * Gets the mouse x-position relative to a given element.
	 * 
	 * @param target
	 *            the element whose coordinate system is to be used
	 * @return the relative x-position
	 */
	public int getRelativeX() {
		return relativeX;
	}

	/**
	 * Gets the mouse y-position relative to a given element.
	 * 
	 * @param target
	 *            the element whose coordinate system is to be used
	 * @return the relative y-position
	 */
	public int getRelativeY() {
		return relativeY;
	}

	/**
	 * Gets the mouse x-position on the user's display.
	 * 
	 * @return the mouse x-position
	 */
	public int getScreenX() {
		return screenX;
	}

	/**
	 * Gets the mouse y-position on the user's display.
	 * 
	 * @return the mouse y-position
	 */
	public int getScreenY() {
		return screenY;
	}

	/**
	 * Gets the mouse x-position relative to the event's current target element.
	 * 
	 * @return the relative x-position
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets the mouse y-position relative to the event's current target element.
	 * 
	 * @return the relative y-position
	 */
	public int getY() {
		return y;
	}

	
	
}
