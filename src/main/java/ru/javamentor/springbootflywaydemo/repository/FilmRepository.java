package ru.javamentor.springbootflywaydemo.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.javamentor.springbootflywaydemo.model.Film;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long>, JpaSpecificationExecutor<Film> {
    boolean existsByFilmId(Integer filmId);

@Query(value = "SELECT * FROM film WHERE (:ratingFrom IS NULL OR rating >= :ratingFrom) " +
        "AND (:ratingTo IS NULL OR rating <= :ratingTo) " +
        "AND (:yearFrom IS NULL OR year >= :yearFrom) " +
        "AND (:yearTo IS NULL OR year <= :yearTo) " +
        "AND (:keyword IS NULL OR LOWER(film_name) LIKE CONCAT('%', LOWER(:keyword), '%')) " +
        "AND (:genres IS NULL OR genres LIKE CONCAT('%', :genres, '%'))",
        countQuery = "SELECT count(*) FROM film WHERE (:ratingFrom IS NULL OR rating >= :ratingFrom) " +
                "AND (:ratingTo IS NULL OR rating <= :ratingTo) " +
                "AND (:yearFrom IS NULL OR year >= :yearFrom) " +
                "AND (:yearTo IS NULL OR year <= :yearTo) " +
                "AND (:keyword IS NULL OR LOWER(film_name) LIKE CONCAT('%', LOWER(:keyword), '%')) " +
                "AND (:genres IS NULL OR genres LIKE CONCAT('%', :genres, '%'))",
        nativeQuery = true)


    List<Film> findByParameters(@Param("ratingFrom") Integer ratingFrom,
                                @Param("ratingTo") Integer ratingTo,
                                @Param("yearFrom") Integer yearFrom,
                                @Param("yearTo") Integer yearTo,
                                @Param("keyword") String keyword,
                                @Param("genres") List<String> genres,
                                Pageable pageable);
}
