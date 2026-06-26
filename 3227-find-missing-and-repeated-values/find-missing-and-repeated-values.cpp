class Solution {
public:
    vector<int> findMissingAndRepeatedValues(vector<vector<int>>& grid) {
       int n=grid.size();
       long long total=(long long )n*n;
      long long expsum=total*(total+1)/2;
      long long expsqsum=total*(total+1)*(2*total+1)/6;
      long long actualsum=0,actualsqsum=0;
      for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            int val=grid[i][j];
            actualsum+=val;
            actualsqsum+=(long long)val*val;
        }
      }
      long long diff=expsum-actualsum;
      long sqdiff=expsqsum-actualsqsum;
      long long sum=sqdiff/diff;
      int missing=(diff+sum)/2;
      int repeating=sum-missing;
      return{repeating,missing};
    }
};