package ninemanmorris.Model;

public class PlayerModel {
  private String name;
  private int score;
  private boolean isRed;
  private int tokenCount;

  public PlayerModel(String name, boolean isRed) {
    this.name = name;
    this.isRed = isRed;
    this.score = 0;
    this.tokenCount = 9; // changable depending on the board
  }

  public String getName() {
    return name;
  }

  // public int getScore() {
  //   return score;
  // }

  // public boolean isRed() {
  //   return isRed;
  // }

  // public void setName(String name) {
  //   this.name = name;
  // }

  // public void setScore(int score) {
  //   this.score = score;
  // }

  // public void setRed(boolean isRed) {
  //   this.isRed = isRed;
  // }

  public int getTokenCount() {
    return tokenCount;
  }

  public void setTokenCount(int newTokenCount) {
    this.tokenCount = newTokenCount;
  }
}