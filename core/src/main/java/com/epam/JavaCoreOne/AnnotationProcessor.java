package com.epam.JavaCoreOne;

import com.epam.JavaCoreOne.annotation.handlers.CodeSmellsHandler;
import com.epam.JavaCoreOne.annotation.handlers.ProdHandler;
import com.epam.JavaCoreOne.transport.repository.TransportRepository;
import com.epam.JavaCoreOne.transport.service.TransportService;

/**
 * Annotations processing
 *
 * @author Roman Gukolov
 */
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
