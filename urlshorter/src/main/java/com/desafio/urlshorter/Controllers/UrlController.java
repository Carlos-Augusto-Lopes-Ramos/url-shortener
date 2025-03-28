package com.desafio.urlshorter.Controllers;

import com.desafio.urlshorter.dtos.UrlRecordDto;
import com.desafio.urlshorter.models.UrlModel;
import com.desafio.urlshorter.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@EnableScheduling
@CrossOrigin(origins = "*")
@RestController
public class UrlController {

    @Autowired
    UrlRepository urlRepository;

    @GetMapping("/")
    public String index() {
        return "Hello World!";
    }

    @PostMapping("/shorten-url")
    @ResponseBody
    public ResponseEntity<Object> createShortUrl(@RequestBody UrlRecordDto urlData) {
        UrlModel verificationUrl = urlRepository.findByLink(urlData.link());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dayFormated = sdf.format(new Date());
        if(verificationUrl != null) {
            return ResponseEntity.status(HttpStatus.OK).body("https://hazway.com.br/programs/encurta/" + verificationUrl.getUrlshort());
        }
        UrlModel url = new UrlModel();
        url.setLink(urlData.link());
        String shortUrl = urlShortBuilder();
        while(urlRepository.findByUrlshort(shortUrl) != null) {
            shortUrl = urlShortBuilder();
        }
        url.setUrlshort(shortUrl);
        url.setDay(dayFormated);
        url = urlRepository.save(url);
        return ResponseEntity.status(HttpStatus.OK).body("https://hazway.com.br/programs/encurta/" + url.getUrlshort());
    }

    @GetMapping("/shorten-url")
    @ResponseBody
    public ResponseEntity<Object> getLongUrl(@RequestBody UrlRecordDto urlData) {
        UrlModel url = urlRepository.findByUrlshort(urlData.link());
        if(url == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(url.getLink());
    }

    public String urlShortBuilder() {
        StringBuilder finalShortUrl = new StringBuilder();
        char[] characters = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f',
                'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A',
                'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
                'W', 'X', 'Y', 'Z'};
        Random aleatorio = new Random();
        int size = aleatorio.nextInt((10 - 5) + 1) + 5;
        for (int i = 0; i <= size; i++) {
            finalShortUrl.append(characters[aleatorio.nextInt(characters.length)]);
        }
        return finalShortUrl.toString();
    }

    @Scheduled(fixedDelay = 86400000)
    public void kill() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dayFormated = sdf.format(new Date());
            List<UrlModel> urlList = urlRepository.findByDayNotLike(dayFormated);
            for(UrlModel url : urlList) {
                urlRepository.delete(url);
            }
        } catch( Exception e) {

        }
    }
}
