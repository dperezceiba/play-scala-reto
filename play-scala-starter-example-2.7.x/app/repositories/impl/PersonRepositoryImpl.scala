package repositories.impl

import domain._
import javax.inject._
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ Future, ExecutionContext }
import java.sql.Date
import repositories.PersonRepository

@Singleton()
class PersonRepositoryImpl @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) extends PersonRepository {

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class PersonEntity(tag: Tag) extends Table[PersonCrud](tag, "person") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def document = column[String]("document")

    def name = column[String]("name")
    
    def birthdate = column[Date]("birthdate")

    def * = (id, document, name, birthdate) <> ((PersonCrud.apply _).tupled, PersonCrud.unapply)

  }

  private val personsTQ = TableQuery[PersonEntity]; 

  override def save(document: String, name: String, birthdate: Date): Future[PersonCrud] = db.run {
    // create a projection of just the document and name. no insert a value for the id column
    (personsTQ.map(p => (p.document, p.name, p.birthdate))
      //return id of new person
      returning personsTQ.map(_.id)
      //work with data and id, and finally insert the person into database
      into ((propData, id) => PersonCrud(id, propData._1, propData._2, propData._3))) += (document, name, birthdate)
  }

  override def findById(id: Long): Future[Option[PersonCrud]] = db.run {
    personsTQ.filter(_.id === id).result.headOption
  }

  override def delete(id: Long): Future[Int] = db.run {
    personsTQ.filter(_.id === id).delete
  }
  
  override def update(id: Long, person: PersonCrud): Future[Int] = db.run {
    personsTQ.filter(_.id === id).update(person)
  }
  
  override def findAll(): Future[Seq[PersonCrud]] = db.run {
    personsTQ.sortBy(_.id).result
  }

}