package com.lambda.buildweek.wunderlist.services;

import com.lambda.buildweek.wunderlist.models.ValidationError;

import java.util.List;

public interface HelperFunctions
{
    List<ValidationError> getConstraintViolation(Throwable cause);
}
