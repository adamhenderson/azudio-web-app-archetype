var dojoConfig = {
	async : true,
	parseOnLoad : true,
	packages : [
	// Application Modules
	{
		name : "app",
		location : "/js/modules"
	},
	// External Libraries on CDNs
	{
		name : 'handlebars',
		location : 'http://cdnjs.cloudflare.com/ajax/libs/handlebars.js/2.0.0'
	}, {
		name : 'dgrid',
		location : '//cdn.rawgit.com/SitePen/dgrid/v0.4.0'
	}, {
		name : 'dstore',
		location : '//cdn.rawgit.com/SitePen/dstore/v1.0.1'
	}, {
		name : 'xstyle',
		location : '//cdn.rawgit.com/kriszyp/xstyle/v0.3.1'
	}, {
		name : 'put-selector',
		location : '//cdn.rawgit.com/kriszyp/put-selector/v0.3.6'
	} ]
};
