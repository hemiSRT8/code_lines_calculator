package counter;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Counter {

    private static final Pattern PATTERN = Pattern.compile("(?s)/\\*(.)*?\\*/");
    private boolean isPartOfMultilineComment = false;

    public long countCode(Stream<String> lines) {
        Preconditions.checkNotNull(lines);

        return lines
                .map(String::trim)
                .filter(this::isCodeLine)
                .count();
    }

    private boolean isCodeLine(String line) {
        if (line.isBlank()) {
            return false;
        }

        if (line.startsWith("//")) {
            return false;
        }

        if (line.startsWith("/*") && !line.contains("*/")) {
            isPartOfMultilineComment = true;
            return false;
        } else if (isPartOfMultilineComment && line.endsWith("*/")) {
            isPartOfMultilineComment = false;
            return false;
        } else if (isPartOfMultilineComment) {
            return false;
        }

        List<String> strings = Splitter
                .on(PATTERN)
                .omitEmptyStrings()
                .splitToList(line);

        return strings
                .stream()
                .anyMatch(string -> !string.isBlank() && !string.startsWith("//"));

    }
}
