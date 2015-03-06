#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.services;

/**
 * AppCore Interface
 */
public interface AppCore {
	public String getApplicationName();
	public void saveComponent();
	public void loadComponent();
	public void duplicateComponent();
	public void deleteComponent();
}
