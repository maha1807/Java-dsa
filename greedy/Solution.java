import java.util.*;
class Meeting{
     int start;
     int end;
     int pos;

     Meeting(int start,int end,int pos){
        this.start=start;
        this.end=end;
        this.pos=pos;
     }
}

class Solution{
  public static ArrayList<Integer>maxMeetings(int N,int[] start,int[] end){
    ArrayList<Meeting>meetings=new ArrayList<>();
    for(int i=0;i<N;i++){
        meetings.add(new Meeting(start[i],end[i],i+1));
    }
    Collections.sort(meetings, (a, b) -> a.end - b.end);
    ArrayList<Integer>ans=new ArrayList<>();

    ans.add(meetings.get(0).pos);
    int lastEnd=meetings.get(0).end;

    for(int i=1;i<N;i++){
      if(meetings.get(i).start>lastEnd){
        ans.add(meetings.get(i).pos);
        lastEnd = meetings.get(i).end;

      }
    }
    return ans;
}
    public static void main(String[] args) {

        int[] start = {1, 3, 0, 5, 8, 5};
        int[] end = {2, 4, 6, 7, 9, 9};

        ArrayList<Integer> result = maxMeetings(start.length, start, end);

        System.out.println("Maximum Meetings = " + result.size());
        System.out.println("Order of Meetings = " + result);
    }
}