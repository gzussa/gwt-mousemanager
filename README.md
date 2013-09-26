# GWT-MouseManager

[![Build Status](https://travis-ci.org/gzussa/gwt-mousemanager.png?branch=master)](https://travis-ci.org/gzussa/gwt-mousemanager)

## About
### What is it?

GWT-MouseManager is a GWT library with two main objectives:

- Simplify mouse event management in GWT projects.
- Homogenize mouse events across browsers brands and versions.

Handling mouse events is part of JavaScript Madness. Mouse actions, such as Click or Double click fire event sequences that vary based on browsers and their versions. These sequences tend to merge to common sequences on modern browsers. However, it will take time before these browsers replace the old ones currently used, forcing developers to write the same pieces of code over and over again to handle mouse events on different browser versions.

Also, these sequences (even in modern browsers) are not very convenient. In some projects, developers are forced to write code for analyzing event sequences that interpret user actions. For example, if you want to detect mouse Down, Mouse Up, Click and Double Click events distinctly, it will not be as easy as creating one listener for each event. Browsers do not only fire one event per user action, but events sequences. In modern browsers, a left button double click will fire the following events (ref: [W3C - Events-Mouseevent-Event-Order](http://www.w3.org/TR/DOM-Level-3-Events/#events-mouseevent-event-order):

1. Mouse Down
2. Mouse Move (optional)
3. Mouse Up
4. Click
5. Mouse Move (optional)
6. Mouse Down
7. Mouse Move (optional)
8. Mouse Up
9. Click
10. Double Click

Your logic behind your Double Click listener will be executed, but it will also execute the logic behind your click, mouse up and mouse down listeners twice.

### Example

Pointless? Yes. Easy-to-understand? I hope so! Fun? Probably just as much as !

[Give it a whirl here.](http://gwt-mousemanager.appspot.com/)

Source code available in the following Github Repo [GWT-MouseManager-Demo](https://github.com/gzussa/gwt-mousemanager-demo).

## Getting Started

### How to Get GWT-MouseManager
#### Build From Source

Check out the sources from `https://github.com/gzussa/gwt-mousemanager.git` and then import the project into Eclipse as a Java Project.
Then set up your project as a Maven project so you can fix dependencies. Optionally, you can set up your project as a Google Web Toolkit project as well.

#### Test and Export
Tests are available in the following path `src/test/java`. Tests are using the JUnit framework.
Finally you can export your project as a executable jar so you can use it. You can either use Eclipse export feature or use Maven. See the [Build](#build) section for more details.


### Hello World

The simplest way to interact with GWT-MouseManager is through the `Surface` Object. `Surface `supports many mouse event handlers. It is also a widget, which means it can be added to any panel. In the following demonstration, we extended `Surface` with `AdvSurface`, which implements the `HasContextMenuHandlers` interface.

```java
 /** 
  * Advance Surface with Context Menu Handler
  */
 public class AdvSurface extends Surface implements HasContextMenuHandlers{
         
         public AdvSurface(int width, int height) {
                 super(width, height);
         }
         
         public HandlerRegistration addContextMenuHandler(ContextMenuHandler handler) {
             return addDomHandler(handler, ContextMenuEvent.getType());
         }       
 }
```
 
- Create a instance of mouseHandlerManager.
- Set the object from which you want to catch the event. In this case we do `setObject(surface)`.
- Set the target to Element, which is used to retrieve the relative position to this element.
- Add your event handlers using gwt-mousemanager events.
- Verify that you have added `gwt-mousemanager.jar` to your build path (You can use Maven dependency).

```java
 import com.gz.gwt.mouse.client.MouseHandlerManager;
 import com.gz.gwt.mouse.client.event.ClickEvent;
 import com.gz.gwt.mouse.client.event.ClickHandler;
 /** 
  * Entry point classes define onModuleLoad().
  */
 public class Demo implements EntryPoint {
         MouseHandlerManager mouseHandlerManager = null;
 
        /** 
          * This is the entry point method.
          */
         public void onModuleLoad() {
                 
                 AdvSurface surface = new AdvSurface(300, 300);
          
                 addMouseHandlers(surface);                      
                 
                 RootPanel.get().add(surface);
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
                                                 //TODO DO YOUR ON CLICK LOGIC
                                         }
                                 });
                 mouseHandlerManager.addHandler(DoubleClickEvent.TYPE,
                                 new DoubleClickHandler() {
                                         public void onDoubleClick(DoubleClickEvent event) {
                                                //TODO DO YOUR ON DOUBLE CLICK LOGIC
                                         }
                                 });
                 mouseHandlerManager.addHandler(MouseMoveEvent.TYPE,
                                 new MouseMoveHandler() {
                                         public void onMouseMove(MouseMoveEvent event) {
                                                //TODO DO YOUR ON MOUSE MOVE LOGIC
                                         }
                                 });
                 mouseHandlerManager.addHandler(MouseDownEvent.TYPE,
                                 new MouseDownHandler() {
                                         public void onMouseDown(MouseDownEvent event) {
                                                //TODO DO YOUR ON MOUSE DOWN LOGIC
                                         }
                                 });
                 mouseHandlerManager.addHandler(MouseUpEvent.TYPE,
                                 new MouseUpHandler() {
                                         public void onMouseUp(MouseUpEvent event) {
                                                //TODO DO YOUR ON MOUSE UP LOGIC
                                         }
                                 });
                 mouseHandlerManager.addHandler(MouseOverEvent.TYPE,
                                 new MouseOverHandler() {
                                         public void onMouseOver(MouseOverEvent event) {
                                                //TODO DO YOUR ON MOUSE OVER LOGIC
                                         }
                                 });
                 mouseHandlerManager.addHandler(MouseOutEvent.TYPE,
                                 new MouseOutHandler() {
                                         public void onMouseOut(MouseOutEvent event) {
                                                //TODO DO YOUR ON MOUSE OUT LOGIC
                                         }
                                 });             
                 mouseHandlerManager.addHandler(MouseWheelEvent.TYPE,
                                 new MouseWheelHandler() {
                                         public void onMouseWheel(MouseWheelEvent event) {
                                                //TODO DO YOUR ON MOUSE WHEEL LOGIC
                                         }
                                 });
                 mouseHandlerManager.addHandler(ContextMenuEvent.TYPE,
                                 new ContextMenuHandler() {
                                         public void onContextMenu(ContextMenuEvent event) {
                                                //TODO DO YOUR ON CONTEXT MENU LOGIC
                                         }
                                 });
         }
         
 }
``` 

- Add the following line to your `*.gwt.xml` file:

```xml
 <inherits name='org.gz.gwt.mousemanager.mousemanager'/>
``` 

## Analysis

### Summary GWT and Browser Compatibility Analysis

#### Wolter Analysis

Jan Wolter wrote an [article](http://unixpapa.com/js/mouse.html) about browser compatibility around mouse events. Jan's work is concretely used in this library, because it details how browsers deal with mouse events and some mouse event attribute, such as the button attribute.

he following tables list the comparison grids Jan created for single and double click events and have the "correct" behavior highlighted in green (Source: <http://unixpapa.com/js/mouse.html>).

![Simple Click Left Button](http://gwt-mousemanager.appspot.com/images/Single_Click_Left_Button.JPG)
![Simple Click Right button](http://gwt-mousemanager.appspot.com/images/Single_Click_Right_Button.JPG)
![Simple Click Middle button](http://gwt-mousemanager.appspot.com/images/Single_Click_Middle_Button.JPG)
![Double Click Left button](http://gwt-mousemanager.appspot.com/images/Double_Click_Left_Button.JPG)
![Double Click Right button](http://gwt-mousemanager.appspot.com/images/Double_Click_Right_Button.JPG)
![Double Click Middle button](http://gwt-mousemanager.appspot.com/images/Double_Click_Middle_Button.JPG)

The following sequences are not listed in Jan's article:

- _Sequence observed on IE8 for Double Click of Left Mouse Button_

1. Mouse Down 
2. Mouse Up
3. Click
4. Mouse Up
5. Click
6. Double Click

- _Sequence observed on IE8 for Double Click of Middle Mouse Button_

1. Mouse Down
2. Mouse Up
3. Click
4. Mouse Up
5. Click

- _Sequence observed on IE8 for Double Click of Right Mouse Button_

1. Mouse Down 
2. Mouse Up
3. Context Menu
4. Mouse Up
5. Context Menu

#### GWT Implementation

GWT deals with browser compatibility for some features. Google created various DOM implementations for different browsers versions. Events are fired by GWT as they come from the browser. However, some attributes, such as the button value is interpreted by GWT based on the browser version. This enables GWT to return a homogenous response across browsers. Unfortunately, GWT does not handle every browser version. This library is here to complement the work.

 _This analysis is based on GWT 2.3_

**GWT DOM Implementations Class Diagram**

![GWT Implementation](http://gwt-mousemanager.appspot.com/images/GWT_Impl.jpg)

Based on Jan's work and the GWT implementation, we can easily define the GWT compatibilities for features in which we have interest. For example, for `eventGetButton()`, GWT has two distinct implementations.

**DOMImpl** has:

```java
public native int eventGetButton(NativeEvent evt) /*-{
    return evt.button || 0;
  }-*/;
```

**DOMImplStandard** has:
```java
public native int eventGetButton(NativeEvent evt) /*-{
    // All modern browsers return 0, 1, and 2 for left, middle, and right,
    // respectively. Because eventGetButton() is expected to return the IE
    // bitfield norms of 1, 4, and 2, we translate them here.
    var button = evt.button;
    if (button == 1) {
      return 4;
    } else if (button == 2) {
      return 2;
    }
    return 1;
  }-*/;
```
In GWT, button values 1, 4 and 2 correspond to left, middle and right mouse button:

![GWT Get Button Grid](http://gwt-mousemanager.appspot.com/images/GWT_GetButton_Grid.JPG)

The following table illustrates that GWT has a correct interpretation of the button event attributes for modern browsers and old IE browsers. GWT-MouseManager helps you to solve compatibility issues by taking care of browser versions.

So far, I have only analyzed the eventGetButton method implementation. However, other methods must also be analyzed.

## Current Implementation

###Summary Implementation Details Version 1

#### Introduction

Based on Jan's work <http://unixpapa.com/js/mouse.html>, the first release of GET-MouseManager only supports the correct browser behaviors marked in Green in the article.

*“correct” Click Event Sequences:*

![ImplV1 Click Sequences](http://gwt-mousemanager.appspot.com/images/ImplV1_Click_Sequences.JPG)

*“correct” double click event sequences:*

![ImplV1 Double Click Sequences](http://gwt-mousemanager.appspot.com/images/ImplV1_Double_Click_Sequences.JPG)

Context Menu event only fires if it is initially fired by browsers, including browsers that don’t fire the Context Menu Event but should.

## Get Involved
### Ways to Contribute

Has GWT-MouseManager been helpful to you?  If you'd like to give back, here are a few ways:

1. Blog about your experiences using GWT-MouseManager, and let us know about it!
3. Improve the docs in the README.
4. Fix a bug or add a new feature and submit a pull request (see below).

### Roadmap
#### To Do

- Test every browser. Please report bugs by specifying your browser details (brand, version, layout engine, etc...).
- Add more reports in the build process using maven plugins. Code coverage, Static code analysis, cyclomatic complexity, etc...

#### To Look Into

- Performance (use browser version while checking event sequences).
- Return missing or rectify incorrect event attributes values in old browsers.
- Simulate missing events in old browsers when possible. For example the ContextMenuEvent

### Pull Requests

Pull requests are welcome. For any significant change or new feature, please start a discussion or log an issue in the [Google Group](http://groups.google.com/group/gwt-mousemanager) first.  This will save you some time, in case your idea is deemed not general enough to be included in GWT-MouseManager.

Before submitting a pull request, please:

1. Write unit tests to cover any new or modified lines of code, and add it to `src/test/java`.
2. Run the Maven task to build and tests the project. See the [Build](#build) section for more info.

## Build

### Maven

GWT-MouseManager uses [Maven](http://maven.apache.org) to verify each build.  If you are not familiar with Maven, check out the [getting started guide](http://maven.apache.org/guides/getting-started/index.html) for an introduction and installation instructions.

Before submitting a pull request, please run the Maven tasks.  To do so:

First, make sure you can compile without error. 

```
mvn compile
```

Then compile and run tests

```
mvn test
```

Finally make sur you can generate the jar file

```
mvn package
```

and/or run

```
mvn install
```

### Continuous Integration

#### Travis-CI
The Maven build is run automatically using [Travis-CI](travis-ci.org) upon every pull request and push to master.  But if any errors are found, you'll need to fix them and re-submit your pull request.  So please run the grunt task locally to save time.

## Contact

Any feedback/question/request please visit the [discussion group](http://groups.google.com/group/gwt-mousemanager)

If you are using the GWT-MouseManager library in your project, I would greatly appreciate it if you can send me a link to your project so I can get an idea on how it is being used and what kind of features/changes would be good to have in the future.

##Licence

The MIT License (MIT)

Copyright (c) 2013 Gregory

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.



[![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/gzussa/gwt-mousemanager/trend.png)](https://bitdeli.com/free "Bitdeli Badge")

