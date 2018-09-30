package com.springAnnotationstudy.Condition;

import com.springAnnotationstudy.Bean.Buyer;
import com.springAnnotationstudy.Bean.Seller;
import com.springAnnotationstudy.Bean.Shop;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ImportCondition  implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {

        StandardAnnotationMetadata annotationMetadata1=(StandardAnnotationMetadata)annotationMetadata;
        MultiValueMap<String,Object> allAnnotationAttributes = annotationMetadata1.getAllAnnotationAttributes(Import.class.getName());
       return new String[]{Buyer.class.getName(),Seller.class.getName()};
    }
}
