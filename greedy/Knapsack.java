import java.util.*;

class item{
     int value;
     int weight;
     item(int value,int weight){
        this.value=value;
        this.weight=weight;
     }
}

class Knapsack{
    double fractionalKnapsack(int[] n, int value,int weight){
        Array.sort(arr,(a,b)->{
            double r1=(double)a.value/a.weight;
            double r2=(double)b.value/b.weight;

            return double.compare(r2,r1);
        });

        double maxValue=0.0;
        for(Item item:arr){
            if(item.weight<=W){
             maxValue+=item.value;
             W-=item.weight;
            }else{
                maxValue+=((double)item.value/item.weight)*W;
                break;
            }
        }
        return maxValue;
    }
}