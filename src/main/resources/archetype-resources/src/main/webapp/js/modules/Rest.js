require([

"dstore/Memory", "dstore/Rest", "dstore/SimpleQuery", "dstore/Trackable", "dstore/Cache", "dojo/_base/declare", "dojo/on", "dojo/dom", "dojo/request", "dgrid/OnDemandGrid", "dojo/domReady!" ], function(

Memory, Rest, SimpleQuery, Trackable, Cache, declare, on, dom, request, OnDemandGrid

) {

    console.debug("Rest Module Called");

    // Define a trackable Memory store
    var TrackableMemStore = declare([ Memory, Trackable ]);

    // Define a caching Rest store
    var CachingRestStore = declare([ Rest, Cache, SimpleQuery, Trackable ]);

    // Instantiate a Caching Rest Store that uses a trackable Memory store as the caching store

    // This setup means that any writeable actions on this store will go to the server via rest,
    // any read actions will hit the cache first and if misses, will then perform a GET request.
    // The OnDemandGrid will be wired up to the cachingstore to reflect changes.
    var peopleStore = new CachingRestStore({
        target : './rest/people/',
        cachingStore : new TrackableMemStore()
    });

    // Using the dstore api to get, search, create and update entities

    // Fetch all people
    peopleStore.fetch().then(function(people) {
        console.debug("People returned", people);
    });

    // Get an entity with an id of '1'
    peopleStore.get('1').then(function(person) {
        console.debug("Get Person 1", person);
    });

    // Wire up the button to add a new person via the rest store
    on(dom.byId("addPersonButton"), "click", function(evt) {

    peopleStore.add({
        name : "New Person"
    }).then(function(newPerson) {
        console.debug("A New Person", newPerson);
    });

        console.log(personGrid);
        });

    var personGrid = new OnDemandGrid({
        collection : peopleStore.cachingStore,
        columns : {
            id : 'ID',
            name : 'Name'
        }
    }, "person-grid");

});