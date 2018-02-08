package others

import org.apache.spark.{SparkConf, SparkContext}


object FileAnalytics {

  def isMatch(s: String): String = {
    if (s.contains("榆林")){
      return s
    }else{
      return ""
    }
  }
  def isMat(s: String): Boolean = {
    s.contains("榆林")
  }

  def main(args: Array[String]) {
    var conf = new SparkConf().setAppName("appname").setMaster("local")
    var sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    //数据格式为：2017-12-14	14jrtt	869373020680535	榆林	clk	1	huaihai2xiaoguo
    var lines = sc.textFile("11.txt")
    // 显示前十行
    lines.take(10).foreach(println)
    // 取第三、四列去重
    var text= lines.map(x=> (x.split("\t")(3),x.split("\t")(2))).distinct()
    //取text第一列，将第二列替换为1
    var rdd=text.map(x=>(x._1,1))
    // 分spid统计 IMEI个数
    var city = rdd.reduceByKey((x,y)=>(x+y))

    city.take(1000).foreach(println)
  }
}
