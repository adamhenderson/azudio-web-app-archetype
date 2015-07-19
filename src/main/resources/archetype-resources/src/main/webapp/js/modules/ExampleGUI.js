define([

"dgrid/OnDemandGrid", "dojo/store/Memory", "dijit/Tree", "dijit/tree/ObjectStoreModel", "dojo/data/ObjectStore", "dijit/layout/ContentPane", "dojo/text!./ExampleGUI.html", "dijit/registry", "dojo/ready", "dgrid/Tree", "dgrid/Keyboard", "dgrid/Selection" ], function(

OnDemandGrid, Memory, Tree, ObjectStoreModel, ObjectStore, ContentPane, ExampleGUI, registry, ready) {

    // Plug-in the Example GUI into the mainTabContainer
    ready(function() {

        var tc = registry.byId("main-tab-container");

        if(tc==null){
            console.error("Could not load the Example GUI, because the TabContainer 'main-tab-container' could not be found.")
            return;
        }
       

        // Initialise
        init();

    });

    /**
     * Initialise the Example GUI
     */
    function init() {

        registry.byId("main-tab-container").addChild(new ContentPane({
            title : "Examples GUI",
            gutters : false,
            content : ExampleGUI,
            style : {
                padding : 0
            }
        }));
        
        // Example GUI Tab - Grids and Tree
        var exampleGUITab_grid = new OnDemandGrid({
            columns : {
                first : 'First Name',
                last : 'Last Name',
                age : 'Age'
            }
        }, "example-gui-tab-grid");

        exampleGUITab_grid.renderArray([ {
            first : 'Bob',
            last : 'Barker',
            age : 89
        }, {
            first : 'Vanna',
            last : 'White',
            age : 55
        }, {
            first : 'Pat',
            last : 'Sajak',
            age : 65
        } ]);

        // Create test store, adding the getChildren() method required by ObjectStoreModel
        var dataStore = new Memory({
            data : [ {
                id : "/default",
                name : 'default',
                type : 'planet',
                population : '6 billion'
            }, {
                id : "/default/main",
                name : 'main',
                type : 'planet',
                population : '6 billion',
                parent : "/default"
            }, {
                id : 'AF',
                name : 'Africa',
                type : 'continent',
                population : '900 million',
                area : '30,221,532 sq km',
                timezone : '-1 UTC to +4 UTC',
                parent : "/default/main"
            }, {
                id : 'EG',
                name : 'Egypt',
                type : 'country',
                parent : 'AF'
            }, {
                id : 'KE',
                name : 'Kenya',
                type : 'country',
                parent : 'AF'
            }, {
                id : 'Nairobi',
                name : 'Nairobi',
                type : 'city',
                parent : 'KE'
            }, {
                id : 'Mombasa',
                name : 'Mombasa',
                type : 'city',
                parent : 'KE'
            }, {
                id : 'SD',
                name : 'Sudan',
                type : 'country',
                parent : 'AF'
            }, {
                id : 'Khartoum',
                name : 'Khartoum',
                type : 'city',
                parent : 'SD'
            }, {
                id : 'AS',
                name : 'Asia',
                type : 'continent',
                parent : "/default/main"
            }, {
                id : 'CN',
                name : 'China',
                type : 'country',
                parent : 'AS'
            }, {
                id : 'IN',
                name : 'India',
                type : 'country',
                parent : 'AS'
            }, {
                id : 'RU',
                name : 'Russia',
                type : 'country',
                parent : 'AS'
            }, {
                id : 'MN',
                name : 'Mongolia',
                type : 'country',
                parent : 'AS'
            }, {
                id : 'OC',
                name : 'Oceania',
                type : 'continent',
                population : '21 million',
                parent : "/default/main"
            }, {
                id : 'EU',
                name : 'Europe',
                type : 'continent',
                parent : "/default/main"
            }, {
                id : 'DE',
                name : 'Germany',
                type : 'country',
                parent : 'EU'
            }, {
                id : 'FR',
                name : 'France',
                type : 'country',
                parent : 'EU'
            }, {
                id : 'ES',
                name : 'Spain',
                type : 'country',
                parent : 'EU'
            }, {
                id : 'IT',
                name : 'Italy',
                type : 'country',
                parent : 'EU'
            }, {
                id : 'NA',
                name : 'North America',
                type : 'continent',
                parent : "/default/main"
            }, {
                id : 'SA',
                name : 'South America',
                type : 'continent',
                parent : "/default/main"
            } ],
            getChildren : function(object) {
                return this.query({
                    parent : object.id
                });
            }
        });

        // Create the model
        var objectStoreModel = new ObjectStoreModel({
            store : dataStore,
            query : {
                id : "/default"
            }
        });

        // Create the Tree and start it up
        var tree = new Tree({
            model : objectStoreModel
        }, "example-gui-tab-tree");
        tree.startup();

    }

});
