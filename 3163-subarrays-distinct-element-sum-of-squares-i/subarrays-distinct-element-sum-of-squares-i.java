class Solution {
    public int sumCounts(List<Integer> nums) {
        int n=nums.size();
        int total=0;
        for(int i=0;i<n;i++){
            boolean[] seen=new boolean[101];
            int distinct=0;
            for(int j=i;j<n;j++){
                if(!seen [nums.get(j)]){
                    seen [nums.get(j)]=true;
                    distinct++;
                }
             total+=distinct*distinct;
            }
        }
        return total; 
    }
}