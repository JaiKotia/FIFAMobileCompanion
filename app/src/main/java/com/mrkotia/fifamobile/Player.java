package com.mrkotia.fifamobile;

/**
 * Created by jai on 30/5/18.
 */

public class Player {

    String id;
    String commonName;
    String first;
    String last;
    String jerseyName;
    String height;
    String weight;

    public void setID(String id) {
        this.id = id;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public void setJerseyName(String jerseyName) {
        this.jerseyName = jerseyName;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**  public Player(int id, String commonName, String first, String last,
                String jerseyName, int height, int weight) {
        this.id = id;
        this.commonName = commonName;
        this.first = first;
        this.last = last;
        this.jerseyName = jerseyName;
        this.height = height;

        this.weight = weight;
    }
  **/

    public String getID() {
        return id;
    }

    public String getCommonName() {
        return commonName;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getJerseyName() {
        return jerseyName;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

}


