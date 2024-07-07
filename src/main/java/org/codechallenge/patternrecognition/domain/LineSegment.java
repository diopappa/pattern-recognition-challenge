package org.codechallenge.patternrecognition.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;

@Getter
@AllArgsConstructor
public class LineSegment {
    private HashSet<Point> points;

    public void addPoint(Point p){
        points.add(p);
    }
}
