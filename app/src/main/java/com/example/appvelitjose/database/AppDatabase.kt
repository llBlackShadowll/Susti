import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.appvelitjose.database.AlumnoDao
import com.example.appvelitjose.models.Alumno

@Database(entities = [Alumno::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun alumnoDao(): AlumnoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val migration_1_2 = object : Migration(1, 2) {
                    override fun migrate(database: SupportSQLiteDatabase) {
                        // Agregar la columna "password" a la tabla "tbljose"
                        database.execSQL("ALTER TABLE tbljose ADD COLUMN password TEXT NOT NULL DEFAULT ''")
                    }
                }

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .addMigrations(migration_1_2) // Agregar migración
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}