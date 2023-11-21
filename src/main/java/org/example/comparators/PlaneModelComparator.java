package org.example.comparators;

import org.example.generated.planes.Plane;
import java.util.Comparator;

public class PlaneModelComparator implements Comparator<Plane> {
    @Override
    public int compare(Plane p1, Plane p2) {
        return p1.getModel().compareTo(p2.getModel());
    }
}
