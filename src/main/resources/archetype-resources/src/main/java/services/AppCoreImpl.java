#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.services;

/**
 * AppCore Implementation;
 */
public class AppCoreImpl implements AppCore{

    public String getApplicationName() {
        return "${artifactId}";
    }

}