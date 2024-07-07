package org.codechallenge.patternrecognition.repository;

import org.codechallenge.patternrecognition.domain.Line;
import org.codechallenge.patternrecognition.domain.LineSegment;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class LineRepository {

    private final HashMap<Line, LineSegment> lines = new HashMap<>();

    public Map<Line, LineSegment> getAll() {
        return lines;
    }

    public void deleteAll() {
        lines.clear();
    }
}
