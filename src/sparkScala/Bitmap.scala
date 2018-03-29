package sparkScala
import java.io._
import scala.collection.mutable.ArrayBuffer

class Bitmap(max:Int) extends Serializable{

  var size = calcElemIndex(max)
  var array = new Array[Int](size+1)

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
    var value= elem | (1 << byteIndex)
    array(elemIndex) = value
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
