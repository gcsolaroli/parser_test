import zio.Chunk
import zio.parser.{ Syntax, StringParserError, SyntaxOps, StringErrSyntaxOps, AnySyntaxOps }

// @main def hello(): Unit =
//   println("Hello world!")
//   println(msg)

// def msg = "I was compiled by Scala 3. :)"

sealed abstract class Schema

object Schema:
  final case class TextValue()                              extends Schema
  final case class NumericValue()                           extends Schema
  final case class ListOfValues(schema: Schema)             extends Schema

  def parse (schemaDefinition: String): Either[StringParserError[String], Schema] =
    SchemaSyntax.parse(schemaDefinition)

object SchemaSyntax:
  val whitespaces: Syntax[String, Char, Char, Unit] = Syntax.charIn(' ').*.asPrinted((), Chunk(' '))

  val textValue: Syntax[String, Char, Char, Schema.TextValue] = Syntax.string("text", Schema.TextValue())

  val numericValue: Syntax[String, Char, Char, Schema.NumericValue] = Syntax.string("integer", Schema.NumericValue())

  lazy val listOfValues: Syntax[String, Char, Char, Schema.ListOfValues] = (item <~ Syntax.char('*')).transform(
      itemSchema => Schema.ListOfValues(itemSchema),
      items => items.schema
  )

  lazy val item: Syntax[String, Char, Char, Schema] =   numericValue  .widen[Schema]
                                                    <>  textValue     .widen[Schema]

  lazy val items: Syntax[String, Char, Char, Schema]  =   listOfValues  .widen[Schema]
                                                      <>  item

  lazy val root: Syntax[String, Char, Char, Schema] = Syntax.char('=') ~> whitespaces ~> items

  lazy val schema: Syntax[String, Char, Char, Schema] = root

  def parse (definition: String): Either[StringParserError[String], Schema] = schema.parseString(definition)

