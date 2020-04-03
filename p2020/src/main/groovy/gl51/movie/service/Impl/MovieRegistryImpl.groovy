package gl51.movie.service.Impl

import gl51.movie.data.Movie
import gl51.movie.service.MovieRegistry

import javax.inject.Singleton

@Singleton
class MovieRegistryImpl implements MovieRegistry {

    private List<Movie> internalRegistry = []

    @Override
    void AddMovieToFavorite(String imdbID) {
        internalRegistry << new Movie(imdbID: imdbID)
    }

    @Override
    List<Movie> ListFavorite() {
        internalRegistry
    }
}
