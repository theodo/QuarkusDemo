package com.theodo.ufo.sse.model;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RegisterForReflection
public class Ufo {
    public String name;

    public Position start;
    public Position destination;
    public Position current;
    public boolean moving;
    public boolean hit;

    public String browserUUID;
}
