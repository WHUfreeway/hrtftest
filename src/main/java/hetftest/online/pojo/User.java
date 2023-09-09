package hetftest.online.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId("userName")
    private String userName;
    private String passWord;
    private String bestHrtfs;
    private int bestHrtf;
    private String token;
}
