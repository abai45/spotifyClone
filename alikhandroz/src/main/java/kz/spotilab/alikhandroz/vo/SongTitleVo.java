package kz.spotilab.alikhandroz.vo;

import jakarta.validation.constraints.NotBlank;

public record SongTitleVo(@NotBlank String value) {
}
