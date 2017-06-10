package models

import models.entity.User
import org.squeryl.Schema

/**
  * Created by anton on 10.06.17.
  */
object DefineScheme extends Schema{
  val user = table[User]("USER")
}
