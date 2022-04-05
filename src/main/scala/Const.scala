final val MAXRING       = 14
final val POWER         = Array(0, 1, 3, 9, 27, 81, 243, 729, 2187, 6561, 19683, 59049, 177147, 531441, 1594323, 4782969, 14348907)
final val SIMATCHNUMBER = Array(0, 0, 1, 3, 10, 30, 95, 301, 980, 3228, 10797, 36487, 124542, 428506, 1485003)

class TpLiveTwin    (var nLive: Int, val live: Array[Int])
class TpUpdateState (var lTwin: TpLiveTwin, val real: Array[Int], var nReal: Int, var byte: Byte, var realterm: Int)
class TpRingNchar   (val ring: Int, val nchar: Int)
class TpBaseCol     (var depth: Int, var col: Int, var on: Int)
class TpTMbind      (val interval: Array[Int], val weight: Array[Array[Int]], val matchW: Array[Array[Array[Int]]])
class TpRealityPack (val twi: Array[Int], var nTw: Int, val sum: Array[Int], val unt: Array[Int], var nUn: Int)



