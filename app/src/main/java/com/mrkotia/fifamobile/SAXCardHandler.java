package com.mrkotia.fifamobile;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jai on 7/6/18.
 */

public class SAXCardHandler extends DefaultHandler {

    private List<Card> cardList = null;
    private Card card = null;

    public List<Card> getCardList() {
        return cardList;
    }

    @Override
    public void startElement (String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if(qName.equalsIgnoreCase("Card")) {
            String id = attributes.getValue("id");
            String playerId = attributes.getValue("playerId");
            String position = attributes.getValue("position");
            String tags = attributes.getValue("tags");




            card = new Card();
            card.setId(id);
            card.setPlayerId(playerId);
            card.setPosition(position);
            card.setTags(tags);

            if(cardList==null) {
                cardList = new ArrayList<>();
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equalsIgnoreCase("Card")) {
            cardList.add(card);
        }
    }

}
