/**
 * This file contains the main Application definition.
 */
define([

"dojo/_base/declare", "dojo/dom", "dojo/Stateful", "dojo/_base/fx"

], function(

declare, dom, Stateful, fx) {

    return new declare("Application", [ Stateful ], {

        loaded : false,

        init : function() {

            if (this.loaded) {
                return;
            }

            // ----------------------------------------------------------
            // Timing Information

            var loadCompleteTime = new Date();
            console.info("Total load time: " + (loadCompleteTime - startTime) + "ms");

            dom.byId('loader-message').innerHTML += " done.";

            // Fade out the Loading splash screen
            setTimeout(function() {
                fx.fadeOut({
                    node : 'loader',
                    duration : 500,
                    onEnd : function(n) {
                        n.style.display = "none";
                    }
                }).play();

                this.loaded = true;

            }, 500);

            console.info("Initialisation complete");
        },

        sayHello : function() {
            return "Hello";
        }

    })();

});
