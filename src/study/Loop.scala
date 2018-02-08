package study

object Loop {
    def main(args: Array[String]) {
      // 局部变量
      var a = 10;

      // do 循环
      do{
        println( "Value of a: " + a );
        a = a + 1;
        if (a > 19){
          print(a)
        }
      }while( a < 20 )
    }
}
