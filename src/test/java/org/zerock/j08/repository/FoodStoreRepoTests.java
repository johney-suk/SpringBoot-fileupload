package org.zerock.j08.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;

@SpringBootTest
public class FoodStoreRepoTests {

    @Autowired
    private FoodStoreRepository storeRepository;

    @Test
    public void testList() {

        Pageable pageable =
                PageRequest.of(0,10, Sort.by("fno").descending());

        Page<Object[]> result = storeRepository.getList(pageable);

        result.getContent().forEach(arr -> System.out.println(Arrays.toString(arr)));

    }

}
