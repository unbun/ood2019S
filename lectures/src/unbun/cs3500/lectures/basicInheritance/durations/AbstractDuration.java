package unbun.cs3500.lectures.basicInheritance.durations;

abstract class AbstractDuration implements Duration{

  /*An example of dependency inversion:
  @Override
  public Duration plus(Duration d) {
                //parent(AbstractDuration) is depending on DurationImp(child)
    return new DurationImpl(this.hours, this.minutes, (int)(this.seconds + d.inSeconds()));
  }
  */

  //Solution to dependency inversion is A Factory Method:
  protected abstract Duration fromSeconds(long s);

  @Override
  public Duration plus(Duration d){
    return this.fromSeconds(this.inSeconds() + d.inSeconds());
  }



  /***** Conversion Methods *****/

  @Override
  public Duration minus(Duration d) {
    return this.fromSeconds(this.inSeconds() - d.inSeconds());
  }

  protected static int hoursOf(long seconds){
    if(seconds / 3600 > Integer.MAX_VALUE){
      throw new ArithmeticException("too many hours for Integer");
    }
    return (int)(seconds / 3600);
  }

  protected static int minutesOf(long seconds){
    return (int)(seconds / 60) % 60;
  }

  protected static int secondsOf(long seconds){
    return (int)(seconds % 60);
  }


  /***** Comparable/Object inherited methods *****/

  @Override
  public int compareTo(Duration d) {
    return Long.compare(this.inSeconds(), d.inSeconds());
  }

  @Override
  public boolean equals(Object o){
    if(this == o){ //don't need this, checks for intentional equality
      return true;
    }

    if(o instanceof Duration){
      return this.inSeconds() == ((Duration) o).inSeconds();
      //return (Duration o).compareTo(this) == 0;
    }

    return false;
  }

  @Override
  public int hashCode() {
    return Long.hashCode(this.inSeconds());
    //return Object.hash(this.field1, this.field2, this.x...);
  }

  @Override
  public String toString() {
    return "DurationImpl: " + this.asHms();
  }


}
