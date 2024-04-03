package co.istad.sokcheatmbankingapi.features.media.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
public record MediaResponse(
        String name,// unique
        String extension,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String contentType, //PNG,JPEG,MP4
        String uri,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long size
) {
}
