package sparkScala


import org.apache.spark.SparkConf
import org.apache.spark.sql._
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.types._





/**
  * Spark SQL UDAS：user defined aggregation function
  * UDF: 函数的输入是一条具体的数据记录，实现上讲就是普通的scala函数-只不过需要注册
  * UDAF：用户自定义的聚合函数，函数本身作用于数据集合，能够在具体操作的基础上进行自定义操作
  */
object WeiXin {

  def main(args:Array[String]):Unit={
    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf = new SparkConf().setAppName("test").setMaster("local")
    val spark = SparkSession.builder
      .enableHiveSupport()
      .config(conf)
      .getOrCreate()

    import spark.implicits._

    val bigData = Array(30846133,2074100,46167622,121575769,37183803,45676108,2128953,244731392,46329593,84534703,122034275,272396323,33385635,260991987,81856065,197190841,175998609,39371344,32087,52,24573201,209687280,175017747,768825254,84036233).toSeq
    val bigDataRDD = spark.sparkContext.makeRDD(bigData)
    val bigDataRowRDD = bigDataRDD.map(line => Row(line))
    val structType = StructType(Array(StructField("name",IntegerType,true)))
    val bigDataDF = spark.createDataFrame(bigDataRowRDD, structType)
    bigDataDF.createTempView("user_tmp")
    //bigDataDF.registerTempTable("bigDataTable")
    var rdd = spark.sql("select name from user_tmp")


  }

}
