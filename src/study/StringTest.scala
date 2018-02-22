package study

object StringTest {
  def main(args: Array[String]) {
    var palindrome = "www.runoob.com";
    var baiduUrl=".cn"
    var Concat = palindrome.concat(baiduUrl)
    println(Concat)
    var len = palindrome.length();
    println( "String Length is : " + len );
  }
}