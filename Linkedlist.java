class Node {

    int data;
    Node next;

    Node(int data1, Node next1) {
        this.data = data1;
        this.next = next1;
    }

    Node(int data1) {
        this.data = data1;
        this.next = null;
    }
}

public class Linkedlist {

    static Node convertArr2LL(int[] arr) {

        if(arr.length == 0)
            return null;

        Node head = new Node(arr[0]);
        Node mover = head;

        for(int i = 1; i < arr.length; i++) {

            Node temp = new Node(arr[i]);

            mover.next = temp;

            mover = temp;
        }

        return head;
    }

    static int lengthOfLL(Node head){
        int cnt=0;
        Node temp=head;
        while(temp!=null)
        {
            temp=temp.next;
            cnt++;
        }
     return cnt;
    }
    static Node removeHead(Node head){
        if(head==null){
            return head;
        }
       
        return head.next;

    }
    static void print(Node head){
        Node temp=head;

        while(temp!=null){
            System.out.println(temp.data+" ");
            temp=temp.next;


        }
        System.out.println();
    }

    static Node deleteTail(Node head){
        if(head==null){
            return null;
        }
        if(head.next==null){
            return null;
        }
        Node temp=head;

        while(temp.next.next!=null){
            temp=temp.next;
        }
        temp.next=null;
        return head;
    }

    static Node deletenode(Node head, int k){
        if(head==null){
            return null;
        }
        if(k==1){
            return head.next;
        }
        int cnt=0;
        Node temp=head;
        Node prev=null;
        while(temp!=null){
            cnt++;
            prev.next=prev.next.next;
            break;
        }
        prev=temp;
        temp=temp.next;

        return head;
    }

    
    public static void main(String[] args) {

        int arr[] = {1,2,3,4,5};
         
    Node head = convertArr2LL(arr);

    int len = lengthOfLL(head);

    head=deleteTail(head);

    print(head);
    }
}