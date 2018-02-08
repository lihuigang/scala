package others
import scala.io.Source
import scala.util.control._

object ChannelSum {
  def ff(line:String): Array[String] ={
    return line.split("\t")
  }

  def get_sum(num:Int): Unit ={

  }

  def get_num(f:String,B:Array[String],A:Array[Int],num:Int){
    var file=Source.fromFile(f)
    var sum = BigInt("0")
    for (line<- file.getLines){
      var aa = 1
      var line1= line.split("\t")
      for (i <- 0 until  A.length ){
        if (B(i)!="-1" && line1(A(i))!="-1"){
          B(i)=line1(A(i))
        }
        //print ("i="+i)
        if (B(i) != line1(A(i))){
          //print("B="+B(i))
          //print("A="+line1(A(i)))
          aa=0
        }else{
          //print("B="+B(i))
          //print("A"+line1(A(i)))
        }
        //println("")
      }
      if ( aa == 1 ){

          println("------------------------")
          println(line1(num).split('.')(0))
          var bbb = BigInt(line1(num).split('.')(0).toString)

        sum+=bbb
        println("price="+bbb)
      }
    }
    println(sum)
    file.close()
  }

  def get_1(f:String): Unit = {
    var file = Source.fromFile(f)
    val loop = new Breaks;
    var i = 0
    loop.breakable {
      for (line <- file.getLines) {
        if (i == 1) {
          loop.break()
        }
        println(line)
        var line1 = line.split("\t")
        println(line1.length)
        for (j <- 0 until line1.length){
          if ( line1(j) == "-1"){
            println (j)
          }
        }
        i=1
      }
    }
    file.close()
  }
  def main(args:Array[String]): Unit ={
    var f_name="/Users/lihuigang/Downloads/app_flow_sdk_channel_sum_m.txt"
    var a = Array(3,5,7,11)
    var b = Array("-1","-1","-1","-1")
    get_1(f_name)
    get_num(f_name,b,a,21)
  }

}
