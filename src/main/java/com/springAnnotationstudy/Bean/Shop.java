package com.springAnnotationstudy.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 *  商家
 */

@Data
@ToString
public class Shop {

    private  Integer ShopId;

    private  String ShopName;

}
