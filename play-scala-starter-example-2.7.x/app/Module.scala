import com.google.inject.AbstractModule
import java.time.Clock

import services.{ApplicationTimer, AtomicCounter, Counter}
import services._
import services.impl.ResolveOperation
import services.impl.PersonsImpl
import services.impl.MessageServiceImpl
import services.PersonService
import services.impl.PersonServiceImpl
import repositories.PersonRepository
import repositories.impl.PersonRepositoryImpl
import services.MessageBrokerService
import services.impl.MessageBrokerServiceImpl

/**
 * This class is a Guice module that tells Guice how to bind several
 * different types. This Guice module is created when the Play
 * application starts.

 * Play will automatically use any class called `Module` that is in
 * the root package. You can create modules in other locations by
 * adding `play.modules.enabled` settings to the `application.conf`
 * configuration file.
 */
class Module extends AbstractModule {

  override def configure() = {
    // Use the system clock as the default implementation of Clock
    bind(classOf[Clock]).toInstance(Clock.systemDefaultZone)
    // Ask Guice to create an instance of ApplicationTimer when the
    // application starts.
    bind(classOf[ApplicationTimer]).asEagerSingleton()
    // Set AtomicCounter as the implementation for Counter.
    bind(classOf[Counter]).to(classOf[AtomicCounter])
    
    // Set SimpleCalculator as the implementation for Calculator
    bind(classOf[Calculator]).to(classOf[SimpleCalculator])
    
    // Set Resolve Opertation as the implementation for Calculator
    bind(classOf[Resolve]).to(classOf[ResolveOperation])
    
     // Set Person as the implementation for Persons
    bind(classOf[Persons]).to(classOf[PersonsImpl])
    
     // Set Message as the implementation for Messages
    bind(classOf[MessageService]).to(classOf[MessageServiceImpl])
    
    bind(classOf[PersonService]).to(classOf[PersonServiceImpl])
    
    bind(classOf[PersonRepository]).to(classOf[PersonRepositoryImpl])
    
    bind(classOf[MessageBrokerService]).to(classOf[MessageBrokerServiceImpl])
    
  }

}
