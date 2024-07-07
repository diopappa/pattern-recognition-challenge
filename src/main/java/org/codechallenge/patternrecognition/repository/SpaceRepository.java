package org.codechallenge.patternrecognition.repository;

import org.codechallenge.patternrecognition.domain.Space;
import org.springframework.stereotype.Repository;

@Repository
public class SpaceRepository {

    private final Space space = new Space();

    public void delete() {
        space.clearSpace();
    }

    public Space get() {
        return space;
    }
}
