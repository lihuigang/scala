package sparkScala
import java.io._
import scala.collection.mutable.ArrayBuffer


class Bitmap(max:Int) {

  var size = calcElemIndex(max)
  var array = Array.range(0,size)

  def calcElemIndex(num: Int): Int = {
    return num / 31
  }

  def calcBitIndex(num: Int): Int = {
    return num % 31
  }

  def set(num:Int) {
    var elemIndex = calcElemIndex(num)
    var byteIndex = calcBitIndex(num)
    var elem = array(elemIndex)
    array(elemIndex) = elem | (1 << byteIndex)
  }

  def clean(i: Int) {

    var elemIndex = calcElemIndex(i)
    var byteIndex = calcBitIndex(i)
    var elem = array(elemIndex)
    array(elemIndex) = elem & (~(1 << byteIndex))
  }
  def test(i:Int):Boolean= {
    var elemIndex = calcElemIndex(i)
    var byteIndex = calcBitIndex(i)
    if ((array(elemIndex) & (1 << byteIndex)) == 0 ){
      return false
    }else {
      return true
    }
  }
}

class BitmapCP() {
  def merge(A:Array[Int],B:Array[Int]):Array[Int]={
    if (A.length > 0) {
      for (i <- 0 to A.length) {
        B(i) = B(i)|A(i)
      }
    }
    return B
  }
  def inter(A:Array[Int],B:Array[Int]):Array[Int] = {
    for (i <- 0 to A.length) {
        B(i)= B(i) & A(i)
    }
    return B
  }

  def count(A:Array[Int]):Int = {
    var result = 0

    for (num <-A) {
      do {
        result += num %2
      } while (num != 0)
    }
    return result
  }
  def user(list:Array[Int]):Array[Int]={
    var result  =  ArrayBuffer[Int]()
    var MAX = list.length * 31
    var bitmap = new Bitmap(MAX)
    bitmap.array=list
    for (i <- 0 to MAX) {
      if (bitmap.test(i)) {
        result.append(i)
      }
    }
  return result.toArray
  }
}
class transform() {
  def to_array(str:String): Array[Int] ={
    var array = new ArrayBuffer[Int]()
    for (i <-str.split(",")) {
      array.append(i.toInt)
    }
    return array.toArray
  }


  def to_string(array:Array[Int]): String ={
    var bitmap_string=""
    for (i <- array){
      if (bitmap_string==""){
        bitmap_string = i.toString
      }
     bitmap_string+= ","+i.toString
    }
    return bitmap_string
  }

  def to_bitmap(array:Array[Int], MAX:Int): Array[Int] ={
    var bitmap = new Bitmap(MAX)
    for (i <- array){
      bitmap.set(i)
    }
    return bitmap.array
  }

}
object Bitmap {
  def main(args: Array[String]) {
    val pt = new transform();
    print(pt.to_array("1,2,3")(0))

  }
}
