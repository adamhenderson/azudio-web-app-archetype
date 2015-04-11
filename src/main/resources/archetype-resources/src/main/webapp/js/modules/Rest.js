require([

"dstore/Rest", "dojo/domReady!" ], function(

Rest

) {

    console.debug("Rest Module Called");

    var peopleStore = new Rest({
        target : '/rest/people/'
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

    // Add new person to the store
    peopleStore.add({
        name : "New Person"
    }).then(function(newPerson) {
        console.debug("A New Person", newPerson);
    });

    // Add new person to the store
    peopleStore.put({
        id : 1,
        name : "Updated" + new Date().getTime()
    }).then(function(newPerson) {
        console.debug("Updated a Person", newPerson);

        peopleStore.fetch().then(function(people) {
            console.debug("People returned", people);
        });

    });

});