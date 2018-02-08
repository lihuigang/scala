package spark

import org.apache.spark.{SparkConf, SparkContext}
object BigDataAnalytics {
  def main (args:Array[String]): Unit ={
    var conf =new SparkConf().setAppName("appname").setMaster("local")
    var sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    var lines = sc.textFile("BigData.txt")
    var text=lines.flatMap(x=>x.split(""))
    text.saveAsTextFile("result")
  }
}
