// 149. Max Points on a Line
// Leetcode Question: https://leetcode.com/problems/max-points-on-a-line/description/

class Solution {
    public int maxPoints(int[][] points) {
        if (points.length == 1) return 1;

        Map<Pair<Double, Double>, Set<Pair<Integer, Integer>>> map = new HashMap<>();
        Map<Integer, Set<Pair<Integer, Integer>>> xmap = new HashMap<>();

        int maxPoints = 0;
        for (int i=0;i<points.length;i++) {
            int x1 = points[i][0];
            int y1 = points[i][1];
            for (int j=i+1;j<points.length;j++) {
                int x2 = points[j][0];
                int y2 = points[j][1];

                if (x1 == x2) {
                    Set<Pair<Integer, Integer>> set;
                    if (xmap.containsKey(x1)) set = xmap.get(x1);
                    else set = new HashSet<>();
                    set.add(new Pair(x1, y1));
                    set.add(new Pair(x2, y2));
                    xmap.put(x1, set);
                    maxPoints = Math.max(maxPoints, xmap.get(x1).size());
                    continue;
                }

                double m = (y2-y1) * 1.0 / (x2-x1);
                double c = y1 - m * x1;

                Pair p = new Pair(m,c);
                Set<Pair<Integer, Integer>> set;
                if (map.containsKey(p)) set = map.get(p);
                else set = new HashSet<>();
                set.add(new Pair(x1, y1));
                set.add(new Pair(x2, y2));
                map.put(p, set);
                
                maxPoints = Math.max(maxPoints, map.get(p).size());
            }
        }
        return maxPoints;
    }
}
