class Solution {
    public int minimizedMaximum(int n, int[] quantities) {
        int left=1;
        int right=0;
        for(int q:quantities){
            right=Math.max(right,q);
        }
        while(left<right){
            int mid=left+(right-left)/2;
            int stores=0;
            for(int q:quantities){
                    stores+=(q+mid-1)/mid;
                }
                if(stores<=n){
                    right=mid;
                }
                else{
                    left=mid+1;
                }
                
            }
       return left; 
    }
}