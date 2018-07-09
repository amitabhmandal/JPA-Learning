package entities;

import java.util.Date;

import org.hibernate.Session;

import utils.HibernateUtil;

public class TestingUser {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory()
            .openSession();

        session.beginTransaction();
        

        NewUser user = new NewUser();
        user.setBirthDate(new Date());
        user.setCreatedBy("AmitabhMandal");

        user.setCreatedDate(new Date());
        user.setEmailAddress("abc@gmail.com");
        user.setFirstName("Amitabh");
        user.setLastName("mandal");
        user.setLastUpdatedDate(new Date());
        user.setLastUpdatedBy("AmitabhMandal");

        session.save(user);
        session.getTransaction()
            .commit();
    }

}

/*

Exception in thread "main" java.lang.ExceptionInInitializerError
at entities.TestingUser.main(TestingUser.java:12)
Caused by: org.hibernate.service.spi.ServiceException: Unable to create requested service [org.hibernate.engine.jdbc.env.spi.JdbcEnvironment]
at org.hibernate.service.internal.AbstractServiceRegistryImpl.createService(AbstractServiceRegistryImpl.java:275)
at org.hibernate.service.internal.AbstractServiceRegistryImpl.initializeService(AbstractServiceRegistryImpl.java:237)
at org.hibernate.service.internal.AbstractServiceRegistryImpl.getService(AbstractServiceRegistryImpl.java:214)
at org.hibernate.id.factory.internal.DefaultIdentifierGeneratorFactory.injectServices(DefaultIdentifierGeneratorFactory.java:152)
at org.hibernate.service.internal.AbstractServiceRegistryImpl.injectDependencies(AbstractServiceRegistryImpl.java:286)
at org.hibernate.service.internal.AbstractServiceRegistryImpl.initializeService(AbstractServiceRegistryImpl.java:243)
at org.hibernate.service.internal.AbstractServiceRegistryImpl.getService(AbstractServiceRegistryImpl.java:214)
at org.hibernate.boot.internal.InFlightMetadataCollectorImpl.<init>(InFlightMetadataCollectorImpl.java:179)
at org.hibernate.boot.model.process.spi.MetadataBuildingProcess.complete(MetadataBuildingProcess.java:119)
at org.hibernate.boot.model.process.spi.MetadataBuildingProcess.build(MetadataBuildingProcess.java:84)
at org.hibernate.boot.internal.MetadataBuilderImpl.build(MetadataBuilderImpl.java:474)
at org.hibernate.boot.internal.MetadataBuilderImpl.build(MetadataBuilderImpl.java:85)
at org.hibernate.cfg.Configuration.buildSessionFactory(Configuration.java:689)
at utils.HibernateUtil.buildSessionFactory(HibernateUtil.java:18)
at utils.HibernateUtil.<clinit>(HibernateUtil.java:11)
... 1 more
Caused by: org.hibernate.HibernateException: Access to DialectResolutionInfo cannot be null when 'hibernate.dialect' not set
at org.hibernate.engine.jdbc.dialect.internal.DialectFactoryImpl.determineDialect(DialectFactoryImpl.java:100)
at org.hibernate.engine.jdbc.dialect.internal.DialectFactoryImpl.buildDialect(DialectFactoryImpl.java:54)
at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.initiateService(JdbcEnvironmentInitiator.java:137)
at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.initiateService(JdbcEnvironmentInitiator.java:35)
at org.hibernate.boot.registry.internal.StandardServiceRegistryImpl.initiateService(StandardServiceRegistryImpl.java:94)
at org.hibernate.service.internal.AbstractServiceRegistryImpl.createService(AbstractServiceRegistryImpl.java:263)
... 15 more

*/

