package sparkScala

import scala.collection.mutable.ArrayBuffer


object Bitmaptransform {
  def merge(A: Array[Int], B: Array[Int]): Array[Int] = {
    if (A.length > 0) {
      for (i <- 0 to A.length-1) {
        B(i) = B(i) | A(i)
      }
    }
    return B
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

  def inter(A: Array[Int], B: Array[Int]): Array[Int] = {
    for (i <- 0 to A.length-1) {
      B(i) = B(i) & A(i)
    }
    return B
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
    var bitmap = new Bitmap(MAX)
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

  def to_bitmap(array: Array[Int], MAX: Int): Array[Int] = {
    var bitmap = new Bitmap(MAX)
    for (i <- array) {
      bitmap.set(i)
    }
    return bitmap.array
  }

  def addEleToBitmap(bitmapArray:Array[Int],num:Int): Array[Int] ={
    var bitmap = new Bitmap(bitmapArray.length-1)
    bitmap.array=bitmapArray
    bitmap.set(num)
    return bitmap.array
  }

  def main(args:Array[String]): Unit ={
    var bt = new Bitmap(1)
    //println (to_string(bt.array))
    bt.set(1)
    print("-----")
    println (to_string(bt.array))
  }

}
