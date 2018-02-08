package study

object Calculate {
  def main(args: Array[String]) {
    var a = 10
    var b = 20
    var c = 0
    println("a == b = " + (a == b))
    println("a != b = " + (a != b))
    println("a > b = " + (a > b))
    println("a < b = " + (a < b))
    println("b >= a = " + (b >= a))
    println("b <= a = " + (b <= a))

    // 逻辑运算
    var e = true
    var f = false

    println("e && f = " + (e && f))

    println("e || f = " + (e || f))

    println("!(e && f) = " + !(e && f))


    // 运算
    a = 10;
    b = 20;
    c = 0;

    c = a + b;
    println("c = a + b  = " + c );

    c += a ;
    println("c += a  = " + c );

    c -= a ;
    println("c -= a = " + c );

    c *= a ;
    println("c *= a = " + c );

    a = 10;
    c = 15;
    c /= a ;
    println("c /= a  = " + c );

    a = 10;
    c = 15;
    c %= a ;
    println("c %= a  = " + c );

    c <<= 2 ;
    println("c <<= 2  = " + c );

    c >>= 2 ;
    println("c >>= 2  = " + c );

    c >>= 2 ;
    println("c >>= a  = " + c );

    c &= a ;
    println("c &= 2  = " + c );

    c ^= a ;
    println("c ^= a  = " + c );

    c |= a ;
    println("c |= a  = " + c );
  }

}
