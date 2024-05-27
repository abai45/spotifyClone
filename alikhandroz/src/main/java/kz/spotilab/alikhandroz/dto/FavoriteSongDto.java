package kz.spotilab.alikhandroz.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record FavoriteSongDto(@NotNull boolean favorite,
                              @NotNull UUID publicId) {
}
