package ch.supsi.teencpu.models.matcher;

import ch.supsi.teencpu.models.exceptions.NullParameterException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lines {

    private String input;
    private String splitter;
    private List<Line> inputLines;

    private Lines(String input, String splitter) {
        this.input = input;
        this.splitter = splitter;
        generateInputLines();
    }

    public static Lines createLines(String input, String splitter) throws NullParameterException {
        if (input != null && splitter != null) {
            return new Lines(input, splitter);
        } else
            throw new NullParameterException("Input must no be null");
    }

    public void generateInputLines() {
        List<String> tmpList = new ArrayList<String>(Arrays.asList(input.split(splitter)));
        List<Line> tmpInputLines = new ArrayList<>();
        int newLineCounter = 1;
        for (String s : tmpList) {
            if (s.contains("\n")) {
                newLineCounter++;
                s = s.replace("\n", "");
            }
            s = s.trim();
            if(!s.matches("(\n)*"))
                tmpInputLines.add(new Line(s, newLineCounter));
        }
        this.inputLines = tmpInputLines;
    }

    public String getInput() {
        return input;
    }

    public String getSplitter() {
        return splitter;
    }

    public void setSplitter(String splitter) {
        this.splitter = splitter;
    }

    public List<Line> getInputLines() {
        return inputLines;
    }

    public void printLines() {
        inputLines.stream().forEach((l) -> System.out.println(l.getValue()));
    }

}
