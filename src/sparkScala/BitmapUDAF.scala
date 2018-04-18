package sparkScala

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.sql.{Row, SQLContext, SparkSession}
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.expressions.UserDefinedAggregateFunction
import org.apache.spark.sql.types._
import org.apache.spark.sql.types.{ArrayType,MapType,StructType}
import org.apache.spark.sql.types.DataType
import org.apache.spark.sql.expressions.MutableAggregationBuffer
import org.apache.log4j.{Level, Logger}
import sparkScala.Bitmaptransform._
import sparkScala.ArrayTransform._
import scala.collection.mutable.ArrayBuffer
import java.lang.Long

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

  case class Person(name: String, age: String)
  // txt 转化为 dataframe
  val peopleDF = spark.sparkContext.textFile("BigData.txt")
    .map(_.split(","))
    .map(row => Person(row(0), row(1).trim.toString))
    .toDF()

  //dataframe 注册为临时表
  peopleDF.createOrReplaceTempView("people")

  // 查询
  spark.udf.register("bitmap",new InputToBitmap)
  spark.udf.register("merge", new BitmapMerge)
  //spark.udf.register("merge", new BitmapInter)
  var sql =
    """
      |
      | SELECT
      |   merge(t1.bitmap) as merge
      | from
      | (
      |   SELECT
      |     name,
      |     bitmap(age) as bitmap
      |   FROM
      |     people
      |   group by name
      |) t1
    """.stripMargin
  var sql1 =
    """
      |   SELECT
      |     name,
      |     bitmap(`age`) as bitmap
      |   FROM
      |     people
      |   group by name
    """.stripMargin
  val teenagersDF = spark.sql(sql)
  teenagersDF.show()
  //teenagersDF.map(teenager => "Name: " + teenager(0)).show()
  def main(args:Array[String]):Unit={
    println(11111)
  }

}

/**
  * 用户自定义函数
  */
class InputToBitmap extends UserDefinedAggregateFunction
{

  override def inputSchema:StructType = StructType(Array(StructField("name",StringType,true)))
  override def bufferSchema:StructType = StructType(Array(StructField("count",ArrayType(LongType),true)))
  override def dataType:DataType = ArrayType(LongType)
  override def initialize(buffer:MutableAggregationBuffer):Unit = {
    //buffer(0)=new Array[Int](1)
    buffer(0)=new Array[Long](0)
  }

  override def deterministic:Boolean = true

  override def update(buffer:MutableAggregationBuffer,input:Row):Unit={
    //buffer(0) = addEleToBitmap(buffer.getAs[Seq[Int]](0).toArray,input.getString(0))
    buffer(0) = ArrayAppend(buffer.getAs[Seq[Long]](0).toArray,input.getString(0))
  }

  override def merge(buffer1:MutableAggregationBuffer,buffer2:Row):Unit={
    //println(buffer1.get(0),buffer2.get(0))
    //buffer1(0) = BitmapArrayMerge(buffer1.getAs[Seq[Int]](0).toArray,buffer2.getAs[Seq[Int]](0).toArray)
    buffer1(0) = ArrayMerge(buffer1.getAs[Seq[Long]](0).toArray,buffer2.getAs[Seq[Long]](0).toArray)
  }
  override def evaluate(buffer:Row):Any = ArrayToBitmap(buffer.getAs[Seq[Long]](0).toArray)
}

class BitmapMerge extends UserDefinedAggregateFunction
{

  override def inputSchema:StructType = StructType(Array(StructField("columns",ArrayType(LongType),true)))
  override def bufferSchema:StructType = StructType(Array(StructField("bitmapMerge",ArrayType(LongType),true)))
  override def dataType:DataType = ArrayType(LongType)
  override def initialize(buffer:MutableAggregationBuffer):Unit = {
    buffer(0)=new Array[Long](0)
  }

  override def deterministic:Boolean = true

  override def update(buffer:MutableAggregationBuffer,input:Row):Unit={
    //println(buffer.getAs[Seq[Int]](0),input.getAs[Seq[Int]](0))
    //buffer(0) = input.getAs[Seq[Int]](0).toArray
    buffer(0) = BitmapArrayMergeLong(buffer.getAs[Seq[Long]](0).toArray,input.getAs[Seq[Long]](0).toArray)
    //print("------------")
    println(buffer.get(0),input.getAs[Seq[Long]](0))
  }

  override def merge(buffer1:MutableAggregationBuffer,buffer2:Row):Unit={
    buffer1(0) = BitmapArrayMergeLong(buffer1.getAs[Seq[Long]](0).toArray,buffer2.getAs[Seq[Long]](0).toArray)
    println(buffer1.get(0),buffer2.get(0))
  }
  override def evaluate(buffer:Row):Any = buffer.get(0)
}

class BitmapInter extends UserDefinedAggregateFunction
{

  override def inputSchema:StructType = StructType(Array(StructField("columns",ArrayType(LongType),true)))
  override def bufferSchema:StructType = StructType(Array(StructField("bitmapInter",ArrayType(LongType),true)))
  override def dataType:DataType = ArrayType(LongType)
  override def initialize(buffer:MutableAggregationBuffer):Unit = {
    buffer(0)=new Array[Long](0)
  }

  override def deterministic:Boolean = true

  override def update(buffer:MutableAggregationBuffer,input:Row):Unit={
    println(buffer.get(0),input.getAs[Seq[Long]](0))
    if(buffer.getAs[Seq[Long]](0).toArray.length > 0) {
      buffer(0) = BitmapArrayInterLong(buffer.getAs[Seq[Long]](0).toArray, input.getAs[Seq[Long]](0).toArray)
    }else{
      buffer(0) = input.getAs[Seq[Long]](0).toArray
    }

  }

  override def merge(buffer1:MutableAggregationBuffer,buffer2:Row):Unit={
    //合并
    if(buffer1.getAs[Seq[Long]](0).toArray.length >0 && buffer2.getAs[Seq[Long]](0).toArray.length > 0 ) {
      buffer1(0) = BitmapArrayInterLong(buffer1.getAs[Seq[Long]](0).toArray, buffer2.getAs[Seq[Long]](0).toArray)
    } else if (buffer1.getAs[Seq[Long]](0).toArray.length == 0 && buffer2.getAs[Seq[Long]](0).toArray.length > 0 ) {
      buffer1(0) = buffer2.getAs[Seq[Long]](0).toArray
    }
  }
  override def evaluate(buffer:Row):Any = buffer.get(0)
}