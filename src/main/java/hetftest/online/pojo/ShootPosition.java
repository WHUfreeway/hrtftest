package hetftest.online.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShootPosition {
    private String userName;
    private String shootingMode;
    private double ActualPositionX;
    private double ActualPositionY;
    private double UserPositionX;
    private double UserPositionY;

}
