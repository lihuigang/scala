package scalaDes

class Upper {
  def upper(strings: String*): Seq[String] = {
    strings.map((s:String) => s.toUpperCase())
  }

  def upper_1(strings: String*) = strings.map(_.toUpperCase())

  def upper_2(agrs:Array[String]): Unit ={
    agrs.map(_.toUpperCase()).foreach(printf("%s ",_))
  }

  def upper_3(strings: String*): Unit ={
    strings.map(_.toUpperCase())
  }
  def upper_4(strings: String*): Seq[String] ={
    strings.map((s:String)=>s)
  }
}

object Upper{
  def main(agrs:Array[String]): Unit ={
    var up = new Upper
    val a1 = Array("aaa", "bbb", "ccc")
    //up.upper_2(a1)
    print(up.upper("aaaa"))
    print(up.upper_1("aaaa"))
    up.upper_2(a1)
    print(up.upper_3("aaaa"))
  }
}