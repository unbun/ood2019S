////
//// YOU SHOULD NOT MODIFY THIS FILE
////
//// (You don't need to submit it, either.)
////

package unbun.cs3500.lectures.basicInheritance.publications;

/**
 * Specifies operations for formatting citations from bibliographic data.
 */
public interface Publication {
  /**
   * Formats a citation in APA style.
   *
   * @return the formatted citation
   */
  String citeApa();

  /**
   * Formats a citation in MLA style.
   *
   * @return the formatted citation
   */
  String citeMla();
}
