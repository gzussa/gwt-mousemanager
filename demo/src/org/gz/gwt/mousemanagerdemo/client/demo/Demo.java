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
package org.gz.gwt.mousemanagerdemo.client.demo;

import gwt.g2d.client.graphics.canvas.CanvasElement;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.gz.gwt.mousemanager.client.MouseHandlerManager;
import org.gz.gwt.mousemanager.client.event.ClickEvent;
import org.gz.gwt.mousemanager.client.event.ClickHandler;
import org.gz.gwt.mousemanager.client.event.ContextMenuEvent;
import org.gz.gwt.mousemanager.client.event.ContextMenuHandler;
import org.gz.gwt.mousemanager.client.event.DoubleClickEvent;
import org.gz.gwt.mousemanager.client.event.DoubleClickHandler;
import org.gz.gwt.mousemanager.client.event.MouseDownEvent;
import org.gz.gwt.mousemanager.client.event.MouseDownHandler;
import org.gz.gwt.mousemanager.client.event.MouseEvent;
import org.gz.gwt.mousemanager.client.event.MouseMoveEvent;
import org.gz.gwt.mousemanager.client.event.MouseMoveHandler;
import org.gz.gwt.mousemanager.client.event.MouseOutEvent;
import org.gz.gwt.mousemanager.client.event.MouseOutHandler;
import org.gz.gwt.mousemanager.client.event.MouseOverEvent;
import org.gz.gwt.mousemanager.client.event.MouseOverHandler;
import org.gz.gwt.mousemanager.client.event.MouseUpEvent;
import org.gz.gwt.mousemanager.client.event.MouseUpHandler;
import org.gz.gwt.mousemanager.client.event.MouseWheelEvent;
import org.gz.gwt.mousemanager.client.event.MouseWheelHandler;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Demo implements EntryPoint {
	private static final String CSS_STYLE_COLUMN = "column";
	private static final String CSS_STYLE_TITLE = "title";
	private static final String CSS_STYLE_COLOR_GREEN = "green";
	private static final String CSS_STYLE_COLOR_ORANGE = "orange";
	private static final String CSS_STYLE_COLOR_BLACK = "black";
	private static final String CSS_STYLE_COLOR_BLUE = "blue";
	private static final String CSS_STYLE_COLOR_RED = "red";
	private static final String CSS_STYLE_COLOR_GREY = "grey";
	private static final String CSS_STYLE_COLOR_BROWN = "brown";
	
	private static final int LIST_EVENT_SIZE = 15;
	private MouseHandlerManager mouseHandlerManager = null;
	private AdvSurface surface = null;

	private VerticalPanel nativeEvents_VerticalPanel;
	private VerticalPanel mouseManagerEvents_VerticalPanel;
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		//Create 3 verticals panels
		HorizontalPanel mainPanel_HorizontalPanel = new HorizontalPanel();
		
		VerticalPanel leftPanel_VerticalPanel = new VerticalPanel();
		leftPanel_VerticalPanel.setStyleName(CSS_STYLE_COLUMN);
		mainPanel_HorizontalPanel.add(leftPanel_VerticalPanel);
		VerticalPanel centerPanel_VerticalPanel = new VerticalPanel();
		centerPanel_VerticalPanel.setStyleName(CSS_STYLE_COLUMN);
		mainPanel_HorizontalPanel.add(centerPanel_VerticalPanel);
		VerticalPanel rightPanel_VerticalPanel = new VerticalPanel();
		rightPanel_VerticalPanel.setStyleName(CSS_STYLE_COLUMN);
		mainPanel_HorizontalPanel.add(rightPanel_VerticalPanel);
		
		Label surfaceTitle_Label = new Label("Surface");
		surfaceTitle_Label.setStyleName(CSS_STYLE_TITLE);
		leftPanel_VerticalPanel.add(surfaceTitle_Label);
		Label nativeHandlerTitle_Label = new Label("Native Events");
		nativeHandlerTitle_Label.setStyleName(CSS_STYLE_TITLE);
		centerPanel_VerticalPanel.add(nativeHandlerTitle_Label);
		Label mouseManagerTitle_Label = new Label("Mouse Manager Events");
		mouseManagerTitle_Label.setStyleName(CSS_STYLE_TITLE);
		rightPanel_VerticalPanel.add(mouseManagerTitle_Label);
		
		surface = new AdvSurface(300, 300);
		addNativeMouseHandlers(surface);
		addMouseHandlers(surface);			
		leftPanel_VerticalPanel.add(surface);
		
		nativeEvents_VerticalPanel = new VerticalPanel();
		centerPanel_VerticalPanel.add(nativeEvents_VerticalPanel);
		
		mouseManagerEvents_VerticalPanel = new VerticalPanel();
		rightPanel_VerticalPanel.add(mouseManagerEvents_VerticalPanel);
		
		RootPanel.get().add(mainPanel_HorizontalPanel);
	}
	/**
	 * Add native Handlers
	 * @param surface
	 */
	private void addNativeMouseHandlers(AdvSurface surface) {
		surface.addClickHandler(new com.google.gwt.event.dom.client.ClickHandler() {
			@Override
			public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
				handleNativeEvent("Click", CSS_STYLE_COLOR_GREEN, event);				
			}
		});
		
		surface.addDoubleClickHandler(new com.google.gwt.event.dom.client.DoubleClickHandler() {
			@Override
			public void onDoubleClick(com.google.gwt.event.dom.client.DoubleClickEvent event) {
				handleNativeEvent("Double Click", CSS_STYLE_COLOR_ORANGE, event);				
			}
		});
		
		surface.addMouseMoveHandler(new com.google.gwt.event.dom.client.MouseMoveHandler() {
			@Override
			public void onMouseMove(com.google.gwt.event.dom.client.MouseMoveEvent event) {
				handleNativeEvent("Mouse Move", CSS_STYLE_COLOR_BLACK, event);				
			}
		});
		
		surface.addMouseDownHandler(new com.google.gwt.event.dom.client.MouseDownHandler() {
			@Override
			public void onMouseDown(com.google.gwt.event.dom.client.MouseDownEvent event) {
				handleNativeEvent("Mouse Down", CSS_STYLE_COLOR_BLUE, event);				
			}
		});
		
		surface.addMouseUpHandler(new com.google.gwt.event.dom.client.MouseUpHandler() {
			@Override
			public void onMouseUp(com.google.gwt.event.dom.client.MouseUpEvent event) {
				handleNativeEvent("Mouse Up", CSS_STYLE_COLOR_RED, event);				
			}
		});
		
		surface.addMouseOutHandler(new com.google.gwt.event.dom.client.MouseOutHandler() {
			@Override
			public void onMouseOut(com.google.gwt.event.dom.client.MouseOutEvent event) {
				handleNativeEvent("Mouse Out", CSS_STYLE_COLOR_GREY, event);				
			}
		});
		
		surface.addMouseOverHandler(new com.google.gwt.event.dom.client.MouseOverHandler() {
			@Override
			public void onMouseOver(com.google.gwt.event.dom.client.MouseOverEvent event) {
				handleNativeEvent("Mouse Over", CSS_STYLE_COLOR_GREY, event);				
			}
		});
		
		surface.addMouseWheelHandler(new com.google.gwt.event.dom.client.MouseWheelHandler() {
			@Override
			public void onMouseWheel(com.google.gwt.event.dom.client.MouseWheelEvent event) {
				handleNativeEvent("Mouse Wheel", CSS_STYLE_COLOR_GREY, event);				
			}
		});
		
		surface.addContextMenuHandler(new com.google.gwt.event.dom.client.ContextMenuHandler() {
			@Override
			public void onContextMenu(com.google.gwt.event.dom.client.ContextMenuEvent event) {
				
				handleNativeEvent("Context Menu", CSS_STYLE_COLOR_BROWN, event);				
			}
		});
		
	}
	/**
	 * Add Mouse Handlers using gwt-mousemanager
	 * @param surface
	 */
	private void addMouseHandlers(AdvSurface surface) {
		mouseHandlerManager = new MouseHandlerManager(null);
		mouseHandlerManager.setObject(surface);

		final CanvasElement canvas = surface.getCanvas();

		mouseHandlerManager.setTarget(canvas);

		// Add handlers
		mouseHandlerManager.addHandler(ClickEvent.TYPE,
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						handleEvent("Click", CSS_STYLE_COLOR_GREEN, event);
					}
				});
		mouseHandlerManager.addHandler(DoubleClickEvent.TYPE,
				new DoubleClickHandler() {
					public void onDoubleClick(DoubleClickEvent event) {
						handleEvent("Double Click", CSS_STYLE_COLOR_ORANGE, event);
					}
				});
		mouseHandlerManager.addHandler(MouseMoveEvent.TYPE,
				new MouseMoveHandler() {
					public void onMouseMove(MouseMoveEvent event) {
						handleEvent("Mouse Move", CSS_STYLE_COLOR_BLACK, event);
					}
				});
		mouseHandlerManager.addHandler(MouseDownEvent.TYPE,
				new MouseDownHandler() {
					public void onMouseDown(MouseDownEvent event) {
						handleEvent("Mouse Down", CSS_STYLE_COLOR_BLUE, event);
					}
				});
		mouseHandlerManager.addHandler(MouseUpEvent.TYPE,
				new MouseUpHandler() {
					public void onMouseUp(MouseUpEvent event) {
						handleEvent("Mouse Up", CSS_STYLE_COLOR_RED, event);
					}
				});
		mouseHandlerManager.addHandler(MouseOverEvent.TYPE,
				new MouseOverHandler() {
					public void onMouseOver(MouseOverEvent event) {
						handleEvent("Mouse Over", CSS_STYLE_COLOR_GREY, event);
					}
				});
		mouseHandlerManager.addHandler(MouseOutEvent.TYPE,
				new MouseOutHandler() {
					public void onMouseOut(MouseOutEvent event) {
						handleEvent("Mouse Out", CSS_STYLE_COLOR_GREY, event);
					}
				});		
		mouseHandlerManager.addHandler(MouseWheelEvent.TYPE,
				new MouseWheelHandler() {
					public void onMouseWheel(MouseWheelEvent event) {
						handleEvent("Mouse Wheel", CSS_STYLE_COLOR_GREY, event);
					}
				});
		mouseHandlerManager.addHandler(ContextMenuEvent.TYPE,
				new ContextMenuHandler() {
					public void onContextMenu(ContextMenuEvent event) {
						handleEvent("Context Menu", CSS_STYLE_COLOR_BROWN, event);
					}
				});
	}
	
	private void handleEvent(String label, String style, GwtEvent<?> event) {		
		int widgetCount = mouseManagerEvents_VerticalPanel.getWidgetCount();
		for(int i = widgetCount; i >= LIST_EVENT_SIZE; i--){
			mouseManagerEvents_VerticalPanel.remove(0);	
		}
		Label events_Label = null;
		if(event instanceof MouseEvent){
			events_Label = new Label(label+" | X = "+((MouseEvent)event).getX()+" | Y = "+((MouseEvent)event).getY()+" | button = "+((MouseEvent)event).getNativeButton());
		}else{
			events_Label = new Label(label);
		}
		events_Label.setStyleName(style);
		if(mouseHandlerManager.isMouseDown()){
			events_Label.setStyleName(CSS_STYLE_COLOR_BLUE);
		}
		
		mouseManagerEvents_VerticalPanel.add(events_Label);
	}
	
	private void handleNativeEvent(String label, String style, com.google.gwt.event.dom.client.DomEvent<?> event) {
		event.preventDefault();
		event.stopPropagation();
		int widgetCount = nativeEvents_VerticalPanel.getWidgetCount();
		for(int i = widgetCount; i >= LIST_EVENT_SIZE; i--){
			nativeEvents_VerticalPanel.remove(0);	
		}
		if(event instanceof com.google.gwt.event.dom.client.MouseEvent){
			Label events_Label = new Label(label+" | X = "+((com.google.gwt.event.dom.client.MouseEvent)event).getX()+" | Y = "+((com.google.gwt.event.dom.client.MouseEvent)event).getY()+" | button = "+((com.google.gwt.event.dom.client.MouseEvent)event).getNativeButton());
			events_Label.setStyleName(style);
			nativeEvents_VerticalPanel.add(events_Label);	
		}else{
			Label events_Label = new Label(label+" | X = "+((com.google.gwt.event.dom.client.ContextMenuEvent)event).getNativeEvent().getClientX()+" | Y = "+((com.google.gwt.event.dom.client.ContextMenuEvent)event).getNativeEvent().getClientY()+" | button = "+((com.google.gwt.event.dom.client.ContextMenuEvent)event).getNativeEvent().getButton());
			events_Label.setStyleName(style);
			nativeEvents_VerticalPanel.add(events_Label);	
		}
	}
}
