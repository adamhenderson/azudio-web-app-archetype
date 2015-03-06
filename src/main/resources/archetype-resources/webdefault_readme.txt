#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
Webdefault Readme
------------------

This guide tells you how to turn off memory mapping for Windows which causes file locking stopping you from editing static files when in development.

Jetty Runner Plugin
-------------------
When using Jetty Runner from http://itguides.googlecode.com the plugin will create jetty.xml files for you under /etc.

1. Copy the webdefault.xml file from the project root into the /etc file alongside the jetty.xml files
2. Open the jetty-x.xml &/or jetty-x-ssl.xml files
3. Locate the <New id="WebAppContext"> element and add the following <Set> element under it:

      	<Set name="defaultsDescriptor"><SystemProperty name="jettyrunner.working.dir" />etc/webdefault.xml</Set>


4. Save the files.

Now on startup the webdefault.xml file will be read first.