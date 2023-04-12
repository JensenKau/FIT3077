package ninemanmorris.Model;

public class BoardModel {
  private final PositionModel[] boardPositions;

  public BoardModel() {
    boardPositions = new PositionModel[24];
    int empty_position = 0; // just to track the empty position in the array

    // add the horizontal and vertical positions
    for (int i = -3; i < 0; i++) {
      boardPositions[empty_position++] = new PositionModel(i, 0);
      boardPositions[empty_position++] = new PositionModel(0, i);
      boardPositions[empty_position++] = new PositionModel(i * -1, 0);
      boardPositions[empty_position++] = new PositionModel(0, i * -1);
    }

    // add the diagonal positions
    for (int i = -3; i < 0; i++) {
      boardPositions[empty_position++] = new PositionModel(i, i);
      boardPositions[empty_position++] = new PositionModel(i * -1, i * -1);
      boardPositions[empty_position++] = new PositionModel(i, i * -1);
      boardPositions[empty_position++] = new PositionModel(i * -1, i);
    }
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
