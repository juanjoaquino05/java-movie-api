package com.applaudostudios.interview.controller.response;

import com.applaudostudios.interview.model.Movie;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaginatedMovieResponse {
    private List<Movie> content;
    private Integer size;
    private Long numberOfElements;
    private Long totalElements;
    private Integer totalPages;
    private Integer number;

}
