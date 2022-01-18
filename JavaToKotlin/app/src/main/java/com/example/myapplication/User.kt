package com.example.myapplication

data class User(var firstName: String?, var lastName: String? = null)

//usage
val jane = User("Jane") // same as User("Jane", null)
val joe = User("Jane", "Doe")
val john = User(firstName = "John", lastName = "Doe")

//data class User(var firstName: String? = null, var lastName: String?)
//val jane = User(lastName = "Doe") //same as User(null, "Doe")