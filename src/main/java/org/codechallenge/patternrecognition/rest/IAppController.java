package org.codechallenge.patternrecognition.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.codechallenge.patternrecognition.domain.Line;
import org.codechallenge.patternrecognition.domain.LineSegment;
import org.codechallenge.patternrecognition.domain.Point;
import org.codechallenge.patternrecognition.model.AddPointRequest;
import org.codechallenge.patternrecognition.model.AddPointResponse;
import org.codechallenge.patternrecognition.model.GetLineSegmentsResponse;
import org.codechallenge.patternrecognition.model.GetSpaceResponse;
import org.codechallenge.patternrecognition.rest.impl.AppController;
import org.springframework.http.ResponseEntity;


/**
 * {@link IAppController} provides a list of APIs to preform pattern recognition as required.
 *
 * @author Marco Pappalardo
 * @see AppController
 * @since 1.0
 */
public interface IAppController {
    String POINT = "/point";
    String SPACE = "/space";
    String LINES = "/lines";

    /**
     * Add a {@link Point} to the space.
     * <p>
     * The API response codes are:
     * <ul>
     *     <li>200 - Point added successfully</li>
     *     <li>400 - Bad or missing input data</li>
     *     <li>406 - Point already added</li>
     * </ul>
     *
     * @param payload {@link AddPointRequest} with point coordinates (x, y)
     * @return a json containing {@link Point} created
     */
    @Operation(summary = "Add a point to the space")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Point added successfully"),
            @ApiResponse(responseCode = "400", description = "Bad or missing input data"),
            @ApiResponse(responseCode = "406", description = "Point already added")
    })
    ResponseEntity<AddPointResponse> addPoint(AddPointRequest payload);

    /**
     * Get all {@link Point} in the space.
     * <p>
     * The API response codes are:
     * <ul>
     *     <li>200 - All points in the space</li>
     * </ul>
     *
     * @return a json containing all {@link Point} in the space
     */
    @Operation(summary = "Get all points in the space")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All points in the space")
    })
    ResponseEntity<GetSpaceResponse> getSpace();

    /**
     * Get all {@link LineSegment} passing through at least N {@link Point}.
     * <p>
     * The API response codes are:
     * <ul>
     *     <li>200 - All matching {@link LineSegment} in the space</li>
     *     <li>406 - N should be at least 2</li>
     * </ul>
     *
     * @param n minimum number of {@link Point} objects laying on the {@link Line}
     * @return a json containing all matching {@link LineSegment} in the space
     */
    @Operation(summary = "Get all line segments passing through at least N points")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All matching line segments in the space"),
            @ApiResponse(responseCode = "406", description = "Line segment should have a minimum set size of 2")
    })
    ResponseEntity<GetLineSegmentsResponse> getLinesThroughNPoints(Long n);

    /**
     * Remove all {@link Point} from the space.
     * <p>
     * The API response codes are:
     * <ul>
     *     <li>204 - All {@link Point} deleted from the space</li>
     * </ul>
     */
    @Operation(summary = "Remove all points from the space")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Space deleted")
    })
    void deleteSpace();
}
