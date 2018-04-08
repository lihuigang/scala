package sparkScala

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.SparkConf
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
