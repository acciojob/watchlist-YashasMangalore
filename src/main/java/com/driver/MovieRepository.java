package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {

    private HashMap<String, Movie> movieMap;
    private HashMap<String, Director> directorMap;
    private HashMap<String, List<String>> directorMovieMapping;

    public MovieRepository(){
        this.movieMap = new HashMap<String, Movie>();
        this.directorMap = new HashMap<String, Director>();
        this.directorMovieMapping = new HashMap<String, List<String>>();
    }

    public void saveMovie(Movie movie)
    {
        String movieName=movie.getName();
        if(!movieMap.containsKey(movieName))
        {
            movieMap.put(movieName,movie);
        }
        // your code here
    }

    public void saveDirector(Director director){
        // your code here
        String directorName = director.getName();
        if(!directorMap.containsKey(directorName))
        {
            directorMap.put(directorName,director);
        }
    }

    public void saveMovieDirectorPair(String movie, String director)
    {
        if(movieMap.containsKey(movie) && directorMap.containsKey(director))
        {
            if(!directorMovieMapping.containsKey(director))
            {
                List<String> list = new ArrayList<>();
                list.add(movie);
                directorMovieMapping.put(director,list);
            }
            else
            {
                List<String> list = directorMovieMapping.get(director);
                list.add(movie);
                directorMovieMapping.put(director,list);
            }
            // your code here
        }
    }

    public Movie findMovie(String movie){
        // your code here
        if(movieMap.containsKey(movie)){
            return movieMap.get(movie);
        }
        return null;
    }

    public Director findDirector(String director){
        // your code here
        if(directorMap.containsKey(director)){
            return directorMap.get(director);
        }
        return null;
    }

    public List<String> findMoviesFromDirector(String director){
        // your code here
        if(directorMovieMapping.containsKey(director))
        {
            List<String> list = directorMovieMapping.get(director);
            return list;
        }
        return null;
    }

    public List<String> findAllMovies()
    {
        return new ArrayList<>(movieMap.keySet());
    }

    public void deleteDirector(String director)
    {
        if (directorMovieMapping.containsKey(director))
        {
            List<String> movies = directorMovieMapping.get(director);
            for (String movieName : movies)
            {
                if (movieMap.containsKey(movieName))
                {
                    movieMap.remove(movieName);
                }
            }
            directorMap.remove(director);
            directorMovieMapping.remove(director);
        }
        else if(directorMap.containsKey(director) )
        {
            directorMap.remove(director);
        }
        // your code here
    }

    public void deleteAllDirector(){
        // your code here
        for(String director : directorMap.keySet())
        {
            if (directorMovieMapping.containsKey(director))
            {
                List<String> movies = directorMovieMapping.get(director);
                for(String movieName : movies)
                {
                    if(movieMap.containsKey(movieName)){
                        movieMap.remove(movieName);
                    }
                }
                directorMap.remove(director);
                directorMovieMapping.remove(director);
            }
        }
    }
}