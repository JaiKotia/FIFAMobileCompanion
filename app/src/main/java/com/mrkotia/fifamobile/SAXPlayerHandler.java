package com.mrkotia.fifamobile;

import android.content.Intent;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jai on 5/6/18.
 */

public class SAXPlayerHandler extends DefaultHandler {

    private List<Player> playerList = null;
    private Player player = null;

    public List<Player> getPlayerList() {
        return playerList;
    }

    @Override
    public void startElement (String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if(qName.equalsIgnoreCase("Player")) {
            String id = attributes.getValue("id");
            String first = attributes.getValue("first");
            String last = attributes.getValue("last");
            String commonName = attributes.getValue("commonName");
            String jerseyName = attributes.getValue("jerseyName");
            String height = attributes.getValue("height");
            String weight = attributes.getValue("weight");

            player = new Player();
            player.setFirst(first);
            player.setLast(last);
            player.setID(id);
            player.setHeight(height);
            player.setWeight(weight);
            player.setCommonName(commonName);
            player.setJerseyName(jerseyName);

            if(playerList==null) {
                playerList = new ArrayList<>();
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equalsIgnoreCase("Player")) {
            playerList.add(player);
        }
    }

}
