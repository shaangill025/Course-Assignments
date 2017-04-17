package Factory;

import enums.ElevatorDirection;
import enums.ElevatorStatus;

public interface ElevatorFactory {
	  public void moveUp();
	  public void moveDown();
	  public void addNewDestinatoin(int destination);
	  public ElevatorDirection direction();
	  public ElevatorStatus status();
}