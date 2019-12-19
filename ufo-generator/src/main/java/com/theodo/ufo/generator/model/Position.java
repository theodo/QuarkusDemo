package com.theodo.ufo.generator.model;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RegisterForReflection    // (Needed for JSON.decode(...) JSON.encode(...) in Native mode
public class Position {
    double lat;
    double lng;
}
