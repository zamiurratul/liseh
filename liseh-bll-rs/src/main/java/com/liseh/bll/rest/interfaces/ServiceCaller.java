package com.liseh.bll.rest.interfaces;

import com.liseh.bll.model.common.GenericResponse;

@FunctionalInterface
public interface ServiceCaller {
    GenericResponse callService();
}
