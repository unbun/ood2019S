package cs3500.hw01.publication;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WebpageTest {
  Publication beansWP = new Webpage("The 9 Healthiest Beans and Legumes You Can Eat",
          "https://www.healthline.com/nutrition/healthiest-beans-legumes", "Thurs, 10 Jan 2019");

  @Test
  public void testCiteAPA() {
    assertEquals("The 9 Healthiest Beans and Legumes You Can Eat. "
                    + "(Thurs, 10 Jan 2019). "
                    + "Retrieved from https://www.healthline"
                    + ".com/nutrition/healthiest-beans-legumes",
            beansWP.citeApa());
  }

  @Test
  public void testCiteMLA() {
    assertEquals("\"The 9 Healthiest Beans and Legumes You Can Eat.\" "
                    + "Web. Thurs, 10 Jan 2019 "
                    + "<https://www.healthline.com/nutrition/healthiest-beans-legumes>.",
            beansWP.citeMla());
  }

}