package io.github.mdaubie.torrentnameparser;

import io.github.mdaubie.torrentnameparser.model.ParsedFilename;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.security.InvalidParameterException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class ParserTest {

    @ParameterizedTest
    @MethodSource("parseValidName")
    void parseValidName(String argument, ParsedFilename expectedResult) {
        ParsedFilename actualResult = FilenameParser.parseFilename(argument);
        assertEquals(actualResult, expectedResult);
    }

    private static Stream<Arguments> parseValidName() {
        String file1 = "The Walking Dead S08E08 How It's Gotta Be.mkv";
        String file2 = "The.Walking.Dead.S07E01.REPACK.720p.HDTV.x265.ShAaNiG.mkv";
        String file3 = "The Walking Dead 7x01 The Day Will Come When You Won't Be.HDTV.KILLERS.en.srt";
        String file4 = "Shame.2011.720p.webrip.x265-RARBG.mp4";
        String file5 = "Whiplash [2014] 1080p 10bit BluRay AAC5.1 HEVC-Vyndros.txt";
        return Stream.of(
                Arguments.of(file1, ParsedFilename.get("The Walking Dead", "8", "8", null, null, null, null, null, null, null, null, "mkv")),
                Arguments.of(file2, ParsedFilename.get("The Walking Dead", "7", "1", null, "REPACK", "720p", "HDTV", "x265", null, null, null, "mkv")),
                Arguments.of(file3, ParsedFilename.get("The Walking Dead", "7", "1", null, null, null, "HDTV", null, null, null, "en", "srt")),
                Arguments.of(file4, ParsedFilename.get("Shame", null, null, "2011", null, "720p", "webrip", "x265", null, "RARBG", null, "mp4")),
                Arguments.of(file5, ParsedFilename.get("Whiplash", null, null, "2014", null, "1080p", "BluRay", "HEVC", "AAC5.1", "Vyndros", null, "txt"))
        );
    }

    @ParameterizedTest
    @MethodSource("parseInvalidName")
    void parseInvalidName(String argument, Class<? extends Exception> exceptionClass) {
        assertThrowsExactly(exceptionClass, () -> FilenameParser.parseFilename(argument));
    }

    private static Stream<Arguments> parseInvalidName() {
        return Stream.of(
                Arguments.of("README.txt", InvalidParameterException.class));
    }
}
