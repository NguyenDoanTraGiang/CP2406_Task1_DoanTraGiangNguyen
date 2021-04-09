# Java Based Traffic Simulator
## Program Working Document

### Specification
The client needs a program that can simulate traffic in city areas; needing to simulate different vechicle types with traffic light, roads and intersections. The user can enter input into the program to customized the city, and the program will display the objects and their status to the user. This version of the program will not display the UI, but the user can enter input for the customization of their city. The objects that are available for the users are cars, traffic lights and single lane roads. the user can choose how many cars, traffic lights and roads will the city has. This version of the simulator will simulate cars moving along single lane roads connected to each other, and move to the end of that road.

### Decomposition
The problem can be broken into operate objects that interact with each other to achieve desired behaviour.
These objects include;
#### Car
Car class with represent a medium size vehicle. The class has the following attributes;
- *id* - an unique indentifier that will differentiate each car.
- *length* - the length of the car, the space the car occupies longways.
- *breadth* - the width of the car, the space the car occupies widthways, half the car's length. 
- *speed* - how far the car moves for each simulation turn.
- *position* - where the car will be place on the road. 
- *currentRoad* - the road the car is currently on.

The car will move using the move() method along the road in the simulation. The speed of the car will be determine by the speed limit of the road the car is travelling on. When the car is in the same position as the traffic light, it will check the status of the traffic light. If the traffic light is green, then the car will keep moving on the nex road. If the traffic light is red, then the car will stop moving. When the car moves to the end of a road and there is no connected road it will stop the simulator.

#### Bus
Bus class will inherit its attributes and behaviour from Car, represent the large sized vehicle. However, Bus's length will be three time the size of Car's length.

#### Motorbike
Motorbike class will inherit its attributes and behaviour from Car class, represent a small sized vehicle. However, the length of the Motorbike class will be half the length of the Car's length.

#### Road
Road class will represent a single lane road object. It has the following attributes;
- *id* - a unique identifier that will differenciate each road.
- *speedLimit* - the maximum speed that cars on the road is allowed to move each turn.
- *length* - the number of segments that the road occupies.
- *startLocation* - the (x,y) coordinate that represents where the road begins.
- *endLocation* - the (x,y) coordinate that represents where the road ends.
- *connectedRoads* - all the other roads that this current road is physically connected to.
- *lightsOnRoad* - all traffic lights that are on the roadd
- *carsOnRoad* - all of the cars currently travelling on this road.

For this version of the program the speedLimit will be set to 1. Meaning the car will only be able to move 1 position each turn, make it easier to deal with traffic lights and end of the road. The length of the road will be decided by user's input. Roads will interact with other roads by being connected to them, creating a simple straight intersection with single lane. Cars will move from the starting position to the end of the road. Traffic light can be places only at the end of the road. 


