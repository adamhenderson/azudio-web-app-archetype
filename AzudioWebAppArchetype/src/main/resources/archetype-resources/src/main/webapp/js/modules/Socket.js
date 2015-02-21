define([ 
         
"dojox/socket", "app/DataStoresModule", "dojo/json" ], function(
        		 
socket, stores, JSON

) {

	var s = socket({
		// This is an example handler that is part of the tapestry-atmosphere integration
		url : "/tapestryatmospherehandlerexample1"
	});

	s.on("open", function(event) {
		console.log("Successfully connected to server", event);
	});

	s.on("message", function(event) {
		// We need to split on the "|" character because atmosphere also preprends the response with the message length as multiple messages can be returned in one response
		// var data = JSON.parse(event.data.split("|")[1]);
		console.log("Message", event.data);
		stores.personRestStore.emit("add", {
			target : {
				id : new Date().getTime(),
				name : new Date().getTime()
			}
		});
	});

	s.on("close", function(event) {
		console.log("Lost connection to the server", event.data);
	});

	return s;

});
