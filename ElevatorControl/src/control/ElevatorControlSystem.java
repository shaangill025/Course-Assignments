package control;

import java.util.LinkedList;

import Factory.ElevatorControlSystemFactory;
import enums.ElevatorDirection;
import enums.ElevatorStatus;

public class ElevatorControlSystem implements ElevatorControlSystemFactory {
	  final int NUM_OF_FLOORS = 3;
	  LinkedList<Integer> pickupLocations;
	  Elevator elevator;
	  
	  public ElevatorControlSystem(){
		  pickupLocations = new LinkedList<Integer>();
		  elevator = new Elevator(1);
	  }

	@Override
	  public void pickUp(int pickUpFloor) {
	    pickupLocations.add(pickUpFloor);
	  }

	  @Override
	  public void destination(int destinationFloor) {
	    elevator.addNewDestinatoin(destinationFloor);
	  }

	  
	  @Override
	  public void step() {
		  if(elevator.status() == ElevatorStatus.ELEVATOR_EMPTY){
			  if (!pickupLocations.isEmpty())
		            elevator.addNewDestinatoin(pickupLocations.poll());
		  }
		  else{
			  if(elevator.direction()== ElevatorDirection.ELEVATOR_UP){
				  elevator.moveUp();
			  }
			  else if(elevator.direction()== ElevatorDirection.ELEVATOR_DOWN){
				  elevator.moveDown();
			  }
			  else{
				  elevator.popDestination();
			  }           
	      }
	  }
	}