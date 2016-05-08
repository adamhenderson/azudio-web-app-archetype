define([

"dojo/_base/declare", "dstore/Memory", "dstore/Rest" ], function(

declare, Memory, Rest, Trackable

) {

    console.info("DataStoresModule called");

    return {

        personRestStore : new declare([ Rest, Trackable ])({
            target : "/rest/people/",
            sortParam : "sort"
        }),

    }
});