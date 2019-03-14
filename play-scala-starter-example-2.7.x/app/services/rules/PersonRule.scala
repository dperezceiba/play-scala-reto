package services.rules

import org.joda.time.Years
import org.joda.time.LocalDate
import domain.PersonCrud

object PersonRule {
  
  private val AGE_PERMITTED = 18;
  
  def validPerson(person: PersonCrud): Boolean = {
    val age: Years = Years.yearsBetween(new LocalDate(person.birthdate), new LocalDate())
    age.getYears >= AGE_PERMITTED
  }
  
}