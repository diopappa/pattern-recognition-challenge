# pattern-recognition-challenge

## Programming Test - Pattern Recognition

### Pattern Recognition


Computer vision involves analyzing patterns in visual images and reconstructing the real world objects that
produced them. The process in often broken up into two phases: feature detection and pattern recognition.
Feature detection involves selecting important features of the image; pattern recognition involves
discovering patterns in the features. We will investigate a particularly clean pattern recognition problem
involving points and line segments. This kind of pattern recognition arises in many other applications, for
example statistical data analysis.

### Problem to solve

Given a set of N feature points in the plane, determine every line that contains N or more of the points, and
return all points involved. You should also expose a REST API that will allow the caller to:

- Add a point to the space
```
    POST /point with body { "x": ..., "y": ... }
```

- Get all points in the space
```
    GET /space
```

- Get all line segments passing through at least N points. Note that a line segment should be a set of
points.
```
    GET /lines/{n}
```
- Remove all points from the space
```
    DELETE /space
```

### Additional rules
- All code should be under version control, on a publicly accessible git repository (e.g., a GitHub
repository);
- Unless specified in the instructions above, the API should consume and produce JSON;
- The languages you can choose to implement are: Java, Kotlin, JavaScript. Other web/JVM based
languages could be taken into consideration.

### Suggestions
- Properly naming variables and documenting the code can help us understand your solution;
- Validating all inputs to your program will help your solution pass our test cases;
- There is no bound on the computational complexity of the solution, but solutions with good
computational complexity will earn you bonus points.

## Implementation

### Assumptions
- The term "line" has been considered as "straight line", so a "line that contains N or more of the points"
means that "at least N points lie on the same straight line".
- In the sentence "line segment should be a set of points", the tern "set" has been considered as mathematical
set, so no ordering has been taken into considerations.

### Algoritm - Part 1: Insertion
Every time a point is added to the space it is checked against other present points. 

1.  If point is already present, an error message is returned

2.  For each point in the space is then created a straight line. A line is uniquely identified by its parameters:

   - slope: angular coefficient of straight line
   - intercept: point of intersection between straight line and y axis

3. The line is put into a map as a key, and its corresponding line segment as value. If the key is already in
the map, the new point will be added to the set of points defining the line segment. 

*N.W.: The easiest way of implementing logic for the **GET /lines/{n}** api is to create the map right at the
point addition. Of course this will increase time for this operation, instead of the get one. However, it's a
widespread, commonly accepted belief that creation would require more time than reception, so this solution is
considered valid by the author.*

4. Created point is the stored and returned as part of the response

### Algoritm - Part 2: Reception
Due to the choice of storing lines in a map, it's enough checking which value of the map, corresponding to the line segment,
have a set of points which have size greater than or equal to the parameter N of the api.

### Testing
Tests where implemented using MockMVC.

### Coverage
Jacoco plugin has been added with the requirement of 0.8 of line coverage.

### Documentation
A basic javadoc, which is created and added to the jar on build, has been added mainly to service class.
For APIs, Swagger has been configured.

### Persistence
Since it's the implementation of a image pattern recognition, it is supposed to be run at runtime, so no persistance has
been implemented. 
