#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.pages;

import static org.junit.Assert.assertEquals;

import org.apache.tapestry5.dom.Document;
import org.apache.tapestry5.test.PageTester;
import org.junit.Test;

public class IndexPageTest {

    @Test
    public void test1() {
        String appPackage = "${package}";
        String appName = "app";
        PageTester tester = new PageTester(appPackage, appName, "src/main/webapp");
        Document doc = tester.renderPage("Index");
        assertEquals(doc.getElementById("application-name").getChildMarkup(), "${application-name}");
    }
}
