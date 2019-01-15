package unbun.cs3500.lectures.basicInheritance.durations;

public interface Duration extends Comparable<Duration>{
  long inSeconds();
  String asHms();
  Duration plus(Duration d);
  Duration minus(Duration d);
}



