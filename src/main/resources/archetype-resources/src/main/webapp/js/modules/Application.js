/**
 * This file contains the main Application definition. 
 */
define([

"dojo/_base/declare", "dojo/dom", "dojo/Stateful", "dojo/_base/fx"

], function(

declare, dom, Stateful, fx) {
    
    return new declare("Application", [ Stateful ], {

        init : function() {
       
            // ----------------------------------------------------------
            // Timing Information

            var loadCompleteTime = new Date();
            console.log("Total load time: " + (loadCompleteTime - startTime) + "ms");

            dom.byId('loader-message').innerHTML += " done.";

            // Fade out the Loading splash screen
            setTimeout(function hideLoader() {
                fx.fadeOut({
                    node : 'loader',
                    duration : 500,
                    onEnd : function(n) {
                        n.style.display = "none";
                    }
                }).play();
            }, 500);

            console.info("Initialisation complete");
        },
        
    })();

});
