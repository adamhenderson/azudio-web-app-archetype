define([

"dojo/_base/declare", "dojo/dom", "dojo/dom-class", "dojo/dom-style", "dojo/dom-construct", "dojo/on", "dojo/_base/array", "dojo/_base/window", "dojo/_base/fx", "dojo/query", "dojo/_base/lang", "dojo/dom-attr", "dojo/Stateful", "dojo/store/Observable", "dojo/_base/event", "dijit/registry", "dijit/Tooltip", "dgrid/OnDemandGrid", "dojo/store/Memory", "dijit/Tree", "dijit/tree/ObjectStoreModel", "dojo/data/ObjectStore", "dgrid/Tree", "dgrid/Keyboard", "dgrid/Selection", "dojo/parser", "dojo/NodeList-traverse" ], function(

declare, dom, domClass, domStyle, domConstruct, on, array, win, fx, query, lang, attr, Stateful, Observable, event, registry, tooltip, OnDemandGrid, Memory, Tree, ObjectStoreModel, ObjectStore) {

	return new declare("Application", [ Stateful ], {

		init : function() {

			// Grid
			var realtimePersonGrid = new OnDemandGrid({
				columns : {
					first : 'First Name',
					last : 'Last Name',
					age : 'Age'
				}
			}, "realtimePersonGrid");

			realtimePersonGrid.renderArray([ {
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

			// Teamsite GUI Tab - Grids and Tree
			var teamsiteGUITab_grid = new OnDemandGrid({
				columns : {
					first : 'First Name',
					last : 'Last Name',
					age : 'Age'
				}
			}, "teamsiteGUITab_grid");
			
			teamsiteGUITab_grid.renderArray([ {
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
			var myStore = new Memory({
				data : [ {
					id : "/default",
					name : 'default',
					type : 'planet',
					population : '6 billion'
				},{
					id : "/default/main",
					name : 'main',
					type : 'planet',
					population : '6 billion',
					parent:"/default"
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
			var myModel = new ObjectStoreModel({
				store : myStore,
				query : {
					id : "/default"
				}
			});

			// Create the Tree.
			var tree = new Tree({
				model : myModel
			},"teamsiteGUITab_tree");
			tree.startup();

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
		}

	})();

});
