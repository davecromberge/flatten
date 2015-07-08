package flatten

import scalaz.Scalaz._

trait Part14 extends Part13 {

  // In the previous part, we had constructs like

  // OptionT(Future.successful(theThing)).

  // The `OptionT` and `Future.successful` parts are not so interesting, they're just to make the container right.

  // Scalaz has a function application operator, that reverses function and parameter.

  // This:
  val y1 = double(5)
  // Is equivalent to this:
  val y2 = 5 |> double

  // Back to our good old service methods, where some are now asynchronous: returning a Future.
  def getUserName(data: Map[String, String]): Option[String] = ???
  def getUser(name: String): Future[Option[User]] = ???
  def getEmail(user: User): String = ???
  def validateEmail(email: String): Option[String] = ???
  def sendEmail(email: String): Future[Option[Boolean]] = ???
  val data: Map[String, String] = ???

  // Exercise, rewrite the for-comprehension from part 13, but use `|>` for applying Future.successful and OptionT.apply
  for {
    username <- getUserName(data)          |> Future.successful |> OptionT.apply
    user <- getUser(username)              |> OptionT.apply
    email = getEmail(user)
    validatedEmail <- validateEmail(email) |> Future.successful |> OptionT.apply
    success <- sendEmail(validatedEmail)   |> OptionT.apply
  } yield success

  def double(i: Int) = i * 2
}
