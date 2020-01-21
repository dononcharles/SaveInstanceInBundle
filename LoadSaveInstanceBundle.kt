package com.app.package

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import java.io.Serializable

/**
 * Created by Komi Donon on 1/21/2020.
 */
 
/**
 * Save and load fields to/from a [Bundle]. All fields should be annotated with [ ].
 */
object LoadSaveInstanceBundle {
    private const val TAG = "LoadSaveInstanceBundle"

    /**
     * Find all fields with the [SaveInstance] annotation and add them to the [Bundle].
     *
     * @param outState
     * The bundle from Activity.onSaveInstanceState or [] Fragment.onSaveInstanceState
     * @param classInstance
     * The object to access the fields which have the [SaveInstance] annotation.
     * @see .load
     */
    @JvmOverloads
    fun save(
        outState: Bundle?,
        classInstance: Any,
        baseClass: Class<*> = classInstance.javaClass
    ) {
        if (outState == null) {
            return
        }
        var clazz: Class<*>? = classInstance.javaClass
        while (baseClass.isAssignableFrom(clazz!!)) {
            val className = clazz.name
            for (field in clazz.declaredFields) {
                if (field.isAnnotationPresent(SaveInstance::class.java)) {
                    field.isAccessible = true
                    val key = className + "#" + field.name
                    try {
                        val value = field[classInstance]
                        if (value is Parcelable) {
                            outState.putParcelable(key, value)
                        } else if (value is Serializable) {
                            outState.putSerializable(key, value)
                        }
                    } catch (t: Throwable) {
                        Log.d(
                            TAG,
                            "The field '$key' was not added to the bundle"
                        )
                    }
                }
            }
            clazz = clazz.superclass
        }
    }

    /**
     * Load all saved fields that have the [SaveInstance] annotation.
     *
     * @param savedInstanceState
     * The saved-instance [Bundle] from an Activity or Fragment.
     * @param classInstance
     * The object to access the fields which have the [SaveInstance] annotation.
     * @see .save
     */
    @JvmOverloads
    fun load(
        savedInstanceState: Bundle?,
        classInstance: Any,
        baseClass: Class<*> = classInstance.javaClass
    ) {
        if (savedInstanceState == null) {
            return
        }
        var clazz: Class<*>? = classInstance.javaClass
        while (baseClass.isAssignableFrom(clazz!!)) {
            val className = clazz.name
            for (field in clazz.declaredFields) {
                if (field.isAnnotationPresent(SaveInstance::class.java)) {
                    val key = className + "#" + field.name
                    field.isAccessible = true
                    try {
                        val fieldVal = savedInstanceState[key]
                        if (fieldVal != null) {
                            field[classInstance] = fieldVal
                        }
                    } catch (t: Throwable) {
                        Log.d(
                            TAG,
                            "The field '$key' was not retrieved from the bundle"
                        )
                    }
                }
            }
            clazz = clazz.superclass
        }
    }
}
