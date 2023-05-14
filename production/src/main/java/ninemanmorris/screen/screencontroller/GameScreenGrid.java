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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import ninemanmorris.shared.MoveType;

public class GameScreenGrid {

    private static final String RED_TOKEN_IMG = "/img/9mm_token_red.png";
    private static final String BLUE_TOKEN_IMG = "/img/9mm_token_blue.png";

    private static final String PICKUP_AUDIO = "/audio/pickup.mp3";

    private static final int DEFAULT_LINE_AMT = 20;

    private static final int GREEN_LIGHT_INDEX = 0;
    private static final int WHITE_LIGHT_INDEX = 1;
    private static final int YELLOW_LIGHT_INDEX = 2;
    private static final int RED_INDEX = 3;
    private static final int BLUE_INDEX = 4;

    private static final int TOKEN_WIDTH_HEIGHT = 40;
    private static final int CIRCLE_SIZE = 27;
    
    private Pane parentPane;
    private GridPane uiGrid;
    private StackPane[][] stackPanes;
    private ArrayList<Line> lines;
    private IInputHandler inputHandler;
    private MediaPlayer mediaPlayer;


    public GameScreenGrid(GridPane uiGrid, Pane parentPane, IInputHandler inputHandler) {
        this.uiGrid = uiGrid;
        this.stackPanes = new StackPane[7][7];
        this.parentPane = parentPane;
        this.lines = new ArrayList<>(DEFAULT_LINE_AMT);
        this.inputHandler = inputHandler;

        this.mediaPlayer = new MediaPlayer(new Media(getClass().getResource(PICKUP_AUDIO).toExternalForm()));

        createStackPanes();
        createLines();

        this.uiGrid.addEventHandler(MouseEvent.MOUSE_CLICKED, createGridClickHandler());
    }

    private EventHandler<MouseEvent> createGridClickHandler() {
        return new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Node clickedNode = event.getPickResult().getIntersectedNode();

                if (clickedNode.getParent() != uiGrid) {
                    clickedNode = clickedNode.getParent();
                }

                if (clickedNode != null) {
                    int rowIndex = GridPane.getRowIndex(clickedNode);
                    int colIndex = GridPane.getColumnIndex(clickedNode);
                    System.out.println("clicked on: " + rowIndex + " " + colIndex);
                    mediaPlayer.seek(mediaPlayer.getStartTime());
                    mediaPlayer.play();
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
                Circle greenCircle = new Circle(CIRCLE_SIZE);
                Circle whiteCircle = new Circle(CIRCLE_SIZE);
                Circle yellowCircle = new Circle(CIRCLE_SIZE);
                stackPanes[i][j] = new StackPane();

                greenCircle.setEffect(new GaussianBlur());
                greenCircle.setStyle("-fx-fill: #7aee11;");
                whiteCircle.setEffect(new GaussianBlur());
                whiteCircle.setStyle("-fx-fill: #ffffff;");
                yellowCircle.setEffect(new GaussianBlur());
                yellowCircle.setStyle("-fx-fill: #e6cc00;");

                red.setFitWidth(TOKEN_WIDTH_HEIGHT);
                red.setFitHeight(TOKEN_WIDTH_HEIGHT);
                blue.setFitWidth(TOKEN_WIDTH_HEIGHT);
                blue.setFitHeight(TOKEN_WIDTH_HEIGHT);

                stackPanes[i][j].getChildren().addAll(new Node[] {
                    greenCircle,
                    whiteCircle,
                    yellowCircle,
                    red,
                    blue
                });

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
            currentLine.setStrokeWidth(20);
            currentLine.setEffect(new GaussianBlur());
            currentLine.setStyle("-fx-stroke: #7aee11;");
            currentLine.setVisible(false);

            lines.add(currentLine);
        }

        this.parentPane.getChildren().addAll(0, lines);
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

    public void updateInteractablePos(boolean[][] newState, MoveType movetype) {
        for (int i = 0; i < newState.length; i++) {
            for (int j = 0; j < newState[i].length; j++) {
                if (movetype == MoveType.SELECT_PHASE) {
                    stackPanes[i][j].getChildren().get(WHITE_LIGHT_INDEX).setVisible(newState[i][j]);
                    stackPanes[i][j].getChildren().get(GREEN_LIGHT_INDEX).setVisible(false);
                } else {
                    stackPanes[i][j].getChildren().get(WHITE_LIGHT_INDEX).setVisible(false);
                    stackPanes[i][j].getChildren().get(GREEN_LIGHT_INDEX).setVisible(newState[i][j]);
                }
            }
        }
    }

    public void updateSelectedPos(int[] newPos) {
        for (int i = 0; i < stackPanes.length; i++) {
            for (int j = 0; j < stackPanes[0].length; j++) {
                if (newPos != null && i == newPos[0] && j == newPos[1]) {
                    stackPanes[i][j].getChildren().get(YELLOW_LIGHT_INDEX).setVisible(true);
                } else {
                    stackPanes[i][j].getChildren().get(YELLOW_LIGHT_INDEX).setVisible(false);
                }
            }
        }
    }

    public void updateMill(List<int[][]> newMills) {
        for (Line line : lines) {
            line.setVisible(false);
        }

        for (int i = 0; i < newMills.size(); i++) {
            int[][] currentTriplets = newMills.get(i);
            double startX = Math.min(currentTriplets[0][1], Math.min(currentTriplets[1][1], currentTriplets[2][1]));
            double startY = Math.min(currentTriplets[0][0], Math.min(currentTriplets[1][0], currentTriplets[2][0]));
            double endX = Math.max(currentTriplets[0][1], Math.max(currentTriplets[1][1], currentTriplets[2][1]));
            double endY = Math.max(currentTriplets[0][0], Math.max(currentTriplets[1][0], currentTriplets[2][0]));
            
            Line currentLine = lines.get(i);
            double width = Math.min(uiGrid.getColumnConstraints().get(0).getPrefWidth(), uiGrid.getPrefWidth() / uiGrid.getColumnCount());
            double height = Math.min(uiGrid.getRowConstraints().get(0).getPrefHeight(), uiGrid.getPrefHeight() / uiGrid.getRowCount());

            startX = (startX * width) + (width / 2) + uiGrid.getLayoutX();
            startY = (startY * height) + (height / 2) + uiGrid.getLayoutY();
            endX = (endX * width) + (width / 2) + uiGrid.getLayoutX();
            endY = (endY * height) + (height / 2) + uiGrid.getLayoutY();

            currentLine.setStartX(startX);
            currentLine.setStartY(startY);
            currentLine.setEndX(endX);
            currentLine.setEndY(endY);
            currentLine.setVisible(true);
        }
    }
}