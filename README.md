# Torrent Name Parser

[![version](https://img.shields.io/github/v/release/mdaubie/torrent-name-parser?display_name=tag)](https://github.com/mdaubie/torrent-name-parser/releases/latest)
[![release status](https://github.com/mdaubie/torrent-name-parser/actions/workflows/release.yml/badge.svg)](https://github.com/mdaubie/torrent-name-parser/actions/workflows/release.yml)
![checks status](https://img.shields.io/github/checks-status/mdaubie/torrent-name-parser/master)
[![publish status](https://github.com/mdaubie/torrent-name-parser/actions/workflows/publish.yml/badge.svg)](https://github.com/mdaubie/torrent-name-parser/actions/workflows/publish.yml)
[![license](https://img.shields.io/github/license/mdaubie/torrent-name-parser)](https://github.com/mdaubie/torrent-name-parser/blob/master/LICENSE)

Library for parsing filenames in torrent convention using regexes

### Notes

- Pattern: the chosen pattern is based on the naming conventions of files shared via torrent (p2p), more precisely the
  films and series files
- Regex: This regex is quite flexible, so it works with most files, I published
  it [here](https://regex101.com/library/HNJVuO) (there is two versions available, the v1 is more complete but more
  demanding)

### Related projects

I am working on a web app project to handle my collection of downloaded movies and series, you can find it
here: [The Movie Shelf](https://github.com/mdaubie/movie-shelf)

This is a personal project for my needs, so it probably won't be interesting for you (I might actually keep it private),
but I need to develop some libraries for this main project which might be useful to you:

- [MKV Toolbox](https://github.com/mdaubie/mkv-toolbox)
- [Srt Parser](https://github.com/mdaubie/srt-parser)
- [Color of Film](https://github.com/mdaubie/color-of-film)
