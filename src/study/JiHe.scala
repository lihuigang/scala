package study

object JiHe {
  def main(args:Array[String]): Unit ={
    // 定义整型 List
    val x = List(1,2,3,4)

    // 定义 Set
    var y = Set(1,3,5,7)

    // 定义 Map
    val z = Map("one" -> 1, "two" -> 2, "three" -> 3)

    // 创建两个不同类型元素的元组
    val a = (10, "Runoob")

    // 定义 Option
    val b:Option[Int] = Some(5)

    println (x)
    println(y)
    println(z)
    println(a)
    println(b)
  }

}
