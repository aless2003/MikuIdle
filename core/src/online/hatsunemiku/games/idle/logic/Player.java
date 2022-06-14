package online.hatsunemiku.games.idle.logic;

import java.util.Objects;

public final class Player {

  private long points;

  public Player(long points) {
    this.points = points;
  }

  public void addPoints(long points) {
    this.points += points;
  }

  public long getPoints() {
    return points;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (obj == null || obj.getClass() != this.getClass())
      return false;
    var that = (Player) obj;
    return this.points == that.points;
  }

  @Override
  public int hashCode() {
    return Objects.hash(points);
  }

  @Override
  public String toString() {
    return "Player[" +
        "points=" + points + ']';
  }

}
