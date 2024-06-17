package com.eazybytes.eazyschool.config;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jdk.dynalink.linker.LinkerServices;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

@Component("eazySchoolProps")
@Data
@ConfigurationProperties(prefix = "eazyschool")
@Validated
public class EazySchoolProps {
    @Min(value = 3, message = "must be between 5 and 25")
    @Max(value = 25, message = "must be between 5 and 25")
    private int pageSize;

    private Map<String,String> contact;

    private List<String> branches;

}
