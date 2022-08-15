# HibernateTutorial

Video:
https://www.youtube.com/watch?v=Yv2xctJxE-w&list=PL4AFF701184976B25&index=1

Download links:
https://sourceforge.net/projects/hibernate/files/hibernate3/3.6.10.Final/


![Desktop-screenshot](https://user-images.githubusercontent.com/2530543/184543962-813b7744-695a-4f1b-80ff-2a61b6c94e9d.png)

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

