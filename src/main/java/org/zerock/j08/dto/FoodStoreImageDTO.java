package org.zerock.j08.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FoodStoreImageDTO {

    private String uuid;

    private String fileName;

    private boolean main;
}
