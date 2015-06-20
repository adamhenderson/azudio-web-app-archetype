#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.services;

/**
 * AppCore Implementation;
 */
public class AppCoreImpl implements AppCore{

    @Override
    public String getApplicationName() {
        return "${artifactId}";
    }

}