class Solution {
    static class selva{
        int i;
        int j;
        int time;
        selva(int i, int j, int time){
            this.i=i;
            this.j=j;
            this.time=time;
        }
    }

    public int orangesRotting(int[][] grid) {

        int time=0;
        int m=grid.length;
        int n=grid[0].length;

        Queue<selva> queue=new LinkedList<>();

        // Step 1: push all rotten oranges into queue
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==2){
                    queue.add(new selva(i,j,0));
                }
            }
        }

        // directions: left, right, up, down
        int dir[][]={
            {0,-1},
            {0,1},
            {-1,0},
            {1,0}
        };

        // Step 2: BFS spreading
        while(!queue.isEmpty()){
            selva temp=queue.poll();

            for(int k=0;k<4;k++){
                int ii=temp.i+dir[k][0];
                int jj=temp.j+dir[k][1];

                if(ii>=0 && ii<m && jj>=0 && jj<n && grid[ii][jj]==1){
                    grid[ii][jj]=2;
                    queue.add(new selva(ii,jj,temp.time+1));
                    time=Math.max(time,temp.time+1);
                }
            }
        }

        // Step 3: check if any fresh orange remains
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1) return -1;
            }
        }

        return time;
    }
}