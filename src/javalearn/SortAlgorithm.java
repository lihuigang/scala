package javalearn;

import java.util.*;

public class SortAlgorithm {

    private Object[] array;

    public SortAlgorithm(Object[] array){
        this.array=array;
    }

    public SortAlgorithm(){
        this.array = new Object[]{78,45,85,97,87};;
    }
    public Object[] InsertionSort(){

        Object[] arrayTmp =  array;
        Object a = arrayTmp[0];

        int num=0;

        int j,i;
        for(i =1;i< arrayTmp.length;i++){

            Object tmpValue = arrayTmp[i];
            for(j=i-1;j>=0&&arrayTmp[j].hashCode()>tmpValue.hashCode();j--){
                //
                arrayTmp[j+1]=arrayTmp[j];
                num++;
            }
            System.out.println("j+1:"+j);
            arrayTmp[j+1]=tmpValue;
        }

        for(i =0;i< arrayTmp.length;i++){
            System.out.println(num+"\t"+i+"\t"+arrayTmp[i]);
        }


        return arrayTmp;
    }

    public static void main(String[] args){
        SortAlgorithm sort = new SortAlgorithm();
        sort.InsertionSort();
    }
}
