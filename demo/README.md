# GWT-MouseManager Demo

## What is it?

This project is a [Demo](http://gwt-mousemanager.appspot.com) in order to illustrate how GWT-MouseManager works. Check out the [GWT-MouseManager GitHub Repository](https://github.com/gzussa/gwt-mousemanager) for more details.

## Build From Source

1. Make sure to install the [Google Plugin for Eclipse](https://developers.google.com/eclipse/).
2. Check out the sources code from this repo and then import the `demo` project into Eclipse as a Java Project.
3. Open the project **Properties**, and go to **Google -> Web Toolkit Settings...** and make sure that the **Use Google Web Toolkit** checkbox is checked. Make sure that the **GWT SDK** is configured correctly and finally make sure that the **Entry Point Modules** is set with file **demo - org.gz.gwt.mousemanagerdemo**.
3. Open the project **Properties**, and go to **Google -> App Engine Settings...** and make sure that the **Use Google App Engine** checkbox is checked.
4. Now you need to add external dependencies. In your classpath, you should have the **JRE System Library**, the **App Engine SDK** library and the **GWT SDK** library. We need to add the **gwt-g2d** and the **mousemanager** jars to the classpath.
- Regarding the **gwt-g2d** jar, go to `war/WEB-INF/lib`, Right click on the **gwt-g2d** jar and select **Build Path -> Add to Build Path**. You can also go to the [gwt-g2d project page](https://code.google.com/p/gwt-g2d/) and build the project from sources in order to get the latest version or you can download jars from the project [Download page](https://code.google.com/p/gwt-g2d/downloads/list?can=1&q=&colspec=Filename+Summary+Uploaded+ReleaseDate+Size+DownloadCount)
- Regarding the **mousemanager**, You can import the [GWT-MouseManager](https://github.com/gzussa/gwt-mousemanager) project in your Eclipse and add it as a reference project into the Demo **Properties -> Build Path -> Configure Build Path**. Then click on the **Projects** tab, Add your GWT-MouseManager project. Your second option is to build the GWT-MouseManager executable jar, add the jar to `war/WEB-INF/lib` and add the jar into your classpath. Check out [GWT-MouseManager Readme](https://github.com/gzussa/gwt-mousemanager) in order to get more details on how to build a executable jar.
5. Go to **Properties -> Build Path -> Configure Build Path**. Then click on the **Projects** tab, set your Default output folder to `demo/war/WEB-INF/classes` (no quotes).
6. Click OK. The project should refresh without compile errors. If you have installed Java 1.6, GWT and AppEngine correctly and the problem still persists, please post it on the group forum.
7. Compile and run it like a normal GWT project.

## Get Involved
### Ways to Contribute

Have GWT-MouseManager been helpful to you?  If you'd like to give back, here are a few ways:

1. Blog about your experiences using GWT-MouseManager, and let us know about it!
3. Improve the docs in the README.
4. Fix a bug or add a new feature and submit a pull request (see below).

### Pull Requests

Pull requests are welcome. For any significant change or new feature, please start a discussion or log an issue in the [Google Group](http://groups.google.com/group/gwt-mousemanager) first.  This will save you some time, in case your idea is deemed not general enough to be included in GWT-MouseManager.

Before submitting a pull request, please make sure the demo works by manually testing it.

## Contact

Any feedback/question/request please visit the [discussion group](http://groups.google.com/group/gwt-mousemanager)

If you are using the GWT-MouseManager library in your project, I would greatly appreciate it if you can send me a link to your project so I can get an idea on how it is being used and what kind of features/changes would be good to have in the future.

##License

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

