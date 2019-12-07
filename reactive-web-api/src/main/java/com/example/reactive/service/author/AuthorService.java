package com.example.reactive.service.author;

import com.example.reactive.webdto.AddAuthorWebRequest;
import io.reactivex.Single;

public interface AuthorService {

    Single<String> addAuthor(AddAuthorWebRequest addAuthorRequest);
}
