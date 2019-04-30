package javalearn;

public class SingleLinkedList {

    private int size;
    private Node head;

    public SingleLinkedList(){
        size=0;
        head=null;
    }
    private class Node{
        private Object data;
        private  Node nextNode;

        public Node(Object obj){
            this.data=obj;
        }

    }

    public Object addData(Object obj){
        Node node = new Node(obj);
        if(size==0){
            head=node;
        }else{
            node.nextNode=head;
            head=node;
            node.data=obj;
        }
        size++;
        return obj;
    }

    public static void main(String[] args){
        SingleLinkedList list = new SingleLinkedList();
        list.addData("aa");
        list.addData("bb");

        System.out.print(list.head.data);
    }
}
