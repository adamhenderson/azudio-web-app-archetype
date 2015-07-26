var dojoConfig = {
    async : true,
    parseOnLoad : true,
    baseUrl : '.',
    packages : [
    // Application Modules
    {
        name : "app",
        location : "/js/app"
    },
    // External Libraries on CDNs
    {
        name : 'handlebars',
        location : 'https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.3/handlebars.amd.min.js'
    }, {
        name : 'dgrid',
        location : '//cdn.rawgit.com/SitePen/dgrid/v0.4.0'
    }, {
        name : 'dstore',
        location : '//cdn.rawgit.com/SitePen/dstore/v1.1.0'
    }, {
        name : 'xstyle',
        location : '//cdn.rawgit.com/kriszyp/xstyle/v0.3.1'
    }, {
        name : 'put-selector',
        location : '//cdn.rawgit.com/kriszyp/put-selector/v0.3.6'
    } ]
};
