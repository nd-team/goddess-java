import com.bjike.goddess.card.config.AppRoot;
import com.bjike.goddess.card.service.CardAPI;
import com.bjike.goddess.dbs.common.exception.SerException;
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

    @Test
    public void testBuyTicket(){//对帐户进行金额增加操作，没有帐户会异常，并进行回滚
        try {
            cardAPI.buyTicketForCard(null,"account123","abc123","D306-2-2D");
        } catch (SerException e) {
            e.printStackTrace();
        }
    }

}
