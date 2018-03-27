package others


import java.text.SimpleDateFormat
import java.util.{Calendar, Date}
import org.joda.time.DateTime
import java.util.Date

/**
  * Created by juanpi on 2015/8/18.
  */
object DateTest {
  def getDate(dt:Any,interval: Int):Any = {
    val dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")
    val cal: Calendar = Calendar.getInstance()
    if (dt == null){
      return dt
    }else if( dt.toString.length == 8 ){
      var Dt= dt.toString
      val dateString = Dt.substring(0,4)+"-"+Dt.substring(4,6)+"-"+Dt.substring(6,8)
      //println(dateString)
      val date = dateFormat.parse(dateString)
      cal.setTime(date);
      cal.add(Calendar.DATE, interval)
      val newdate = dateFormat.format(cal.getTime())
      return newdate.replace("-","")
    }else{
      return dt
    }
  }

  def getYesterday():String={
    var  dateFormat:SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")
    var cal:Calendar=Calendar.getInstance()
    cal.add(Calendar.DATE,1)
    var yesterday=dateFormat.format(cal.getTime())
    return yesterday
  }

  def main(args: Array[String]) {
    println (getDate("20180101",1));
  }
}
