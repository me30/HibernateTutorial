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
