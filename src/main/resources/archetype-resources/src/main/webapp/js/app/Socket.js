define([

"dojox/socket", "app/DataStoresModule" ], function(

socket, stores

) {

    var s = socket({
        // This is an example handler that is part of the tapestry-atmosphere integration
        url : "/tapestryatmospherehandlerexample1"
    });

    s.on("open", function(event) {
        console.info("Successfully connected to server", event);
    });

    s.on("message", function(event) {
        console.info("Message", event.data);
        stores.personRestStore.emit("add", {
            target : {
                id : new Date().getTime(),
                name : new Date().getTime()
            }
        });
    });

    s.on("close", function(event) {
        console.info("Lost connection to the server", event.data);
    });

    return s;

});
