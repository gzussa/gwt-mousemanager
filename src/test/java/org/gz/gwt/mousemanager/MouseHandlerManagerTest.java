/*
 * Copyright 2012 Gregory Zussa
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.gz.gwt.mousemanager;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseUpEvent;
import org.gz.gwt.mousemanager.client.EventSequences;
import org.gz.gwt.mousemanager.client.MouseHandlerManager;

public class MouseHandlerManagerTest{
	
	@Test
	public void testDoubleClickSequences(){
		//Simple positive check for each sequences
		List<String> eventList = EventSequences.doubleClick_Sequence_1;
		assertTrue(MouseHandlerManager.checkDoucleClickEventSequencesMatch(eventList));
		
		eventList = EventSequences.doubleClick_Sequence_2;
		assertTrue(MouseHandlerManager.checkDoucleClickEventSequencesMatch(eventList));
		
		eventList = EventSequences.doubleClick_Sequence_3;
		assertTrue(MouseHandlerManager.checkDoucleClickEventSequencesMatch(eventList));
		
		eventList = EventSequences.doubleClick_Sequence_4;
		assertTrue(MouseHandlerManager.checkDoucleClickEventSequencesMatch(eventList));
		
		eventList = EventSequences.doubleClick_Sequence_5;
		assertTrue(MouseHandlerManager.checkDoucleClickEventSequencesMatch(eventList));
		
		eventList = EventSequences.doubleClick_Sequence_6;
		assertTrue(MouseHandlerManager.checkDoucleClickEventSequencesMatch(eventList));
		
		eventList = EventSequences.doubleClick_Sequence_7;
		assertTrue(MouseHandlerManager.checkDoucleClickEventSequencesMatch(eventList));
		
		//Simple positive check for each sequences with MouseMove inserted
		eventList = new ArrayList<String>(){{
				add(MouseMoveEvent.getType().getName());
			add(MouseDownEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
			add(MouseUpEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
			add(ClickEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
			add(MouseDownEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
			add(MouseUpEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
			add(ClickEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
			add(DoubleClickEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
		}};
		assertTrue(MouseHandlerManager.checkDoucleClickEventSequencesMatch(eventList));

		eventList = new ArrayList<String>(){{
				add(MouseMoveEvent.getType().getName());
			add(MouseDownEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
			add(MouseUpEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
			add(ClickEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
			add(MouseUpEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
			add(ClickEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
			add(DoubleClickEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
		}};
		assertTrue(MouseHandlerManager.checkDoucleClickEventSequencesMatch(eventList));
		
		eventList = new ArrayList<String>(){{
				add(MouseMoveEvent.getType().getName());
			add(MouseDownEvent.getType().getName()); 
				add(MouseMoveEvent.getType().getName());
			add(MouseUpEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
			add(ClickEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
			add(MouseUpEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
			add(ClickEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
		}};
		assertTrue(MouseHandlerManager.checkDoucleClickEventSequencesMatch(eventList));

		eventList = new ArrayList<String>(){{
				add(MouseMoveEvent.getType().getName());
			add(MouseDownEvent.getType().getName()); 
				add(MouseMoveEvent.getType().getName());
			add(MouseUpEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
			add(ContextMenuEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
			add(MouseUpEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
			add(ContextMenuEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
		}};
		assertTrue(MouseHandlerManager.checkDoucleClickEventSequencesMatch(eventList));
		
		eventList = new ArrayList<String>(){{
				add(MouseMoveEvent.getType().getName());
			add(MouseDownEvent.getType().getName()); 
				add(MouseMoveEvent.getType().getName());
			add(MouseUpEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
			add(ContextMenuEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
			add(MouseDownEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
			add(MouseUpEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
			add(ContextMenuEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
		}};
		assertTrue(MouseHandlerManager.checkDoucleClickEventSequencesMatch(eventList));
		
		eventList = new ArrayList<String>(){{
				add(MouseMoveEvent.getType().getName());
			add(MouseDownEvent.getType().getName()); 
				add(MouseMoveEvent.getType().getName());
			add(ContextMenuEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
			add(MouseUpEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
			add(MouseDownEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
			add(ContextMenuEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
			add(MouseUpEvent.getType().getName());	
				add(MouseMoveEvent.getType().getName());
		}};
		assertTrue(MouseHandlerManager.checkDoucleClickEventSequencesMatch(eventList));
		
		eventList = new ArrayList<String>(){{
				add(MouseMoveEvent.getType().getName());
			add(MouseDownEvent.getType().getName()); 
				add(MouseMoveEvent.getType().getName());
			add(MouseUpEvent.getType().getName());	
				add(MouseMoveEvent.getType().getName());
			add(MouseDownEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
			add(MouseUpEvent.getType().getName());		
				add(MouseMoveEvent.getType().getName());
		}};
		assertTrue(MouseHandlerManager.checkDoucleClickEventSequencesMatch(eventList));		
		
		//Negative check with non-matching sequences
		eventList = new ArrayList<String>(){{
			add(MouseDownEvent.getType().getName()); 
			add(MouseUpEvent.getType().getName());
		}};
		assertFalse(MouseHandlerManager.checkDoucleClickEventSequencesMatch(eventList));
		
		//Test two consecutive MouseMove
		eventList = new ArrayList<String>(){{
				add(MouseMoveEvent.getType().getName());
			add(MouseDownEvent.getType().getName()); 
				add(MouseMoveEvent.getType().getName());
			add(MouseUpEvent.getType().getName());	
				add(MouseMoveEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
			add(MouseDownEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
			add(MouseUpEvent.getType().getName());		
				add(MouseMoveEvent.getType().getName());
		}};
		assertFalse(MouseHandlerManager.checkDoucleClickEventSequencesMatch(eventList));	
		
		//Test with incorrect Event at the beginning
		eventList = new ArrayList<String>(){{
				add(MouseDownEvent.getType().getName()); 
				add(MouseUpEvent.getType().getName());  
				add(MouseUpEvent.getType().getName()); 
			add(MouseDownEvent.getType().getName()); 
			add(MouseUpEvent.getType().getName());	
			add(MouseDownEvent.getType().getName());
			add(MouseUpEvent.getType().getName());		
		}};
		assertTrue(MouseHandlerManager.checkDoucleClickEventSequencesMatch(eventList));	
		
	}
	
	@Test
	public void testClickSequences(){
		//Simple positive check for each sequences
		List<String> eventList = EventSequences.click_Sequence_1;
		assertTrue(MouseHandlerManager.checkClickEventSequencesMatch(eventList));
		
		eventList = EventSequences.click_Sequence_2;
		assertTrue(MouseHandlerManager.checkClickEventSequencesMatch(eventList));
		
		eventList = EventSequences.click_Sequence_3;
		assertTrue(MouseHandlerManager.checkClickEventSequencesMatch(eventList));
		
		eventList = EventSequences.click_Sequence_4;
		assertTrue(MouseHandlerManager.checkClickEventSequencesMatch(eventList));
		
		//Simple positive check for each sequences with MouseMove inserted
		eventList = new ArrayList<String>(){{
				add(MouseMoveEvent.getType().getName());
			add(MouseDownEvent.getType().getName()); 
				add(MouseMoveEvent.getType().getName());
			add(MouseUpEvent.getType().getName());	
				add(MouseMoveEvent.getType().getName());
			add(ClickEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
		}};
		assertTrue(MouseHandlerManager.checkClickEventSequencesMatch(eventList));
		
		eventList = new ArrayList<String>(){{
				add(MouseMoveEvent.getType().getName());
			add(MouseDownEvent.getType().getName()); 
				add(MouseMoveEvent.getType().getName());
			add(MouseUpEvent.getType().getName());	
				add(MouseMoveEvent.getType().getName());
			add(ContextMenuEvent.getType().getName());	
				add(MouseMoveEvent.getType().getName());
		}};
		assertTrue(MouseHandlerManager.checkClickEventSequencesMatch(eventList));
		
		eventList = new ArrayList<String>(){{
				add(MouseMoveEvent.getType().getName());
			add(MouseDownEvent.getType().getName()); 
				add(MouseMoveEvent.getType().getName());
			add(MouseUpEvent.getType().getName());	
				add(MouseMoveEvent.getType().getName());
		}};
		assertTrue(MouseHandlerManager.checkClickEventSequencesMatch(eventList));
		
		eventList = new ArrayList<String>(){{
				add(MouseMoveEvent.getType().getName());
			add(MouseDownEvent.getType().getName()); 
				add(MouseMoveEvent.getType().getName());
			add(ContextMenuEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
			add(MouseUpEvent.getType().getName());	
				add(MouseMoveEvent.getType().getName());
		}};
		assertTrue(MouseHandlerManager.checkClickEventSequencesMatch(eventList));		
		
//		eventList = new ArrayList<String>(){{
//				add(MouseMoveEvent.getType().getName());
//			add(ClickEvent.getType().getName()); 
//				add(MouseMoveEvent.getType().getName());
//			add(ContextMenuEvent.getType().getName());
//				add(MouseMoveEvent.getType().getName());
//		}};
//		assertTrue(MouseHandlerManager.checkClickEventSequencesMatch(eventList));	
		
		//Negative check with non-matching sequences
		eventList = new ArrayList<String>(){{
			add(MouseUpEvent.getType().getName()); 
			add(MouseUpEvent.getType().getName());
		}};
		assertFalse(MouseHandlerManager.checkClickEventSequencesMatch(eventList));
		
		//Test two consecutive MouseMove
		eventList = new ArrayList<String>(){{
			add(MouseMoveEvent.getType().getName());
			add(ClickEvent.getType().getName()); 
				add(MouseMoveEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
			add(ContextMenuEvent.getType().getName());
				add(MouseMoveEvent.getType().getName());
		}};
		assertFalse(MouseHandlerManager.checkClickEventSequencesMatch(eventList));	
		
//		//Test with incorrect Event at the beginning
//		eventList = new ArrayList<String>(){{
//				add(MouseUpEvent.getType().getName()); 
//				add(MouseUpEvent.getType().getName());  
//				add(MouseUpEvent.getType().getName()); 
//			add(ClickEvent.getType().getName()); 
//			add(ContextMenuEvent.getType().getName());			
//		}};
//		assertTrue(MouseHandlerManager.checkClickEventSequencesMatch(eventList));	
		
	}
	
}
