require([

"dstore/Memory", "dstore/Rest", "dstore/SimpleQuery", "dstore/Trackable", "dstore/Cache", "dojo/_base/declare", "dojo/on", "dojo/dom", "dojo/request", "dgrid/OnDemandGrid", "dgrid/Keyboard", "dgrid/Selection", "dojo/domReady!" ], function(

Memory, Rest, SimpleQuery, Trackable, Cache, declare, on, dom, request, OnDemandGrid, Keyboard, Selection

) {

    console.debug("Rest Module Called");

    dom.byId("updateSelectedPersonButton").disabled = true;

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

    // Using the dstore api to get, search, create and update entities

    // Load all items into the store
    peopleStore.fetch();

    // Get an entity with an id of '1'
    peopleStore.get("1").then(function(person) {
        console.debug("Get Person 1 (should be a XHR)", person);
    }, function(err) {
        console.error("Could not get Person 1 - oh well at least it was handled", err.message);
    });

    // Demonstrate attempting to get a non-existent person to show error handling
    peopleStore.get("100000").then(function(person) {
        console.error("Person 100000 was found!", person);
    }, function(err) {
        console.error("Could not get Person 100000 - oh well at least it was handled", err.message);
    });

    // Wire up the button to add a new person via the rest store
    on(dom.byId("addPersonButton"), "click", function() {

        peopleStore.add({
            name : "New Person"
        }).then(function(newPerson) {
            console.debug("A New Person", newPerson);
        });

    });

    // Wire up the button to update button to demostrate updating an existing item
    on(dom.byId("updatePerson1Button"), "click", function() {

        peopleStore.get(1).then(function(person) {

            person.name = "Person" + new Date().getTime();

            peopleStore.put(person);

        }, function(err) {

            console.error("Could not get the Person 1 item i order to update it.", err);

        });

    });

    // Define and create a dgrid that will display the list of people with Selection and Keyboard navigation built-in
    var personGrid = new (declare([ OnDemandGrid, Selection, Keyboard ]))({
        collection : peopleStore.cachingStore,
        columns : {
            id : "ID",
            name : "Name"
        },
        sort : "id"
    }, "person-grid");

    // When a row in the grid is selected get the corresponding item from the store
    personGrid.on("dgrid-select", function(event) {

        console.info("The following items were selected...")

        // Get the rows that were just selected
        var rows = event.rows;

        for (var i = 0; i < rows.length; i++) {
            console.debug("Selected item:", rows[i].data);
        }

        // Iterate through all currently-selected items
        // personGrid.selection holds an object whose keys are the item ids that are currently selected
        for ( var id in personGrid.selection) {

            if (personGrid.selection[id]) {
                console.debug(personGrid.selection[id])
            }

        }

    });

    personGrid.on('dgrid-deselect', function(event) {

        console.info("The following items were deselected...", event, personGrid.selection);

        // Get the rows that were just deselected
        var rows = event.rows;

        for (var i = 0; i < rows.length; i++) {
            console.debug("Deselected item:", rows[i].data);
        }

        // Iterate through all currently-selected items
        for ( var id in personGrid.selection) {

            if (personGrid.selection[id]) {
                console.debug(personGrid.selection[id])
            }

        }

    });

    // When a row in the grid is selected enable the update button
    personGrid.on("dgrid-select", function() {

        dom.byId("updateSelectedPersonButton").disabled = false;

    });

    // When the grid has no selection disable the update button
    personGrid.on('dgrid-deselect', function() {

        // NOTE: Using ECMAScript 5 Object.keys() function
        if (Object.keys(personGrid.selection).length === 0) {

            dom.byId("updateSelectedPersonButton").disabled = true;

        }

    });

    on(dom.byId("updateSelectedPersonButton"), "click", function() {

        function createPerson(person) {

            person.name = "Person" + new Date().getTime();

            peopleStore.put(person);

        }

        // Iterate through all currently-selected items
        for ( var id in personGrid.selection) {

            if (personGrid.selection.hasOwnProperty(id)) {
                peopleStore.get(id).then(createPerson(person));
            }

        }

    });

});