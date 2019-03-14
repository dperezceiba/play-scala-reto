package services.rule

import org.scalatest.FlatSpec
import java.sql.Date
import domain.PersonCrud
import org.joda.time.LocalDate
import services.rules.PersonRule

class PersonRuleSpec extends FlatSpec{
  
  "A Person" should "Error when violates the rule" in {
     val testData = PersonCrud(0L, "1051", "Dario", new Date(new LocalDate(2010, 6, 8).toDate().getTime))
     assert(!PersonRule.validPerson(testData))
  }
  
  "A Person" should "Pass when meet  the rule" in {
     val testData = PersonCrud(0L, "1051", "Dario", new Date(new LocalDate(1991, 6, 8).toDate().getTime))
     assert(PersonRule.validPerson(testData))
  }
  
}