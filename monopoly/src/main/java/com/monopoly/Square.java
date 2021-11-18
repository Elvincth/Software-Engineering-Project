package com.monopoly;

public class Square {
    private String name;
    private int position;

    Square(String name, int position) {
        this.name = name;
        this.position = position;
    }

    @Generated
    public String getName() {
        return name;
    }

    @Generated
    public String getDisplayName() {
        return name;
    }

    @Generated
    public void setName(String newName) {
        name = newName;
    }

    public int getPosition() {
        return position;
    }

}
