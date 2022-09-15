The problem here is that you mix "table-per-class" inheritance and GenerationType.Auto. Consider an identity column in MsSQL. It is column based. In a "table-per-class" strategy you use one table per class and each one has an ID.

Try:

@GeneratedValue(strategy = GenerationType.TABLE)