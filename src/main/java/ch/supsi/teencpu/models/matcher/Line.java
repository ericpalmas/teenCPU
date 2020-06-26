package ch.supsi.teencpu.models.matcher;

public class Line {

    private final String value;
    private final int LineNr;

    public Line(String value, int lineNr) {
        this.value = value;
        LineNr = lineNr;
    }

    public String getValue() {
        return value;
    }

    public int getLineNr() {
        return LineNr;
    }
}
