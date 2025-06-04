package postnl.cbs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import postnl.cbs.model.MappingEntry;
import postnl.cbs.model.XsltAnalysisResult;
import postnl.cbs.util.XsltParser;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.util.List;

@Service
public class XsltService {

    private static final Logger logger = LoggerFactory.getLogger(XsltService.class);

    public XsltAnalysisResult analyzeXslt(String xsltContent) throws Exception {
        logger.debug("Starting XSLT analysis");
        validateXslt(xsltContent);

        List<MappingEntry> mappings = XsltParser.extractMappings(xsltContent);
        String sampleInput = "<sample><data>input</data></sample>";
        String sampleOutput = "<result><value>output</value></result>";

        return new XsltAnalysisResult(mappings, sampleInput, sampleOutput);
    }

    private void validateXslt(String content) throws Exception {
        TransformerFactory factory = TransformerFactory.newInstance();
        factory.newTransformer(new StreamSource(new StringReader(content)));
    }
}