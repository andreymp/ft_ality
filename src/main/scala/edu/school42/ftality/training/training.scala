package edu.school42.ftality.training

import scala.annotation.tailrec
import scala.io.StdIn.readChar

object Constants {
  val STARTING_STATE: List[Char] = List.empty
}

def startTraining(machineMemory: (Map[Char, String], Map[List[Char], String])) : Unit = {
  readAndHandleInput(machineMemory)
}

@tailrec
def readAndHandleInput(machineMemory: (Map[Char, String], Map[List[Char], String]))(implicit states: List[Char] = Constants.STARTING_STATE): Unit =
  readChar() match {
    case 'q' =>
      println("finishing training ...")
      sys.exit()
    case input => machineMemory._1.getOrElse(input, "") match {
      case "" =>
        println("Unknown")
        readAndHandleInput(machineMemory)(states = Constants.STARTING_STATE)
      case action =>
        println(action)
        readAndHandleInput(machineMemory)(states = checkCombinations(states:+ input, machineMemory._2))
    }
  }

def checkCombinations(states: List[Char], combinations: Map[List[Char], String]): List[Char] = {
  if combinations.keySet.contains(states) then
    println(combinations(states))
    Constants.STARTING_STATE
  else
    combinations.keySet.count(combination => combination.indexOfSlice(states) == 0) match {
      case 0 => List(states.last)
      case _ => states
    }
}
