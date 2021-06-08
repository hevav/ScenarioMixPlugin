package ru.elytrium.elytramix.utils;

import org.bukkit.util.Vector;

public class HitBoundingBox {
    public static boolean hitBoundingBox(Vector minB, Vector maxB, Vector origin, Vector direction, Vector coord) {
        if (coord == null) coord = new Vector(); // if caller doesn't care, we still need it

        Quadrant quadrantX, quadrantY, quadrantZ;
        Vector candidatePlane = new Vector();
        boolean inside = true;

        // Find candidate planes

        if (origin.getX() < minB.getX()) {
            quadrantX = Quadrant.LEFT;
            candidatePlane.setX(minB.getX());
            inside = false;
        }
        else if (origin.getX() > maxB.getX()) {
            quadrantX = Quadrant.RIGHT;
            candidatePlane.setX(maxB.getX());
            inside = false;
        }
        else {
            quadrantX = Quadrant.MIDDLE;
        }

        if (origin.getY() < minB.getY()) {
            quadrantY = Quadrant.LEFT;
            candidatePlane.setY(minB.getY());
            inside = false;
        }
        else if (origin.getY() > maxB.getY()) {
            quadrantY = Quadrant.RIGHT;
            candidatePlane.setY(maxB.getY());
            inside = false;
        }
        else {
            quadrantY = Quadrant.MIDDLE;
        }

        if (origin.getZ() < minB.getZ()) {
            quadrantZ = Quadrant.LEFT;
            candidatePlane.setZ(minB.getZ());
            inside = false;
        }
        else if (origin.getZ() > maxB.getZ()) {
            quadrantZ = Quadrant.RIGHT;
            candidatePlane.setZ(maxB.getZ());
            inside = false;
        }
        else {
            quadrantZ = Quadrant.MIDDLE;
        }

        // Ray origin inside bounding box
        if (inside) {
            coord.setX(origin.getX());
            coord.setY(origin.getY());
            coord.setZ(origin.getZ());
            return true;
        }

        // Calculate T distances to candidate planes
        Vector maxT = new Vector();
        if (quadrantX != Quadrant.MIDDLE && direction.getX() != 0.0)
            maxT.setX((candidatePlane.getX() - origin.getX()) / direction.getX());
        else
            maxT.setX(-1);

        if (quadrantY != Quadrant.MIDDLE && direction.getY() != 0.0)
            maxT.setY((candidatePlane.getY() - origin.getY()) / direction.getY());
        else
            maxT.setY(-1);

        if (quadrantZ != Quadrant.MIDDLE && direction.getZ() != 0.0)
            maxT.setZ((candidatePlane.getZ() - origin.getZ()) / direction.getZ());
        else
            maxT.setZ(-1);

        // Get largest of the maxT's for final choice of intersection
        Plane whichPlane = Plane.X;
        double maxT_whichPlane = maxT.getX();
        if (maxT_whichPlane < maxT.getY()) {
            whichPlane = Plane.Y;
            maxT_whichPlane = maxT.getY();
        }
        if (maxT_whichPlane < maxT.getZ()) {
            whichPlane = Plane.Z;
            maxT_whichPlane = maxT.getZ();
        }

        // Check final candidate actually inside box
        if (maxT_whichPlane < 0.0) return false;

        if (whichPlane != Plane.X) {
            coord.setX(origin.getX() + maxT_whichPlane * direction.getX());
            if (coord.getX() < minB.getX() || coord.getX() > maxB.getX())
                return false;
        }
        else {
            coord.setX(candidatePlane.getX());
        }

        if (whichPlane != Plane.Y) {
            coord.setY(origin.getY() + maxT_whichPlane * direction.getY());
            if (coord.getY() < minB.getY() || coord.getY() > maxB.getY())
                return false;
        }
        else {
            coord.setY(candidatePlane.getY());
        }

        if (whichPlane != Plane.Z) {
            coord.setZ(origin.getZ() + maxT_whichPlane * direction.getZ());
            if (coord.getZ() < minB.getZ() || coord.getZ() > maxB.getZ())
                return false;
        }
        else {
            coord.setZ(candidatePlane.getZ());
        }

        return true;
    }

    private static enum Quadrant {
        LEFT, MIDDLE, RIGHT;
    }

    private static enum Plane {
        X, Y, Z;
    }

}
