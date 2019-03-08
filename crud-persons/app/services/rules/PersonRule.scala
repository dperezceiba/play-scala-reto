package services.rules

import domain.Person
import org.joda.time.Years
import org.joda.time.LocalDate

object PersonRule {
  
  private val AGE_PERMITTED = 18;
  
  def validPerson(person: Person): Boolean = {
    val age: Years = Years.yearsBetween(new LocalDate(person.birthdate), new LocalDate())
    age.getYears >= AGE_PERMITTED
  }
  
}