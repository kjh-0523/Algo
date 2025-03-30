import java.util.*;

class Solution {
    public static class Robot {
        int x, y, route;

        public Robot(int x, int y) {
            this.x = x;
            this.y = y;
            this.route = 0;
        }

        public void nextRoute() {
            this.route++;
        }
    }

    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        List<Robot> robots = new ArrayList<>();

        for (int[] route : routes) {
            int startIdx = route[0] - 1;
            robots.add(new Robot(points[startIdx][0], points[startIdx][1]));
        }

        boolean running = true;
        while (running) {
            running = false;
            Map<String, Integer> positionMap = new HashMap<>();

            for (int i = 0; i < robots.size(); i++) {
                Robot robot = robots.get(i);

                if (robot.route < routes[i].length) {
                    running = true;
                    move(robot, routes[i], points);

                    String key = robot.x + "," + robot.y;
                    positionMap.put(key, positionMap.getOrDefault(key, 0) + 1);
                }
            }

            for (int cnt : positionMap.values()) {
                if (cnt > 1) {
                    answer++; 
                }
            }
        }
        return answer;
    }

    public static void move(Robot robot, int[] route, int[][] points) {
        while (robot.route < route.length) {
            int nextIdx = route[robot.route] - 1;
            int[] destination = points[nextIdx];

            if (robot.x != destination[0]) {
                robot.x += (robot.x < destination[0]) ? 1 : -1;
            } else if (robot.y != destination[1]) {
                robot.y += (robot.y < destination[1]) ? 1 : -1;
            }

            if (robot.x == destination[0] && robot.y == destination[1]) {
                robot.nextRoute();
            }

            break;
        }
    }
}
