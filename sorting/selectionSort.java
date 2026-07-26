

class Solution{
    public int SelectSort(int[] nums){
        int n=nums.length;
        for(int i=0li<n-1;i++){
            int min_idx=i;
            for(int j=i+1;j<n-1;j++){
                if(nums[j]<nums[min_idx]){
                    min_idx=j;
                }

               
            }
             int temp=nums[min_idx];
                arr[min_idx]=arr[i];
                arr[i]=temp;
        }
    }
}