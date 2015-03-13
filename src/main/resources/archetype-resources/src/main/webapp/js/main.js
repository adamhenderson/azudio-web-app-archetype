var startTime = new Date();
require([ "dojo/ready", "app/Application", "dojo/parser", "app/ExampleGUI" ], function(ready, Application) {
    ready(function() {
        Application.init();
    });
});