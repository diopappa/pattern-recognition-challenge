package org.codechallenge.patternrecognition.domain;

import lombok.Getter;

import java.util.HashSet;

@Getter
public class Space {
    private final HashSet<Point> points;

    public Space() {
        points = new HashSet<>();
    }

    public void addPoint(Point p){
        points.add(p);
    }

    public void clearSpace() {
        points.clear();
    }
}
