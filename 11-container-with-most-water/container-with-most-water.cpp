class Solution {
public:
    int maxArea(vector<int>& height) {
        int left=0;
        int right=height.size()-1;
        long long maxWater=0;
        while(left<right){
            long long h=min(height[left],height[right]);
            long long w=right-left;
            long long area=h*w;
            maxWater=max(maxWater,area);
            if(height[left]<height[right])
            left++;
            else
            right--;
        }
      return(int)maxWater;  
    }
};