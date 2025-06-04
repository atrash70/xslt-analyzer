package postnl.cbs.model;

import java.util.List;

public class XsltAnalysisResult {
    private List<MappingEntry> mappings;
    private String sampleInput;
    private String sampleOutput;

    public XsltAnalysisResult(List<MappingEntry> mappings, String sampleInput, String sampleOutput) {
        this.mappings = mappings;
        this.sampleInput = sampleInput;
        this.sampleOutput = sampleOutput;
    }

    public List<MappingEntry> getMappings() {
        return mappings;
    }

    public String getSampleInput() {
        return sampleInput;
    }

    public String getSampleOutput() {
        return sampleOutput;
    }
}
