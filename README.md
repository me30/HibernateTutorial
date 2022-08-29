# HibernateTutorial

Video:
https://www.youtube.com/watch?v=Yv2xctJxE-w&list=PL4AFF701184976B25&index=1

Download links:
https://sourceforge.net/projects/hibernate/files/hibernate3/3.6.10.Final/

Install Mysql:
https://www.youtube.com/watch?v=GIRcpjg-3Eg&ab_channel=edureka%21

![Desktop-screenshot](https://user-images.githubusercontent.com/2530543/184543962-813b7744-695a-4f1b-80ff-2a61b6c94e9d.png)

# Hibernate :
- Hibernate is a high-performance Object/Relational persistence and query service.
- Hibernate not only takes care of the mapping from Java classes to database tables (and from Java data types to SQL data types), but also provides data query and retrieval facilities.
- Hibernate is a Java framework that simplifies the development of Java application to interact with the database. 
- It is an open source, lightweight, ORM (Object Relational Mapping) tool. Hibernate implements the specifications of JPA (Java Persistence API) for data persistence.

# ORM Tool

- An ORM tool simplifies the data creation, data manipulation and data access. It is a programming technique that maps the object to the data stored in the database.

- The ORM tool internally uses the JDBC API to interact with the database.

![image](https://user-images.githubusercontent.com/2530543/186497738-902a4cf0-6d9b-4701-86f6-dbf6dfe1fe93.png)

# What is JPA?
Java Persistence API (JPA) is a Java specification that provides certain functionality and standard to ORM tools. The javax.persistence package contains the JPA classes and interfaces.

# Advantages of Hibernate Framework

1) Open Source and Lightweight
Hibernate framework is open source under the LGPL license and lightweight.

2) Fast Performance
The performance of hibernate framework is fast because cache is internally used in hibernate framework. There are two types of cache in hibernate framework first level cache and second level cache. First level cache is enabled by default.

3) Database Independent Query
HQL (Hibernate Query Language) is the object-oriented version of SQL. It generates the database independent queries. So you don't need to write database specific queries. Before Hibernate, if database is changed for the project, we need to change the SQL query as well that leads to the maintenance problem.

4) Automatic Table Creation
Hibernate framework provides the facility to create the tables of the database automatically. So there is no need to create tables in the database manually.

5) Simplifies Complex Join
Fetching data from multiple tables is easy in hibernate framework.

6) Provides Query Statistics and Database Status
Hibernate supports Query cache and provide statistics about query and database status.

# Hibernate Architecture

Hibernate has a layered architecture which helps the user to operate without having to know the underlying APIs. 

Hibernate makes use of the database and configuration data to provide persistence services (and persistent objects) to the application.

The Hibernate architecture includes many objects such as persistent object, session factory, transaction factory, connection factory, session, transaction etc.

## The Hibernate architecture is categorized in four layers.
1) Java application layer
2) Hibernate framework layer
3) Backhand api layer
4) Database layer

## Let's see the diagram of hibernate architecture:

![image](https://user-images.githubusercontent.com/2530543/186500865-34a24ebf-6759-41dd-9b94-460e9c6d0c48.png)

## The high level architecture of Hibernate with mapping file and configuration file.

![image](https://user-images.githubusercontent.com/2530543/186501035-4c592140-bf5d-4c57-af14-e560bfc9a916.png)

Hibernate framework uses many objects such as session factory, session, transaction etc. alongwith existing Java API such as JDBC (Java Database Connectivity), JTA (Java Transaction API) and JNDI (Java Naming Directory Interface).

JDBC provides a rudimentary level of abstraction of functionality common to relational databases, allowing almost any database with a JDBC driver to be supported by Hibernate. 

JNDI and JTA allow Hibernate to be integrated with J2EE application servers.

## Elements of Hibernate Architecture

For creating the first hibernate application, we must know the elements of Hibernate architecture. They are as follows:

#### Configuration Object

- The Configuration object is the first Hibernate object you create in any Hibernate application. 

- It is usually created only once during application initialization. 

- It represents a configuration or properties file required by the Hibernate.

- The Configuration object provides two keys components −

	- Database Connection − This is handled through one or more configuration files supported by Hibernate. These files are hibernate.properties and hibernate.cfg.xml.

	- Class Mapping Setup − This component creates the connection between the Java classes and database tables.
 
#### SessionFactory (org.hibernate.SessionFactory)

- Configuration object is used to create a SessionFactory which in turn configures Hibernate for the application using the supplied configuration file and allows for a Session to be instantiated. 

- The SessionFactory is a thread safe object and used by all the threads of an application.

- The SessionFactory is a heavyweight object; it is usually created during application start up and kept for later use. 

- You would need one SessionFactory per database using a separate configuration file. So, if you are using multiple databases, then you would have to create multiple SessionFactory objects.

- The org.hibernate.SessionFactory interface provides factory method to get the object of Session.

#### Session (org.hibernate.Session)

- A Session is used to get a physical connection with a database. 

- The Session object is lightweight and designed to be instantiated each time an interaction is needed with the database. 

- Persistent objects are saved and retrieved through a Session object. It is a short-lived object and wraps the JDBC connection. 

- The session objects should not be kept open for a long time because they are not usually thread safe and they should be created and destroyed them as needed.

- The session object provides an interface between the application and data stored in the database. 

- It is factory of Transaction, Query and Criteria. 

- It holds a first-level cache (mandatory) of data. 

- The org.hibernate.Session interface provides methods to insert, update and delete the object. 

- It also provides factory methods for Transaction, Query and Criteria.

- The session objects should not be kept open for a long time because they are not usually thread safe and they should be created and destroyed them as needed. The main function of the Session is to offer, create, read, and delete operations for instances of mapped entity classes.

- Instances may exist in one of the following three states at a given point in time:

  1) transient − A new instance of a persistent class, which is not associated with a Session and has no representation in the database and no identifier value is considered transient by Hibernate.

  2) persistent − You can make a transient instance persistent by associating it with a Session. A persistent instance has a representation in the database, an identifier value and is associated with a Session.

  3) detached − Once we close the Hibernate Session, the persistent instance will become a detached instance.

#### Transaction

- A Transaction represents a unit of work with the database and most of the RDBMS supports transaction functionality. 

- Transactions in Hibernate are handled by an underlying transaction manager and transaction (from JDBC or JTA).

- This is an optional object and Hibernate applications may choose not to use this interface, instead managing transactions in their own application code.

- The transaction object specifies the atomic unit of work. 

- It is optional. 

- The org.hibernate.Transaction interface provides methods for transaction management.

```
Transaction transObj = null;
Session sessionObj = null;
try {
    SessionFactory sf = new Configuration().configure().buildSessionFactory();
    sessionObj = sf.openSession();
    transObj = sessionObj.beginTransaction();
 
    //Perform Some Operation Here
    transObj.commit();
} catch (HibernateException exObj) {
    if(transObj!=null){
        transObj.rollback();
    }
    exObj.printStackTrace(); 
} finally {
    sessionObj.close(); 
}

```

#### Query Object

- Query objects use SQL or Hibernate Query Language (HQL) string to retrieve data from the database and create objects. 

- A Query instance is used to bind query parameters, limit the number of results returned by the query, and finally to execute the query.

```
String hql = "FROM com.hibernatebook.criteria.Employee";
Query query = session.createQuery(hql);
List results = query.list();

```

#### Criteria Object

- Criteria objects are used to create and execute object oriented criteria queries to retrieve objects.

```
Criteria cr = session.createCriteria(Employee.class);
List results = cr.list();
```

#### ConnectionProvider (org.hibernate.connection.ConnectionProvider)
- (Optional) A factory for, and pool of, JDBC connections. 

- It abstracts the application from underlying javax.sql.DataSource or java.sql.DriverManager. 

- It is not exposed to application, but it can be extended and/or implemented by the developer.


- It is a factory of JDBC connections. 

- It abstracts the application from DriverManager or DataSource. 

- It is optional.

```
public void testUserProvidedConnection() throws Exception {
	ConnectionProvider dcp = new DriverManagerConnectionProvider();
	dcp.configure( Environment.getProperties() );
	Session s = getSessions().openSession( dcp.getConnection() );
	Transaction tx = s.beginTransaction();
	s.find("from Fo");
	tx.commit();
	Connection c = s.disconnect();
	assertTrue( c!=null );
	s.reconnect(c);
	tx = s.beginTransaction();
	s.find("from Fo");
	tx.commit();
	assertTrue( s.close()==c );
	c.close();
}
```

#### TransactionFactory (org.hibernate.TransactionFactory)
 - (Optional) A factory for org.hibernate.Transaction instances. 
 
 - It is not exposed to the application, but it can be extended and/or implemented by the developer.

- It is a factory of Transaction. 

# Hibernate - Configuration

- The list of important properties, you will be required to configure for a databases in a standalone situation:

![image](https://user-images.githubusercontent.com/2530543/186509016-0cdda58a-2975-43fd-a7e7-e59b4cd2b38b.png)

- If you are using a database along with an application server and JNDI, then you would have to configure the following properties:

![image](https://user-images.githubusercontent.com/2530543/186509530-d2a9bab9-7a88-495c-8c87-811ca02928f1.png)

- List of various important databases dialect property type:

![image](https://user-images.githubusercontent.com/2530543/186509748-74f57a08-4774-4616-90ee-7c9e358874e7.png)

# @Entity

- The EJB 3 standard annotations are contained in the javax.persistence package, so we import this package as the first step. 

- We used the @Entity annotation to the pojo class/ entity class, which marks this class as an entity bean, so it must have a no-argument constructor that is visible with at least protected scope.


# @Table

- The @Table annotation allows you to specify the details of the table that will be used to persist the entity in the database.

- The @Table annotation provides four attributes, allowing you to override the name of the table, its catalogue, and its schema, and enforce unique constraints on columns in the table. For now, we are using just table name, which is EMPLOYEE.

# @Id and @GeneratedValue

- Each entity bean will have a primary key, which you annotate on the class with the @Id annotation. The primary key can be a single field or a combination of multiple fields depending on your table structure.

- By default, the @Id annotation will automatically determine the most appropriate primary key generation strategy to be used but you can override this by applying the @GeneratedValue annotation, which takes two parameters strategy and generator that I'm not going to discuss here, so let us use only the default key generation strategy. 

- Letting Hibernate determine which generator type to use makes your code portable between different databases.

# @Column

- The @Column annotation is used to specify the details of the column to which a field or property will be mapped. You can use column annotation with the following most commonly used attributes:

 1) name attribute permits the name of the column to be explicitly specified.

 2) length attribute permits the size of the column used to map a value particularly for a String value.

 3) nullable attribute permits the column to be marked NOT NULL when the schema is generated.

 4) unique attribute permits the column to be marked as containing only unique values.
 
# Java Persistence / JPA: @Column vs @Basic

@Basic signifies that an attribute is to be persisted and a standard mapping is to be used. It has parameters which allow you to specify whether the attribute is to be lazily loaded and whether it's nullable.

@Column allows you to specify the name of the column in the database to which the attribute is to be persisted.

If you specify one without the other then you get default behaviour which is sensible, so commonly folks use only one with the exception of special cases.

So if we wanted a lazy loading of an attribute and to specify a column name we can say

@Basic(fetch=FetchType.LAZY)
@Column(name="WIBBLE")
If we neeed the default, non-lazy behaviour then just the @Column would have been sufficient.

# @LOB
@Lob Specifies that a persistent property or field should be persisted as a large object to a database-supported large object type.

# @Temporal
@Temporal is a JPA annotation which can be used to store in the database table on of the following column items:

> DATE (java.sql.Date)

> TIME (java.sql.Time)

> TIMESTAMP (java.sql.Timestamp)

Generally when we declare a Date field in the class and try to store it.
It will store as TIMESTAMP in the database.

> @Temporal

> private Date joinedDate;

Above code will store value looks like 08-07-17 04:33:35.870000000 PM

If we want to store only the DATE in the database,
We can use/define TemporalType.

> @Temporal(TemporalType.DATE)

> private Date joinedDate;

This time, it would store 08-07-17 in database

There are some other attributes as well as @Temporal which can be used based on the requirement.

# Transient, Persistent and Detached Objects in Hibernate

We have seen that every entity object are passed to three states of the object before saving and updating the row in the database table as per as given in the following picture.

![image](https://user-images.githubusercontent.com/2530543/184584719-9a7ecb8e-b899-4f6b-8bbb-bc18539da88c.png)

1. Transient State:

A New instance of  a persistent class which is not associated with a Session, has no representation in the database and no identifier value is considered transient by Hibernate:

```java
UserDetail user = new UserDetail();
user.setUserName("Dinesh Rajput");
// user is in a transient state
```

2. Persistent State:

A persistent instance has a representation in the database , an identifier value and is associated with a Session. You can make a transient instance persistent by associating it with a Session:

```java
Long id = (Long) session.save(user);
// user is now in a persistent state
```

3. Detached State:

Now, if we close the Hibernate Session, the persistent instance will become a detached instance: it isn’t attached to a Session anymore (but can still be modified and reattached to a new Session later though).

```java
session.close();
//user in detached state
```

Difference between Transient and Detached States:

Transient objects do not have association with the databases and session objects. They are simple objects and not persisted to the database. Once the last reference is lost, that means the object itself is lost. And of course , garbage collected. The commits and rollbacks will have no effects on these objects. They can become into persistent objects through the save method calls of Session object.

The detached object have corresponding entries in the database. These are persistent and not connected to the Session object. These objects have the synchronized data with the database when the session was closed. Since then, the change may be done in the database which makes this object stale. The detached object can be reattached after certain time to another object in order to become persistent again.

Lets see in the following example to save or update the user data…

```java
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.sdnext.hibernate.tutorial.dto.UserDetails;

public class HibernateTestDemo {
 /**
  * @param args
  */
 public static void main(String[] args) 
 {
  UserDetails userDetails = new UserDetails();
                userDetails.setUserName("Dinesh Rajput");
                userDetails.setAddress("Noida City");
  //Here 'userDetails' is TRANSIENT object
  
                SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
  Session session = sessionFactory.openSession();
  session.beginTransaction();
  
   session.save(userDetails);
  //Here 'userDetails' is PERSISTENT object
   userDetails.setUserName("User Updated after session close");

                session.getTransaction().commit();
  session.close();
  //Here 'userDetails' is DETACHED object
 }
}
```
![image](https://user-images.githubusercontent.com/2530543/184584825-00f0cf4e-740c-44b2-9ca5-95554cc5623c.png)

# Difference Between get() and load() in Hibernate
![image](https://user-images.githubusercontent.com/2530543/184589870-bc3489ed-966f-4fbd-92b5-f7db952ad72b.png)


# Difference between a primary key and a surrogate key?
The primary key is a unique key in your table that you choose that best uniquely identifies a record in the table. All tables should have a primary key, because if you ever need to update or delete a record you need to know how to uniquely identify it.

A surrogate key is an artificially generated key.


# @GeneratedValue?
By marking the @Id field with @GeneratedValue we are now enabling id generation. Which means that the persistence layer will generate an Id value for us and handle the auto incrementing. Our application can choose 1 of 4 generations strategies:

1) AUTO[GenerationType.AUTO]
If we're using the default generation type, the persistence provider will determine values based on the type of the primary key attribute. This type can be numerical or UUID.

2) TABLE[GenerationType.TABLE]
The TableGenerator uses an underlying database table that holds segments of identifier generation values.

````java
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, 
      generator = "table-generator")
    @TableGenerator(name = "table-generator", 
      table = "dep_ids", 
      pkColumnName = "seq_id", 
      valueColumnName = "seq_value")
    private long depId;

    // ...
}
````

3) SEQUENCE[GenerationType.SEQUENCE]
Sequence objects use special database objects to generate ids. Sequence objects are only supported in some databases, such as Oracle, DB2, and Postgres. Usually, a SEQUENCE object has a name, an INCREMENT, and other database object settings. Each time the <sequence>.NEXTVAL is selected the sequence is incremented by the INCREMENT.

To use a sequence-based id, Hibernate provides the SequenceStyleGenerator class.

This generator uses sequences if our database supports them. It switches to table generation if they aren't supported.

In order to customize the sequence name, we can use the @GenericGenerator annotation with SequenceStyleGenerator strategy.

````java
@Entity
public class User {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
      name = "sequence-generator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
        @Parameter(name = "sequence_name", value = "user_sequence"),
        @Parameter(name = "initial_value", value = "4"),
        @Parameter(name = "increment_size", value = "1")
        }
    )
    private long userId;
    
    // ...
}
````

4) IDENTITY [GenerationType.IDENTITY]
When we specify the generation strategy as GenerationType.IDENTITY we are telling the persistence provider(hibernate) to let the database handle the auto incrementing of the id. If you were to use postgres as an underling database and specified the strategy as IDENTITY.

Identity sequencing uses special IDENTITY columns in the database to allow the database to automatically assign an id to the object when its row is inserted. Identity columns are supported in many databases, such as MySQL, DB2, SQL Server, Sybase and Postgres. Oracle does not support IDENTITY columns but they can be simulated through using sequence objects and triggers.

This type of generation relies on the IdentityGenerator, which expects values generated by an identity column in the database. This means they are auto-incremented.

````java
@Entity
public class Student {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long studentId;

    // ...
}
````

If not strategy is specified then AUTO is assumed

# @ElementCollection 

In SQL, A FOREIGN KEY is a field (or collection of fields) in one table, that refers to the PRIMARY KEY in another table.The table with the foreign key is called the child table, and the table with the primary key is called the referenced or parent table.

In Hibernate Annotation, @ElementCollection is the feature which gets the columns values from another table without mapping two tables. 

We have taken two entity student and college. In college entity, we will fetch students without mapping student and college entity. 

@CollectionTable will join the two tables for the given primary and foreign key.


# @JoinTable

When a join table is used in mapping a relationship with an embeddable class on the owning side of the relationship, the containing entity rather than the embeddable class is considered the owner of the relationship.

If the JoinTable annotation is missing, the default values of the annotation elements apply. The name of the join table is assumed to be the table names of the associated primary tables concatenated together (owning side first) using an underscore.


# Difference between @GeneratedValue and @GenericGenerator [Ref : https://stackoverflow.com/questions/18205574/difference-between-generatedvalue-and-genericgenerator ]

@GeneratedValue:

The @GeneratedValue annotation denotes that a value for a column, which must be annotated with @Id is generated. The elements strategy and generator on the annotation describe how the generated value is obtained.

There are four possible values for the strategy element on the @GeneratedValue annotation: IDENTITY, AUTO, TABLE and SEQUENCE.

The generator element of the @GeneratedValue annotation denotes the name of the primary key generator.

generates identifiers of type long, short or int that are unique only when no other process is inserting data into the same table. Do not use in a cluster.

@GenericGenerator:

@GenericGenerator is a hibernate annotation used to denote a custom generator, which can be a class or shortcut to a generator supplied by Hibernate. increment is a shortcut to a Hibernate generator that

uses a hi/lo algorithm to efficiently generate identifiers of type long, short or int, given a table and column (by default hibernate_unique_key and next_hi respectively) as a source of hi values. The hi/lo algorithm generates identifiers that are unique only for a particular database.

More in details for understanding:- 

relationships between the Hibernate primary key generation strategies and specific generator respectively, as specified in org.hibernate.id.IdentifierGeneratorFactory

	static {
	    GENERATORS.put("uuid", UUIDHexGenerator.class);     // "deprecated" for new use
	    GENERATORS.put("hilo", TableHiLoGenerator.class);   // removed in Hibernate 5
	    GENERATORS.put("assigned", Assigned.class);
	    GENERATORS.put("identity", IdentityGenerator.class);
	    GENERATORS.put("select", SelectGenerator.class);
	    GENERATORS.put("sequence", SequenceGenerator.class);
	    GENERATORS.put("seqhilo", SequenceHiLoGenerator.class);
	    GENERATORS.put("increment", IncrementGenerator.class);
	    GENERATORS.put("foreign", ForeignGenerator.class);
	    GENERATORS.put("guid", GUIDGenerator.class);
	    GENERATORS.put("uuid.hex", UUIDHexGenerator.class); // uuid.hex is deprecated
	    GENERATORS.put("sequence-identity", SequenceIdentityGenerator.class);
	}


In Hibernate 4.3 I've found org.hibernate.id.factory.internal.DefaultIdentifierGeneratorFactory class with 3 more strategies:

    register("uuid2", UUIDGenerator.class);
    register("enhanced-sequence", SequenceStyleGenerator.class);
    register("enhanced-table", TableGenerator.class);
    
The above fifteen strategies, plus native, are sixteen generation strategies supported in Hibernate by default.

Example with native:

@GeneratedValue(generator = "nativeGenerator")
@GenericGenerator(name = "nativeGenerator", strategy = "native")
