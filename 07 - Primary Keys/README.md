
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


