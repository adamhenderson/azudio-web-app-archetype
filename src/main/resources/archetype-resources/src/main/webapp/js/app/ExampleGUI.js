define([

"dojo/_base/declare", "dojo/dom-construct", "dojo/ready", "dijit/registry", "dijit/Tree", "dijit/layout/ContentPane", "dgrid/OnDemandGrid", "dgrid/extensions/DijitRegistry", "dstore/Memory", "dojo/text!app/ExampleGUI.html" ], function(

declare, domConstruct, ready, registry, Tree, ContentPane, OnDemandGrid, DijitRegistry, Memory, ExampleGUI) {

    // Plug-in the Example GUI into the mainTabContainer
    ready(function() {

        var tc = registry.byId("main-tab-container");

        if (tc !== undefined) {

            tc.addChild(new ContentPane({
                title : "Examples GUI",
                gutters : false,
                content : ExampleGUI,
                style : {
                    padding : 0
                }
            }));

            // Initialise
            init();
        }
    });

    /**
     * Initialise the Example GUI
     */
    function init() {

        // Example GUI Tab - Grids and Tree

        new declare([ OnDemandGrid, DijitRegistry ])({
            collection : new Memory({
                data : [ {
                    "first" : "Dave",
                    "last" : "Daveson",
                    "age" : 21,
                    "id" : 1
                }, {
                    "first" : "Pete",
                    "last" : "Peteson",
                    "age" : 51,
                    "id" : 2
                }, {
                    "first" : "Nick",
                    "last" : "Nickson",
                    "age" : 44,
                    "id" : 3
                }, {
                    "first" : "Sara",
                    "last" : "Sarson",
                    "age" : 19,
                    "id" : 4
                }, {
                    "first" : "Tim",
                    "last" : "Timson",
                    "age" : 21,
                    "id" : 5
                } ]
            }),
            columns : {
                first : 'First Name',
                last : 'Last Name',
                age : 'Age'
            }

        }, "example-gui-tab-grid");

        // Create test store, adding the getChildren() method required by ObjectStoreModel
        var dataStore = new Memory({
            data : [ {
                "name" : "US Government",
                "id" : "root",
                "children" : [ {
                    "name" : "Congress",
                    "id" : "congress"
                }, {
                    "name" : "Executive",
                    "id" : "exec"
                }, {
                    "name" : "Judicial",
                    "id" : "judicial"
                } ]
            } ],
            getChildren : function(object, onComplete) {
                return onComplete(object.children || []);
            },
            mayHaveChildren : function(item) {
                return "children" in item;
            },
            getRoot : function(onItem) {
                // there should be only a single object in (the root of) this collection,
                // so we just return that
                this.forEach(onItem);
            },
            getLabel : function(object) {
                return object.name;
            }
        });

        // Create the Tree and start it up
        var tree = new Tree({
            model : dataStore,
            getIconClass : function(/* dojo.store.Item */item, /* Boolean */opened) {
                return (!item || this.model.mayHaveChildren(item)) ? (opened ? "dijitFolderOpened" : "dijitFolderClosed") : "dijitLeaf"
            }
        }, "example-gui-tab-tree");
        tree.startup();

    }

});
