package edu.ucne.angelgonzalez_p1_ap2.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.angelgonzalez_p1_ap2.data.local.dao.SistemaDao
import edu.ucne.angelgonzalez_p1_ap2.data.local.entity.SistemaEntity

@Database(
    entities = [
        SistemaEntity::class
    ],
    version = 3,
    exportSchema = false
)abstract class AngelGonzalez_P1_AP2Db : RoomDatabase(){
    abstract fun sistemaDao(): SistemaDao
}