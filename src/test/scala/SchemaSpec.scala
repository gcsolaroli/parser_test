import zio.test.{ TestConsole, ZIOSpecDefault, assertTrue }
import zio.test.TestResult.{ allSuccesses }
import zio.Chunk

object SchemaSuite extends ZIOSpecDefault:
  def spec = suite("Validator features") (
    test("Test Schema definition parsing"):
      assertTrue(Right(Schema.NumericValue()) == Schema.parse("= integer"))
    ,
    test("Test Schema definition parsing"):
      assertTrue(Right(Schema.TextValue()) == Schema.parse("= text"))
    ,
    test("Test Schema definition parsing"):
      assertTrue(Right(Schema.ListOfValues(Schema.NumericValue())) == Schema.parse("= integer*"))
    ,
  )

