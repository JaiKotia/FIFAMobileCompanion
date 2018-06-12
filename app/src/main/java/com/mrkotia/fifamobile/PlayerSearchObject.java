package com.mrkotia.fifamobile;

/**
 * Created by jai on 12/6/18.
 */

public class PlayerSearchObject {

        private String playerName;
        private String position;
        private String baseOVR;
        private String tags;

    public PlayerSearchObject(String playerName, String position, String baseOVR, String tags) {
        this.playerName = playerName;
        this.position = position;
        this.baseOVR = baseOVR;
        this.tags = tags;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPosition() {
        return position;
    }

    public String getBaseOVR() {
        return baseOVR;
    }

    public String getTags() {
        return tags;
    }
}
