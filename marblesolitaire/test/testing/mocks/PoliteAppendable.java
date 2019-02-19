package testing.mocks;

import java.io.IOException;

public class PoliteAppendable implements Appendable {

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    if (csq.toString().equalsIgnoreCase("Please don't fail.")) {
      return this;
    } else {
      throw new IOException("Please be more polite: " + csq.toString());
    }
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    if (!csq.equals("Please don't fail.")) {
      return this.append("Thank you for being polite.\n");
    } else {
      throw new IOException("Please be more polite");
    }
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("Please be more polite");
  }
}
