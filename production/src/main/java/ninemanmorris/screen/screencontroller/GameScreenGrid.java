package ninemanmorris.screen.screencontroller;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class GameScreenGrid {

    private static final String RED_TOKEN_IMG = "/img/9mm_token_red.png";
    private static final String BLUE_TOKEN_IMG = "/img/9mm_token_blue.png";

    private static final int DEFAULT_TOKEN_POOL_SIZE = 15;
    private static final int DEFAULT_LIIGHT_POOL_SIZE = 30;
    
    private GridPane uiGrid;
    private StackPane[][] stackPanes;

    private EventHandler<MouseEvent> gridClickHandler;

    private ObjectPool<ImageView> redTokenPool;
    private ObjectPool<ImageView> blueTokenPool;

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

        this.redTokenPool = new ObjectPool<>(DEFAULT_TOKEN_POOL_SIZE, new IObjectPoolHandler<ImageView>() {

            @Override
            public ImageView createItem() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'createItem'");
            }

            @Override
            public ImageView resetItem(ImageView item) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'resetItem'");
            }
            
        });

        this.blueTokenPool = new ObjectPool<>(DEFAULT_TOKEN_POOL_SIZE, new IObjectPoolHandler<ImageView>() {

            @Override
            public ImageView createItem() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'createItem'");
            }

            @Override
            public ImageView resetItem(ImageView item) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'resetItem'");
            }
            
        });
    }

    public void updateBoard(Boolean[][] newState) {
        for (int i = 0; i < newState.length; i++) {
            for (int j = 0; j < newState[i].length; j++) {
                // TODO: retrieve from pool and add it here (be sure to also remove old item and return to pool)
                
            }
        }
    }

    public void updateInteractablePos(boolean[][] newState) {
        for (int i = 0; i < newState.length; i++) {
            for (int j = 0; j < newState[i].length; j++) {
                
            }
        }
    }

    // TODO: figure out how to get mill from backend
    public void updateMill(boolean[][] newState) {

    }
}
