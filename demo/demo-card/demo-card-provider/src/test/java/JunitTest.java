import com.bjike.goddess.card.config.AppRoot;
import com.bjike.goddess.card.dto.CardDTO;
import com.bjike.goddess.card.entity.Card;
import com.bjike.goddess.card.service.CardAPI;
import com.bjike.goddess.common.api.dto.Condition;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.RestrictionType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppRoot.class)
public class JunitTest {


    @Autowired
    private CardAPI cardAPI;

    @Before
    public void initCard(){//自动初始新帐户
        CardDTO cardDTO = new CardDTO();
        cardDTO.getConditions().add(new Condition("account","123", RestrictionType.EQ));
        try {
            Card card = cardAPI.findOne(cardDTO);
            if(null==card){
                cardAPI.save(new Card("123","123",1000L));
                System.out.println("自动初始化帐户:123,密码:123,余额:1000");
            }
        } catch (SerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBuyTicket(){//对帐户进行金额增加操作，没有帐户会异常，并进行回滚
        try {
            cardAPI.buyTicket(null,"123","123");
        } catch (SerException e) {
            e.printStackTrace();
        }
    }

}
