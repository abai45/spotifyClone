package kz.spotilab.alikhandroz.dto;

import jakarta.validation.constraints.NotNull;
import kz.spotilab.alikhandroz.vo.SongAuthorVo;
import kz.spotilab.alikhandroz.vo.SongTitleVo;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ReadSongInfoDto {
    private SongTitleVo title;
    private SongAuthorVo author;
    @NotNull
    private byte[] cover;
    @NotNull
    private String coverContentType;
    @NotNull
    private boolean isFavorite;
    @NotNull
    private UUID publicId;
}
