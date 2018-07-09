import java.util.Date;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;

import entities.AccountType;

public class DemoTesterApplication {

    public static void main(String[] args) {

        Configuration configuration = new Configuration();

        // In hibernate we must register our mapping meta-data with our
        // configuration.

        // Since our meta-data is contained within a class, and we are
        // performing via annotations we have to register the account type
        // with our configuration.

        configuration.addAnnotatedClass(AccountType.class);

        configuration.setProperties(new Properties() {
            {
                put("hibernate.connection.username", "root");
                put("hibernate.connection.password", "12345678");
                put("hiberbate.connection.driver_class", "com.mysql.jdbc.Driver");
                put("hibernate.connection.url", "jdbc:mysql://localhost:3306/ifinances");
                put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                put("hibernate.show_sql", "true");
                put("hibernate.format_sql", "true");
                // put("hibernate.generate_statistics", "true");

                // Without formatting on
                // Hibernate: insert into ACCOUNT_TYPE (LAST_UPDATED_DATE, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, NAME) values (?, ?, ?, ?, ?)

                // With Formatting on
                /*
                insert 
                into
                    ACCOUNT_TYPE
                    (LAST_UPDATED_DATE, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, NAME) 
                values
                    (?, ?, ?, ?, ?)
                 */

                // To enable hibernate show the parameters of the SQL use the following Logging
                // # Log JDBC bind parameter runtime arguments
                // log4j.logger.org.hibernate.type=trace

                // Why do I need to configure the SQL dialect of a data source?

                // hibernate.dialect property makes Hibernate to generate the appropriate SQL statements
                // for the chosen database.

                // Hibernate uses "dialect" configuration to know which database you are
                // using so that it can convert hibernate query to database specific query.

                // The SQL dialect converts the HQL query which we write in our java or any other object oriented program to the specific database SQL.

                /*
                The SQL dialect converts the HQL query which we write in our java or any other object oriented program to the specific database SQL.
                
                For example in the java suppose I write List employees = session.createQuery("FROM Employee").list();
                
                but when my dialect is <property name="hibernate.dialect"> org.hibernate.dialect.MySQLDialect
                
                The HQL ("FROM Employee") gets converted to "SELECT * FROM EMPLOYEE" before hitting the MySQL database
                
                */
            }

        });

        // Building Session Factory

        SessionFactory sessionFactory = configuration.buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
            .build());

        Session session = sessionFactory.openSession();

        session.beginTransaction();
        AccountType accountType = new AccountType();
        accountType.setName("Checking");

        accountType.setCreatedDate(new Date());
        accountType.setLastUpdatedDate(new Date());

        accountType.setCreatedBy("AmitabhMandal");
        accountType.setLastUpdatedBy("AmitabhMandal");

        session.save(accountType);
        session.getTransaction()
            .commit();
        session.close();
        System.out.println("Inserted Successfully");

    }

}

/* Dialect Not specified exception

Exception in thread "main" org.hibernate.service.spi.ServiceException: Unable to create requested service [org.hibernate.engine.jdbc.env.spi.JdbcEnvironment]
at org.hibernate.service.internal.AbstractServiceRegistryImpl.createService(AbstractServiceRegistryImpl.java:275)
at org.hibernate.service.internal.AbstractServiceRegistryImpl.initializeService(AbstractServiceRegistryImpl.java:237)
at org.hibernate.service.internal.AbstractServiceRegistryImpl.getService(AbstractServiceRegistryImpl.java:214)
at org.hibernate.engine.jdbc.internal.JdbcServicesImpl.configure(JdbcServicesImpl.java:51)
at org.hibernate.boot.registry.internal.StandardServiceRegistryImpl.configureService(StandardServiceRegistryImpl.java:100)
at org.hibernate.service.internal.AbstractServiceRegistryImpl.initializeService(AbstractServiceRegistryImpl.java:246)
at org.hibernate.service.internal.AbstractServiceRegistryImpl.getService(AbstractServiceRegistryImpl.java:214)
at org.hibernate.engine.jdbc.connections.internal.BasicConnectionCreator.convertSqlException(BasicConnectionCreator.java:116)
at org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionCreator.makeConnection(DriverManagerConnectionCreator.java:37)
at org.hibernate.engine.jdbc.connections.internal.BasicConnectionCreator.createConnection(BasicConnectionCreator.java:58)
at org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections.addConnections(DriverManagerConnectionProviderImpl.java:363)
at org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections.<init>(DriverManagerConnectionProviderImpl.java:282)
at org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections.<init>(DriverManagerConnectionProviderImpl.java:260)
at org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections$Builder.build(DriverManagerConnectionProviderImpl.java:401)
at org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl.buildPool(DriverManagerConnectionProviderImpl.java:112)
at org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl.configure(DriverManagerConnectionProviderImpl.java:75)
at org.hibernate.boot.registry.internal.StandardServiceRegistryImpl.configureService(StandardServiceRegistryImpl.java:100)
at org.hibernate.service.internal.AbstractServiceRegistryImpl.initializeService(AbstractServiceRegistryImpl.java:246)
at org.hibernate.service.internal.AbstractServiceRegistryImpl.getService(AbstractServiceRegistryImpl.java:214)
at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.buildJdbcConnectionAccess(JdbcEnvironmentInitiator.java:145)
at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.initiateService(JdbcEnvironmentInitiator.java:66)
at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.initiateService(JdbcEnvironmentInitiator.java:35)
at org.hibernate.boot.registry.internal.StandardServiceRegistryImpl.initiateService(StandardServiceRegistryImpl.java:94)
at org.hibernate.service.internal.AbstractServiceRegistryImpl.createService(AbstractServiceRegistryImpl.java:263)
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
at DemoTesterApplication.main(DemoTesterApplication.java:41)
Caused by: org.hibernate.HibernateException: Access to DialectResolutionInfo cannot be null when 'hibernate.dialect' not set
at org.hibernate.engine.jdbc.dialect.internal.DialectFactoryImpl.determineDialect(DialectFactoryImpl.java:100)
at org.hibernate.engine.jdbc.dialect.internal.DialectFactoryImpl.buildDialect(DialectFactoryImpl.java:54)
at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.initiateService(JdbcEnvironmentInitiator.java:137)
at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.initiateService(JdbcEnvironmentInitiator.java:35)
at org.hibernate.boot.registry.internal.StandardServiceRegistryImpl.initiateService(StandardServiceRegistryImpl.java:94)
at org.hibernate.service.internal.AbstractServiceRegistryImpl.createService(AbstractServiceRegistryImpl.java:263)
... 36 more

*/

// Driver Missing Exception
/*
2018-06-12 11:10:21 INFO  Version:46 - HHH000412: Hibernate Core {5.3.1.Final}
2018-06-12 11:10:21 INFO  Environment:213 - HHH000206: hibernate.properties not found
2018-06-12 11:10:21 INFO  Version:49 - HCANN000001: Hibernate Commons Annotations {5.0.3.Final}
2018-06-12 11:10:22 WARN  pooling:73 - HHH10001002: Using Hibernate built-in connection pool (not for production use!)
2018-06-12 11:10:22 INFO  pooling:129 - HHH10001005: using driver [null] at URL [jdbc:mysql://localhost:3306/ifinances]
2018-06-12 11:10:22 INFO  pooling:138 - HHH10001001: Connection properties: {user=root, password=****}
2018-06-12 11:10:22 INFO  pooling:143 - HHH10001003: Autocommit mode: false
2018-06-12 11:10:22 INFO  DriverManagerConnectionProviderImpl:281 - HHH000115: Hibernate connection pool size: 20 (min=1)
2018-06-12 11:10:22 WARN  JdbcEnvironmentInitiator:132 - HHH000342: Could not obtain connection to query metadata : null
2018-06-12 11:10:22 INFO  Dialect:157 - HHH000400: Using dialect: org.hibernate.dialect.MySQLDialect
2018-06-12 11:10:22 INFO  LobCreatorBuilderImpl:63 - HHH000422: Disabling contextual LOB creation as connection was null
2018-06-12 11:10:22 WARN  SqlExceptionHelper:129 - SQL Error: 0, SQLState: 08001
2018-06-12 11:10:22 ERROR SqlExceptionHelper:131 - No suitable driver found for jdbc:mysql://localhost:3306/ifinances
Exception in thread "main" org.hibernate.service.spi.ServiceException: Unable to create requested service [org.hibernate.engine.jdbc.env.spi.JdbcEnvironment]
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
        at DemoTesterApplication.main(DemoTesterApplication.java:42)
Caused by: org.hibernate.exception.JDBCConnectionException: Error calling DriverManager#getConnection
        at org.hibernate.exception.internal.SQLStateConversionDelegate.convert(SQLStateConversionDelegate.java:115)
        at org.hibernate.exception.internal.StandardSQLExceptionConverter.convert(StandardSQLExceptionConverter.java:42)
        at org.hibernate.engine.jdbc.spi.SqlExceptionHelper.convert(SqlExceptionHelper.java:111)
        at org.hibernate.engine.jdbc.connections.internal.BasicConnectionCreator.convertSqlException(BasicConnectionCreator.java:118)
        at org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionCreator.makeConnection(DriverManagerConnectionCreator.java:37)
        at org.hibernate.engine.jdbc.connections.internal.BasicConnectionCreator.createConnection(BasicConnectionCreator.java:58)
        at org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections.addConnections(DriverManagerConnectionProviderImpl.java:363)
        at org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections.<init>(DriverManagerConnectionProviderImpl.java:282)
        at org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections.<init>(DriverManagerConnectionProviderImpl.java:260)
        at org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections$Builder.build(DriverManagerConnectionProviderImpl.java:401)
        at org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl.buildPool(DriverManagerConnectionProviderImpl.java:112)
        at org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl.configure(DriverManagerConnectionProviderImpl.java:75)
        at org.hibernate.boot.registry.internal.StandardServiceRegistryImpl.configureService(StandardServiceRegistryImpl.java:100)
        at org.hibernate.service.internal.AbstractServiceRegistryImpl.initializeService(AbstractServiceRegistryImpl.java:246)
        at org.hibernate.service.internal.AbstractServiceRegistryImpl.getService(AbstractServiceRegistryImpl.java:214)
        at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.buildJdbcConnectionAccess(JdbcEnvironmentInitiator.java:145)
        at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.initiateService(JdbcEnvironmentInitiator.java:66)
        at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.initiateService(JdbcEnvironmentInitiator.java:35)
        at org.hibernate.boot.registry.internal.StandardServiceRegistryImpl.initiateService(StandardServiceRegistryImpl.java:94)
        at org.hibernate.service.internal.AbstractServiceRegistryImpl.createService(AbstractServiceRegistryImpl.java:263)
        ... 13 more
Caused by: java.sql.SQLException: No suitable driver found for jdbc:mysql://localhost:3306/ifinances
        at java.sql.DriverManager.getConnection(DriverManager.java:689)
        at java.sql.DriverManager.getConnection(DriverManager.java:208)
        at org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionCreator.makeConnection(DriverManagerConnectionCreator.java:34)
        ... 28 more

*/