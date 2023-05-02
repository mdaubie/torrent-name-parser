package io.github.mdaubie.torrentnameparser.model;

import java.security.InvalidParameterException;
import java.util.Objects;

public class ParsedSeriesEpisodeFilename extends ParsedFilename {
    public final Integer season;
    public final Integer episode;

    protected ParsedSeriesEpisodeFilename(String title, String season, String episode, String release, String resolution, String quality, String codec, String audio, String team, String language, String extension) {
        super(title, release, resolution, quality, codec, audio, team, language, extension);
        if (season == null) throw new InvalidParameterException("Missing season number");
        if (episode == null) throw new InvalidParameterException("Missing episode number");
        this.season = Integer.valueOf(season);
        this.episode = Integer.valueOf(episode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParsedSeriesEpisodeFilename that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(season, that.season) && Objects.equals(episode, that.episode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), season, episode);
    }
}
