package org.codechallenge.patternrecognition.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Line {
    private final Double slope;
    private final Double intercept;

    public Line(Point p1, Point p2) {
        this.slope = findSlope(p1, p2);
        this.intercept = findIntercept(p1);
    }

    private Double findSlope(Point p1, Point p2) {
        if ((p2.getX() - p1.getX()) == 0)
            return Double.NaN;

        if ((p2.getY() - p1.getY()) == 0)
            return 0.0;

        return (double) (p2.getY() - p1.getY()) / (double) (p2.getX() - p1.getX());
    }

    private Double findIntercept(Point p) {
        if (Double.isNaN(this.slope))
            return 0D;

        return p.getY() - this.slope * p.getX();
    }
}
