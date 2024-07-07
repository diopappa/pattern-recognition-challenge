package org.codechallenge.patternrecognition.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddPointRequest {
    @Schema(required = true)
    private int x;
    @Schema(required = true)
    private int y;
}
