package com.example.example.data

data class Service(val id: String,
                   val serviceName: String?,
                   var groupList : List<Group>? = arrayListOf()) {
}