import java.util



/*

val string = "Knol is a unit of Knowledge & Dus comes from Druksh which is Sanskrit for a tree, hence Knoldus is a tree of Knowledge."


val list =  List("Knol", "Knowledge","Dus", "Druksh", "Knoldus is a tree of Knowledge")



val count =  string.distinct.toCharArray.map(x=>(x,string.count(_==x)))
//string.toList.distinct.foreach(c => println(string.count(_ == c)))



val word = list.distinct.map(x=>if(string.contains(x)) x )*/

def check(l: Int, list: List[Int]) = {
  list.contains(-l)
}

def check2(l: Int, y:Int) = {
  l == y
}

/*def checkSum(list: List[Int]): Boolean = {
  def checkbox(list: List[Int], n:Int): Boolean = {
    list match {
      case Nil => false
      case x:: xs =>
        if(x == 0) true else if(check(x+n, list)) {
        true
      }
        else if(checkSum(xs))
        true
        else checkbox(xs, x + n)
    }
  }
  checkbox(list, 0)
}*/

def checkSum2(list: List[Int], size:Int, target: Int, lookup: util.Map[String, Boolean]): Boolean = {
    list match {
      case _ if size > 0 && target == 0   => true
      case _ if (size < 0 || target < 0)  => false
      case _ =>
        val key = size + "|" + target
        if (!lookup.containsKey(key)) {
          // Case 1. include current item in the subset (A[n]) & recur
          // for remaining items (n - 1) with decreased sum (sum - A[n])
          val include = checkSum2(list, size - 1, target - list(size), lookup)
          // Case 2. exclude current item n from subset and recur for
          // remaining items (n - 1)
          val exclude = checkSum2(list, size - 1, target, lookup)
          // assign true if we get subset by including or excluding
          // current item
          lookup.put(key, include || exclude)
        }
        // return solution to current sub-problem
        lookup.get(key)
    }
}



def checkSum3(list: List[Int], y: Int): Boolean = {
  def checkbox(list: List[Int], n:Int): Boolean = {
    list match {
      case Nil => false
      case x:: xs =>
        if(x == y) true else if(check2(x+n, y)) {
          true
        }
        else if(checkSum3(xs, y))
          true
        else checkbox(xs, x + n)
    }
  }
  checkbox(list, 0)
}

val list =  List(10,1,2,3,5,-1,4,2,6)

val lookup = new util.HashMap[String, Boolean]
//checkSum(list)
//checkSum2(list, list.length -1,14, lookup)

val newlist = List(10,1,2,-3,3,4,2,6)

  checkSum3(newlist, -3)
/*
if (checkSum2(list, list.length - 1, 5, lookup))
  System.out.println("Yes")
else
  System.out.println("No")*/
