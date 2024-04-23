package edu.iu.dcrispin.ducksservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "ducks")
public class DuckData {

    @Id
    private int id;
    private String name;
    private String type;
    public DuckData() {
    }
    public DuckData(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }


    public String toLine() {
        return String.format("%1$s,%2$s,%3$s", id, name, type);
    }

    public String toLine(int id) {
        return String.format("%1$s,%2$s,%3$s", id, name, type);
    }

    public static DuckData fromLine(String line) {
        String[] tokens = line.split(",");
        return new DuckData(Integer.parseInt(tokens[0]), tokens[1], tokens[2]);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
