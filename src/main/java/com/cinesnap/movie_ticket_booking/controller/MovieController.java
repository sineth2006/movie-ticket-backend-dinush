package com.cinesnap.movie_ticket_booking.controller;

import com.cinesnap.movie_ticket_booking.dto.MovieDTO;
import com.cinesnap.movie_ticket_booking.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/movies")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {

    private final MovieService movieService;

    ///------------film category------------///
    @GetMapping(value = "/genre", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MovieDTO>> getMoviesByGenre(@RequestParam("name") String genre) {
        List<MovieDTO> genreResults = movieService.getMoviesByGenre(genre);
        return new ResponseEntity<>(genreResults, HttpStatus.OK);
    }

    ///------------movie search------------///
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MovieDTO>> searchMovies(@RequestParam("title") String title) {
        List<MovieDTO> searchResults = movieService.searchMoviesByTitle(title);
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }
     ///------------save movie------------///
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> savemovie(@RequestBody MovieDTO movieDTO) {
        movieService.savemovie(movieDTO);
        return new  ResponseEntity<>(HttpStatus.CREATED);
    }
    ///------------get selected movie------------///
    @GetMapping(value= "/{movieId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDTO> getSelectedMovie(@PathVariable String movieId)throws ChangeSetPersister.NotFoundException  {
        System.out.println("Selected Movie: " + movieId);
        movieService.getselectecmovie(movieId);
        return new  ResponseEntity<>(movieService.getselectecmovie(movieId), HttpStatus.OK);
    }
    ///------------get all movies------------///
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MovieDTO>> getMovies() {
        movieService.getallmovies();
        return new ResponseEntity<>(movieService.getallmovies(), HttpStatus.OK);
    }
    ///------------update movie by id------------///
    @PatchMapping(value = "{movieId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateMovie(@PathVariable String movieId,@RequestBody MovieDTO movieDTO) {
        movieService.updatemovie(movieId,movieDTO);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    ///------------delete movie by id------------///
    @DeleteMapping(value = "{movieId}")
    public ResponseEntity<Void> deleteMovie(@PathVariable String movieId) {
        movieService.deletemovie(movieId);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
