package com.knoldus.testing

object FindSum {

/*
  def checkSum(list: List[Int]) = {

    def checkbox(list: List[Int], n:Int): Boolean = {

      list match {
        case List() => false
        case x:: xs => if(x+n == 0)  true  else checkbox(xs, x + n)
      }
    }

    checkbox(list, 0)
  }

*/

  import java.util

    def subsetSum(a: Array[Int],
                  n: Int,
                  sum: Int,
                  lookup: util.Map[String, Boolean]): Boolean = {
      // return true if sum becomes 0 (subset found)
      if (sum == 0) return true
      // base case: no items left or sum becomes negative
      if (n < 0 || sum < 0) return false
      // construct an unique map key from dynamic elements of the input
      val key = n + "|" + sum
      // if sub-problem is seen for the first time, solve it and
      // store its result in a map
      if (!lookup.containsKey(key)) {
        // Case 1. include current item in the subset (A[n]) & recur
        // for remaining items (n - 1) with decreased sum (sum - A[n])
        val include = subsetSum(a, n - 1, sum - a(n), lookup)
        // Case 2. exclude current item n from subset and recur for
        // remaining items (n - 1)
        val exclude = subsetSum(a, n - 1, sum, lookup)
        // assign true if we get subset by including or excluding
        // current item
        lookup.put(key, include || exclude)
      }
      // return solution to current sub-problem
      lookup.get(key)
    }

    def main(args: Array[String]): Unit = {
      // Input: set of items and a sum
      val a = Array(7, 3, 2, 5, 8)
      val sum = 14

      val lookup = new util.HashMap[String, Boolean]
      if (subsetSum(a, a.length - 1, sum, lookup))
        System.out.println("Yes")
      else
        System.out.println("No")

  }

}
