import com.google.inject.AbstractModule

import services.PersonService
import services.MessageBrokerService
import services.impl.MessageBrokerServiceImpl
import repositories.impl.PersonRepositoryImpl
import repositories.PersonRepository
import services.impl.PersonServiceImpl

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
    bind(classOf[SystemGlobal]).asEagerSingleton()
     // Set Person service as the implementation for Persons
    bind(classOf[PersonService]).to(classOf[PersonServiceImpl])
    // Set Person Repository as the implementation of persons
    bind(classOf[PersonRepository]).to(classOf[PersonRepositoryImpl])
    
    bind(classOf[MessageBrokerService]).to(classOf[MessageBrokerServiceImpl])
  }

}
