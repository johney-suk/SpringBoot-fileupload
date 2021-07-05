package org.zerock.j08.dto;

import lombok.*;
import org.zerock.j08.entity.FoodStoreImage;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FoodStoreDTO {

    private Long fno;

    private String fname;

    private List<FoodStoreImage> imageList;
}
