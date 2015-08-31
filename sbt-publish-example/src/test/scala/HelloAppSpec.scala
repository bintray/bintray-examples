import org.scalatest._
import org.scalamock.scalatest.MockFactory

class  HelloAppSpec extends FlatSpec with MockFactory{

  "HelloApp" should "say hello to the world by default" in {
      val printMock = mockFunction[String, Unit]("printMock")
      printMock.expects("Hello world !").once
      HelloApp.hello(printMock)(Array.empty[String])
  }

  it should "say hello to eric" in {
    val printMock = mockFunction[String, Unit]("printMock")
    printMock.expects("Hello eric !").once
    HelloApp.hello(printMock)(Array("eric"))
  }
}