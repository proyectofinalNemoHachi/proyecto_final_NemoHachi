package com.yeinerdpajaro.nemohachi.data

import android.util.Log
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yeinerdpajaro.nemohachi.model.Perdidos
import kotlinx.coroutines.tasks.await

class PerdidosRepository {

    private var db = Firebase.firestore
    private var auth: FirebaseAuth = Firebase.auth
    suspend fun savePerdidos(perdidos: Perdidos): ResourceRemote<String?> {
        return try {
            val uid = auth.currentUser?.uid
            val path  = uid?.let { db.collection("lost")}
            val documentPerdidos = path?.document()
            perdidos.id = documentPerdidos?.id
            perdidos?.id?.let{path?.document(it)?.set(perdidos)?.await()}
            ResourceRemote.Success(data = perdidos.id)
        } catch (e: FirebaseFirestoreException){
            e.localizedMessage?.let{ Log.e("FirebaseAuthException",it)}
            ResourceRemote.Error(message = e.localizedMessage)
        }catch(e: FirebaseNetworkException){
            e.localizedMessage?.let{ Log.e("FirebaseAuthException",it)}
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }

    suspend fun loadPerdidos(): ResourceRemote<QuerySnapshot?> {
        return try {
            val docRef = auth.uid?.let { db.collection("lost")}
            val result = docRef?.get()?.await()
            ResourceRemote.Success(data = result)
        } catch (e: FirebaseAuthException){
            e.localizedMessage?.let{ Log.e("FirebaseAuthException",it)}
            ResourceRemote.Error(message = e.localizedMessage)
        }catch(e: FirebaseNetworkException){
            e.localizedMessage?.let{ Log.e("FirebaseAuthException",it)}
            ResourceRemote.Error(message = e.localizedMessage)
        }

    }

}