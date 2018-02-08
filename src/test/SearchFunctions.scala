package test

import java.io.Serializable

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

class SearchFunctions(val query: String) extends Serializable {
  def isMatch_0(s: String): Boolean = {
    s.contains(query)
  }
  def isMatch_1(s: String): String = {
    //return s.split(query)
    return s
  }
  def getMatchesFunctionReference(rdd: RDD[String]): RDD[String] = {

    // 问题:"isMatch"表示"this.isMatch"，因此我们要传递整个"this"
    rdd.filter(isMatch_0)
  }
  def getMatchesFieldReference(rdd: RDD[String]): RDD[String] = {
    // 问题:"query"表示"this.query"，因此我们要传递整个"this"
    rdd.map(isMatch_1)
  }
  def getMatchesNoReference(rdd: RDD[String]): RDD[String] = {
    // 安全:只把我们需要的字段拿出来放入局部变量中
    val query_ = this.query
    rdd.map(x => x.split(query_).toString())
  }
}
object SearchFunctions {
  def main(args: Array[String]) {
    var conf = new SparkConf().setAppName("appname").setMaster("local")
    var sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    //数据格式为：2017-12-14	14jrtt	869373020680535	榆林	clk	1	huaihai2xiaoguo
    var lines = sc.textFile("huaihai2xiaoguo.txt")
    // 显示前十行
    var line = new SearchFunctions("绥德").getMatchesFunctionReference(lines)
    line.take(1000).foreach(println)
  }
}
