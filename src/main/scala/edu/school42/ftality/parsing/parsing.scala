package edu.school42.ftality.parsing

import edu.school42.ftality.utility.printParsingErrorMessage

import scala.io.{BufferedSource, Source}
import scala.util.matching.Regex

def parse(grammarFile: String): (Map[Char, String], Map[List[Char], String]) = {
  val actionsRegexp: Regex = """^(\w)=>(\w+)$""".r
  val combinationsRegexp: Regex = """^([\w-]+)fin=>(\w+)$""".r
  using(grammarFile) { source =>
    if source.isEmpty then printParsingErrorMessage(source)
    source.getLines().foldLeft((Map.empty[Char, String], Map.empty[List[Char], String])) {
      case ((actions, combinations), line) => line match {
        case actionsRegexp(hook, action) => (actions + (hook.charAt(0) -> action.replace("_", " ")), combinations)
        case combinationsRegexp(hooks, action) =>
          (actions, combinations + (hooks.split("-").filter(hook => hook.nonEmpty).map(hook => hook.charAt(0)).toList -> action.replace("_", " ")))
        case _ => printParsingErrorMessage(source)
      }
    }
  }
}

def using[B](grammarFile: String)(f: BufferedSource => B): B =
  try
    val source: BufferedSource = Source.fromResource(grammarFile)
    val result: B = f(source)
    source.close()
    result
  catch case _: Exception =>
    println("error while opening file")
    sys.exit(1)