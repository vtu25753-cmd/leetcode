class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int num=image[sr][sc];
        if(image[sr][sc]==color){
            return image;
        }
        func(image,sr,sc,color,num);
        return image;
        
    }
    public static void func(int[][] img,int i,int j,int color,int num){
        if(i<0 || j<0 || i>=img.length || j>=img[0].length || img[i][j]!=num){
            return;
        }
        img[i][j]=color;
        func(img,i+1,j,color,num);
        func(img,i-1,j,color,num);
        func(img,i,j-1,color,num);
        func(img,i,j+1,color,num);


    }
}