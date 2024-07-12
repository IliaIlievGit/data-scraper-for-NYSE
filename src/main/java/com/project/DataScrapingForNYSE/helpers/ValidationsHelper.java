package com.project.DataScrapingForNYSE.helpers;

import com.project.DataScrapingForNYSE.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidationsHelper {

    public static <T> void checkIfEntityIsPresent(Optional<T> potentialEntity, String entity, String attribute, String value) {
        if (potentialEntity.isEmpty()) {
            throw new EntityNotFoundException(entity, attribute, value);
        }
    }
}
