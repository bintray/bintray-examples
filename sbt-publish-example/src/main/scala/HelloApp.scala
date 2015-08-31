object HelloApp extends App {

  def hello(out:String=>Unit)(args:Array[String]) =  {
    val param = args.headOption.getOrElse("world")
    out(s"Hello $param !")
  }

  def helloToConsole = hello(Console.println)(args)

  helloToConsole
}