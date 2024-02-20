import edu.school42.ftality.training.startTraining
import edu.school42.ftality.utility.printMachineMemory
import edu.school42.ftality.parsing.parse


@main def start(grammarFile: String): Unit = {
  grammarFile match {
    case null | "" => println("grammar file must be presented")
    case grammarFile => 
      if !grammarFile.endsWith(".fagr") then println("wrong grammar file format")
      else
        println("starting analyzing grammar file...")
        val machineMemory: (Map[Char, String], Map[List[Char], String]) = parse(grammarFile)
        printMachineMemory(machineMemory)
        println("starting training ...")
        startTraining(machineMemory)
  }
}
