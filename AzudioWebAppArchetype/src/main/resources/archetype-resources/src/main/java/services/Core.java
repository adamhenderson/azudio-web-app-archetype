#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.services;

/**
 * Core Interface
 */
public interface Core {
	public String getApplicationName();
	public void saveComponent();
	public void loadComponent();
	public void duplicateComponent();
	public void deleteComponent();
}
