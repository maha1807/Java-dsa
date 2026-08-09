public class StackClass{
    static Class Node{
        int data;
        Node next;
        public Node(int data){
            this.data=data;
            this.next=null;
        }
    }

    static class Stack{
        static public Node head;
        public static void push(int data){
            Node newNode=new Node(int data);
            if(head==null){
                head=newNode;
                return;
            }
            newNode.next=head;
            head.nextNode;
        }
    }
    public static void pop(){
        int top=head.data;
            if(head==null){
                return -1;
            }
          head=head.next;
          return top;

        }
    }
public static void peek(){
        if(head==null){
            return -1;
        }
        int top=head.data;
        return top;
        }
    
public static void main(String[] args){
    Stack<Integer>st=new Stack<>();
    st.pudh(1);
    st.push(2);
    st.push(3);
    st.push(4);

    while(!st.isEmpty()){
        System.out.println(st.peek());
        st.pop();
    }
}