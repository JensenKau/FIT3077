package ninemanmorris.screen.screencontroller;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class GameScreenGrid {

    private static final String RED_TOKEN_IMG = "/img/9mm_token_red.png";
    private static final String BLUE_TOKEN_IMG = "/img/9mm_token_blue.png";
    
    private GridPane uiGrid;
    private StackPane[][] stackPanes;

    private EventHandler<MouseEvent> gridClickHandler;

    public GameScreenGrid(GridPane uiGrid) {
        this.gridClickHandler = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Node clickedNode = event.getPickResult().getIntersectedNode();

                if (clickedNode != null) {
                    int rowIndex = GridPane.getRowIndex(clickedNode);
                    int colIndex = GridPane.getColumnIndex(clickedNode);
                    System.out.println("clicked on: " + rowIndex + " " + colIndex);
                    // morrisGame.handleInput(rowIndex, colIndex);
                }
            }
        };

        this.uiGrid = uiGrid;
        this.stackPanes = new StackPane[7][7];

        for (int i = 0; i < stackPanes.length; i++) {
            for (int j = 0; j < stackPanes[i].length; j++) {
                stackPanes[i][j] = new StackPane();
                uiGrid.add(stackPanes[i][j], j, i);
            }
        }

        this.uiGrid.addEventHandler(MouseEvent.MOUSE_CLICKED, gridClickHandler);
    }

    public void updateBoard(Boolean[][] newState) {
        
    }


}
