#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.services;

/**
 * Core Implementation;
 */
public class CoreImpl implements Core{

	public String getApplicationName() {
		return "${artifactId}";
	}

	public void saveComponent() {
	}

	public void loadComponent() {
	}

	public void duplicateComponent() {
	}

	public void deleteComponent() {
	}

}