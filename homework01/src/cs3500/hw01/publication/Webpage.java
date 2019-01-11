package cs3500.hw01.publication;

/**
 * Represents bibliographic information for webpages.
 */
public class Webpage implements Publication {
  String title;
  String url;
  String retrieved;

  /**
   * Constructs a {@code Webpage} object.
   *
   * @param title     the title of the page
   * @param url       the url to get to the page
   * @param retrieved the date that the page was retreived
   */
  public Webpage(String title, String url, String retrieved) {
    this.title = title;
    this.url = url;
    this.retrieved = retrieved;
  }

  @Override
  public String citeApa() {
    return title + ". (" + retrieved + "). Retrieved from " + url;
  }

  @Override
  public String citeMla() {
    return "\"" + title + ".\" Web. " + retrieved + " <" + url + ">.";
  }
}
