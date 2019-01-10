////
//// DO NOT MODIFY THIS FILE
////
//// You don't need to submit it, but you should make sure it compiles.
//// Further explanation appears below.
////

import cs3500.hw01.duration.Duration;
import cs3500.hw01.publication.Publication;

/**
 * This class is provided to check that your code implements the expected API.
 * If your code compiles with an unmodified version of this class, then it very
 * likely will also compile with the tests that we use to evaluate your code.
 */
public class Hw01TypeChecks {
  static Publication webpage =
          new cs3500.hw01.publication.Webpage("The 25 Most Epic Cat Beards Of All Time",
                  "http://www.buzzfeed.com/summeranne/"
                          + "the-25-most-epic-cat-beards-of-all-time",
                  "Fri, 15 May 2015 08:15:49 -0400");

  static void durationHasFormatMethod(Duration duration) {
    String result = duration.format("Template");
  }

  public Hw01TypeChecks() {
    throw new RuntimeException("uninstantiable");
  }
}
