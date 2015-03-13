define([

"dojo/_base/declare", "dstore/Memory", "dstore/Rest", "dstore/Trackable" ], function(

declare, Memory, Rest, Trackable

) {

	return {

		personRestStore : new declare([ Rest, Trackable ])({
			target : "/rest/person/",
			sortParam : "sort"
		}),

		// Store for data about how the page is constructed
		trackableMemoryStore1 : new declare([ Memory, Trackable ])({
			data : [ {
				id : 'page',
				componentDefinition : null,
				name : 'Person',
				type : 'page'
			} ],
			getChildren : function(object) {
				return this.query({
					parent : this.getIdentity(object)
				});
			}
		})
	}
});