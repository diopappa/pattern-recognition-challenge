package org.codechallenge.patternrecognition.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.codechallenge.patternrecognition.domain.Point;

@AllArgsConstructor
@Getter
public class AddPointResponse {
    private final Point point;
}
