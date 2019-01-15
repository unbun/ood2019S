package unbun.cs3500.lectures.basicInheritance.durations;

//final class means you can't extend from it

public final class DurationImpl extends AbstractDuration {
  private final int hours, minutes, seconds; //final avoids mutation

  /**
   * Constructor for DurationImpl.
   * All values must be greater than zero. Values are simlified for time
   * (i.e. 70 seconds --> 1 minute 10 seconds
   * @param hours hours of durations
   * @param minutes minutes of durations
   * @param seconds seconds of durations
   */
  public DurationImpl(int hours, int minutes, int seconds) {
    if(hours < 0 || minutes < 0 || seconds < 0){
      throw new IllegalArgumentException("Cannot have negative values");
    }

    this.hours = hours;
    this.minutes = minutes;
    this.seconds = seconds;
  }

  public DurationImpl(long inSeconds){
    this(secondsOf(inSeconds), minutesOf(inSeconds), hoursOf(inSeconds));
  }

  @Override
  protected Duration fromSeconds(long s){
    return new DurationImpl(s);
  }

  @Override
  public String asHms() {
    return String.format("%d:%02d:%02d", hours, minutes, seconds);
  }

  @Override
  public long inSeconds() {
    return (long)(hours) * 3600 + (long)(minutes) * 60 + (long)(seconds);
  }
}
