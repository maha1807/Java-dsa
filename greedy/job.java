import java.util.*;

class Jobs{
    int id;
    int profit;
    int deadline;

    Jobs(int id,int deadline,int profit){
        this.id=id;
         this.deadline=deadline;
        this.profit=profit;
       
    }
}
class Job{
    public static int[] JobScheduling(Jobs[] job, int n){
        Arrays.sort(jobs,(a,b)->b.profit-a.profit);
        int maxDeadline=0;

        for(Job job:arr){
            maxDeadline=Math.max(maxDeadline,job.deadline);
        }

        int[] slot=new int[maxDeadline+1];
        Arrays.fill(slot,-1);

        int count=0;
        int totalProfit=0;

        for(Job job:arr){
            for(int j=job.deadline;j>0;j--){
                   if (slot[j] == -1) {

                    slot[j] = job.id;
                    countJobs++;
                    totalProfit += job.profit;
                    break;
            }
        }
   return new int[]{countJobs,totalProfit};

        
    }
    
}