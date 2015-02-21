var startTime = new Date();
require([ "dojo/ready", "app/Application", "dojo/domReady!" ], function(ready, application) {
	ready(function() {
		application.init();
	});
});
