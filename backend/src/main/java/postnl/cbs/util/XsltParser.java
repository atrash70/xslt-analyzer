package postnl.cbs.util;

import org.w3c.dom.*;
import org.xml.sax.InputSource;
import postnl.cbs.model.MappingEntry;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class XsltParser {
    public static List<MappingEntry> extractMappings(String xslt) throws Exception {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                .parse(new InputSource(new StringReader(xslt)));

        NodeList valueOfNodes = doc.getElementsByTagName("xsl:value-of");
        List<MappingEntry> mappings = new ArrayList<>();

        for (int i = 0; i < valueOfNodes.getLength(); i++) {
            Element elem = (Element) valueOfNodes.item(i);
            String input = elem.getAttribute("select");
            String output = elem.getParentNode().getNodeName(); // basic approximation
            mappings.add(new MappingEntry(input, output));
        }
        return mappings;
    }
}
