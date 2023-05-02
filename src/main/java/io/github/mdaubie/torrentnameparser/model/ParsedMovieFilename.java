package io.github.mdaubie.torrentnameparser.model;

import java.util.Objects;

public class ParsedMovieFilename extends ParsedFilename {
    public final Integer year;

    protected ParsedMovieFilename(String title, String year, String release, String resolution, String quality, String codec, String audio, String team, String language, String extension) {
        super(title, release, resolution, quality, codec, audio, team, language, extension);
        if (year == null) {
            this.year = null;
            return;
        }
        this.year = Integer.valueOf(year);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParsedMovieFilename that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(year, that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), year);
    }
}
