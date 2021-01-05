package com.example.hangamanga.observer

import com.google.gson.reflect.TypeToken
import com.example.hangamanga.models.Category
import com.example.hangamanga.models.Word
import java.lang.reflect.Type

object ConcreteWords : ICache {
    override val url: String = "https://mama.sh/hangman/api"
    override val type: Type = object : TypeToken<ArrayList<Word>>() {}.type
    override var content: ArrayList<*> = ArrayList<Word>()

    override val observers: ArrayList<IObserver> = ArrayList()

    fun isNull(): Boolean {
        return content.isEmpty()
    }

    fun getCategories(): ArrayList<Category> {
        val categories: ArrayList<Category> = ArrayList()
        content.forEach { word ->
            if (word is Word){
                var are = false
                categories.forEach { category ->
                    if (word.category == category.title) {
                        are = true
                        category.words.add(word)
                    }
                }
                if (!are) {
                    categories.add(Category(word.category, arrayListOf(word)))
                }
            }
        }
        return categories
    }

    fun getCategory(title: String): Category {
        val category: Category = Category(title, ArrayList())
        content.forEach { word ->
            if(word is Word){
                if (word.category == category.title) {
                    category.words.add(word)
                }
            }
        }
        return category
    }

    fun setWords(words: ArrayList<Word>) {
        content = words
        sendUpdateEvent()
    }
}