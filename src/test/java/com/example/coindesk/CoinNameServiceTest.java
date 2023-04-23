package com.example.coindesk;


import com.example.coindesk.back.bean.CoinName;
import com.example.coindesk.back.bean.request.CoinNameReq;
import com.example.coindesk.back.service.ICoinNameService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest // loading full application configuration
@AutoConfigureTestDatabase // loading with embedded database
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CoinNameServiceTest {

    @Autowired
    ICoinNameService coinNameService;

    @Test
    public void findAll_print_withoutException() throws JsonProcessingException {
        //assert
        Assertions.assertDoesNotThrow(() -> {
            coinNameService.findAll();
            System.out.println(coinNameService.findAll());
        });
    }

    @Test
    public void insert_uniqueName_should_success() throws JsonProcessingException {
        //prepare
        CoinNameReq req = getTestInsertReq();
        coinNameService.insert(req);

        // result
        CoinName coinName=coinNameService.findByEn(req.getEnName());

        // assert
        Assertions.assertNotNull(coinName);
    }

    @Test
    public void insert_duplicateEnName_should_throw_DataIntegrityViolationException() throws JsonProcessingException {
        // prepare
        CoinName insertRes=coinNameService.insert(getTestInsertReq());
        // assert: unique key duplicate error
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> coinNameService.insert(getTestInsertReq()));
    }


    @Test
    public void update_existingData_should_success() throws JsonProcessingException {
        //prepare
        String updateChName="updateName";

        CoinNameReq req=getTestInsertReq();
        CoinName insertRes=coinNameService.insert(req);

        // refill req with pk from res
        req.setId(insertRes.getId());
        req.setChName(updateChName);

        //update
        CoinName updateRes = coinNameService.update(req);

        //assert
        Assertions.assertEquals(updateRes.getChName(),updateChName);
    }

    @Test
    public void delete_existing_should_success_and_change_count_equal_1() throws JsonProcessingException {
        // prepare
        CoinName insertRes=coinNameService.insert(getTestInsertReq());

        int count = coinNameService.deleteById(insertRes.getId());
        //assert
        Assertions.assertEquals(count,1);
    }

    @Test
    public void delete_non_existing_should_success_and_change_count_equal_0() throws JsonProcessingException {
        // prepare
        CoinName insertRes=coinNameService.insert(getTestInsertReq());

        //delete existingdata
        coinNameService.deleteById(insertRes.getId());

        int count = coinNameService.deleteById(insertRes.getId());
        //assert
        Assertions.assertEquals(count,0);
    }

    final String uniqueName="unique";

    private CoinNameReq getTestInsertReq(){
        CoinNameReq req = new CoinNameReq(uniqueName, uniqueName);
        return req;
    }



}
