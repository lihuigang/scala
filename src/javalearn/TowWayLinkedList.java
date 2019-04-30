package javalearn;

public class TowWayLinkedList {

    private int size;
    private Node head;
    private Node tail;

    public TowWayLinkedList(){
        size=0;
        head=null;
        tail=null;
    }
    private class Node{
        private Object data;
        private  Node nextNode;
        private  Node prevNode;

        public Node(Object obj){
            this.data=obj;
        }

    }

    public Object addHead(Object obj){
        Node newNode = new Node(obj);
        if(size==0){
            head=newNode;
            tail=newNode;
        }else{
            newNode.nextNode=head;
            //newNode.nextNode.prevNode=newNode;
            head.prevNode=newNode;
            head=newNode;
            newNode.data=obj;
        }
        size++;
        return obj;
    }

    public Object addTail(Object obj){
        Node newNode = new Node(obj);
        if(size==0){
            head=newNode;
            tail=newNode;
        }else{
            newNode.prevNode=tail;
            tail.nextNode=newNode;
            //newNode.prevNode.nextNode=newNode;
            tail=newNode;
            newNode.data=obj;
        }
        size++;
        return obj;
    }

    public static void main(String[] args){
        TowWayLinkedList list = new TowWayLinkedList();
        list.addHead("aa");
        list.addHead("bb");
        list.addTail("cc");

        System.out.print(list.tail.prevNode.prevNode.data);
    }
}
