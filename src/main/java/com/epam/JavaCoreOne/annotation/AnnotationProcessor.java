package com.epam.JavaCoreOne.annotation;

import com.epam.JavaCoreOne.transport.repository.TransportRepository;
import com.epam.JavaCoreOne.transport.service.TransportService;

public class AnnotationProcessor {

    public static void main(String[] args) {
        AnnotationUtil inspectorTransportRepository = new AnnotationUtil(TransportRepository.class);
        AnnotationUtil inspectorTransportService = new AnnotationUtil(TransportService.class);
        inspectorTransportRepository.inspectorAnnotation();
        inspectorTransportService.inspectorAnnotation();
    }
}
