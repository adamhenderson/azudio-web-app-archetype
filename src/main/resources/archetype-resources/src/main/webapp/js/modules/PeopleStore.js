define([

"dstore/Memory", "dstore/Rest", "dstore/SimpleQuery", "dstore/Trackable", "dstore/Cache", "dojo/_base/declare" ], function(

Memory, Rest, SimpleQuery, Trackable, Cache, declare

) {

    console.debug("PeopleStore Module Called");

    // Define a trackable Memory store
    var TrackableMemStore = declare([ Memory, Trackable ]);

    // Define a caching Rest store
    var CachingRestStore = declare([ Rest, Cache, SimpleQuery, Trackable ]);

    // Instantiate a Caching Rest Store that uses a Trackable Memory store as the caching store

    // This setup means that any writeable actions on this store will go to the server via rest,
    // any read actions will hit the cache first and if misses, will then perform a GET request.
    // The OnDemandGrid will be wired up to the cachingstore to reflect changes.
    var peopleStore = new CachingRestStore({
        target : "./rest/people/",
        sortParam : "sort",
        cachingStore : new TrackableMemStore()
    });

    // console.log(peopleStore);

    return peopleStore;

});