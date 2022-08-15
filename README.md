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


