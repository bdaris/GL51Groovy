package gl51.movie.service.Impl

import gl51.movie.service.MovieRegistry
import io.micronaut.test.annotation.MicronautTest
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
    }

    /*void "test qui ne plante plus"(){
        expect:
            true == true
    }*/
}
