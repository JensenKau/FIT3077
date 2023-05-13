package ninemanmorris.screen.screencontroller;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class GameScreenGrid {

    private static final String RED_TOKEN_IMG = "/img/9mm_token_red.png";
    private static final String BLUE_TOKEN_IMG = "/img/9mm_token_blue.png";

    private static final int DEFAULT_LINE_AMT = 20;

    private static final int LIGHT_INDEX = 0;
    private static final int RED_INDEX = 1;
    private static final int BLUE_INDEX = 2;

    private static final int TOKEN_WIDTH_HEIGHT = 40;
    
    private Pane parentPane;
    private GridPane uiGrid;
    private StackPane[][] stackPanes;
    private ArrayList<Line> lines;
    private IInputHandler inputHandler;


    public GameScreenGrid(GridPane uiGrid, Pane parentPane, IInputHandler inputHandler) {
        this.uiGrid = uiGrid;
        this.stackPanes = new StackPane[7][7];
        this.parentPane = parentPane;
        this.lines = new ArrayList<>(DEFAULT_LINE_AMT);
        this.inputHandler = inputHandler;

        createStackPanes();
        createLines();

        this.uiGrid.addEventHandler(MouseEvent.MOUSE_CLICKED, createGridClickHandler());
    }

    private EventHandler<MouseEvent> createGridClickHandler() {
        return new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Node clickedNode = event.getPickResult().getIntersectedNode();

                if (clickedNode != null) {
                    int rowIndex = GridPane.getRowIndex(clickedNode);
                    int colIndex = GridPane.getColumnIndex(clickedNode);
                    System.out.println("clicked on: " + rowIndex + " " + colIndex);
                    inputHandler.handleInput(rowIndex, colIndex);
                }
            }
        };
    }

    private void createStackPanes() {
        Image redTokenImg = new Image(getClass().getResource(RED_TOKEN_IMG).toExternalForm());
        Image blueTokenImg = new Image(getClass().getResource(BLUE_TOKEN_IMG).toExternalForm());

        for (int i = 0; i < stackPanes.length; i++) {
            for (int j = 0; j < stackPanes[i].length; j++) {
                ImageView red = new ImageView(redTokenImg);
                ImageView blue = new ImageView(blueTokenImg);
                Circle circle = new Circle(27);
                stackPanes[i][j] = new StackPane();

                circle.setEffect(new GaussianBlur());
                circle.setStyle("-fx-fill: #7aee11;");

                red.setFitWidth(TOKEN_WIDTH_HEIGHT);
                red.setFitHeight(TOKEN_WIDTH_HEIGHT);
                blue.setFitWidth(TOKEN_WIDTH_HEIGHT);
                blue.setFitHeight(TOKEN_WIDTH_HEIGHT);

                stackPanes[i][j].getChildren().add(circle);
                stackPanes[i][j].getChildren().add(red);
                stackPanes[i][j].getChildren().add(blue);

                for (Node node : stackPanes[i][j].getChildren()) {
                    node.setVisible(false);
                }

                uiGrid.add(stackPanes[i][j], j, i);
            }
        }
    }

    private void createLines() {
        while (lines.size() < 20) {
            Line currentLine = new Line(0, 0, 0, 0);
            currentLine.setStrokeWidth(15);
            currentLine.setStyle("-fx-stroke: #7aee11;");
            currentLine.setVisible(false);

            lines.add(currentLine);
        }

        this.parentPane.getChildren().addAll(0, lines);
    }

    public void updateAll(Boolean[][] board, boolean[][] interactables, List<int[][]> mills) {
        updateBoard(board);
        updateInteractablePos(interactables);
        updateMill(mills);
    }

    public void updateBoard(Boolean[][] newState) {
        for (int i = 0; i < newState.length; i++) {
            for (int j = 0; j < newState[i].length; j++) {
                if (newState[i][j] == null) {
                    stackPanes[i][j].getChildren().get(RED_INDEX).setVisible(false);
                    stackPanes[i][j].getChildren().get(BLUE_INDEX).setVisible(false);
                } else {
                    stackPanes[i][j].getChildren().get(RED_INDEX).setVisible(newState[i][j]);
                    stackPanes[i][j].getChildren().get(BLUE_INDEX).setVisible(!newState[i][j]);
                }
            }
        }
    }

    public void updateInteractablePos(boolean[][] newState) {
        for (int i = 0; i < newState.length; i++) {
            for (int j = 0; j < newState[i].length; j++) {
                stackPanes[i][j].getChildren().get(LIGHT_INDEX).setVisible(newState[i][j]);
            }
        }
    }

    public void updateMill(List<int[][]> newMills) {
        for (Line line : lines) {
            line.setVisible(false);
        }

        for (int i = 0; i < newMills.size(); i++) {
            int[][] currentTriplets = newMills.get(i);
            int startX = Math.min(currentTriplets[0][0], Math.min(currentTriplets[1][0], currentTriplets[2][0]));
            int startY = Math.min(currentTriplets[0][1], Math.min(currentTriplets[1][1], currentTriplets[2][1]));
            int endX = Math.max(currentTriplets[0][0], Math.max(currentTriplets[1][0], currentTriplets[2][0]));
            int endY = Math.max(currentTriplets[0][1], Math.max(currentTriplets[1][1], currentTriplets[2][1]));

            Line currentLine = lines.get(i);

            currentLine.setStartX(startX);
            currentLine.setStartY(startY);
            currentLine.setEndX(endX);
            currentLine.setEndY(endY);
            currentLine.setVisible(true);
        }
    }
}
