package ninemanmorris.player;

import ninemanmorris.gamelogic.IMorrisGameStateListener;
import ninemanmorris.player.playertype.*;
import ninemanmorris.player.playertype.computer.ComputerAI;

public class PlayerCreationReqRes {
    
    private PlayerType playerType;
    private IMorrisGameStateListener listener;
    private Player player;
    private boolean isRed;

    public static PlayerCreationReqRes createPlayer(PlayerCreationReqRes request) {
        PlayerCreationReqRes response = null;

        if (request.getPlayerType() == PlayerType.HUMAN) {
            response = new PlayerCreationReqRes(
                request.getPlayerType(),
                request.getIsRed(),
                request.getListener(),
                new HumanPlayer(request.getIsRed())
            );

        } else if (request.getPlayerType() == PlayerType.COMPUTER) {
            ComputerAI aiPlayer = new ComputerAI(request.getIsRed());

            response = new PlayerCreationReqRes(
                request.getPlayerType(),
                request.getIsRed(),
                aiPlayer.getComputerBoard(),
                aiPlayer
            );
        }

        return response;
    }

    private PlayerCreationReqRes(PlayerType playerType, boolean isRed, IMorrisGameStateListener listener, Player player) {
        this.playerType = playerType;
        this.listener = listener;
        this.isRed = isRed;
        this.player = player;
    }

    public PlayerCreationReqRes(PlayerType playerType, boolean isRed, IMorrisGameStateListener listener) {
        this(playerType, isRed, listener, null);
    }

    public PlayerCreationReqRes(PlayerType playerType, boolean isRed) {
        this(playerType, isRed, null);
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public IMorrisGameStateListener getListener() {
        return listener;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean getIsRed() {
        return isRed;
    }
}
