package com.example.toeic.service;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;

import java.io.IOException;

public interface FileService {
    Resource loadFileAsResource(String pathName);
    HttpHeaders loadHttpHeaders(Resource resource) throws IOException;
}
