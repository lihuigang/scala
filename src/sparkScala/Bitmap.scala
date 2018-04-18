package sparkScala
import java.io._
import scala.collection.mutable.ArrayBuffer

class Bitmap() extends Serializable{


  var array = new Array[Int](0)

  def calcElemIndex(num:Long): Int = {
    return (num / 31).toInt
  }

  def calcBitIndex(num: Long): Int = {
    return (num % 31).toInt
  }

  def set(num:Long) {
    var elemIndex = calcElemIndex(num)
    var byteIndex = calcBitIndex(num)
    if (elemIndex > array.length-1){
      var ArrayBuferTest= new ArrayBuffer[Int](0)
      ArrayBuferTest ++= array
      ArrayBuferTest ++= new Array[Int](elemIndex-array.length+1)
      array = ArrayBuferTest.toArray
    }
    var elem = array(elemIndex)
    var value= elem | (1 << byteIndex)
    array(elemIndex) = value
  }

  def clean(i: Long) {

    var elemIndex = calcElemIndex(i)
    var byteIndex = calcBitIndex(i)
    var elem = array(elemIndex)
    array(elemIndex) = elem & (~(1 << byteIndex))
  }
  def test(i:Long):Boolean= {
    var elemIndex = calcElemIndex(i)
    var byteIndex = calcBitIndex(i)
    if ((array(elemIndex) & (1 << byteIndex)) == 0 ){
      return false
    }else {
      return true
    }
  }
}
