# HibernateTutorial

Video:
https://www.youtube.com/watch?v=Yv2xctJxE-w&list=PL4AFF701184976B25&index=1

Download links:
https://sourceforge.net/projects/hibernate/files/hibernate3/3.6.10.Final/


![Desktop-screenshot](https://user-images.githubusercontent.com/2530543/184543962-813b7744-695a-4f1b-80ff-2a61b6c94e9d.png)

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
