class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int sum = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    sum = Math.max(sum,dfs(grid,i,j));
                }
            }
        }
        return sum;
    }
    static int dfs(int[][] grid,int i,int j){
        int n = grid.length;
        int m = grid[0].length;
        if(i<0||j<0||i>=n||j>=m||grid[i][j]==0){
            return 0;
        }
        grid[i][j]=0;
        return 1+dfs(grid,i+1,j)+
                dfs(grid,i-1,j)+
                dfs(grid,i,j+1)+
                dfs(grid,i,j-1);
    }
}