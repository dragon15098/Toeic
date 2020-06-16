package com.example.toeic.controller;

import com.example.toeic.service.FileService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;

@RestController
@RequestMapping("/api/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @GetMapping("/**")
    public ResponseEntity<Object> get(HttpServletRequest request) throws IOException {
        String relativeUrl = this.extractRelativeUrl(request);
        Resource resource = this.fileService.loadFileAsResource(relativeUrl);
        HttpHeaders httpHeaders = this.fileService.loadHttpHeaders(resource);
        return new ResponseEntity<>(resource, httpHeaders, HttpStatus.OK);
    }


    private String extractRelativeUrl(HttpServletRequest request) {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE); // /files/relativeUrl
        String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE); // /files/**
        return new AntPathMatcher().extractPathWithinPattern(bestMatchPattern, path); // relativeUrl
    }
}
