#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
* This package contains the REST Resources for the ${application-name} application. By placing these resource classes here means that the Tynamo Auto-Discovery feature is not used, this is because CommitAfter advice does not work with auto-discovery.
* 
* @see http://stackoverflow.com/questions/14428364/unable-to-persist-entities-with-tynamo-resteasy-when-service-is-in-autodiscover
*/
package ${package}.services;