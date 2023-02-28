package com.example.onepercentbetter.data.repository

import android.util.Log
import com.example.onepercentbetter.domain.model.item.Item
import com.example.onepercentbetter.domain.model.item.ItemDifficulty
import com.example.onepercentbetter.domain.model.item.ItemStatus
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class ItemFirestoreRepositoryImpl : ItemRepository {

    private val db = Firebase.firestore

    override suspend fun getTodayItems(): List<Item> {
        val resultList = mutableListOf<Item>()
        val itemList = db.collection(COLLECTION)
            .get()
            .addOnFailureListener {
                Log.e("ItemFireStoreRespository", "error: ${it.message}")
            }.await()

        for (item in itemList) {
            resultList.add(
                Item(
                    id = item.id,
                    title = item.getString("title")!!,
                    description = item.getString("description")!!,
                    status = ItemStatus.getStatusByName(item.getString("status")!!),
                    difficulty = ItemDifficulty.getDifficultyByName(item.getString("difficulty")!!),
                    createdAt = item.getString("createdAt")!!,
                )
            )
        }
        return resultList
    }

    override suspend fun update(item: Item) {
        val docRef = db.collection(COLLECTION).document(item.id)
        docRef.update(
            mapOf(
                "id" to item.id,
                "title" to item.title,
                "description" to item.description,
                "status" to item.status.name,
                "difficulty" to item.difficulty.name
            )
        )
    }

    override suspend fun insert(item: Item) {
        db.collection("itens")
            .document(item.id)
            .set(item)
            .await()
    }

    override suspend fun getById(id: String): Item? {
        val item = db.collection(COLLECTION).document(id)
            .get()
            .addOnFailureListener {
                Log.d("ItemFireStoreRepo", "error: ${it.message}")
            }.await()
        return item.toObject(Item::class.java)
    }

    private companion object {
        const val COLLECTION = "itens"
    }
}