package ch.supsi.teencpu.presentation;

import javafx.scene.layout.Pane;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeEditor {


    private ResourceBundle bundle = ResourceBundle.getBundle("i18n/strings");

    private String[] KEYWORDS = {
            bundle.getString("double"),
            bundle.getString("push") + "\\s+" + "\\d+",
            bundle.getString("read"),
            bundle.getString("jump") + "\\s+" + "\\d+",
            bundle.getString("jumpifzero") + "\\s+" + "\\d+",
            bundle.getString("write"),
            bundle.getString("subtract"),
            bundle.getString("sum"),
            bundle.getString("terminate"),
            bundle.getString("pop")
    };

    private final String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
    private final String SEMICOLON_PATTERN = ";";

    private final Pattern PATTERN = Pattern.compile("(?<KEYWORD>" + KEYWORD_PATTERN + ")" + "|(?<SEMICOLON>" + SEMICOLON_PATTERN + ")");


    public void initCodeEditor(Pane codeAreaPane, CodeArea codeArea) {
        // add line numbers to the left of area
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        codeArea.replaceText(0, 0, "");
        codeAreaPane.getChildren().add(new VirtualizedScrollPane<>(codeArea));
        // plain changes = ignore style changes that are emitted when syntax highlighting is reapplied
        // multi plain changes = save computation by not rerunning the code multiple times
        //   when making multiple changes (e.g. renaming a method at multiple parts in file)
        codeArea.multiPlainChanges()
                // do not emit an event until 500 ms have passed since the last emission of previous stream
                .successionEnds(Duration.ofMillis(500))
                // run the following code block when previous stream emits an event
                .subscribe(ignore -> codeArea.setStyleSpans(0, computeHighlighting(codeArea.getText())));
    }

    private StyleSpans<Collection<String>> computeHighlighting(String text) {
        Matcher matcher = PATTERN.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
        while(matcher.find()) {
            String styleClass = matcher.group("KEYWORD") != null ? "keyword"
                    : matcher.group("SEMICOLON") != null ? "semicolon"
                    : null;
            /* never happens */
            assert styleClass != null;
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }

    public Pattern getPATTERN() {
        return PATTERN;
    }
}
