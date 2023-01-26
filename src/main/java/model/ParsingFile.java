package model;

import java.util.Objects;

public abstract class ParsingFile {
    public final String title;
    public final String release;
    public final String resolution;
    public final String quality;
    public final String codec;
    public final String audio;
    public final String team;
    public final String language;
    public final String extension;

    protected ParsingFile(String title, String release, String resolution, String quality, String codec, String audio, String team, String language, String extension) {
        this.title = title;
        this.release = release;
        this.resolution = resolution;
        this.quality = quality;
        this.codec = codec;
        this.audio = audio;
        this.team = team;
        this.language = language;
        this.extension = extension;
    }

    public static ParsingFile get(String title, String season, String episode, String year, String release, String resolution, String quality, String codec, String audio, String team, String language, String extension) {
        if (season != null && episode != null)
            return new SeriesEpisodeFile(title, season, episode, release, resolution, quality, codec, audio, team, language, extension);
        else
            return new MovieFile(title, year, release, resolution, quality, codec, audio, team, language, extension);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParsingFile that)) return false;
        return Objects.equals(title, that.title) && Objects.equals(release, that.release) && Objects.equals(resolution, that.resolution) && Objects.equals(quality, that.quality) && Objects.equals(codec, that.codec) && Objects.equals(audio, that.audio) && Objects.equals(team, that.team) && Objects.equals(language, that.language) && Objects.equals(extension, that.extension);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, release, resolution, quality, codec, audio, team, language, extension);
    }
}
