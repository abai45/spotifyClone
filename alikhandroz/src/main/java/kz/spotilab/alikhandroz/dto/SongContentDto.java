package kz.spotilab.alikhandroz.dto;

import jakarta.persistence.Lob;

import java.util.UUID;

public record SongContentDto(UUID publicId,
                             @Lob byte[] file,
                             String fileContentType) {
}
