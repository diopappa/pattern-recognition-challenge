package org.codechallenge.patternrecognition.rest.impl;

import lombok.RequiredArgsConstructor;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.codechallenge.patternrecognition.model.AddPointRequest;
import org.codechallenge.patternrecognition.model.AddPointResponse;
import org.codechallenge.patternrecognition.model.GetLineSegmentsResponse;
import org.codechallenge.patternrecognition.model.GetSpaceResponse;
import org.codechallenge.patternrecognition.rest.IAppController;
import org.codechallenge.patternrecognition.service.IRecognitionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * {@link AppController} implements the APIs listed in {@link IAppController}
 *
 * @author Marco Pappalardo
 * @see IAppController
 * @since 1.0
 */
@RestController
@RequestMapping(
        path = "/api/v1",
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
@RequiredArgsConstructor
public class AppController implements IAppController {
    private final IRecognitionService recognitionService;

    private static final Log log = LogFactory.getLog(AppController.class);

    /**
     * {@link IAppController#addPoint(AddPointRequest)} implementation
     */
    @Override
    @PostMapping(POINT)
    public ResponseEntity<AddPointResponse> addPoint(@RequestBody AddPointRequest request) {
        return ResponseEntity.ok().body(new AddPointResponse(recognitionService.addPoint(request)));
    }

    /**
     * {@link IAppController#getSpace()} implementation
     */
    @Override
    @GetMapping(SPACE)
    public ResponseEntity<GetSpaceResponse> getSpace() {
        return ResponseEntity.ok().body(new GetSpaceResponse(recognitionService.getSpace()));
    }

    /**
     * {@link IAppController#getLinesThroughNPoints(Long)} implementation
     */
    @Override
    @GetMapping(LINES + "/{n}")
    public ResponseEntity<GetLineSegmentsResponse> getLinesThroughNPoints(@PathVariable("n") Long n) {
        return ResponseEntity.ok().body(new GetLineSegmentsResponse(recognitionService.getLinesThroughNPoints(n)));
    }

    /**
     * {@link IAppController#deleteSpace()} implementation
     */
    @Override
    @DeleteMapping(SPACE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteSpace() {
        recognitionService.clearSpace();
    }
}
