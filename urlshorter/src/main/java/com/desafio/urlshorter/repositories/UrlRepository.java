package com.desafio.urlshorter.repositories;

import com.desafio.urlshorter.models.UrlModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlRepository extends JpaRepository<UrlModel, Integer> {
    UrlModel findByLink(String link);
    List<UrlModel> findByDayNotLike(String day);
    UrlModel findByUrlshort(String urlshort);
}
