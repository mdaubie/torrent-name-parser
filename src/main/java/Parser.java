import model.ParsingFile;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Parser {
    //Commented regex published on https://regex101.com/library/HNJVuO
    String regex = """
            ^
            (?<title>[-\\w'"]+(?<separator> [\\s.] )(?: [-\\w'"]+\\2 )*?)
            (?:
            (?! \\d+ \\2 )(?: s (?: \\2? )? )?(?<season> \\d\\d? )(?: e|x (?:\\2? )? )
            (?<episode> \\d\\d? )(?: e\\d\\d? (?:-e?\\d\\d?)? | x\\d\\d? )?\s(?: \\2- )?
            | [(\\[]?(?<year>\\d{4})[)\\]]?
            )
            | (?<release> PROPER | REPACK | LIMITED | EXTENDED | INTERNAL | NEW(?: SOURCE)? | NUKED | UNRATED | .*? EDITION | HC)
            | (?<resolution> \\d{3,4} ?p)
            | (?<quality> HDTV | WEB[-.]?DL | HDDVD | DVDRip | DVD | B[DR]Rip | Blu[-. ]?Ray | HDRip | WEBRIP )
            | (?<codec> XviD | X26[45] | h26[45] | hevc )
            | (?<audio> AC3 | AAC(?:5.1)? | DTS | DD5\\.1)
            | (?:-(?<team>.*?))?
            (?:\\.(?<language>en|fr|eng|fre))?
            (?:\\.(?<extension>mkv|avi|mp4|srt|txt))?
            $
            """;
    //TODO add more video and subtitles extension

    static ParsingFile parseText(String text) {
        int flags = Pattern.CASE_INSENSITIVE | Pattern.COMMENTS;
        final Pattern pattern = Pattern.compile(regex, flags);
        final Matcher matcher = pattern.matcher(text);
        int count = 0;
        String title = null;
        String season = null;
        String episode = null;
        String year = null;
        String release = null;
        String resolution = null;
        String quality = null;
        String codec = null;
        String audio = null;
        String team = null;
        String language = null;
        String extension = null;
        while (matcher.find()) {
            count++;
            try {
                if (count == 1) {
                    char separator = matcher.group("separator").charAt(0);
                    if (matcher.group("title") != null) {
                        title = trimTitle(matcher.group("title"), separator);
                    }
                    season = matchString(season, "season", matcher);
                    episode = matchString(episode, "episode", matcher);
                    year = matchString(year, "year", matcher);
                } else {
                    release = matchString(release, "release", matcher);
                    resolution = matchString(resolution, "resolution", matcher);
                    quality = matchString(quality, "quality", matcher);
                    codec = matchString(codec, "codec", matcher);
                    audio = matchString(audio, "audio", matcher);
                    team = matchString(team, "team", matcher);
                    language = matchString(language, "language", matcher);
                    extension = matchString(extension, "extension", matcher);
                }
            } catch (NullPointerException e) {
                throw new InvalidParameterException("File could not be identified as subtitles or video file, check the extension");
            }
        }
        if (count != 0) {
            return ParsingFile.get(title, season, episode, year, release, resolution, quality, codec, audio, team, language, extension);
        } else {
            throw new InvalidParameterException("File could not be identified as subtitles or video file, check the extension");
        }
    }

    private static String trimTitle(String title, char separator) {
        title = title.replace(separator, ' ');
        if (title.endsWith("- ")) title = title.substring(0, title.length() - 2);
        return title.strip();
    }

    private static String matchString(String target, String name, Matcher matcher) {
        if (matcher.group(name) != null) {
            target = matcher.group(name);
        }
        return target;
    }
}
