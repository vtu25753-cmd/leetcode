class Solution {
    // time : O(n * n), n = number of rows and columns traverse through
    // space: O(n * n), n = number of rows and columns in matrix

    // mat 
    //        [0,0,0]
    //        [0,1,0]
    //        [1,1,1]

    // matrix                   ->   output  
    //        [0,  0, 0]                    [0,  0,  0] 
    //        [0, -1, 0]                    [0,  1,  0]
    //        [-1,-1,-1]                    [1,  2,  1]

    // if cell is 0 -> next cell is -1, that cell will be 0 + 1 = 1
    // if cell is 1 -> next cell is -1, that cell will be 1 + 1 = 2 

    public int[][] updateMatrix(int[][] mat) {
        int[][] matrix = mat;

        int r = matrix.length;
        int c = matrix[0].length;

        Queue<int[]> q = new ArrayDeque<>();

        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(matrix[i][j] == 0) q.add(new int[] {i, j});
                else matrix[i][j] = -1;
            }
        }

       while(!q.isEmpty()) {
            // current cell info
            int[] cell = q.poll();
            int row = cell[0];
            int col = cell[1];

            int[][] directions = {{0,1}, {0,-1}, {1,0}, {-1,0}};

            // exploring next 4 cells
            for(int[] direction: directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if(newRow >= 0 && newCol >= 0 && newRow < r && newCol < c) {
                    if(matrix[newRow][newCol] == -1) {
                        matrix[newRow][newCol] = matrix[row][col] + 1;
                        q.add(new int[] {newRow, newCol});
                    }
                }
            }
       }

       return matrix;
    }
}