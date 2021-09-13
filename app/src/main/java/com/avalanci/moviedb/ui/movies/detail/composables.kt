package com.avalanci.moviedb.ui.movies.detail

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.annotation.NonNull
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberImagePainter
import com.avalanci.moviedb.R
import com.avalanci.moviedb.domain.model.Cast
import com.avalanci.moviedb.domain.model.Movie
import com.avalanci.moviedb.ui.common.AnimatedToolBar
import com.avalanci.moviedb.ui.common.Empty
import com.avalanci.moviedb.ui.common.State
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun MovieDetail(viewModel: MovieDetailViewModel, onBack: () -> Unit) {
	val viewState by viewModel.state.collectAsState()

	MovieDetailContent(
		viewState.state,
		viewState.movie,
		viewState.castList,
		onBack
	)
}

@Composable
fun MovieDetailContent(
	state: State,
	movie: Movie?,
	castList: List<Cast>,
	onBack: () -> Unit
) {
	val scrollState = rememberScrollState()
	val configuration = LocalConfiguration.current
	val trailerProgressInSeconds = remember { mutableStateOf(0f) }

	Box {
		when (state) {
			State.CONTENT -> {
				val videoId = movie!!.videoId
				if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE && videoId != null) {
					TrailerItem(videoId, trailerProgressInSeconds)
				} else {
					MovieDetailList(movie, castList, scrollState, trailerProgressInSeconds)
				}
			}
			State.PROGRESS -> {
			}
			State.EMPTY -> Empty(string = stringResource(R.string.global_error))
		}
		AnimatedToolBar(title = movie?.title ?: "", scrollState = scrollState, onBack)
	}
}

@SuppressLint("ModifierParameter")
@Composable
fun MovieDetailList(
	movie: Movie,
	castList: List<Cast>,
	scrollState: ScrollState,
	progressSeconds: MutableState<Float>
) {
	val itemModifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)

	Column(
		modifier = Modifier.verticalScroll(scrollState)
	) {
		Image(
			painter = rememberImagePainter(data = movie.posterUrl),
			contentDescription = null,
			contentScale = ContentScale.Crop,
			modifier = Modifier
				.fillMaxWidth()
				.aspectRatio(0.7f)
		)
		movie.overview?.let {
			DescriptionItem(description = it, itemModifier)
		}
		movie.videoId?.let {
			HeaderItem(stringResource(R.string.trailer), itemModifier)
			TrailerItem(videoId = it, progressSeconds)
		}

		HeaderItem(stringResource(R.string.cast), itemModifier)
		LazyRow {
			items(castList) {
				CastItem(it)
			}
			item {
				Spacer(modifier = Modifier.width(16.dp))
			}
		}

	}
}

@Composable
fun DescriptionItem(description: String, modifier: Modifier) {
	Column(modifier = modifier) {
		Text(stringResource(R.string.description), style = MaterialTheme.typography.body1)
		Spacer(modifier = Modifier.height(4.dp))
		Text(text = description, style = MaterialTheme.typography.caption)
	}
}

@Composable
fun HeaderItem(text: String, modifier: Modifier) {
	Text(text, style = MaterialTheme.typography.body1, modifier = modifier)
}

@Composable
fun TrailerItem(videoId: String, progressSeconds: MutableState<Float>) {
	// This is the official way to access current context from Composable functions
	val context = LocalContext.current
	val lifecycle = LocalLifecycleOwner.current.lifecycle

	val configuration = LocalConfiguration.current

	val youTubePlayer = remember(context) {
		YouTubePlayerView(context).apply {
			addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
				override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
					if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
						youTubePlayer.loadVideo(videoId, progressSeconds.value)
					} else {
						youTubePlayer.cueVideo(videoId, progressSeconds.value)
					}
				}

				override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
					progressSeconds.value = second
				}
			})
		}
	}

	lifecycle.addObserver(youTubePlayer)

	// Gateway to traditional Android Views
	AndroidView(
		factory = { youTubePlayer }
	)
}

@Composable
fun CastItem(cast: Cast) {
	Card(
		shape = MaterialTheme.shapes.large,
		backgroundColor = MaterialTheme.colors.surface,
		modifier = Modifier
			.padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 16.dp)
			.width(124.dp)
	) {
		Column {
			Image(
				painter = rememberImagePainter(data = cast.imageUrl),
				contentDescription = null,
				contentScale = ContentScale.Crop,
				modifier = Modifier.size(124.dp)
			)
			Text(
				text = cast.name,
				style = MaterialTheme.typography.caption,
				maxLines = 1,
				overflow = TextOverflow.Ellipsis,
				modifier = Modifier.padding(8.dp)
			)
		}
	}
}
