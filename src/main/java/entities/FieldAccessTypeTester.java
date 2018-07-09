package entities;

import java.util.Date;

import org.hibernate.Session;

import utils.HibernateUtil;
/* Through @Access annotation we can choose whether hibernate will use 
 * field access type to fetch/set value directly from the fields or from 
 * setter/getter methods.
 * 
 * If @Access(value=AccessType.FIELDS) is choosen and all column annotations are on fields
 * then hibernate will use those fields for direct access even if the modifier of those fields
 * is private.
 * 
 * If @Access(value=AccessType.PROPERTY) then it will be using methods to fetch/set values
 * 
 *  
 * Whether to choose access = fields or property
 * If we choose properties then some business rules can be applied on those methods such 
 * that hibernate executes those business rules.
 * 
 * If the column annotation is on the fields and the @Access(value=AccessType.PROPERTY)
 * then it will lead to following exception.
 * 
 * aused by: org.hibernate.AnnotationException: No identifier specified for entity: entities.NewUser
        at org.hibernate.cfg.InheritanceState.getElementsToProcess(InheritanceState.java:231)
        at org.hibernate.cfg.AnnotationBinder.bindClass(AnnotationBinder.java:767)
        at org.hibernate.boot.model.source.internal.annotations.AnnotationMetadataSourceProcessorImpl.processEntityHierarchies(AnnotationMetadataSourceProcessorImpl.java:250)
        at org.hibernate.boot.model.process.spi.MetadataBuildingProcess$1.processEntityHierarchies(MetadataBuildingProcess.java:231)
        at org.hibernate.boot.model.process.spi.MetadataBuildingProcess.complete(MetadataBuildingProcess.java:274)
        at org.hibernate.boot.model.process.spi.MetadataBuildingProcess.build(MetadataBuildingProcess.java:84)
        at org.hibernate.boot.internal.MetadataBuilderImpl.build(MetadataBuilderImpl.java:474)
        at org.hibernate.boot.internal.MetadataBuilderImpl.build(MetadataBuilderImpl.java:85)
        at org.hibernate.cfg.Configuration.buildSessionFactory(Configuration.java:689)
        at utils.HibernateUtil.buildSessionFactory(HibernateUtil.java:24)
        at utils.HibernateUtil.<clinit>(HibernateUtil.java:12)
        ... 1 more
 * 
 * Thus concluding vice vera not allowed.
 * 
 * Thus when Access type is specified on fields then column annotations needs to be present 
 * on the fields.
 * 
 * When Access type is specified as property then column annotations needs to be present on 
 * the methods. 
 * 
 * 
 * 
 */


public class FieldAccessTypeTester {

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
