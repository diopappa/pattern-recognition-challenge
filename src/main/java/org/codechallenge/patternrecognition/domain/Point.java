package org.codechallenge.patternrecognition.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Point {
    private final int x;
    private final int y;
}
