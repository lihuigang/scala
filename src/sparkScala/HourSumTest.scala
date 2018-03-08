package sparkScala


import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.log4j.{Level, Logger}

object HourSumTes {
  Logger.getLogger("org").setLevel(Level.ERROR)
  val conf = new SparkConf().setAppName("test").setMaster("local")
  val spark = SparkSession.builder
    .enableHiveSupport()
    .config(conf)
    .getOrCreate()

  import spark.implicits._
  //channel_id	is_channel	acti_dev_num	new_cust_num	hour
  case class Hour(channel_id: String, is_channel:Int,acti_dev_num_1:Int,new_cust_num:Int,	hour:Int)
  case class Python_Hour(channel_id: String, is_channel:Int,acti_dev_num:Int,new_cust_num:Int,	hour:Int)
  // txt 转化为 dataframe
  val hourPF = spark.sparkContext.textFile("hour.txt")
    .map(_.split("\t"))
    .map(row => Hour(row(0), row(1).trim.toInt,row(2).trim.toInt,row(3).trim.toInt,row(4).trim.toInt))
    .toDF()
  val python_hourPF = spark.sparkContext.textFile("python_hour.txt")
    .map(_.split("\t"))
    .map(row => Python_Hour(row(0), row(1).trim.toInt,row(2).trim.toInt,row(3).trim.toInt,row(4).trim.toInt))
    .toDF()
  //dataframe 注册为临时表
  hourPF.createOrReplaceTempView("hour")
  python_hourPF.createOrReplaceTempView("python_hour")
  // 查询
  val python_hour = spark.sql("SELECT * FROM python_hour ")
  var hour=spark.sql("select * from hour")
  var a=python_hour.join(hour,python_hour("channel_id")===hour("channel_id")&&python_hour("is_channel")===hour("is_channel")&&python_hour("hour")===hour("hour"),"right_outer")
  var b=python_hour.join(hour,Seq("channel_id","is_channel","hour"),"right_outer")
  def aaa(i:Int): Unit ={
    i>=0
  }
  println (python_hour.count())
  println (hour.count())
  println(a.count())
  b.filter("acti_dev_num is null").show
  b.select($"channel_id",$"is_channel").show()
  b.map(table=>"channel_id:"+table.getAs[Int]("channel_id")).show()
  def main(args:Array[String]):Unit={
    println(11111)
  }
}