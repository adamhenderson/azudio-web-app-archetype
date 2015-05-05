var startTime = new Date();
require([ "dojo/ready", "app/Application", "dojo/parser", "app/ExampleGUI","app/Rest" ], function(ready, Application) {
    ready(function() {
        Application.init();
    });
});