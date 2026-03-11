class Solution {
    public String longestPrefix(String s) {
        char[] arr = s.toCharArray();
        int[] lps = new int[s.length()];  // LPS (Longest Prefix Suffix) array
        
        int i = 0;  // Length of previous longest prefix suffix
        int j = 1;  // Current position in string
        
        // Build LPS array using KMP algorithm
        while (j < arr.length) {
            if (arr[i] == arr[j]) {
                // Characters match: extend current prefix-suffix
                lps[j] = ++i;
                j++;
            } else {
                // Characters don't match
                if (i == 0) {
                    // No prefix to fall back to
                    lps[j++] = 0;
                } else {
                    // Fall back to previous LPS value
                    i = lps[i - 1];
                }
            }
        }
        
        // If no prefix-suffix match, return empty string
        if (lps[arr.length - 1] == 0)
            return "";
        
        // Extract the prefix-suffix from the end
        int start = arr.length - lps[arr.length - 1];
        return s.substring(start, arr.length);
    }
}