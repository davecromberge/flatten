package answers

import flatten.Part14
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scalaz.contrib.std.futureInstance
import scalaz.Scalaz._
import scalaz._

trait Part14Answers extends Part14 {

  for {
    username <- getUserName(data) |> Future.successful |> OptionT.apply
    user <- getUser(username) |> OptionT.apply
    email = getEmail(user)
    validatedEmail <- validateEmail(email) |> Future.successful |> OptionT.apply
    success <- sendEmail(email) |> OptionT.apply
  } yield success

}
