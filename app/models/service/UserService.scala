package models.service

import javax.inject.Inject

import com.google.inject.ImplementedBy
import models.entity.User
import org.squeryl.adapters.PostgreSqlAdapter
import org.squeryl.{Session, SessionFactory}
import play.api.db.Database
import org.squeryl.PrimitiveTypeMode._
/**
  * Created by anton on 10.06.17.
  */
@ImplementedBy(classOf[UserServiceImpl])
trait UserService extends Service[User]

class UserServiceImpl @Inject()(db: Database) extends UserService{

  import models.DefineScheme._
  SessionFactory.concreteFactory = Some(() => Session.create(db.getConnection(), new PostgreSqlAdapter))

  override def insert(data: User): Unit = {
    transaction{
      user.insert(data)
    }
  }

  override def selectAll2(): Seq[User] = {
    transaction{
      from(user)(u => select(u)).toList
    }
  }

  override def selectById(id: String): User = {
    transaction{
      from(user)(u => where(u.id === id) select u).single
    }
  }

  override def delete(id: String): Unit = {
    transaction{
      user.deleteWhere(u => u.id === id)
    }
  }

  override def update2(data: User): Unit = {
    transaction{
      update(user)(u => where(u.id === data.id) set(u.name := data.name, u.phoneNumber := data.phoneNumber))
    }
  }
}
