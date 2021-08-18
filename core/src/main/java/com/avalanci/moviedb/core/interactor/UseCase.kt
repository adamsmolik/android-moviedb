package com.avalanci.moviedb.core.interactor

import com.avalanci.moviedb.core.exception.Failure
import com.avalanci.moviedb.core.functional.Either
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means that any use
 * case in the application should implement this contract).
 *
 * By convention each [UseCase] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 */
abstract class UseCase<out Type, in Params> where Type : Any {

	abstract suspend fun run(params: Params): Either<Failure, Type>

	operator fun invoke(
		params: Params,
		scope: CoroutineScope = GlobalScope,
		onResult: (Either<Failure, Type>) -> Unit = {}
	) {
		scope.launch(Dispatchers.Main) {
			val deferred = async(Dispatchers.IO) {
				run(params)
			}
			onResult(deferred.await())
		}
	}

	class None
}
