package com.knoldus.testing

case class Tree(data: Int, children: List[Tree])



object Tree {
  def countChildren(tree: Tree, n: Int):Int = {
    def innerTree(tree: Tree): Int = {
      if(tree.children.size > n)
        tree.children.foldLeft(1){(count, acc ) => count + innerTree(acc)}
      else if(tree.children.isEmpty) 0
      else tree.children.foldLeft(0){(count, acc ) => count + innerTree(acc)}
    }
    innerTree(tree)
  }
}
