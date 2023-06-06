package com.yeinerdpajaro.nemohachi.ui.signup.data

import android.util.Log
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yeinerdpajaro.nemohachi.model.Anuncio
import kotlinx.coroutines.tasks.await

class AnuncioRepository {

    private var db = Firebase.firestore
    private var auth: FirebaseAuth = Firebase.auth
    suspend fun saveSerie(anuncio: Anuncio): ResourceRemote<String?> {
        return try {
            val uid = auth.currentUser?.uid
            val path  = uid?.let { db.collection("announcements")}
            val documentAnuncio = path?.document()
            anuncio.id = documentAnuncio?.id
            anuncio.id?.let{path?.document(it)?.set(anuncio)?.await()}
            ResourceRemote.Success(data = anuncio.id)

        } catch (e: FirebaseFirestoreException){
            e.localizedMessage?.let{ Log.e("FirebaseAuthException",it)}
            ResourceRemote.Error(message = e.localizedMessage)
        }catch(e: FirebaseNetworkException){
            e.localizedMessage?.let{ Log.e("FirebaseAuthException",it)}
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }

    suspend fun loadSeries(): ResourceRemote<QuerySnapshot?> {
        return try {
            val docRef = auth.uid?.let { db.collection("announcements").document(it).collection("announcements")}
            val result = docRef?.get()?.await()
            ResourceRemote.Success(data = result)
        } catch (e: FirebaseAuthException){
            e.localizedMessage?.let{ Log.e("FirebaseAuthException",it)}
            ResourceRemote.Error(message = e.localizedMessage)
        }catch(e:FirebaseNetworkException){
            e.localizedMessage?.let{ Log.e("FirebaseAuthException",it)}
            ResourceRemote.Error(message = e.localizedMessage)
        }

    }


}