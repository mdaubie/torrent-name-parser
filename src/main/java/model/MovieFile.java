package model;

import java.util.Objects;

public class MovieFile extends ParsingFile {
    public final Integer year;

    protected MovieFile(String title, String year, String release, String resolution, String quality, String codec, String audio, String team, String language, String extension) {
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
        if (!(o instanceof MovieFile that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(year, that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), year);
    }
}
