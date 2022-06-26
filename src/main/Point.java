package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Point {
    public int x;
    public int y;
    public Point previous;

    public Point(int x, int y, Point previous) {
        this.x = x;
        this.y = y;
        this.previous = previous;
    }
    
    @Override
    public boolean equals(Object o) {
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    
    
    @Override
	public String toString() {
		return "X: " + x + ", Y: " + y; 
	}

	@Override
    public int hashCode() { return Objects.hash(x, y); }

    public Point offset(int ox, int oy) { 
    	return new Point(x + ox, y + oy, this);  
    }
    
    public static boolean IsWalkable(int[][] map, Point point) {
        if (Graph.doesExist(point)) {
        	return map[point.x][point.y] != 1;
        } else {
        	return false;
        }
    }

    public static List<Point> FindNeighbors(int[][] map, Point point) {
        List<Point> neighbors = new ArrayList<>();
        if (Graph.doesExist(point.offset(0,  1))) {
        	Point up = point.offset(0,  1);
        	if (IsWalkable(map, up)) neighbors.add(up);
        } 
        if (Graph.doesExist(point.offset(0,  -1))) {
        	Point down = point.offset(0,  -1);
        	if (IsWalkable(map, down)) neighbors.add(down);
        }
        if (Graph.doesExist(point.offset(-1, 0))) {
        	Point left = point.offset(-1, 0);
        	if (IsWalkable(map, left)) neighbors.add(left);
        }
        if (Graph.doesExist(point.offset(1, 0))) {
        	Point right = point.offset(1, 0);
        	if (IsWalkable(map, right)) neighbors.add(right);
        }
        return neighbors;
    }
    
    public static List<Point> FindPath(int[][] map, Point start, Point end) {
        boolean finished = false;

        // list of points that have been reviewed
        List<Point> used = new ArrayList<>();
        used.add(start);

        // Main Loop
        while (!finished) {
            List<Point> newOpen = new ArrayList<>();
            for(int i = 0; i < used.size(); ++i){
                Point point = used.get(i);
                
                // for each point thats been used find their neighbours
                for (Point neighbor : FindNeighbors(map, point)) {
                    if (!used.contains(neighbor) && !newOpen.contains(neighbor)) {
                        newOpen.add(neighbor);
                        //debug
//                        System.out.println("Neighbor added " + neighbor.toString());
                    }
                }
            }
            
//            System.out.println("new open size: " + newOpen.size());
            
            for(Point point : newOpen) {
                used.add(point);
                //debug
//                System.out.println("used added " + point.toString());
                
                if (end.equals(point)) {
//                	System.out.println("end found");
                    finished = true;
                    break;
                }
            }

            if (!finished && newOpen.isEmpty()) {
            	System.out.println("No Path Possible");
                return null;
            }
        }

        List<Point> path = new ArrayList<>();
        Point point = used.get(used.size() - 1);
        while(point.previous != null) {
            path.add(0, point);
            point = point.previous;
        }
//        System.out.println("Path returned");
        return path;
    }

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
    
    
}
