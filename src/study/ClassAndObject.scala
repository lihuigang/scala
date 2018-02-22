package study

class Point(xc: Int, yc: Int) {
  var x: Int = xc
  var y: Int = yc

  def move(dx: Int, dy: Int) {
    x = x + dx
    y = y + dy
    println ("x 的坐标点: " + x);
    println ("y 的坐标点: " + y);
  }
}
object ClassAndObject{
  def main(args:Array[String]): Unit ={
    var a = new Point(1,2)
    a.move(1,2)
  }
}
