package com.theodo.radar.model;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RegisterForReflection    // (Needed for JSON.decode(...) JSON.encode(...) in Native mode
public class Radar {
    public String name;
    public Position position;
    public double distance;
}
