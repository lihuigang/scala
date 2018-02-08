package study
//修饰符
class Modifier {

    class Inner{
      private def f(){
        println("f")
      }

      class InnerMost{
        f() // 正确
      }

    def outer(): Unit ={
      var outer = new Inner()
      return outer.f()
      new InnerMost()
    }
  }

  class Super{
    protected def f() {println("f")}
  }

  class Sub extends Super{
    f()
  }
  class Other{
    printf("1234")
    // (new Super).f() 错误
  }

  class Out {
    class Inner {
      def f() { println("f") }
      class InnerMost {
        f() // 正确
      }
    }
    (new Inner).f() // 正确因为 f() 是 public
  }

  def GetResult(num:Int): Unit ={
    if (num == 1){
      var Fact = new Inner()
      Fact.outer()
    }
    if (num == 2){
      var Fact = new Super()
    }
    if (num == 3){
      var Fact = new Other()
    }
  }


}

object Modifier{
  def main(args:Array[String]): Unit ={
    var aa = new Modifier()
    aa.GetResult(1)
  }
}

