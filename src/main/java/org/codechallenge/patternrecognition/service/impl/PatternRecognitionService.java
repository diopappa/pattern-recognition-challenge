package org.codechallenge.patternrecognition.service.impl;

import org.codechallenge.patternrecognition.domain.Line;
import org.codechallenge.patternrecognition.domain.LineSegment;
import org.codechallenge.patternrecognition.domain.Point;
import org.codechallenge.patternrecognition.domain.Space;
import org.codechallenge.patternrecognition.model.AddPointRequest;
import org.codechallenge.patternrecognition.repository.LineRepository;
import org.codechallenge.patternrecognition.repository.SpaceRepository;
import org.codechallenge.patternrecognition.rest.IAppController;
import org.codechallenge.patternrecognition.rest.impl.AppController;
import org.codechallenge.patternrecognition.service.IRecognitionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * {@link AppController} implements the APIs listed in {@link IAppController}
 *
 * @author Marco Pappalardo
 * @see IAppController
 * @since 1.0
 */
@Service
public class PatternRecognitionService implements IRecognitionService {

    private final SpaceRepository spaceRepository;
    private final LineRepository lineRepository;

    public PatternRecognitionService(SpaceRepository spaceRepository, LineRepository lineRepository) {
        this.spaceRepository = spaceRepository;
        this.lineRepository = lineRepository;
    }

    /**
     * {@link IRecognitionService#addPoint(AddPointRequest)} implementation
     */
    @Override
    public Point addPoint(AddPointRequest payload) {
        //STEP 1:CREATE NEW POINT
        Point newPoint = new Point(payload.getX(), payload.getY());

        //STEP 2: INPUT VALIDATION
        validateInsert(newPoint);

        //STEP 3: ADD NEW LINES PASSING THROUGH NEW POINT
        Space space = spaceRepository.get();
        HashMap<Line, LineSegment> pointsAligned = (HashMap<Line, LineSegment>) lineRepository.getAll();

        space.getPoints().forEach(point -> {
            Line newLine = new Line(newPoint, point);
            pointsAligned.compute(newLine, (line, lineSegment) -> {
                if( lineSegment == null)
                    lineSegment = new LineSegment(new HashSet<>(Arrays.asList(point, newPoint)));
                else
                    lineSegment.addPoint(newPoint);

                return lineSegment;
            });
        });

        //STEP 4: STORE NEW POINT
        space.addPoint(newPoint);

        //STEP 5: RETURN IT
        return newPoint;
    }


    private void validateInsert(Point newPoint) throws ResponseStatusException {
        if (spaceRepository.get().getPoints().contains(newPoint))
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Point already added");
    }

    private void validateGetLinesThroughNPoints(Long n) throws ResponseStatusException {
        if (n < 2)
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Line segment should have a minimum set size of 2");
    }

    /**
     * {@link IRecognitionService#getSpace()} implementation
     */
    @Override
    public Space getSpace() {
        return spaceRepository.get();
    }

    /**
     * {@link IRecognitionService#clearSpace()} implementation
     */
    public void clearSpace() {
        spaceRepository.delete();
        lineRepository.deleteAll();
    }

    /**
     * {@link IRecognitionService#getLinesThroughNPoints(Long)} implementation
     */
    public List<LineSegment> getLinesThroughNPoints(Long n) {
        validateGetLinesThroughNPoints(n);
        return lineRepository.getAll().values().stream().filter(lineSegment -> lineSegment.getPoints().size()>=n).collect(Collectors.toList());
    }
}
