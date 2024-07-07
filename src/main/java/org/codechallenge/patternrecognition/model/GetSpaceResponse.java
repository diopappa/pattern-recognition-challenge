package org.codechallenge.patternrecognition.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.codechallenge.patternrecognition.domain.Space;

@Getter
@AllArgsConstructor
public class GetSpaceResponse {
    private Space space;
}
