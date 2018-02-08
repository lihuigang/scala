package study

/* 不能使用一下关键字作为变量
abstract	case	catch	class
def	do	else	extends
false	final	finally	for
forSome	if	implicit	import
lazy	match	new	null
object	override	package	private
protected	return	sealed	super
this	throw	trait	try
true	type	val	var
while	with	yield
-	:	=	=>
<-	<:	<%	>:
#	@
*/

object Variable {
  var a:String ="A"
  var b="B"
  def main(args:Array[String]){
    System.out.print(a,b)
  }
}
