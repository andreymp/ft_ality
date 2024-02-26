package edu.school42.ftality.utility

import jdk.internal.loader.Resource

import scala.io.Source

def printMachineMemory(machineMemory: (Map[Char, String], Map[List[Char], String])): Unit = {
  println("Actions:")
  machineMemory._1.foreach { (hook, action) => println(hook + " --> " + action) }
  println("Combinations:")
  machineMemory._2.foreach { (hooks, action) => println(hooks.foldLeft("") { case (combination, hook) =>
    if combination.isEmpty then combination + hook else combination + " -> " + hook} + " --> " + action)
  }
}
