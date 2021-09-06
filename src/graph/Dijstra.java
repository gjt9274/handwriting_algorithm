package graph;

import java.util.Arrays;

public class Dijstra {
    public static void main(String[] args) {
        int MAX_WEIGHT = Integer.MAX_VALUE;
        int[] graph0 = new int[]{0,MAX_WEIGHT,10,MAX_WEIGHT,30,100};
        int[] graph1 = new int[]{MAX_WEIGHT, 0, 5, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT};
        int[] graph2 = new int[]{MAX_WEIGHT,MAX_WEIGHT, 0,  50, MAX_WEIGHT,MAX_WEIGHT};
        int[] graph3 = new int[]{MAX_WEIGHT,  MAX_WEIGHT, MAX_WEIGHT,0, MAX_WEIGHT, 10};
        int[] graph4 = new int[]{MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 20, 0, 60};
        int[] graph5 = new int[]{MAX_WEIGHT, MAX_WEIGHT,MAX_WEIGHT, MAX_WEIGHT,  MAX_WEIGHT,0};

        int[][] G = new int[][]{graph0,graph1,graph2,graph3,graph4,graph5};

        shortestPath(G);
    }
    public static void shortestPath(int[][] G) {
        int vertex = G.length; //顶点数

        //用于记录原点到每个点的最短路径
        int[] d = new int[vertex];
        //判断该店的最短路径是否求出
        boolean[] visited = new boolean[vertex];
        //记录前驱节点
        int[] prev = new int[vertex];

        //初始化，源点到其他点距离为无穷大，每个点都没被访问过
        for (int i = 0; i < vertex; i++) {
            visited[i] = false;
            prev[i] = 0;
            d[i] = G[0][i];
        }
        d[0] = 0;
        visited[0] = true;

        for (int i = 1;i < vertex; i++) {
            //先找出从源点出发最近的点
            int min = Integer.MAX_VALUE;
            int index = -1;
            for (int j = 0; j < vertex; j++) {
                if (!visited[j] && d[j] < min){
                    min = d[j];
                    index = j;
                }
            }

            //更新最短路径
            d[index] = min;
            visited[index] = true;

            for (int m = 1; m < vertex; m++) {
                if (!visited[m] && d[index] + G[index][m] < G[0][m]) {
                    d[m] = d[index] + G[index][m];
                    prev[m] = index;
                }
            }

        }
        System.out.println(Arrays.toString(d));
    }
}


