package postnl.cbs.model;

public class MappingEntry {
    private String inputPath;
    private String outputPath;

    public MappingEntry(String inputPath, String outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    public String getInputPath() {
        return inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }
}
