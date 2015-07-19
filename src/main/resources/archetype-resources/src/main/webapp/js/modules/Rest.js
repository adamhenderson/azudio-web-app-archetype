require([

"dstore/Memory", "dstore/Rest", "dstore/SimpleQuery", "dstore/Trackable", "dstore/Cache", "dojo/_base/declare", "dojo/on", "dojo/dom", "dojo/request", "dgrid/OnDemandGrid", "dgrid/Keyboard", "dgrid/Selection", "app/PeopleStore", "dojo/domReady!" ], function(

Memory, Rest, SimpleQuery, Trackable, Cache, declare, on, dom, request, OnDemandGrid, Keyboard, Selection, peopleStore

) {

    console.debug("Rest Module Called", peopleStore);

    dom.byId("updateSelectedPersonButton").disabled = true;

    // Using the dstore api to get, search, create and update entities

    // Load all items into the store
    // peopleStore.fetch();

    // Get an entity with an id of '1'
    peopleStore.get("1").then(function(person) {

        console.debug("Got Person 1", person);

    }, function(err) {

        console.error("Could not get Person 1 - oh well at least it was handled");

    });

    // Demonstrate attempting to get a non-existent person to show error handling
    peopleStore.get("100000").then(function(person) {
    }, function(err) {
        console.error("Could not get Person 100000 - oh well at least it was handled");
    });

    // Wire up the button to add a new person via the rest store
    on(dom.byId("addPersonButton"), "click", function(evt) {

        peopleStore.add({
            name : "New Person"
        }).then(function(newPerson) {
            console.debug("A New Person", newPerson);
        });

    });

    // Wire up the button to update button to demostrate updating an existing item
    on(dom.byId("updatePerson1Button"), "click", function(evt) {

        peopleStore.get(1).then(function(person) {

            person.name = "Person" + new Date().getTime();

            peopleStore.put(person);

        }, function(err) {

            console.error("Could not get the Person 1 item i order to update it.");

        });

    });

    on(dom.byId("updateSelectedPersonButton"), "click", function(event) {

        // Get the rows that were just selected
        var rows = event.rows;

        // Iterate through all currently-selected items
        for ( var id in personGrid.selection) {

            peopleStore.get(id).then(function(person) {

                person.name = "Person" + new Date().getTime();

                peopleStore.put(person);

            });

        }

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
    personGrid.on("dgrid-select", function(event) {

        dom.byId("updateSelectedPersonButton").disabled = false;

    });

    // When the grid has no selection disable the update button
    personGrid.on('dgrid-deselect', function(event) {

        // NOTE: Using ECMAScript 5 Object.keys() function
        if (Object.keys(personGrid.selection).length == 0) {

            dom.byId("updateSelectedPersonButton").disabled = true;

        }

    });

});