package com.rest.restfulwebservices.restfulwebservices.filter;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {


    //field1 and field 2 are only sent
    @GetMapping("/filtering")
    public MappingJacksonValue retrieveSomeBean(){
       SomeBean someBean=new SomeBean("value1","value2","value3");

        SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");

        FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
        MappingJacksonValue mapping=new MappingJacksonValue(someBean);
        mapping.setFilters(filters);

        return mapping;
    }

    //field2 and field3 are only sent
    @GetMapping("/filtering-list")
    public MappingJacksonValue retrieveListofSomeBean(){
        List<SomeBean> list=Arrays.asList(new SomeBean("value1","value2","value3"),
                new SomeBean("valueK1","valueK2","valueK3"));
        SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");

        FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
        MappingJacksonValue mapping=new MappingJacksonValue(list);
        mapping.setFilters(filters);

        return mapping;
    }
}
