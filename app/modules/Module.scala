package modules

import java.sql.Connection
import javax.inject.Inject

import akka.actor.ActorSystem
import com.google.inject.AbstractModule
import com.typesafe.config.ConfigFactory
import liquibase.Liquibase
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.{ClassLoaderResourceAccessor, CompositeResourceAccessor, FileSystemResourceAccessor}
import play.api.db.Database
import play.api.inject.ApplicationLifecycle

import scala.concurrent.Future

trait LiquibaseComponent

class LiquibaseComponentImpl @Inject()(lifecycle: ApplicationLifecycle, db: Database) extends LiquibaseComponent {

  lazy val config = ConfigFactory.load()

  def performMigrations: Unit = {
    val changeLog = config.getString("liquibase.changelog")

    db.withConnection { connection =>
      val liqui = try {
        getLiquibase(changeLog, connection)
      } catch {
        case e: Exception => throw new Exception("Liquibase can't be instantiated")
      }
      liqui.update("dev")
    }

  }

  def getLiquibase(changeLogFilePath: String, connection: Connection): Liquibase = {
    val fileAccessor = new FileSystemResourceAccessor()
    val classLoaderAccessor = new ClassLoaderResourceAccessor(classLoader)
    val fileOpener = new CompositeResourceAccessor(fileAccessor, classLoaderAccessor)
    new Liquibase(changeLogFilePath, fileOpener, new JdbcConnection(connection))
  }

  def classLoader: ClassLoader = classOf[ActorSystem].getClassLoader

  lifecycle.addStopHook { () =>
    // previous contents of Plugin.onStop
    Future.successful(())
  }

  performMigrations
}


class LiquibaseModule extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[LiquibaseComponent]).to(classOf[LiquibaseComponentImpl]).asEagerSingleton()
  }
}