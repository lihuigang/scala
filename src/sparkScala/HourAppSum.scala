package sparkScala

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object HourAppSum {
  //Logger.getLogger("org").setLevel(Level.ERROR)
  val conf = new SparkConf().setAppName("test").setMaster("local")
  val spark = SparkSession.builder
    .enableHiveSupport()
    .config(conf)
    .getOrCreate()

  import spark.implicits._
  //channel_id	is_channel	acti_dev_num	new_cust_num	hour
  case class Hour(channel_id: String, is_channel:Int,acti_dev_num:Int,new_cust_num:Int,	hour:Int)
  // txt 转化为 dataframe
  val hourPF = spark.sparkContext.textFile("hour.txt")
    .map(_.split("\t"))
    .map(row => Hour(row(0), row(1).trim.toInt,row(2).trim.toInt,row(3).trim.toInt,row(4).trim.toInt))
    .toDF()
  val python_hourPF = spark.sparkContext.textFile("python_hour.txt")
    .map(_.split("\t"))
    .map(row => Hour(row(0), row(1).trim.toInt,row(2).trim.toInt,row(3).trim.toInt,row(4).trim.toInt))
    .toDF()
  //dataframe 注册为临时表
  hourPF.createOrReplaceTempView("hour")
  python_hourPF.createOrReplaceTempView("python_hour")
  // 查询
  val teenagersDF = spark.sql("SELECT * FROM python_hour a right join hour b on a.channel_id=b.channel_id and a.is_channel=b.is_channel and a.hour =b.hour where a.hour is null")
  teenagersDF.show()
  //teenagersDF.map(teenager => "Name: " + teenager(0)).show()
  def main(args:Array[String]):Unit={
    println(11111)
  }

}
