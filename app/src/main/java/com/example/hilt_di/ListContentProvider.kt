package com.example.hilt_di

import android.content.*
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import com.example.hilt_di.module.qualifier.ItemService
import com.example.hilt_di.network.NetworkService
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

private const val ITEMS_TABLE = "items"
private const val AUTHORITY = "com.example.android.hilt.provider"
private const val CODE_ITEMS = 1

class ListContentProvider : ContentProvider() {

    private val matcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI(AUTHORITY, ITEMS_TABLE, CODE_ITEMS)
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor {
        val code: Int = matcher.match(uri)
        return if (code == CODE_ITEMS) {
            val appContext = context?.applicationContext ?: throw IllegalStateException()
            val itemService: NetworkService = getItemsService(appContext)

            val cursor: Cursor =  MatrixCursor(arrayOf("items")).apply {
                for(i in itemService.getData()){
                    addRow(arrayOf(i))
                }
            }
            cursor.setNotificationUri(appContext.contentResolver, uri)
            cursor
        } else {
            throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    @InstallIn(ActivityComponent::class)
    @EntryPoint
    interface ItemsContentProviderEntryPoint {
        @ItemService fun itemService() : NetworkService
    }

    private fun getItemsService(appContext: Context):NetworkService {
        val hiltEntryPoint = EntryPointAccessors.fromApplication(
            appContext,
            ItemsContentProviderEntryPoint::class.java
        )
        return hiltEntryPoint.itemService()
    }

    override fun getType(p0: Uri): String? {
        throw UnsupportedOperationException("Only reading operations are allowed")
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        throw UnsupportedOperationException("Only reading operations are allowed")
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        throw UnsupportedOperationException("Only reading operations are allowed")
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        throw UnsupportedOperationException("Only reading operations are allowed")
    }
}