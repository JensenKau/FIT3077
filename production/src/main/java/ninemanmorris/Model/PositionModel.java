package ninemanmorris.Model;

public class PositionModel {

  private int x;
  private int y;
  private boolean isOccupiedByRed;
  private boolean isOccupiedByBlue;

  public PositionModel(int x, int y) {
    this.x = x;
    this.y = y;
    this.isOccupiedByRed = false;
    this.isOccupiedByBlue = false;
  }
}
