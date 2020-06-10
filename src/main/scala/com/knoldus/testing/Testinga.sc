
val string = "Knol is a unit of Knowledge & Dus comes  from Druksh which is Sanskrit for a tree, hence Knoldus is a tree of Knowledge."


val removeSpecialCharacter = string.split(" ")

val countMap =
  string.toLowerCase.toCharArray.filter(_.isLetter).map(x=> (x,string.toLowerCase.count(_==x)))

val n4 = countMap.toList.groupBy(_._2).mapValues(_.map(_._1)).map(_.swap)

val n5 = n4.keys.map(_.distinct).map(_.toString)

val check = countMap.toList.groupBy(_._2).mapValues(_.map(_._1))
