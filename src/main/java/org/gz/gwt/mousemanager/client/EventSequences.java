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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseUpEvent;

/**
 * Interface defining event sequences browsers fires
 * @author gregory.zussa
 */
public interface EventSequences {
	
	/**
	 * Based on Jan Wolter work (http://unixpapa.com/js/mouse.html)
	 * - Sequence for Double Click of Left Mouse Button
	 * -- On IE ≥ 9.0 / Gecko ≥ 1.7 / Opera ≥ 9.10 / WebKit ≥ 412 (Win & Mac) / WebKit ≥ 533.4 (Linux)
	 * - Sequence for Double Click of Middle Mouse Button
	 * -- On WebKit (Win & Mac) / Webkit ≥ 433.4 (Linux)
	 */
	public static ArrayList<String> doubleClick_Sequence_1 = new ArrayList<String>(){{
		add(MouseDownEvent.getType().getName()); 
		add(MouseUpEvent.getType().getName());
		add(ClickEvent.getType().getName());
		add(MouseDownEvent.getType().getName());
		add(MouseUpEvent.getType().getName());
		add(ClickEvent.getType().getName());
		add(DoubleClickEvent.getType().getName());
	}};
	
	/**
	 * Sequence observed on IE8 for Double Click of Left Mouse Button
	 */
	public static ArrayList<String> doubleClick_Sequence_2 = new ArrayList<String>(){{
		add(MouseDownEvent.getType().getName()); 
		add(MouseUpEvent.getType().getName());
		add(ClickEvent.getType().getName());
		add(MouseUpEvent.getType().getName());
		add(ClickEvent.getType().getName());
		add(DoubleClickEvent.getType().getName());
	}};
	
	/**
	 * Sequence observed on IE8 for Double Click of Middle Mouse Button
	 */
	public static ArrayList<String> doubleClick_Sequence_3 = new ArrayList<String>(){{
		add(MouseDownEvent.getType().getName()); 
		add(MouseUpEvent.getType().getName());
		add(ClickEvent.getType().getName());
		add(MouseUpEvent.getType().getName());
		add(ClickEvent.getType().getName());
	}};
	
	/**
	 * Sequence observed on IE8 for Double Click of Right Mouse Button
	 */
	public static ArrayList<String> doubleClick_Sequence_4 = new ArrayList<String>(){{
		add(MouseDownEvent.getType().getName()); 
		add(MouseUpEvent.getType().getName());
		add(ContextMenuEvent.getType().getName());
		add(MouseUpEvent.getType().getName());
		add(ContextMenuEvent.getType().getName());
	}};
	
	/**
	 * Based on Jan Wolter work (http://unixpapa.com/js/mouse.html)
	 * - Sequence for Double Click of Right Mouse Button
	 * -- On Gecko ≥ 2.0 (Win) / Internet Explorer / Opera ≥ 10.50
	 */
	public static ArrayList<String> doubleClick_Sequence_5 = new ArrayList<String>(){{
		add(MouseDownEvent.getType().getName()); 
		add(MouseUpEvent.getType().getName());
		add(ContextMenuEvent.getType().getName());
		add(MouseDownEvent.getType().getName());
		add(MouseUpEvent.getType().getName());
		add(ContextMenuEvent.getType().getName());
	}};
	
	/**
	 * Based on Jan Wolter work (http://unixpapa.com/js/mouse.html)
	 * - Sequence for Double Click of Right Mouse Button
	 * -- On 2.0 > Gecko ≥ 1.7 (Win) / Gecko ≥ 1.7 (Linux) / Safari / Chrome ≥ 1.0 (Win & Mac) / Chrome ≥ 5.0 (Linux)
	 */
	public static ArrayList<String> doubleClick_Sequence_6 = new ArrayList<String>(){{
		add(MouseDownEvent.getType().getName()); 
		add(ContextMenuEvent.getType().getName());
		add(MouseUpEvent.getType().getName());		
		add(MouseDownEvent.getType().getName());
		add(ContextMenuEvent.getType().getName());
		add(MouseUpEvent.getType().getName());		
	}};

	/**
	 * Based on Jan Wolter work (http://unixpapa.com/js/mouse.html)
	 * - Sequence for Double Click of Right Mouse Button
	 * -- On Gecko ≥ 1.7 (Mac) / Konqueror 
	 * - Sequence for Double Click of Middle Mouse Button
	 * -- On Gecko ≥ 1.7 / Opera ≥ 8.0 / Konqueror
	 */
	public static ArrayList<String> doubleClick_Sequence_7 = new ArrayList<String>(){{
		add(MouseDownEvent.getType().getName()); 
		add(MouseUpEvent.getType().getName());		
		add(MouseDownEvent.getType().getName());
		add(MouseUpEvent.getType().getName());		
	}};
	
	/**
	 * Based on Jan Wolter work (http://unixpapa.com/js/mouse.html)
	 * - Sequence for Single Click of Left Mouse Button
	 * -- On All Browsers
	 * - Sequence for Single Click of Middle Mouse Button
	 * -- On Internet Explorer / WebKit 
	 */
	public static ArrayList<String> click_Sequence_1 = new ArrayList<String>(){{
		add(MouseDownEvent.getType().getName()); 
		add(MouseUpEvent.getType().getName());		
		add(ClickEvent.getType().getName());		
	}};
	
	/**
	 * Based on Jan Wolter work (http://unixpapa.com/js/mouse.html)
	 * - Sequence for Single Click of Right Mouse Button
	 * -- On Gecko ≥ 2.0 (Win) / Internet Explorer / Chrome ≥ 1.0 (Win) / Opera ≥10.50
	 */
	public static ArrayList<String> click_Sequence_2 = new ArrayList<String>(){{
		add(MouseDownEvent.getType().getName()); 
		add(MouseUpEvent.getType().getName());		
		add(ContextMenuEvent.getType().getName());		
	}};
	
	/**
	 * Based on Jan Wolter work (http://unixpapa.com/js/mouse.html)
	 * - Sequence for Single Click of Right Mouse Button
	 * -- On Gecko (Mac) / Konqueror
	 * - Sequence for Single Click of Middle Mouse Button
	 * -- On Gecko / Opera ≥ 8.0 / Konqueror
	 */
	public static ArrayList<String> click_Sequence_3 = new ArrayList<String>(){{
		add(MouseDownEvent.getType().getName()); 
		add(MouseUpEvent.getType().getName());		
	}};
	
	/**
	 * Based on Jan Wolter work (http://unixpapa.com/js/mouse.html)
	 * - Sequence for Single Click of Right Mouse Button
	 * -- On Gecko < 2.0 (Win) / Gecko (Linux) / Safari / Chrome (Linux, Mac)
	 */
	public static ArrayList<String> click_Sequence_4 = new ArrayList<String>(){{
		add(MouseDownEvent.getType().getName()); 		
		add(ContextMenuEvent.getType().getName());
		add(MouseUpEvent.getType().getName());		
	}};
}
