package ca.kevinlowe;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String p = "<p>this is the paragraph.</p>";
        System.out.println(StringEscapeUtils.escapeHtml4(p));
    }
}
