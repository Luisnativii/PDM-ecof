package com.moviles.taller2_moviles

import android.database.sqlite.SQLiteConstraintException
import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviles.taller2_moviles.data.api.ApiClient
import com.moviles.taller2_moviles.data.api.FamilyApi
import com.moviles.taller2_moviles.data.api.MembersApi
import com.moviles.taller2_moviles.data.database.MyApplication
import com.moviles.taller2_moviles.data.database.entity.Family
import com.moviles.taller2_moviles.data.database.entity.FamilyMember
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.net.HttpURLConnection
import java.net.URL

class MainViewModel : ViewModel() {

    //Lista de todas las familias
    private val _familiesGetted = MutableStateFlow<List<Family>>(emptyList())
    val familiesGetted: StateFlow<List<Family>> = _familiesGetted.asStateFlow()

    // Una familia
    private val _family = MutableStateFlow<Family>(Family())
    val family: StateFlow<Family> = _family.asStateFlow()

    //Lista de todos los miembros de una familia
    private val _famiMembers = MutableStateFlow<List<FamilyMember>>(emptyList())
    val famiMembers: StateFlow<List<FamilyMember>> = _famiMembers.asStateFlow()


    //Estados de la UI
    private val _uiState = MutableStateFlow<UIState>(UIState.Ready)
    val uiState: StateFlow<UIState> = _uiState

    private val db = MyApplication.database

    //Insert a new family
    fun insertFamily(family: Family, familyMemberList: List<FamilyMember>) {
        //Insert family and family members
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val famID = db.familyDAO().insertFamily(family)
                familyMemberList.forEach { member ->
                    val fammember = member.copy(familyId = famID.toInt())
                    db.familyMemberDAO().insertFamilyMember(fammember)
                }
                _uiState.value = UIState.Success("Family inserted successfully")
            } catch (e: Exception) {
                when (e) {
                    is SQLiteConstraintException -> {
                        Log.i("SQLiteConstraintException", e.message.toString())
                        _uiState.value = UIState.Error("ID already exists")
                    }

                    else -> {
                        Log.i("Exception", e.message.toString())
                        _uiState.value = UIState.Error("Error inserting family")
                    }
                }
            }
        }
    }



    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun insertApiFamily() {

        selectAllFamilies()

        viewModelScope.launch(Dispatchers.IO) {
            try {

                for (family in _familiesGetted.value){
                    val familyID = family.id

                    selectAllFamilyMembers(familyID)

                    val familymembersapi = mutableListOf<MembersApi>()

                    for(_famiMembers in _famiMembers.value){
                        Log.i("MainViewModel", _famiMembers.toString())

                        val MembersApi = MembersApi(

                            DUI = _famiMembers.DUI,
                            fullName = _famiMembers.fullName,
                            dateBirth = _famiMembers.dateBirth,
                            canReadWrite = _famiMembers.canReadWrite,
                            scholarity = _famiMembers.scholarity
                        )
                        familymembersapi.add(MembersApi)
                    }

                    val familymembersapiImmutable = familymembersapi.toList()



                    val variable = FamilyApi (
                        id = family.id,
                                familyName = family.familyName,
                                location = family.location,
                                geographicLocation = family.geographicLocation,
                                houseType = family.houseType,
                                risk = family.risk,
                                FamilyMembers = familymembersapiImmutable
                    )

                    Log.i("MainViewModel", variable.toString())
                    val response = ApiClient.apiService.insertFamilyAPI(variable)
                    Log.i("MainViewModel", response.toString())
                }

                deleteData()
                _uiState.value = UIState.SuccessAPI


            } catch (e: Exception) {
                when(e) {
                    is HttpURLConnection -> {
                        e.responseMessage?.let { Log.i("MainViewModel", it) }
                    }
                    else -> {
                        Log.i("MainViewModel", e.toString())
                    }
                }
            }
        }
    }

    //Select all (Borrar)
    fun selectAll() {
        viewModelScope.launch(Dispatchers.IO) {
            val families = db.familyDAO().selectAllFamilies()
            families.forEach { family ->
                Log.i("Family:, ", family.toString())
            }
            val familyMembers = db.familyMemberDAO().selectEveryFamilyMembers()
            familyMembers.forEach { member ->
                Log.i("FamilyMember:, ", member.toString())
            }
        }
    }

    // Select all families
    fun selectAllFamilies() {
        viewModelScope.launch(Dispatchers.IO) {
            _familiesGetted.value = db.familyDAO().selectAllFamilies()
        }
    }


    //Select one family by id
fun selectFamilyById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _family.value = db.familyDAO().selectFamilyById(id)
            Log.i("Family:, ", family.toString())
        }
    }

    //Select all family members from a family
    fun selectAllFamilyMembers(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _famiMembers.value = db.familyMemberDAO().selectAllFamilyMembers(id)
        }
    }

    //Delete all data
    fun deleteData(){
        viewModelScope.launch(Dispatchers.IO) {
            db.familyDAO().dropdata()
            db.familyDAO().dropdataFamilyMember()
            Log.i("MainViewModel", "Data deleted")
        }
    }

    fun setStateToReady() {
        _uiState.value = UIState.Ready
    }

}

sealed class UIState {
    data object Ready : UIState()
    data class Success(val msg: String) : UIState()
    data class Error(val msg: String) : UIState()
    data object SuccessAPI : UIState()
}