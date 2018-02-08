package others

import java.io._

import scala.io._

class FileMerge(var file:String,var line:String,var num:Int) {
  val writer = new FileWriter(file,true)
  if (line.contains("device_id") && num==2){
    print ("------")
  }else{
    writer.write(line)
  }
  writer.close()
}

object FileMerge {
  def main(args: Array[String]) {
    var batch="";
    var file_name=""
    var file= Source.fromFile(file_name)
    for (line <- file.getLines()){
      var list=line.split("\t")
      for (i <- 0 to (list.length-1)){
        if (list(i)==""){
          list(i)="null"
        }
      }
      val str = list.mkString("\t")+"\t"+batch+"\n"
      new FileMerge("imp_batch_"+batch+".txt",str,1)
    }
    file.close()
  }
}