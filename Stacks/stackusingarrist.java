ArrayList<Integer>list=new ArrayyList<>();
public static void push(int data){
    if(list.isEmpty()){
        return -1;
    }
    list.add(data);
}
public static void pop(){
    int top;
    if(list.isEmpty()){
        return -1;
    }
    top=list.get(list.size()-1);
    list.remove(list.size()-1);
    return top;
    
public static void peek(){
    if(isEmpty()){
        return -1;
    }
    return list.get(list.remove()-1);
}