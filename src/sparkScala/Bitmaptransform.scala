package sparkScala

import scala.collection.mutable.ArrayBuffer
import java.lang._


object Bitmaptransform {
  def BitmapArrayMerge(BitmapArrayA: Array[Int], BitmapArrayB: Array[Int]): Array[Int] = {

    var MaxArray=BitmapArrayA
    var MinArray=BitmapArrayB

    if (BitmapArrayA.length <= BitmapArrayB.length){
      MaxArray = BitmapArrayB
      MinArray = BitmapArrayA
    }

    for (i <- 0 to MaxArray.length-1) {
      if(i <= MinArray.length-1){
        MaxArray(i) = MaxArray(i) | MinArray(i)
      }else {
        return MaxArray
      }
    }
    return MaxArray
  }

  def StrMerge(A: String, B:String): String = {
    var array_A = to_array(A)
    var array_B = to_array(B)
    if (array_A.length > 0) {
      for (i <- 0 to array_A.length-1) {
        array_B(i) = array_B(i) | array_A(i)
      }
    }
    return to_string(array_B)
  }

  def inter(BitmapArrayA: Array[Int], BitmapArrayB: Array[Int]): Array[Int] = {
    var MaxArray=BitmapArrayA
    var MinArray=BitmapArrayB

    if (BitmapArrayA.length <= BitmapArrayB.length){
      MaxArray = BitmapArrayB
      MinArray = BitmapArrayA
    }
    for (i <- 0 to MinArray.length-1) {
      MinArray(i) = MaxArray(i) & MinArray(i)
    }
    return MinArray
  }

  def count(A: Array[Int]): Int = {
    var result = 0

    for (num <- A) {
      do {
        result += num % 2
      } while (num != 0)
    }
    return result
  }

  def user(list: Array[Int]): Array[Int] = {
    var result = ArrayBuffer[Int]()
    var MAX = list.length * 31
    var bitmap = new Bitmap()
    bitmap.array = list
    for (i <- 0 to MAX) {
      if (bitmap.test(i)) {
        result.append(i)
      }
    }
    return result.toArray
  }

  def to_array(str: String): Array[Int] = {
    var array = new ArrayBuffer[Int]()
    if (str.contains(",")) {
      for (i <- str.split(",")) {
        array.append(i.toInt)
      }
      return array.toArray
    }else{
      return Array()
    }
  }


  def to_string(array: Array[Int]): String = {
    var bitmap_string = ""
    for (i <- array) {
      if (bitmap_string == "") {
        bitmap_string = i.toString
      }else {
        bitmap_string += "," + i.toString
      }
    }
    return bitmap_string
  }

  def to_bitmap(array: Array[Int]): Array[Int] = {
    var bitmap = new Bitmap()
    for (i <- array) {
      bitmap.set(i)
    }
    return bitmap.array
  }

  def addEleToBitmap(bitmapArray:Array[Int],num:Int): Array[Int] ={
    var bitmap = new Bitmap()
    bitmap.array=bitmapArray
    bitmap.set(num)
    return bitmap.array
  }

  def main(args:Array[String]): Unit ={
    var bt = new Bitmap()
    //println (to_string(bt.array))
    var a = 0
    bt.set(a)
    println("-----")
    println (to_string(bt.array))
  }

}
