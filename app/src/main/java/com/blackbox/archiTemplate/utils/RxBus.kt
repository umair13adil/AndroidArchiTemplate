package com.blackbox.archiTemplate.utils

/**
 * Created by umair on 12/09/2017.
 */
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class RxBus {

    private val publisher = PublishSubject.create<Any>()

    fun publish(event: Any) {
        publisher.onNext(event)
    }

    // Listen should return an Observable and not the publisher
    // Using ofType we filter only events that match that class type
    fun <T> listen(eventType: Class<T>): Observable<T> = publisher.ofType(eventType)
}