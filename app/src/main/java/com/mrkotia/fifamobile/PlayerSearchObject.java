package com.mrkotia.fifamobile;

import java.io.Serializable;

/**
 * Created by jai on 12/6/18.
 */

public class PlayerSearchObject implements Serializable {

    private String playerName;
    private String position;
    private String tags;
    private String baseOVR;
    private String playerID;
    private String cardID;

    public PlayerSearchObject(String playerName, String position, String tags, String baseOVR, String playerID, String cardID) {
        this.playerName = playerName;
        this.position = position;
        this.tags = tags;
        this.baseOVR = baseOVR;
        this.playerID = playerID;
        this.cardID = cardID;
    }

    public String getPlayerID() { return playerID; }

    public String getCardID() { return cardID; }

    public String getPlayerName() {
        return playerName;
    }

    public String getPosition() {
        return position;
    }

    public String getTags() {
        return tags;
    }

    public String getBaseOVR() {
        return baseOVR;
    }

}
