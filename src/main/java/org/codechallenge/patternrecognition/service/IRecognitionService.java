package org.codechallenge.patternrecognition.service;

import org.codechallenge.patternrecognition.domain.Line;
import org.codechallenge.patternrecognition.domain.LineSegment;
import org.codechallenge.patternrecognition.domain.Point;
import org.codechallenge.patternrecognition.domain.Space;
import org.codechallenge.patternrecognition.model.AddPointRequest;
import org.codechallenge.patternrecognition.service.impl.PatternRecognitionService;

import java.util.List;


/**
 * {@link IRecognitionService} provides all methods to preform pattern recognition as required.
 *
 * @author Marco Pappalardo
 * @see PatternRecognitionService
 * @since 1.0
 */
public interface IRecognitionService {
    /**
     * Add a {@link Point} to the {@link Space}.
     *
     * @param payload {@link AddPointRequest} with point coordinates (x, y)
     * @return the {@link Point} created
     */
    Point addPoint(AddPointRequest payload);


    /**
     * Get all {@link Point} in the {@link Space}.
     *
     * @return the {@link Space} containing all {@link Point}
     */
    Space getSpace();

    /**
     * Get all {@link LineSegment} passing through at least N {@link Point}.
     * <p>
     * It will filter every element of the {@link Space} which contains a {@link LineSegment}
     * with a set of {@link Point} objects having size over N.
     *
     * @param n minimum number of {@link Point} objects laying on the {@link Line}
     * @return a list containing all matching {@link LineSegment} in the {@link Space}
     */
    List<LineSegment> getLinesThroughNPoints(Long n);

    /**
     * Remove all {@link Point} from the {@link Space}.
     */
    void clearSpace();
}
