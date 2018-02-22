package test

import scala.io.Source
import scala.collection.mutable.ArrayBuffer

object loadPartitions {
  val ab = ArrayBuffer[Int]()
  def main(args:Array[String]) {
    var file = "/Users/lihuigang/lhg/partition.txt"
    var text = Source.fromFile(file)
    for (line<- text.getLines){
      var tmp= new Array[Int](1)
      var ll=line.split("=")(3).substring(0,8).toInt
      tmp(0)=ll
      ab+=tmp(0)
    }
    var c=ab.sortWith((a,b)=>a<b)
    c.foreach(println)
  }
}
