package gl51.movie.service.Impl

import gl51.movie.data.Movie
import gl51.movie.service.MovieClient
import io.micronaut.test.annotation.MicronautTest
import io.micronaut.test.annotation.MockBean
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class MovieRegistryImplTest extends Specification {

    @Inject
    MovieRegistryImpl registry

    void "Injection should work"(){
        expect:
            registry != null
    }

    void "favorites should be empty"(){
        expect:
        registry.ListFavorite() == []
    }

    void "add a favorite should fill in the database"(){
        when:
            registry.AddMovieToFavorite("aaaa")
        then:
            registry.ListFavorite().size() == 1
            registry.ListFavorite().find{it.titre == 'my movie'}
    }


    @MockBean(MovieClientImpl)
    MovieClient movieClient() {
        def mock = Mock(MovieClient)
        mock.getMovieDetail("aaaa") >> new Movie(imdbID: 'aaaa', titre: 'my movie')
        mock
    }

}
