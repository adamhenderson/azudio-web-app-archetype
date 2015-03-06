var startTime = new Date();
require([ "dojo/ready", "app/Application",  "dojo/parser"], function(ready, Application) {
	ready(function() {
		Application.init();
	});
});