package unbun.cs3500.lectures.basicInheritance.durations;

public final class CompactDuration extends AbstractDuration {
  private final long inSeconds;

  public CompactDuration(long inSeconds){
    this.inSeconds = inSeconds;
  }

  public CompactDuration(int hours, int mins, int seconds){
    this((long)(hours) * 3600 + (long)(mins) * 60 + (long)(seconds));
  }

  @Override
  protected Duration fromSeconds(long s) {
    return new CompactDuration(s);
  }

  public String asHms(){
    return String.format("%d:%02d:%02d", hoursOf(this.inSeconds),
                                          minutesOf(this.inSeconds),
                                          secondsOf(this.inSeconds));
  }

  @Override
  public long inSeconds() {
    return this.inSeconds;
  }
}
