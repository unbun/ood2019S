package unbun.cs3500.lectures.lecture2;

//final class means you can't extend from it

public final class DurationImpl implements Duration {
  private final int hours, minutes, seconds; //final avoids mutation

  /**
   * Constructor for DurationImpl.
   * All values must be greater than zero. Values are simlified for time
   * (i.e. 70 seconds --> 1 minute 10 seconds
   * @param hours hours of durations
   * @param minutes minutes of duration
   * @param seconds seconds of duration
   */
  public DurationImpl(int hours, int minutes, int seconds) {
    if(hours < 0 || minutes < 0 || seconds < 0){
      throw new IllegalArgumentException("Cannot have negative Duration");
    }

    this.seconds = seconds % 60;
    this.minutes = minutes + seconds / 60;
    this.hours = hours + minutes / 60;
  }

  @Override
  public long inSeconds() {
    return hours * 3600 + minutes * 60 + seconds;
  }

  @Override
  public String asHms() {
    return String.format("%2s" ,hours) + ":" + String.format("%2s", minutes)
            + ":" + String.format("%2s", seconds);
  }

  @Override
  public Duration plus(Duration d) {
    return null;
  }

  @Override
  public Duration minus(Duration d) {
    return null;
  }

  @Override
  public int compareTo(Duration o) {
    return 0;
  }
}
