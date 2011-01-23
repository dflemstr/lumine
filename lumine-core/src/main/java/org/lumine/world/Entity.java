package org.lumine.world;

import org.lumine.util.math.Vector3D;

public interface Entity {
    public Vector3D position();

    public void setPosition(Vector3D position);
}
