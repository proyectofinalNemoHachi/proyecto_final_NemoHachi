package com.yeinerdpajaro.nemohachi.data

import android.util.Log
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yeinerdpajaro.nemohachi.model.User
import kotlinx.coroutines.tasks.await

class UserRepository {

    private var auth: FirebaseAuth = Firebase.auth

    private var db = Firebase.firestore
   suspend fun signUpUser(email: String, password: String): ResourceRemote<String?>{
       return try {
          val result =  auth.createUserWithEmailAndPassword(email, password).await()
           ResourceRemote.Success(data= result.user?.uid)

       } catch (e: FirebaseAuthException){
           e.localizedMessage?.let{ Log.e("FirebaseAuthException",it)}
           ResourceRemote.Error(message = e.localizedMessage)
       }catch(e:FirebaseNetworkException){
           e.localizedMessage?.let{ Log.e("FirebaseAuthException",it)}
           ResourceRemote.Error(message = e.localizedMessage)
       }
   }

    suspend fun signInUser(email: String, password: String): ResourceRemote<String?>{
        return try {
            val result =  auth.signInWithEmailAndPassword(email, password).await()
            ResourceRemote.Success(data= result.user?.uid)

        } catch (e: FirebaseAuthException){
            e.localizedMessage?.let{ Log.e("FirebaseAuthException",it)}
            ResourceRemote.Error(message = e.localizedMessage)
        }catch(e:FirebaseNetworkException){
            e.localizedMessage?.let{ Log.e("FirebaseAuthException",it)}
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }

    fun isSessionActive(): Boolean {
        return auth.currentUser != null
    }

    fun signOut() {
        auth.signOut()
    }

    suspend fun createUser(user: User): ResourceRemote<String?> {

        return try {
            user.uid?.let { db.collection("users").document(it).set(user).await() }
            ResourceRemote.Success(data= user.uid)

        } catch (e: FirebaseAuthException){
            e.localizedMessage?.let{ Log.e("FirebaseAuthException",it)}
            ResourceRemote.Error(message = e.localizedMessage)
        }catch(e:FirebaseNetworkException){
            e.localizedMessage?.let{ Log.e("FirebaseAuthException",it)}
            ResourceRemote.Error(message = e.localizedMessage)
        }



    }


}