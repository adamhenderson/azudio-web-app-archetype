var startTime = new Date();
require([ "dojo/ready", "app/Application"], function(ready, application) {
	ready(function() {
		application.init();
	});
});
