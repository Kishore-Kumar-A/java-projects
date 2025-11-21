public class LinearSearch {
    public static void main(String[] args) {
        int[] nums={1,2,3,4,5};
        int target=4;
        int index=-1;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==target){
                index=i;
            }
        }if(index!=-1){
        System.out.println(index);}
        else{
            System.out.println("Target not found");
        }
    }
}
