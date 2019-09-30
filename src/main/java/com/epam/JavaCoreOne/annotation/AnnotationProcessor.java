package com.epam.JavaCoreOne.annotation;

import com.epam.JavaCoreOne.PublicTransportPark;
import com.epam.JavaCoreOne.transport.repository.TransportRepository;
import com.epam.JavaCoreOne.transport.service.TransportService;

public class AnnotationProcessor {

    public static void main(String[] args) {
        CodeSmellsHandler codeSmellsHandler = new CodeSmellsHandler();
        ProdHandler prodHandler = new ProdHandler();

        codeSmellsHandler.inspectorAnnotation(TransportRepository.class);
        codeSmellsHandler.inspectorAnnotation(TransportService.class);

        prodHandler.runProdCode(ProdHandler.class);
        prodHandler.runProdCode(PublicTransportPark.class);
    }
}
