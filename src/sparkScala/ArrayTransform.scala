package sparkScala

import scala.collection.mutable.ArrayBuffer
import sparkScala.ArrayTransform._
import java.lang._

object ArrayTransform {
  def ArrayCompress(ArrayInput:Array[Int]):Array[Long]={
    var ArrayOutput = new ArrayBuffer[java.lang.Long](0)
    var isFirst = 0
    var bitmap = 0
    var bitmapCount = 0
    for (element <- ArrayInput){
      if (isFirst == 0 ){
        bitmap = element
        isFirst = 1
      }
      if (element == bitmap){
        bitmapCount = bitmapCount + 1
      }else{
        ArrayOutput += BitmapAndNumtoLongBitmap(bitmap,bitmapCount)
        bitmap = element.toInt
        bitmapCount = 1
      }
    }
    ArrayOutput += BitmapAndNumtoLongBitmap(bitmap,bitmapCount)
    return ArrayOutput.toArray
  }

  def ArrayUncompress(ArrayInput:Array[Long]):Array[Int]={
    var ArrayOutput = new ArrayBuffer[Int](0)
    for (element <- ArrayInput){
      var ArrayTest = LongBitmapToBitmapAndNUm(element)
      for(index <-  1 to ArrayTest(1)){
        ArrayOutput += ArrayTest(0)
      }
    }
    return ArrayOutput.toArray
  }

  def BitmapAndNumtoLongBitmap(bitmap:Int,Num:Int): Long ={
    val LongBitmap = bitmap|(Num.toLong<<31)
    return LongBitmap
  }

  def LongBitmapToBitmapAndNUm(LongBitmap:Long): Array[Int] ={
    var ArrayValue = new Array[Int](2)
    ArrayValue(1) = (LongBitmap>>31).toInt
    ArrayValue(0) = (LongBitmap & ~((LongBitmap>>31)<<31) ).toInt
    return ArrayValue
  }

  def main(args:Array[String]): Unit ={
    var A = new Array[Int](50000000)
    var longb = BitmapAndNumtoLongBitmap(123421342,1)
    var aa = LongBitmapToBitmapAndNUm(longb)
    //println(aa(1))
    println(ArrayUncompress(Array(2147483680L))(0))
  }
}
