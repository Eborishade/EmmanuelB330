package com.example.myapplication

/* --Standard--
object Repository {
    private val _users = mutableListOf<User>()
    val users: List<User>
    get() = _users

    val formattedUserNames: List<String>
        get() {
            return _users.map { user ->
                if (user.lastName != null) {
                    if (user.firstName != null) {
                        "${user.firstName} ${user.lastName}"
                    } else {
                        user.lastName ?: "Unknown"
                    }
                } else {
                    user.firstName ?: "Unknown"
                }
            }
        }


    init {
        val user1 = User("Jane", "")
        val user2 = User("John", null)
        val user3 = User("Anne", "Doe")

        _users.add(user1)
        _users.add(user2)
        _users.add(user3)
    }
}
usage:
val formattedUserNames = Repository.formattedUserNames

--End Standard--
 */


/* -- AS EXTENSION --

// extension function
fun User.getFormattedName(): String {
    return if (lastName != null) {
        if (firstName != null) {
            "$firstName $lastName"
        } else {
            lastName ?: "Unknown"
        }
    } else {
        firstName ?: "Unknown"
    }
}

// extension property
val User.userFormattedName: String
    get() {
        return if (lastName != null) {
            if (firstName != null) {
                "$firstName $lastName"
            } else {
                lastName ?: "Unknown"
            }
        } else {
            firstName ?: "Unknown"
        }
    }

 usage:
val user = User(...)
val name = user.getFormattedName()
val formattedName = user.userFormattedName
-- End EXTENSION --
 */


/* -- OPTIMIZED --
val User.formattedName: String
    get() {
        return if (lastName != null) {
            if (firstName != null) {
                "$firstName $lastName"
            } else {
                lastName ?: "Unknown"
            }
        } else {
            firstName ?: "Unknown"
        }
    }

object Repository {


    private val _users = mutableListOf<User>()
    val users: List<User>
        get() = _users

    val formattedUserNames: List<String>
        get() {
            return _users.map { user -> user.formattedName }
        }

    init {
        val user1 = User("Jane", "")
        val user2 = User("John", null)
        val user3 = User("Anne", "Doe")


        _users.apply{
            //this == _users
            add(user1)
            add(user2)
            add(user3)
        }
    }
}
 */

// -- FINAL --
val User.formattedName: String
    get() {
        return if (lastName != null) {
            if (firstName != null) {
                "$firstName $lastName"
            } else {
                lastName ?: "Unknown"
            }
        } else {
            firstName ?: "Unknown"
        }
    }

object Repository {

    private val _users = mutableListOf(User("Jane", ""), User("John", null), User("Anne", "Doe"))
    val users: List<User>
        get() = _users

    val formattedUserNames: List<String>
        get() = _users.map { user -> user.formattedName }
}