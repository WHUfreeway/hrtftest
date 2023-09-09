package hetftest.online.pojo;

import lombok.Data;

@Data
public class UserScore {
    private String userName;
    private String shootMode;
    private String time;
    private int hrtfIndex;
    private float accuracy;
    private float checkAcc;
}
