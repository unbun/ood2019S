package unbun.cs3500.lectures.lecture2;

public interface Duration extends Comparable<Duration>{
  long inSeconds();
  String asHms();
  Duration plus(Duration d);
  Duration minus(Duration d);
}



