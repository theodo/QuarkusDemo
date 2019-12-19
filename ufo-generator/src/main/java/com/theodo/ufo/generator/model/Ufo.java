package com.theodo.ufo.generator.model;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RegisterForReflection    // (Needed for JSON.decode(...) JSON.encode(...) in Native mode
public class Ufo {
    public String name;

    public Position start;
    public Position destination;
    public Position current;
    public boolean moving;
    public boolean hit;

    public boolean updatePosition() {
        double deltaLat = (destination.lat - start.lat) / 100;
        double deltaLon = (destination.lng - start.lng) / 100;

        current.lat += deltaLat;
        current.lng += deltaLon;

        double epsiLat = Math.abs(current.lat - destination.lat);
        double epsiLng = Math.abs(current.lng - destination.lng);

        if (epsiLat < 1E-4 && epsiLng < 1E-4) {
            Position tmp = start;
            start = destination;
            destination = tmp;
            current = new Position(start.getLat(), start.getLng());
            return true;
        }
        return false;
    }
}
