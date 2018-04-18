package sparkScala

import scala.collection.mutable.ArrayBuffer
import java.lang._
import sparkScala.ArrayTransform._

object Bitmaptransform {
  def BitmapArrayMergeLong(LongBitmapArrayA: Array[Long], LongBitmapArrayB: Array[Long]): Array[Long] = {
    var BitmapArrayA = ArrayUncompress(LongBitmapArrayA)
    var BitmapArrayB = ArrayUncompress(LongBitmapArrayB)
    if (BitmapArrayA.length >= BitmapArrayB.length) {
      for (i <- 0 to BitmapArrayA.length - 1) {
        if (i <= BitmapArrayB.length - 1) {
          BitmapArrayA(i) = BitmapArrayA(i) | BitmapArrayB(i)
        } else {
          return ArrayCompress(BitmapArrayA)
        }
      }
      return ArrayCompress(BitmapArrayA)
    }else{
      for (i <- 0 to BitmapArrayB.length - 1) {
        if (i <= BitmapArrayA.length - 1) {
          BitmapArrayB(i) = BitmapArrayB(i) | BitmapArrayA(i)
        } else {
          return ArrayCompress(BitmapArrayB)
        }
      }
      return ArrayCompress(BitmapArrayB)
    }
  }

  def BitmapArrayInterLong(LongBitmapArrayA: Array[Long], LongBitmapArrayB: Array[Long]): Array[Long] = {
    var BitmapArrayA = ArrayUncompress(LongBitmapArrayA)
    var BitmapArrayB = ArrayUncompress(LongBitmapArrayB)
    if (BitmapArrayA.length <= BitmapArrayB.length) {
      for (i <- 0 to BitmapArrayA.length - 1) {
        if (i <= BitmapArrayB.length - 1) {
          BitmapArrayA(i) = BitmapArrayA(i) & BitmapArrayB(i)
        } else {
          return ArrayCompress(BitmapArrayA)
        }
      }
      return ArrayCompress(BitmapArrayA)
    }else{
      for (i <- 0 to BitmapArrayB.length - 1) {
        if (i <= BitmapArrayA.length - 1) {
          BitmapArrayB(i) = BitmapArrayB(i) & BitmapArrayA(i)
        } else {
          return ArrayCompress(BitmapArrayB)
        }
      }
      return ArrayCompress(BitmapArrayB)
    }
  }

  def BitmapArrayMerge(BitmapArrayA: Array[Int], BitmapArrayB: Array[Int]): Array[Int] = {
    if (BitmapArrayA.length >= BitmapArrayB.length) {
      for (i <- 0 to BitmapArrayA.length - 1) {
        if (i <= BitmapArrayB.length - 1) {
          BitmapArrayA(i) = BitmapArrayA(i) | BitmapArrayB(i)
        } else {
          return BitmapArrayA
        }
      }
      return BitmapArrayA
    }else{
      for (i <- 0 to BitmapArrayB.length - 1) {
        if (i <= BitmapArrayA.length - 1) {
          BitmapArrayB(i) = BitmapArrayB(i) | BitmapArrayA(i)
        } else {
          return BitmapArrayB
        }
      }
      return BitmapArrayB
    }
  }

  def BitmapArrayLongToNum(InputBitmapLong: Array[Long]): Array[Int] = {
    var result = ArrayBuffer[Int]()
    var BitmapArray = ArrayUncompress(InputBitmapLong)
    var MAX = BitmapArray.length * 31
    var bitmap = new Bitmap()
    bitmap.array = BitmapArray
    for (i <- 0 to MAX -1 ) {
      if (bitmap.test(i)) {
        result.append(i)
      }
    }
    return result.toArray
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
    println(bitmap.array.toSeq)
    for (i <- 0 to MAX -1 ) {
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

  def addEleToBitmap(bitmapArray:Array[Int],num:String): Array[Int] ={
    var bitmap = new Bitmap()
    bitmap.array=bitmapArray
    bitmap.set(num.toLong)
    return bitmap.array
  }

  def ArrayToBitmap(InputArray:Array[Long]): Array[Long] ={
    var bitmap = new Bitmap()
    for (element <- InputArray){
      bitmap.set(element.toInt)
    }
    return ArrayCompress(bitmap.array)
  }

  def ArrayAppend(InputArray:Array[Long],num:String): Array[Long] ={
    var SetTest = InputArray.toSet
    SetTest += num.toLong
    return SetTest.toArray
  }


  def ArrayMerge(InputArray1:Array[Long],InputArray2:Array[Long]): Array[Long] ={
    var SetTest = InputArray1.toSet ++ InputArray2.toSet
    return SetTest.toArray
  }

  def main(args:Array[String]): Unit ={
    var bt = new Bitmap()
    //println (to_string(bt.array))
    var a = 0
    bt.set(a)
    println("-----")
    println (ArrayUncompress(Array(2147483686L, 1731838187929600L, 2148007936L)).length)
  }

}
