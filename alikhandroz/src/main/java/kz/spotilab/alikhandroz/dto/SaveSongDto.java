package kz.spotilab.alikhandroz.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import kz.spotilab.alikhandroz.vo.SongAuthorVo;
import kz.spotilab.alikhandroz.vo.SongTitleVo;

public record SaveSongDto(@Valid SongTitleVo songTitleVo,
                          @Valid SongAuthorVo songAuthorVo,
                          @NotNull byte[] cover,
                          @NotNull String coverContentType,
                          @NotNull byte[] file,
                          @NotNull String fileContentType) {
}
