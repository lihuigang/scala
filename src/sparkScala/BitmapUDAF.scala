package sparkScala

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.sql.{Row, SQLContext, SparkSession}
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.expressions.UserDefinedAggregateFunction
import org.apache.spark.sql.types.IntegerType
import org.apache.spark.sql.types.{ArrayType,MapType,StructType}
import org.apache.spark.sql.types.DataType
import org.apache.spark.sql.expressions.MutableAggregationBuffer
import org.apache.log4j.{Level, Logger}
import sparkScala.Bitmaptransform.{to_array,to_string,to_bitmap,user,merge,count,StrMerge,addEleToBitmap}

/**
  * Spark SQL UDAS：user defined aggregation function
  * UDF: 函数的输入是一条具体的数据记录，实现上讲就是普通的scala函数-只不过需要注册
  * UDAF：用户自定义的聚合函数，函数本身作用于数据集合，能够在具体操作的基础上进行自定义操作
  */
object BitmapUDAF {

  Logger.getLogger("org").setLevel(Level.ERROR)
  val conf = new SparkConf().setAppName("test").setMaster("local")
  val spark = SparkSession.builder
    .enableHiveSupport()
    .config(conf)
    .getOrCreate()

  import spark.implicits._

  case class Person(name: String, age: Long)
  // txt 转化为 dataframe
  val peopleDF = spark.sparkContext.textFile("BigData.txt")
    .map(_.split(","))
    .map(row => Person(row(0), row(1).trim.toInt))
    .toDF()

  //dataframe 注册为临时表
  peopleDF.createOrReplaceTempView("people")

  // 查询
  spark.udf.register("bitmap",new BitmapUDAF)
  val teenagersDF = spark.sql("SELECT name,bitmap(age) FROM people group by name ")
  teenagersDF.show()
  //teenagersDF.map(teenager => "Name: " + teenager(0)).show()
  def main(args:Array[String]):Unit={
    println(11111)
  }

}

/**
  * 用户自定义函数
  */
class BitmapUDAF extends UserDefinedAggregateFunction
{
  var bitmap = new Bitmap(2000000)
  var list = bitmap.array
  override def inputSchema:StructType = StructType(Array(StructField("name",IntegerType,true)))
  override def bufferSchema:StructType = StructType(Array(StructField("count",StringType,true)))
  override def dataType:DataType = StringType
  override def deterministic:Boolean = true
  override def initialize(buffer:MutableAggregationBuffer):Unit = {
    buffer(0)=to_string(list)
  }

  override def update(buffer:MutableAggregationBuffer,input:Row):Unit={
    buffer(0) = to_string(addEleToBitmap(to_array(buffer.getString(0)),input.getInt(0)))
  }

  override def merge(buffer1:MutableAggregationBuffer,buffer2:Row):Unit={
    //println(buffer1.getString(0),buffer2.getString(0))
    buffer1(0) = StrMerge(buffer1.getString(0),buffer2.getString(0))
  }
  override def evaluate(buffer:Row):Any = buffer.getString(0)
}