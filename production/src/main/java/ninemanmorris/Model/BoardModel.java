package ninemanmorris.Model;

public class BoardModel {
  private final PositionModel[][] BOARD_POSITIONS;
  private final int MORRIS_GAME = 9; // can change depending on the board being played
  private final int MIDDLE_POINT = MORRIS_GAME / 3;
  private final int SIZE = MIDDLE_POINT * 2 + 1;


  public BoardModel() {
    BOARD_POSITIONS = new PositionModel[SIZE][SIZE];

    // add the horizontal and vertical positions
    for (int i = 0; i < MIDDLE_POINT; i++) {
      BOARD_POSITIONS[i][MIDDLE_POINT] = new PositionModel(i, MIDDLE_POINT);
      BOARD_POSITIONS[MIDDLE_POINT][i] = new PositionModel(MIDDLE_POINT, i);
      BOARD_POSITIONS[i + MIDDLE_POINT + 1][MIDDLE_POINT] = new PositionModel(i + MIDDLE_POINT + 1, MIDDLE_POINT);
      BOARD_POSITIONS[MIDDLE_POINT][i + MIDDLE_POINT + 1] = new PositionModel(MIDDLE_POINT, i + MIDDLE_POINT + 1);
    }

    // add the diagonal positions
    for (int i = 0; i < MIDDLE_POINT; i++) {
      BOARD_POSITIONS[i][i] = new PositionModel(i, i);
      BOARD_POSITIONS[i + MIDDLE_POINT + 1][i + MIDDLE_POINT + 1] = new PositionModel(i + MIDDLE_POINT + 1,
          i + MIDDLE_POINT + 1);
      BOARD_POSITIONS[MIDDLE_POINT * 2 - i][i] = new PositionModel(MIDDLE_POINT * 2 - i, i);
      BOARD_POSITIONS[i][MIDDLE_POINT * 2 - i] = new PositionModel(i, MIDDLE_POINT * 2 - i);
    }
  }
  
  public PositionModel getPosition(int x, int y) {
    return BOARD_POSITIONS[x][y];
  }
  
  // check if it is a valid move
  // public boolean isValidMove(int x, int y) {
  //   for (PositionModel position : boardPositions) {
  //     if (position.getX() == x && position.getY() == y) {
  //       return true;
  //     }
  //   }
  //   return false;
  // }

}
