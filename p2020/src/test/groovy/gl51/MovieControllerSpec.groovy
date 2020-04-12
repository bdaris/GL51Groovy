package gl51

import gl51.movie.data.Movie
import gl51.movie.data.MovieRequest
import gl51.movie.service.Impl.MovieClientImpl
import gl51.movie.service.MovieClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.test.annotation.MockBean
import io.reactivex.Flowable
import spock.lang.AutoCleanup
import spock.lang.Specification
import spock.lang.Shared

import javax.inject.Inject

@MicronautTest
class MovieControllerSpec extends Specification {


    @Shared @Inject
    EmbeddedServer embeddedServer

    @Shared @AutoCleanup @Inject @Client("/")
    RxHttpClient client

    void "test index"() {
        given:
        Flowable flowable = client.retrieve(HttpRequest.GET("/movie"), Argument.listOf(Movie))
        def content = flowable.firstElement().blockingGet()

        expect:

        content == []
    }

    void "testFilmCreation"() {
        when:
        HttpResponse response = client.toBlocking().exchange(
                HttpRequest.POST("/movie", new MovieRequest(imdbID: "aaaa"))
        )
        Flowable flowable = client.retrieve(HttpRequest.GET("/movie"), Argument.listOf(Movie))
        def content = flowable.firstElement().blockingGet()
        then:
        response.status == HttpStatus.CREATED
        content.find { it.titre == 'mymovie' && it.imdbID == "aaaa" }
    }

    @MockBean(MovieClientImpl)
    MovieClient movieClient() {
        def mock = Mock(MovieClient)
        mock.getMovieDetail("aaaa") >> new Movie(imdbID: "aaaa", titre: 'mymovie')
        mock
    }
}
