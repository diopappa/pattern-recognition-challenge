package org.codechallenge.patternrecognition.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.codechallenge.patternrecognition.domain.LineSegment;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetLineSegmentsResponse {
    private List<LineSegment> lines;
}
