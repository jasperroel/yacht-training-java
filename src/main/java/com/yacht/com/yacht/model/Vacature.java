package com.yacht.com.yacht.model;

public class Vacature {
    public String id;
    public String title;
    public String description;
    public String vakgebied;

    @Override
    public String toString() {
        return "Vacature{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", vakgebied='" + vakgebied + '\'' +
                '}';
    }
}
