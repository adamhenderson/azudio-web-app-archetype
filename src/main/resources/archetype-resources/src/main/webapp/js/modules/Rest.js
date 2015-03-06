require([

"dojo/_base/declare", "app/DataStoresModule", "dgrid/OnDemandGrid", "dgrid/Selection", "app/Socket", "dojo/domReady!" ], function(

declare, datastores, DGrid, Selection, Socket

) {

	new declare([ DGrid, Selection ])({
		collection : datastores.personStore.track(),
		columns : {
			id : "ID",
			name : "Name"
		}
	}, "restGrid");

});