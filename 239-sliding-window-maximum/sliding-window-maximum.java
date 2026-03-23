class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) 
    {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        PriorityQueue<int[]> maxh = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        for(int i = 0; i < n; i++)
        {
            while(!maxh.isEmpty() && maxh.peek()[1] <= i - k)
            {
                maxh.poll();
            }
            
            maxh.offer(new int[]{nums[i], i});

            if(i - k + 1 >= 0)
            {
                res[i - k + 1] = maxh.peek()[0];
            }
        }

        return res;
    }
}