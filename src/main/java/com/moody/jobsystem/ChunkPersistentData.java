package com.moody.jobsystem;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class ChunkPersistentData {

    private List<Location> blocksAlreadyMined;
    private Chunk chunk;

    public ChunkPersistentData(Chunk chunk) {
        PersistentDataContainer container = chunk.getPersistentDataContainer();
        String blocks = container.get(new NamespacedKey(JobSystem.getPlugin(), "blocks"), PersistentDataType.STRING);
        if (blocks == null) {
            this.chunk = chunk;
            this.blocksAlreadyMined = new ArrayList<>();
        } else {
            for (int i = 0; i < blocks.length(); i++) {
                if (blocks.charAt(i) == '[') {
                    int locationIndex = 1;
                    String tempCoordinate = "";
                    Location location = new Location(chunk.getWorld(), 0, 0,0 );
                    for (int i2 = i + 1; i2 < blocks.indexOf(']', i + 1); i2++) {
                        if (blocks.charAt(i2) == ',') {
                            switch (locationIndex) {
                                case 1:
                                    location.setX(Double.parseDouble(tempCoordinate));
                                    break;
                                case 2:
                                    location.setY(Double.parseDouble(tempCoordinate));
                                    break;
                            }
                            locationIndex++;
                            tempCoordinate = "";
                        } else if (blocks.charAt(i2) != ' ') {
                            tempCoordinate += blocks.charAt(i2);
                        }
                        if (i2 == (blocks.indexOf(']', i + 1) - 1)) {
                            tempCoordinate += blocks.charAt(i2);
                            location.setZ(Double.parseDouble(tempCoordinate));
                            tempCoordinate = "";
                        }
                    }
                    if (this.blocksAlreadyMined == null) {
                        this.blocksAlreadyMined = new ArrayList<>();
                    }
                    this.blocksAlreadyMined.add(location);
                }
            }
            this.chunk = chunk;
        }
    }

    public boolean isNull() {
        if(this.blocksAlreadyMined == null) return true;
        return false;
    }

    public String convertLocationsToString() {
        String rueckgabe = "";
        for (int i = 0; i < this.blocksAlreadyMined.size(); i++) {
            if (i != this.blocksAlreadyMined.size() - 1) {
                rueckgabe += "[" + this.blocksAlreadyMined.get(i).getX() + ", " + this.blocksAlreadyMined.get(i).getY() + ", " + this.blocksAlreadyMined.get(i).getZ() + "]";
                rueckgabe += ", ";
            } else {
                rueckgabe += "[" + this.blocksAlreadyMined.get(i).getX() + ", " + this.blocksAlreadyMined.get(i).getY() + ", " + this.blocksAlreadyMined.get(i).getZ() + "]";
            }
        }
        return rueckgabe;
    }

    public void addLocation(Location location) {
        if (this.blocksAlreadyMined == null) {
            this.blocksAlreadyMined = new ArrayList<>();
        }
        this.blocksAlreadyMined.add(location);
        this.chunk.getPersistentDataContainer().set(new NamespacedKey(JobSystem.getPlugin(), "blocks"), PersistentDataType.STRING, this.convertLocationsToString());
    }

    public void removeLocation(Location location) {
        if (this.blocksAlreadyMined == null) {
            this.blocksAlreadyMined = new ArrayList<>();
        }
        this.blocksAlreadyMined.remove(location);
        this.chunk.getPersistentDataContainer().set(new NamespacedKey(JobSystem.getPlugin(), "blocks"), PersistentDataType.STRING, this.convertLocationsToString());
    }

    public boolean contains(Location location) {
        if (this.blocksAlreadyMined == null) {
            this.blocksAlreadyMined = new ArrayList<>();
        }
        for (Location location1 : this.blocksAlreadyMined) {
            if (location1.equals(location)) {
                return true;
            }
        }
        return false;
    }

}
